package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Export controller class
class ExportDialog {
	private static Boolean reset;
	public Boolean getReset() {return reset;}
	private void setReset(Boolean reset) {this.reset = reset;}

	public ExportDialog(JFrame jFrame, Export export) {

		// Set dimensions and padding
		final int W = 300;
		final int H = 120;
		final int PAD = 5;

		// Initialize reset
		reset = false;

		// Store current extension to track if it changes
		String extension = export.getExtension();

		// Set up the dialog window
		JDialog jDialog = new JDialog(jFrame, "Export options", true);
		jDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		jDialog.getContentPane().setLayout(null);

		// Sample rate label
		JLabel sampleRateLabel = new JLabel("Sample rate:");
		sampleRateLabel.setBounds(PAD, PAD, (W/2)-PAD, 25);
		jDialog.add(sampleRateLabel);

		// Sample rate combo box
		String[] sampleRateIndex = {
			"44100",
			"48000",
			"same as source"
		};
		JComboBox sampleRateComboBox = new JComboBox(sampleRateIndex);
		sampleRateComboBox.setBounds(W/2, PAD, (W/2)-PAD, 25);
		switch (export.getSampleRate()) {
			case 0:
				sampleRateComboBox.setSelectedIndex(2);
				break;
			case 48000:
				sampleRateComboBox.setSelectedIndex(1);
				break;
			default:
				sampleRateComboBox.setSelectedIndex(0);
				break;
		}
		jDialog.add(sampleRateComboBox);

		// Encoding label
		JLabel encodingLabel = new JLabel("Encoding:");
		encodingLabel.setBounds(PAD, PAD+25, (W/2)-PAD, 25);
		jDialog.add(encodingLabel);

		// Encoding combo box
		String[] encodingIndex = {
			"MP3",
			"16-bit ALAC",
			"24-bit ALAC",
			"16-bit FLAC",
			"24-bit FLAC",
			"16-bit PCM",
			"24-bit PCM"
		};
		JComboBox encodingComboBox = new JComboBox(encodingIndex);
		encodingComboBox.setBounds(W/2, PAD+25, (W/2)-PAD, 25);
		String codec = export.getCodec();
		String bitDepth = export.getBitDepth();
		switch (codec) {
			case "mp3":
				encodingComboBox.setSelectedIndex(0);
				break;
			case "alac":
				if (bitDepth.equals("s16")) {encodingComboBox.setSelectedIndex(1);
				} else {encodingComboBox.setSelectedIndex(2);}
				break;
			case "flac":
				if (bitDepth.equals("s16")) {encodingComboBox.setSelectedIndex(3);
				} else {encodingComboBox.setSelectedIndex(4);}
				break;
			case "pcm_s16le":
				encodingComboBox.setSelectedIndex(5);
				break;
			case "pcm_s24le":
				encodingComboBox.setSelectedIndex(6);
				break;

		}
		jDialog.add(encodingComboBox);

		// Revert to defaults button
		JButton defaults = new JButton("Revert to defaults");
		defaults.setBounds(PAD, (PAD+25)*2, W-PAD*2, 25);
		defaults.addActionListener(e -> {
			sampleRateComboBox.setSelectedIndex(0);
			encodingComboBox.setSelectedIndex(0);
		});
		jDialog.add(defaults);

		// Apply button
		JButton apply = new JButton("Apply");
		apply.setBounds(PAD, (PAD+25)*3, W-PAD*2, 25);
		apply.addActionListener(e -> {
			switch (sampleRateComboBox.getSelectedIndex()) {
				case 0:
					export.setSampleRate(44100);
					break;
				case 1:
					export.setSampleRate(48000);
					break;
				case 2:
					export.setSampleRate(0);
					break;
			}
			switch (encodingComboBox.getSelectedIndex()) {
				case 0:
					export.setBitRate(320000);
					export.setCodec("mp3");
					export.setBitDepth("fltp");
					export.setCompressionLevel(0);
					export.setExtension("mp3");
					break;
				case 1:
					export.setBitRate(0);
					export.setCodec("alac");
					export.setBitDepth("s16p");
					export.setCompressionLevel(0);
					export.setExtension("m4a");
					break;
				case 2:
					export.setBitRate(0);
					export.setCodec("alac");
					export.setBitDepth("s32p");
					export.setCompressionLevel(0);
					export.setExtension("m4a");
					break;
				case 3:
					export.setBitRate(0);
					export.setCodec("flac");
					export.setBitDepth("s16");
					export.setCompressionLevel(12);
					export.setExtension("flac");
					break;
				case 4:
					export.setBitRate(0);
					export.setCodec("flac");
					export.setBitDepth("s32");
					export.setCompressionLevel(12);
					export.setExtension("flac");
					break;
				case 5:
					export.setBitRate(0);
					export.setCodec("pcm_s16le");
					export.setBitDepth("s16");
					export.setCompressionLevel(0);
					export.setExtension("wav");
					break;
				case 6:
					export.setBitRate(0);
					export.setCodec("pcm_s24le");
					export.setBitDepth("s32");
					export.setCompressionLevel(0);
					export.setExtension("wav");
					break;
			}
			setReset(!extension.equals(export.getExtension()));
			jDialog.setVisible(false);
			jDialog.dispose();
		});
		jDialog.add(apply);

		// Finish setting up dialog window and show
		jDialog.pack();
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);
		jDialog.setVisible(true);
	}
}