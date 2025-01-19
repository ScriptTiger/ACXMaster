package acxmaster;

// Export class
class Export {

	//////////////////
	// Class variables
	//////////////////

	private static int sampleRate = 44100;
	private static int bitRate = 320000;
	private static String codec = "mp3";
	private static String bitDepth = "fltp";
	private static int compressionLevel = 0;
	private static String extension = "mp3";
	private static String currentDirectory = "";

	//////////
	// Getters
	//////////

	public int getSampleRate() {return sampleRate;}
	public int getBitRate() {return bitRate;}
	public String getCodec() {return codec;}
	public String getBitDepth() {return bitDepth;}
	public int getCompressionLevel() {return compressionLevel;}
	public String getExtension() {return extension;}
	public String getCurrentDirectory() {return currentDirectory;}

	//////////
	// Setters
	//////////

	public void setSampleRate(int sampleRate) {this.sampleRate = sampleRate;}
	public void setBitRate(int bitRate) {this.bitRate = bitRate;}
	public void setCodec(String codec) {this.codec = codec;}
	public void setBitDepth(String bitDepth) {this.bitDepth = bitDepth;}
	public void setCompressionLevel(int compressionLevel) {this.compressionLevel = compressionLevel;}
	public void setExtension(String extension) {this.extension = extension;}
	public void setCurrentDirectory(String currentDirectory) {this.currentDirectory = currentDirectory;}
}