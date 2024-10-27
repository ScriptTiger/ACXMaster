package acxmaster;

// Options class
class Options {

	//////////////////
	// Class variables
	//////////////////

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

	public String getRnnn() {return rnnn;}
	public String getGate() {return gate;}
	public String getDeclick() {return declick;}
	public Boolean getStereo() {return stereo;}
	public String getNoise() {return noise;}
	public Boolean getNoWarn() {return noWarn;}
	public String getCustom() {return custom;}
	public String getChain() {return rnnn+gate+custom+declick;}
	public String getNoiseChain() {
		if (!noise.isEmpty()) {
			return "[noise];"+noise+"[noise]amix=2:shortest,";
		} else {return ",";}
	}
	public String getStereoChain() {
		if (stereo) {return ",asplit,join";
		} else {return "";}
	}

	//////////
	// Setters
	//////////

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
}