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
	private static String saveFileString;

	// Analysis stats
	private static int sampleRate;
	private static float ii;
	private static float itp;
	private static Float ilra;
	private static float it;
	private static float to;
	private static double otp;
	private static float oi;
	private static double rms;
	private static String overallFloorString;
	private static double overallFloor;
	private static int sampleCount;
	private static double duration;
	private static String sampleFloorString;
	private static float sampleFloor;

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
	public float getII() {return ii;}
	public float getITP() {return itp;}
	public float getOI() {return oi;}
	public double getOTP() {return otp;}
	public double getRMS() {return rms;}
	public String getOverallFloorString() {return overallFloorString;}
	public double getOverallFloor() {return overallFloor;}
	public double getDuration() {return duration;}
	public String getSampleFloorString() {return sampleFloorString;}
	public float getSampleFloor() {return sampleFloor;}

	// Additional filters
	public String getChain() {
		if (chain.isEmpty()) {chain = options.getChain()+graphicEQ.getChain()+options.getNoiseChain();}
		return chain;
	}

	//////////
	// Setters
	//////////

	// Resets
	public void nextBatch() {chain = "";}
	public void nextFile() {
		overallFloor = 0;
		overallFloorString = null;
		sampleFloor = 0;
		sampleFloorString = null;
	}

	// Files
	public void setFiles(File[] files) {this.files = files;}
	public void setIsSingle(Boolean isSingle) {this.isSingle = isSingle;}
	public void setSaveFile(File saveFile) {this.saveFile = saveFile;}
	private void setSaveFileString(File file) {
		if (isSingle) {
			saveFileString = saveFile.getPath();
			return;
		}
		File tmpSaveFile;
		String base;
		String[] elements = file.getName().split("\\.");
		int count = elements.length;
		if (count > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < count-1; i++) {
				stringBuilder.append(elements[i]);
				if (i+1 < count-1) {stringBuilder.append(".");}
			}
			base = stringBuilder.toString();
		} else {base = file.getName();}
		tmpSaveFile = saveFile.toPath().resolve(base+".mp3").toFile();
		for (int i = 2;; i++) {
			if (!tmpSaveFile.exists()) {break;}
			tmpSaveFile = saveFile.toPath().resolve(base+" ("+String.valueOf(i)+").mp3").toFile();
		}
		saveFileString = tmpSaveFile.getPath();
	}

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
			while ((line = stderrBufferedReader.readLine()) != null) {
				if (line.startsWith("[Parsed_astats_")) {
					String[] stat = line.split("] ")[1].split(": ");
					switch (stat[0]) {
						case "Peak level dB":
							if (!post) {break;}
							otp = Double.parseDouble(stat[1]);
							break;
						case "RMS level dB":
							if (!post) {break;}
							rms = Double.parseDouble(stat[1]);
							break;
						case "Noise floor dB":
							if (!post) {break;}
							overallFloor = 0;
							overallFloorString = stat[1];
							overallFloor = Double.parseDouble(stat[1]);
							break;
						case "Number of samples":
							if (sampleCount > 0) {break;}
							sampleCount = Integer.parseInt(stat[1]);
							duration = (double)sampleCount/(double)sampleRate;
							break;
					}
				}
				if (post) {
					if (line.startsWith("Input Integrated:")) {oi = Float.parseFloat(line.split("\\s+")[2]);}
					if (line.startsWith("[Parsed_volumedetect_")) {
						String[] stat = line.split("] ")[1].split(": ");
						if (stat[0].equals("mean_volume")) {
							sampleFloor = 0;
							sampleFloorString = stat[1].split(" ")[0];
							sampleFloor = Float.parseFloat(sampleFloorString);
						}
					}
				} else {
					if (sampleRate == 0&&line.matches("^\\s+Stream\\s#\\d.*")) {
						sampleRate = Integer.parseInt(line.split(",")[1].replace("Hz", "").trim());
					}
					if (line.startsWith("Input True Peak:")) {itp = Float.parseFloat(line.split("\\s+")[3]);}
					if (line.startsWith("Input Integrated:")) {ii = Float.parseFloat(line.split("\\s+")[2]);}
					if (line.startsWith("Input LRA:")) {ilra = Float.parseFloat(line.split("\\s+")[2]);}
					if (line.startsWith("Input Threshold:")) {it = Float.parseFloat(line.split("\\s+")[2]);}
					if (line.startsWith("Target Offset:")) {to = Float.parseFloat(line.split("\\s+")[2]);}
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
			fileString = saveFileString;
			if (options.getStereo()) {layout = "stereo";}
		} else {
			setSaveFileString(file);
			sampleRate = 0;
			sampleCount = 0;
			fileString = file.getPath();
			tmpChain = getChain();
		}
		String[] stats = {"ffmpeg", "-hide_banner", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl="+layout+","+tmpChain+"asplit[loudnorm],astats=measure_perchannel=none;[loudnorm]loudnorm=print_format=summary", "-f", "null", ""};
		ffmpeg(post, stats);
		if (!post) {
			String splitAndMerge = "";
			if (options.getStereo()) {splitAndMerge = ",asplit,amerge";}
			int endSample = (int)(duration*(double)192000)-192000;
			String[] predict = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+tmpChain+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(ii)+":measured_LRA="+String.valueOf(ilra)+":measured_tp="+String.valueOf(itp)+":measured_thresh="+String.valueOf(it)+":offset="+String.valueOf(to)+splitAndMerge+",asplit=4[endSample][astats][loudnorm],atrim=end_sample=192000[startSample];[endSample]atrim=start_sample="+String.valueOf(endSample)+",[startSample]concat=2:0:1,volumedetect;[astats]astats=measure_perchannel=none;[loudnorm]loudnorm=print_format=summary", "-t", String.valueOf((float)duration), "-f", "null", ""};
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
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"loudnorm=i="+String.valueOf(targets.getI())+":lra="+String.valueOf(targets.getLRA())+":tp="+String.valueOf(targets.getTP())+":measured_I="+String.valueOf(ii)+":measured_LRA="+String.valueOf(ilra)+":measured_tp="+String.valueOf(itp)+":measured_thresh="+String.valueOf(it)+":offset="+String.valueOf(to)+splitAndMerge, "-t", String.valueOf((float)duration), "-ar", "44.1k", "-ab", "192k", "-f", "mp3", saveFileString};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {}
	}
}