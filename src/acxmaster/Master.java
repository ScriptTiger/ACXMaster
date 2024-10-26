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

	// Resets
	public void nextBatch() {chain = "";}

	// Files
	public void setFiles(File[] files) {this.files = files;}
	public void setIsSingle(Boolean isSingle) {this.isSingle = isSingle;}
	public void setSaveFile(File saveFile) {this.saveFile = saveFile;}

	////////////////////
	// Utility functions
	////////////////////

	// Function to wrap FFmpeg for analysis
	private void ffmpeg(Boolean post, String[] ffmpeg) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(ffmpeg);
			InputStream stderr = process.getErrorStream();
			InputStreamReader stderrReader = new InputStreamReader(stderr);
			BufferedReader stderrBufferedReader = new BufferedReader(stderrReader);
			String line = null;
			int astatsCount = 0;
			while ((line = stderrBufferedReader.readLine()) != null) {
				if (line.startsWith("[Parsed_astats_")) {
					String[] stat = line.split("] ")[1].split(": ");
					switch (stat[0]) {
						case "Overall":
							astatsCount++;
							break;
						case "Peak level dB":
							if (post) {audioInfo.setOTP(Double.parseDouble(stat[1]));}
							break;
						case "RMS level dB":
							if (post) {
								if (astatsCount == 1) {audioInfo.setRMS(Double.parseDouble(stat[1]));
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
							if (audioInfo.getSampleCount() == 0) {
								audioInfo.setSampleCount(Integer.parseInt(stat[1]));
								audioInfo.setDuration((double)audioInfo.getSampleCount()/(double)audioInfo.getSampleRate());
							}
							break;
					}
				}
				if (post) {if (line.startsWith("Input Integrated:")) {audioInfo.setOI(Float.parseFloat(line.split("\\s+")[2]));}
				} else {
					if (audioInfo.getSampleRate() == 0&&line.matches("^\\s+Stream\\s#\\d.*")) {
						audioInfo.setSampleRate(Integer.parseInt(line.split(",")[1].replace("Hz", "").trim()));
					}
					if (line.startsWith("Input True Peak:")) {audioInfo.setITP(Float.parseFloat(line.split("\\s+")[3]));}
					if (line.startsWith("Input Integrated:")) {audioInfo.setII(Float.parseFloat(line.split("\\s+")[2]));}
					if (line.startsWith("Input LRA:")) {audioInfo.setILRA(Float.parseFloat(line.split("\\s+")[2]));}
					if (line.startsWith("Input Threshold:")) {audioInfo.setIT(Float.parseFloat(line.split("\\s+")[2]));}
					if (line.startsWith("Target Offset:")) {audioInfo.setTO(Float.parseFloat(line.split("\\s+")[2]));}
				}
			}
			process.waitFor();
		} catch (Exception exception) {}
	}

	//////////////////////////
	// Primary class functions
	//////////////////////////

	// Function to check ffmpeg
	public Boolean check() {
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
		Boolean post = false;
		if (file == null) {post = true;}
		String tmpChain = "";
		String layout = "mono";
		String fileString;
		if (post) {
			fileString = audioInfo.getSaveFileString();
			if (options.getStereo()) {layout = "stereo";}
		} else {
			audioInfo = new AudioInfo(file, saveFile, isSingle);
			fileString = file.getPath();
			tmpChain = getChain();
		}
		String[] stats = {"ffmpeg", "-hide_banner", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl="+layout+","+tmpChain+"asplit[loudnorm],astats=measure_perchannel=none;[loudnorm]loudnorm=print_format=summary", "-f", "null", ""};
		ffmpeg(post, stats);
		if (!post) {
			String splitAndMerge = "";
			if (options.getStereo()) {splitAndMerge = ",asplit,amerge";}
			int endSample = (int)(audioInfo.getDuration()*(double)192000)-192000;
			String[] predict = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+tmpChain+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(audioInfo.getII())+":measured_LRA="+String.valueOf(audioInfo.getILRA())+":measured_tp="+String.valueOf(audioInfo.getITP())+":measured_thresh="+String.valueOf(audioInfo.getIT())+":offset="+String.valueOf(audioInfo.getTO())+splitAndMerge+",asplit=4[endSample][astats][loudnorm],atrim=end_sample=192000[startSample];[endSample]atrim=start_sample="+String.valueOf(endSample)+",[startSample]concat=2:0:1,astats=measure_perchannel=none:measure_overall=RMS_level;[astats]astats=measure_perchannel=none;[loudnorm]loudnorm=print_format=summary", "-t", String.valueOf((float)audioInfo.getDuration()), "-f", "null", ""};
			ffmpeg(true, predict);
		}
	}

	// Function to master files
	public void master(File file) {
		String fileString = file.getPath();
		String splitAndMerge = "";
		if (options.getStereo()) {splitAndMerge = ",asplit,amerge";}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(audioInfo.getII())+":measured_LRA="+String.valueOf(audioInfo.getILRA())+":measured_tp="+String.valueOf(audioInfo.getITP())+":measured_thresh="+String.valueOf(audioInfo.getIT())+":offset="+String.valueOf(audioInfo.getTO())+splitAndMerge, "-t", String.valueOf((float)audioInfo.getDuration()), "-ar", "44.1k", "-ab", "192k", "-f", "mp3", audioInfo.getSaveFileString()};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {}
	}
}