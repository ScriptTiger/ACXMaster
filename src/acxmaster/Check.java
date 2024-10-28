package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// I/O and buffered I/O deps
import java.io.*;

// Check class
class Check {
	public static Boolean skip = false;
	public Boolean getSkip() {return skip;}
	private void setSkip() {skip = true;}

	public Check(AudioInfo audioInfo, Boolean mode, File file) {
		float otp;
		double duration;
		if (mode) {
			otp = audioInfo.getROTP();
			duration = audioInfo.getDuration();
		} else {
			otp = (float)Math.round((float)audioInfo.getOTP()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1);
			duration = (double)audioInfo.getRoughDuration();
		}
		float rms = (float)Math.round((float)audioInfo.getRMS()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1);
		double overallFloor = audioInfo.getOverallFloor();
		float sampleFloor = 0;
		if (audioInfo.getSampleFloor() != 0) {
			sampleFloor = (float)Math.round((float)audioInfo.getSampleFloor()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1);
		}
		String warnings = "";
		String tense;
		if (mode) {tense = " will be ";
		} else {tense = " is ";}
		if (rms < -23 || rms > -18) {
			warnings = warnings+
			"* Your dBRMS should be between -23 and -18, but yours"+tense+String.valueOf(rms)+".\n";
		}
		if (otp < -6 || otp > -3) {
			warnings = warnings+
			"* Your true peak should be between -6 dBTP and -3 dBTP, but yours"+tense+String.valueOf(otp)+" dBTP.\n";
		}
		if (overallFloor == 0) {
			warnings = warnings+
			"* Your noise floor"+tense+"considered unnatural because it contains a value of "+audioInfo.getOverallFloorString()+".\n";
		}
		if (sampleFloor != 0 && (sampleFloor < -90 || sampleFloor > -60)) {
			warnings = warnings+
			"* ACX recommends at least 1 second of room tone, and no more than 5 seconds, at the start and end of every audio file\n"+
			"with a noise floor greater than -90 dBRMS and less than -60 dBRMS, but yours"+tense+String.valueOf(sampleFloor)+" dBRMS.\n";
		}
		if (duration > 7200) {
			warnings = warnings+
			"* Your audio file should be no longer than 120 minutes, but yours"+tense+"exceeding that.\n";
		}
		if (!mode) {
			String layout = audioInfo.getLayout();
			int sampleRate = audioInfo.getSampleRate();
			int bitRate = audioInfo.getBitRate();
			String codec = audioInfo.getCodec();
			if (!layout.equals("mono") && !layout.equals("stereo")) {
				warnings = warnings+
				"* Your channel layout should be either mono or stereo, but yours is "+layout+".\n";
			}
			if (sampleRate != 44100) {
				warnings = warnings+
				"* Your sample rate should be 44100 Hz, but yours is "+String.valueOf(sampleRate)+" Hz.\n";
			}
			if (bitRate < 192) {
				warnings = warnings+
				"* Your bit rate should be at least 192 kb/s or higher, but yours is "+String.valueOf(bitRate)+" kb/s.\n";
			}
			if (!codec.equals("mp3")) {
				warnings = warnings+
				"* Your audio file should be encoded to mp3, but yours is encoded to "+codec+".\n";
			}
		}
		if (!warnings.isEmpty()) {
			if (mode) {
				warnings = "\""+file.getPath()+"\"\n\n"+warnings+
				"\nWould you like to encode the audio file anyway?\n";
				int choice = JOptionPane.showConfirmDialog(null, warnings, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice != JOptionPane.YES_OPTION){setSkip();};
			} else {
				warnings = "\""+file.getPath()+"\"\n\n"+warnings;
				int choice = JOptionPane.showConfirmDialog(null, warnings, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}

	}
}