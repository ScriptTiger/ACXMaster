package acxmaster;

// Graphic EQ class
class GraphicEQ {

	//////////////////
	// Class variables
	//////////////////

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

	//////////
	// Getters
	//////////

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
	public String getChain() {
		return "superequalizer="+
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
	}

	//////////
	// Setters
	//////////

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
}