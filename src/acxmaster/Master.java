package acxmaster;

// I/O and buffered I/O deps
import java.io.*;

// Master class
class Master {

	//////////////////
	// Class variables
	//////////////////

	// Files
	private static File[] files;
	private static Boolean isSingle;
	private static File save;
	private static String saveString;

	// 18-band EQ
	private static String oneBand = "0";
	private static String twoBand = "0";
	private static String threeBand = "0";
	private static String fourBand = "0";
	private static String fiveBand = "0";
	private static String sixBand = "0";
	private static String sevenBand = "0";
	private static String eightBand = "0";
	private static String nineBand = "0";
	private static String tenBand = "0";
	private static String elevenBand = "0";
	private static String twelveBand = "0";
	private static String thirteenBand = "0";
	private static String fourteenBand = "0";
	private static String fifteenBand = "0";
	private static String sixteenBand = "0";
	private static String seventeenBand = "0";
	private static String eighteenBand = "0";

	// Analysis stats
	private static String sampleRateString;
	private static int sampleRateInt;
	private static String ii;
	private static String itp;
	private static String ilra;
	private static String it;
	private static String to;
	private static String rms;
	private static String floor;
	private static String sampleCountString;
	private static int sampleCountInt;
	private static double durationDouble;
	private static String durationString;

	// Loudnorm targets
	private static String i = "-20.0";
	private static String lra = "7.0";
	private static String tp = "-3.0";

	// Additional filters
	private static String rnnn = "";
	private static String gate = "";
	private static String declick = "";
	private static Boolean stereo = false;

	//////////
	// Getters
	//////////

	// Files
	public File[] getFiles() {return files;}
	public int getFileCount() {return files.length;}
	public Boolean isSingle() {return isSingle;}

	// 18-band EQ
	public String getOneBand() {return oneBand;}
	public String getTwoBand() {return twoBand;}
	public String getThreeBand() {return threeBand;}
	public String getFourBand() {return fourBand;}
	public String getFiveBand() {return fiveBand;}
	public String getSixBand() {return sixBand;}
	public String getSevenBand() {return sevenBand;}
	public String getEightBand() {return eightBand;}
	public String getNineBand() {return nineBand;}
	public String getTenBand() {return tenBand;}
	public String getElevenBand() {return elevenBand;}
	public String getTwelveBand() {return twelveBand;}
	public String getThirteenBand() {return thirteenBand;}
	public String getFourteenBand() {return fourteenBand;}
	public String getFifteenBand() {return fifteenBand;}
	public String getSixteenBand() {return sixteenBand;}
	public String getSeventeenBand() {return seventeenBand;}
	public String getEighteenBand() {return eighteenBand;}

	// Analysis stats
	public String getII() {return ii;}
	public String getITP() {return itp;}
	public String getRMS() {return rms;}

	// Loudnorm targets
	public String getI() {return i;}
	public String getLRA() {return lra;}
	public String getTP() {return tp;}

	// Additional filters
	public String getRnnn() {return rnnn;}
	public String getGate() {return gate;}
	public String getDeclick() {return declick;}
	public Boolean getStereo() {return stereo;}

	//////////
	// Setters
	//////////

	// Files
	public void setFiles(File[] files) {this.files = files;}
	public void setIsSingle(Boolean isSingle) {this.isSingle = isSingle;}
	public void setSave(File save) {this.save = save;}
	private void setSaveString(File file) {
		if (isSingle) {
			saveString = save.getPath();
			return;
		}
		File saveFile;
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
		saveFile = save.toPath().resolve(base+".mp3").toFile();
		for (int i = 2;; i++) {
			if (!saveFile.exists()) {break;}
			saveFile = save.toPath().resolve(base+" ("+String.valueOf(i)+").mp3").toFile();
		}
		saveString = saveFile.getPath();
	}

	// 18-band EQ
	public void setOneBand(String gain) {oneBand = gain;}
	public void setTwoBand(String gain) {twoBand = gain;}
	public void setThreeBand(String gain) {threeBand = gain;}
	public void setFourBand(String gain) {fourBand = gain;}
	public void setFiveBand(String gain) {fiveBand = gain;}
	public void setSixBand(String gain) {sixBand = gain;}
	public void setSevenBand(String gain) {sevenBand = gain;}
	public void setEightBand(String gain) {eightBand = gain;}
	public void setNineBand(String gain) {nineBand = gain;}
	public void setTenBand(String gain) {tenBand = gain;}
	public void setElevenBand(String gain) {elevenBand = gain;}
	public void setTwelveBand(String gain) {twelveBand = gain;}
	public void setThirteenBand(String gain) {thirteenBand = gain;}
	public void setFourteenBand(String gain) {fourteenBand = gain;}
	public void setFifteenBand(String gain) {fifteenBand = gain;}
	public void setSixteenBand(String gain) {sixteenBand = gain;}
	public void setSeventeenBand(String gain) {seventeenBand = gain;}
	public void setEighteenBand(String gain) {eighteenBand = gain;}

	// Loudnorm targets
	public void setI(String i) {this.i = i;}
	public void setLRA(String lra) {this.lra = lra;}
	public void setTP(String tp) {this.tp = tp;}

