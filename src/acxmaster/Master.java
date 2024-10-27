package acxmaster;

// I/O and buffered I/O deps
import java.io.*;

// Master class
class Master {

	//////////////////
	// Class variables
	//////////////////

	// Singletons
	private final GraphicEQ graphicEQ = new GraphicEQ();
	private final Targets targets = new Targets();
	private final Options options = new Options();

	// Files
	private static File[] files;
	private static Boolean isSingle;
	private static File saveFile;

	// Analysis stats
	private static AudioInfo audioInfo;

	// Additional filters
	private static String chain = "";

	//////////
	// Getters
	//////////

	// Singletons
	public GraphicEQ getGraphicEQ() {return graphicEQ;}
	public Targets getTargets() {return targets;}
	public Options getOptions() {return options;}

	// Files
	public File[] getFiles() {return files;}
	public int getFileCount() {return files.length;}
	public Boolean isSingle() {return isSingle;}

	// Analysis stats
	public AudioInfo getAudioInfo() {return audioInfo;}

	// Additional filters
	private String getChain() {
		if (chain.isEmpty()) {chain = options.getChain()+graphicEQ.getChain()+options.getNoiseChain();}
		return chain;
	}

	//////////
	// Setters
	//////////

	// Files
	public void setFiles(File[] files) {
			this.files = files;
			chain = "";
	}
	public void setIsSingle(Boolean isSingle) {this.isSingle = isSingle;}
	public void setSaveFile(File saveFile) {this.saveFile = saveFile;}

	////////////////////
	// Utility functions
	////////////////////

