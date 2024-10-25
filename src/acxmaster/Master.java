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
	private static File saveFile;
	private static String saveFileString;

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

	// Loudnorm targets
	private static float i = -20;
	private static float lra = 7;
	private static float tp = -3;

	// Additional filters
	private static String chain = "";
	private static String rnnn = "";
	private static String gate = "";
	private static String declick = "";
	private static Boolean stereo = false;
	private static String noise = "";
	private static Boolean noWarn = false;
	private static String custom = "";

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

	// Loudnorm targets
	public float getI() {return i;}
	public float getLRA() {return lra;}
	public float getTP() {return tp;}

	// Additional filters
	public String getChain() {
		if (chain.isEmpty()) {setChain();}
		return chain;
	}
	public String getRnnn() {return rnnn;}
	public String getGate() {return gate;}
	public String getDeclick() {return declick;}
	public Boolean getStereo() {return stereo;}
	public String getNoise() {return noise;}
	public Boolean getNoWarn() {return noWarn;}
	public String getCustom() {return custom;}

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
	public void setI(float i) {this.i = i;}
	public void setLRA(float lra) {this.lra = lra;}
	public void setTP(float tp) {this.tp = tp;}

	// Additional filters
	private void setChain() {
		chain = rnnn+gate+custom+declick+
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
			eighteenBand+"dB";
		if (!noise.isEmpty()) {
			chain = chain+"[noise];"+noise+"[noise]amix=2:shortest,";
		} else {chain = chain+",";}
	}
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
	public void noise(Boolean enabled) {
		if (enabled) {noise = "anoisesrc=44100:-70dB:c=brown,";
		} else {noise = "";}
	}
	public void setNoise(String noise) {this.noise = noise;}
	public void setNoWarn(Boolean enabled) {noWarn = enabled;}
	public void setCustom(String custom) {this.custom = custom;}

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
			if (stereo) {layout = "stereo";}
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
			if (stereo) {splitAndMerge = ",asplit,amerge";}
			int endSample = (int)(duration*(double)192000)-192000;
			String[] predict = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+tmpChain+"loudnorm=i="+String.valueOf(i)+":lra="+String.valueOf(lra)+":tp="+String.valueOf(tp)+":measured_I="+String.valueOf(ii)+":measured_LRA="+String.valueOf(ilra)+":measured_tp="+String.valueOf(itp)+":measured_thresh="+String.valueOf(it)+":offset="+String.valueOf(to)+splitAndMerge+",asplit=4[endSample][astats][loudnorm],atrim=end_sample=192000[startSample];[endSample]atrim=start_sample="+String.valueOf(endSample)+",[startSample]concat=2:0:1,volumedetect;[astats]astats=measure_perchannel=none;[loudnorm]loudnorm=print_format=summary", "-t", String.valueOf((float)duration), "-f", "null", ""};
			ffmpeg(true, predict);
		}
	}

	// Function to master files
	public void master(File file) {
		String fileString = file.getPath();
		String splitAndMerge = "";
		if (stereo) {splitAndMerge = ",asplit,amerge";}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+getChain()+"loudnorm=i="+String.valueOf(i)+":lra="+String.valueOf(lra)+":tp="+String.valueOf(tp)+":measured_I="+String.valueOf(ii)+":measured_LRA="+String.valueOf(ilra)+":measured_tp="+String.valueOf(itp)+":measured_thresh="+String.valueOf(it)+":offset="+String.valueOf(to)+splitAndMerge, "-t", String.valueOf((float)duration), "-ar", "44.1k", "-ab", "192k", "-f", "mp3", saveFileString};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {}
	}
}