	// Additional filters
	public void rnnn(Boolean enabled) {
		if (enabled) {rnnn = "arnndn=std.rnnn,";
		} else {rnnn = "";}
	}
	public void setRnnn(String rnnn) {this.rnnn = rnnn;}
	public void gate(Boolean enabled) {
		if (enabled) {gate = "agate,";
		} else {gate = "";}
	}
	public void setGate(String gate) {this.gate = gate;}
	public void declick(Boolean enabled) {
		if (enabled) {declick = "adeclick,";
		} else {declick = "";}
	}
	public void setDeclick(String declick) {this.declick = declick;}
	public void setStereo(Boolean enabled) {stereo = enabled;}

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
		String filters = "";
		String layout = "mono";
		String fileString;
		if (post) {
			fileString = saveString;
			if (stereo) {layout = "stereo";}
		} else {
			setSaveString(file);
			sampleRateString = null;
			fileString = file.getPath();
			filters = rnnn+gate+declick+
				"superequalizer="+
				oneBand+"dB:"+
				twoBand+"dB:"+
				threeBand+"dB:"+
				fourBand+"dB:"+
				fiveBand+"dB:"+
				sixBand+"dB:"+
				sevenBand+"dB:"+
				eightBand+"dB:"+
				nineBand+"dB:"+
				tenBand+"dB:"+
				elevenBand+"dB:"+
				twelveBand+"dB:"+
				thirteenBand+"dB:"+
				fourteenBand+"dB:"+
				fifteenBand+"dB:"+
				sixteenBand+"dB:"+
				seventeenBand+"dB:"+
				eighteenBand+"dB,";
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] loudnorm = {"ffmpeg", "-hide_banner", "-i", fileString, "-vn", "-sn", "-dn", "-af", "aformat=cl="+layout+","+filters+"loudnorm=print_format=summary", "-f", "null", ""};
			Process process = runtime.exec(loudnorm);
			InputStream stderr = process.getErrorStream();
			InputStreamReader stderrReader = new InputStreamReader(stderr);
			BufferedReader stderrBufferedReader = new BufferedReader(stderrReader);
			String line = null;
			while ((line = stderrBufferedReader.readLine()) != null) {
				if (!post) {
					if (sampleRateString == null&&line.matches("^\\s+Stream\\s#\\d.*")) {
						sampleRateString = line.split(",")[1].replace("Hz", "").trim();
						sampleRateInt = Integer.parseInt(sampleRateString);
					}
					if (line.startsWith("Input Integrated:")) {ii = line.split("\\s+")[2];}
					if (line.startsWith("Input True Peak:")) {itp = line.split("\\s+")[3];}
				}
				if (line.startsWith("Input LRA:")) {ilra = line.split("\\s+")[2];}
				if (line.startsWith("Input Threshold:")) {it = line.split("\\s+")[2];}
				if (line.startsWith("Target Offset:")) {to = line.split("\\s+")[2];}
			}
			process.waitFor();
			if (post) {
				String[] stats = {"ffmpeg", "-hide_banner", "-i", fileString, "-vn", "-sn", "-dn", "-af", "aformat=cl="+layout+","+filters+"astats=measure_perchannel=none", "-f", "null", ""};
				process = runtime.exec(stats);
				stderr = process.getErrorStream();
				stderrReader = new InputStreamReader(stderr);
				stderrBufferedReader = new BufferedReader(stderrReader);
				line = null;
				while ((line = stderrBufferedReader.readLine()) != null) {
					if (line.startsWith("[Parsed_astats_")) {
						String[] stat = line.split("] ")[1].split(": ");
						switch (stat[0]) {
							case "Peak level dB":
								itp = stat[1];
								break;
							case "RMS level dB":
								rms = stat[1];
								break;
							case "Noise floor dB":
								floor = stat[1];
								break;
							case "Number of samples":
								sampleCountString = stat[1];
								sampleCountInt= Integer.parseInt(sampleCountString);
								durationDouble = (double)sampleCountInt/(double)44100;
								durationString = String.valueOf(durationDouble);
								break;
						}
					}
				}
				process.waitFor();
			}
		} catch (Exception exception) {}
	}

	// Function to master files
	public void master(File file) {
		String fileString = file.getPath();
		String filters = rnnn+gate+declick+
			"superequalizer="+
			oneBand+"dB:"+
			twoBand+"dB:"+
			threeBand+"dB:"+
			fourBand+"dB:"+
			fiveBand+"dB:"+
			sixBand+"dB:"+
			sevenBand+"dB:"+
			eightBand+"dB:"+
			nineBand+"dB:"+
			tenBand+"dB:"+
			elevenBand+"dB:"+
			twelveBand+"dB:"+
			thirteenBand+"dB:"+
			fourteenBand+"dB:"+
			fifteenBand+"dB:"+
			sixteenBand+"dB:"+
			seventeenBand+"dB:"+
			eighteenBand+"dB,";
		String splitAndMerge = "";
		if (stereo) {splitAndMerge = ",asplit,amerge";}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+filters+"loudnorm=i="+i+":lra="+lra+":tp="+tp+":measured_I="+ii+":measured_LRA="+ilra+":measured_tp="+itp+":measured_thresh="+it+":offset="+to+splitAndMerge, "-ar", "44.1k", "-ab", "192k", "-f", "mp3", saveString};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {}
	}
}