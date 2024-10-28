package acxmaster;

// I/O and buffered I/O deps
import java.io.*;

// Audio info class
class AudioInfo {

	//////////////////
	// Class variables
	//////////////////

	// Files
	private static String saveFileString = null;

	// Analysis stats
	private static String codec = null;
	private static int sampleRate = 0;
	private static String layout = null;
	private static int bitRate = 0;
	private static String roughDurationString = null;
	private static float roughDuration = 0;
	private static float ii = 0;
	private static float itp = 0;
	private static float ilra = 0;
	private static float it = 0;
	private static float to = 0;
	private static float oi = 0;
	private static float rotp = 0;
	private static double otp = 0;
	private static double rms = 0;
	private static String overallFloorString = null;
	private static double overallFloor = 0;
	private static int sampleCount = 0;
	private static double duration = 0;
	private static String sampleFloorString = null;
	private static double sampleFloor = 0;

	//////////
	// Getters
	//////////

	// Files
	public String getSaveFileString() {return saveFileString;}

	// Analysis stats
	public String getCodec() {return codec;}
	public int getSampleRate() {return sampleRate;}
	public String getLayout() {return layout;}
	public int getBitRate() {return bitRate;}
	public String getRoughDurationString() {return roughDurationString;}
	public float getRoughDuration() {return roughDuration;}
	public float getII() {return ii;}
	public float getITP() {return itp;}
	public float getILRA() {return ilra;}
	public float getIT() {return it;}
	public float getTO() {return to;}
	public float getOI() {return oi;}
	public float getROTP() {return rotp;}
	public double getOTP() {return otp;}
	public double getRMS() {return rms;}
	public String getOverallFloorString() {return overallFloorString;}
	public double getOverallFloor() {return overallFloor;}
	public int getSampleCount() {return sampleCount;}
	public double getDuration() {return duration;}
	public String getSampleFloorString() {return sampleFloorString;}
	public double getSampleFloor() {return sampleFloor;}

	//////////
	// Setters
	//////////

	// Constructor for Check mode
	public AudioInfo(File file) {
	}

	// Constructor for Master mode
	public AudioInfo(File file, File saveFile, Boolean isSingle) {
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

	// Analysis stats
	public void setCodec(String codec) {this.codec = codec;}
	public void setSampleRate(int sampleRate) {this.sampleRate = sampleRate;}
	public void setLayout(String layout) {this.layout = layout;}
	public void setBitRate(int bitRate) {this.bitRate = bitRate;}
	public void setRoughDurationString(String roughDurationString) {this.roughDurationString = roughDurationString;}
	public void setRoughDuration(float roughDuration) {this.roughDuration = roughDuration;}
	public void setII(float ii) {this.ii = ii;}
	public void setITP(float itp) {this.itp = itp;}
	public void setILRA(float ilra) {this.ilra = ilra;}
	public void setIT(float it) {this.it = it;}
	public void setTO(float to) {this.to = to;}
	public void setOI(float oi) {this.oi = oi;}
	public void setROTP(float rotp) {this.rotp = rotp;}
	public void setOTP(double otp) {this.otp = otp;}
	public void setRMS(double rms) {this.rms = rms;}
	public void setOverallFloorString(String overallFloorString) {this.overallFloorString = overallFloorString;}
	public void setOverallFloor(double overallFloor) {this.overallFloor = overallFloor;}
	public void setSampleCount(int sampleCount) {this.sampleCount = sampleCount;}
	public void setDuration(double duration) {this.duration = duration;}
	public void setSampleFloorString(String sampleFloorString) {this.sampleFloorString = sampleFloorString;}
	public void setSampleFloor(double sampleFloor) {this.sampleFloor = sampleFloor;}
}