	// Function to wrap FFmpeg
	private void ffmpeg(Boolean post, String[] ffmpeg) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(ffmpeg);
			InputStream stderr = process.getErrorStream();
			InputStreamReader stderrReader = new InputStreamReader(stderr);
			BufferedReader stderrBufferedReader = new BufferedReader(stderrReader);
			String line = null;
			int astatsCount = 0;
			Boolean getSampleRate = true;
			while ((line = stderrBufferedReader.readLine()) != null) {
				if (line.startsWith("[Parsed_astats_")) {
					String[] stat = line.split("] ")[1].split(": ");
					switch (stat[0]) {
						case "Overall":
							astatsCount++;
							break;
						case "Peak level dB":
							if (post) {
								try {audioInfo.setOTP(Double.parseDouble(stat[1]));} catch (Exception exception) {}
							}
							break;
						case "RMS level dB":
							if (post) {
								if (astatsCount == 1) {
									try {audioInfo.setRMS(Double.parseDouble(stat[1]));} catch (Exception exception) {}
								} else {
									audioInfo.setSampleFloor(0);
									audioInfo.setSampleFloorString(stat[1]);
									try {audioInfo.setSampleFloor(Double.parseDouble(stat[1]));} catch (Exception exception) {}
								}
							}
							break;
						case "Noise floor dB":
							if (post) {
								audioInfo.setOverallFloor(0);
								audioInfo.setOverallFloorString(stat[1]);
								try {audioInfo.setOverallFloor(Double.parseDouble(stat[1]));} catch (Exception exception) {}
							}
							break;
						case "Number of samples":
							if (!post) {
								try {audioInfo.setSampleCount(Integer.parseInt(stat[1]));} catch (Exception exception) {}
								try {audioInfo.setDuration((double)audioInfo.getSampleCount()/(double)audioInfo.getSampleRate());} catch (Exception exception) {}
							}
							break;
					}
				}
				if (post) {
					if (line.startsWith("Output Integrated:")) {
						try {audioInfo.setOI(Float.parseFloat(line.split("\\s+")[2]));} catch (Exception exception) {}
					}
					if (line.startsWith("Output True Peak:")) {
						try {audioInfo.setROTP(Float.parseFloat(line.split("\\s+")[3]));} catch (Exception exception) {}
					}
				} else {
					if (line.matches("^\\s+Duration:\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{2}.*")) {
						audioInfo.setRoughDurationString(line.split("\\s+")[2].replace(",", ""));
						String[] roughDurationParsed = audioInfo.getRoughDurationString().split(":");
						try {audioInfo.setRoughDuration((Float.parseFloat(roughDurationParsed[0])*3600)+(Float.parseFloat(roughDurationParsed[1])*60)+Float.parseFloat(roughDurationParsed[2]));} catch (Exception exception) {}
					}
					if (getSampleRate && line.matches("^\\s+Stream\\s#\\d.*")) {
						try {audioInfo.setSampleRate(Integer.parseInt(line.split(",")[1].replace("Hz", "").trim()));} catch (Exception exception) {}
						getSampleRate = false;
					}
					if (line.startsWith("Input True Peak:")) {
						try {audioInfo.setITP(Float.parseFloat(line.split("\\s+")[3]));} catch (Exception exception) {}
					}
					if (line.startsWith("Input Integrated:")) {
						try {audioInfo.setII(Float.parseFloat(line.split("\\s+")[2]));} catch (Exception exception) {}
					}
					if (line.startsWith("Input LRA:")) {
						try {audioInfo.setILRA(Float.parseFloat(line.split("\\s+")[2]));} catch (Exception exception) {}
					}
					if (line.startsWith("Input Threshold:")) {
						try {audioInfo.setIT(Float.parseFloat(line.split("\\s+")[2]));} catch (Exception exception) {}
					}
					if (line.startsWith("Target Offset:")) {
						try {audioInfo.setTO(Float.parseFloat(line.split("\\s+")[2]));} catch (Exception exception) {}
					}
				}
			}
			process.waitFor();
		} catch (Exception exception) {}
	}

	//////////////////////////
	// Primary class functions
	//////////////////////////

	// Function to check ffmpeg
	public Boolean ffCheck() {
		Boolean err = true;
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-version"};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {
			new ErrorDialog(exception.getMessage());
			err = false;
		}
		return err;
	}

	// Function to analyze files
	public void analyze(File file) {
		audioInfo = new AudioInfo(file, saveFile, isSingle);
		String[] analyze = {"ffmpeg", "-hide_banner", "-i", file.getPath(), "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"asplit[loudnorm],astats=measure_perchannel=none:measure_overall=Noise_floor+Number_of_samples+Peak_level+RMS_level;[loudnorm]loudnorm=print_format=summary", "-f", "null", ""};
		ffmpeg(false, analyze);
	}

	// Function check files
	public void check(File file) {
		audioInfo = new AudioInfo(file);
		String[] preCheck = {"ffmpeg", "-hide_banner", "-i", file.getPath(), "-vn", "-sn", "-dn", "-frames:a", "0", "-f", "null", ""};
		ffmpeg(false, preCheck);
		String end = String.valueOf(audioInfo.getRoughDuration()-1);
		String[] check = {"ffmpeg", "-hide_banner", "-i", file.getPath(), "-vn", "-sn", "-dn", "-filter_complex", "asplit=3[end][astats],atrim=end=1[start];[end]atrim=start="+end+",[start]concat=2:0:1,astats=measure_perchannel=none:measure_overall=RMS_level;[astats]astats=measure_perchannel=none:measure_overall=Noise_floor+Number_of_samples+Peak_level+RMS_level", "-f", "null", ""};
		ffmpeg(true, check);
	}

	// Function to predict problems
	public void predict(File file) {
		String end = String.valueOf(audioInfo.getRoughDuration()-1);
		String[] predict = {"ffmpeg", "-hide_banner", "-i", file.getPath(), "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(audioInfo.getII())+":measured_LRA="+String.valueOf(audioInfo.getILRA())+":measured_tp="+String.valueOf(audioInfo.getITP())+":measured_thresh="+String.valueOf(audioInfo.getIT())+":offset="+String.valueOf(audioInfo.getTO())+":print_format=summary"+options.getStereoChain()+",asplit=3[end][astats],atrim=end=1[start];[end]atrim=start="+end+",[start]concat=2:0:1,astats=measure_perchannel=none:measure_overall=RMS_level;[astats]astats=measure_perchannel=none:measure_overall=Noise_floor+Number_of_samples+Peak_level+RMS_level", "-f", "null", ""};
		ffmpeg(true, predict);
	}

	// Function to master files
	public void master(File file) {
		String end = String.valueOf(audioInfo.getRoughDuration()-1);
		String[] master = {"ffmpeg", "-hide_banner", "-y", "-i", file.getPath(), "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(audioInfo.getII())+":measured_LRA="+String.valueOf(audioInfo.getILRA())+":measured_tp="+String.valueOf(audioInfo.getITP())+":measured_thresh="+String.valueOf(audioInfo.getIT())+":offset="+String.valueOf(audioInfo.getTO())+":print_format=summary"+options.getStereoChain()+",asplit=4[end][astats][master],atrim=end=1[start];[end]atrim=start="+end+",[start]concat=2:0:1,astats=measure_perchannel=none:measure_overall=RMS_level[null0];[astats]astats=measure_perchannel=none:measure_overall=Noise_floor+Number_of_samples+Peak_level+RMS_level[null1]", "-map", "[master]", "-ar", "44.1k", "-ab", "192k", "-f", "mp3", audioInfo.getSaveFileString(), "-map", "[null0]", "-map", "[null1]", "-f", "null", ""};
		ffmpeg(true, master);
	}
}