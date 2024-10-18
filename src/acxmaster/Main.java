package acxmaster;

// Main standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// I/O and buffered I/O deps
import java.io.*;

// File chooser filter dep
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JPanel {
	private static Master master = new Master();
	private static JFrame jFrame;

	private Main() {

		final int W = 400;
		final int H = 400;
		final int PAD = 5;

		// Store current look and feel
		LookAndFeel lookAndFeel = UIManager.getLookAndFeel();

		// JPanel properties
		setPreferredSize(new Dimension(W, H));
		setLayout(null);

		// Integrated loudness label
		JLabel iiLabel = new JLabel("Integrated loudness:");
		iiLabel.setBounds(PAD, PAD*2, (W/2)-PAD, 25);
		add(iiLabel);

		// Integrated loudness text field
		JTextField iiTextField = new JTextField();
		iiTextField.setBounds((W/2)-PAD, PAD*2, (W/2)-PAD, 25);
		iiTextField.setEditable(false);
		iiTextField.setBackground(Color.WHITE);
		add(iiTextField);

		// True peak label
		JLabel itpLabel = new JLabel("True peak:");
		itpLabel.setBounds(PAD, PAD*4+25, (W/2)-PAD, 25);
		add(itpLabel);

		// True peak text field
		JTextField itpTextField = new JTextField();
		itpTextField.setBounds((W/2)-PAD, PAD*4+25, (W/2)-PAD, 25);
		itpTextField.setEditable(false);
		itpTextField.setBackground(Color.WHITE);
		add(itpTextField);

		// Noise suppression checkbox
		JCheckBox rnnnCheckbox = new JCheckBox("Noise suppression");
		rnnnCheckbox.setBounds(PAD, (H/2)-(25/2), (W/2)-PAD*2, 25);
		rnnnCheckbox.addActionListener(e -> {master.rnnn(rnnnCheckbox.isSelected());});
		add(rnnnCheckbox);

		// Stereo checkbox
		JCheckBox stereoCheckbox = new JCheckBox("Stereo");
		stereoCheckbox.setBounds((W/2)-PAD, (H/2)-(25/2), (W/2)-PAD*2, 25);
		stereoCheckbox.addActionListener(e -> {master.stereo(stereoCheckbox.isSelected());});
		add(stereoCheckbox);

		// Declick checkbox
		JCheckBox declickCheckbox = new JCheckBox("Declick");
		declickCheckbox.setBounds(PAD, (H/2)+(25/2), (W/2)-PAD*2, 25);
		declickCheckbox.addActionListener(e -> {master.declick(declickCheckbox.isSelected());});
		add(declickCheckbox);

		// Gate checkbox
		JCheckBox gateCheckbox = new JCheckBox("Noise gate");
		gateCheckbox.setBounds((W/2)-PAD, (H/2)+(25/2), (W/2)-PAD*2, 25);
		gateCheckbox.addActionListener(e -> {master.gate(gateCheckbox.isSelected());});
		add(gateCheckbox);

		// Initialize master button
		JButton masterButton = new JButton("Master!");

		// Save chooser text field
		JTextField saveChooserTextField = new JTextField();
		saveChooserTextField.setBounds(PAD, H-25*2-PAD*2, W-PAD*2, 25);
		saveChooserTextField.setEditable(false);
		saveChooserTextField.setBackground(Color.WHITE);
		add(saveChooserTextField);

		// Save chooser
		JButton saveButton = new JButton("Save as...");
		saveButton.setEnabled(false);
		saveButton.setBounds(PAD, H-25*3-PAD*3, W-PAD*2, 25);
		saveButton.addActionListener(e -> {
			JFileChooser saveChooser = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				saveChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			saveChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("MP3 File", "mp3");
			saveChooser.addChoosableFileFilter(fileNameExtensionFilter);
			saveChooser.showSaveDialog(jFrame);
			String save = saveChooser.getSelectedFile().getPath();
			if (!save.toLowerCase().endsWith(".mp3")) {save = save+".mp3";}
			master.setSave(save);
			saveChooserTextField.setText(save);
			masterButton.setEnabled(true);
			masterButton.setText("Master!");
		});
		add(saveButton);

		// File chooser text field
		JTextField fileChooserTextField = new JTextField();
		fileChooserTextField.setBounds(PAD, H-25*4-PAD*4, W-PAD*2, 25);
		fileChooserTextField.setEditable(false);
		fileChooserTextField.setBackground(Color.WHITE);
		add(fileChooserTextField);

		// File chooser
		JButton chooseButton = new JButton("Choose audio file...");
		chooseButton.setBounds(PAD, H-25*5-PAD*5, W-PAD*2, 25);
		chooseButton.addActionListener(e -> {
			JFileChooser fileChooser = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				fileChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			fileChooser.showDialog(jFrame, "Choose audio file...");
			String file = fileChooser.getSelectedFile().getPath();
			master.setFile(file);
			fileChooserTextField.setText(file);
			saveButton.setEnabled(true);
		});
		add(chooseButton);

		// Finish building master button
		masterButton.setBounds(PAD, H-25-PAD, W-PAD*2, 25);
		masterButton.setEnabled(false);
		masterButton.addActionListener(e -> {
			masterButton.setEnabled(false);
			chooseButton.setEnabled(false);
			saveButton.setEnabled(false);
			masterButton.setText("Analyzing source audio...");
			new Thread(
				new Runnable() {
					public void run() {
						if (master.analyze(false)) {
							masterButton.setText("Mastering...");
							master.master();
							masterButton.setText("Analyzing mastered audio...");
							master.analyze(true);
							iiTextField.setText(master.getII()+" LUFS");
							itpTextField.setText(master.getITP()+" dBTP");
							masterButton.setText("Master complete!");
						} else {masterButton.setText("An error occurred!");}
						fileChooserTextField.setText("");
						saveChooserTextField.setText("");
						chooseButton.setEnabled(true);
					}
				}
			).start();
		});
		add(masterButton);
	}

	public static void main(String[] args) {

		// Construct and set up jFrame
		JFrame jFrame = new JFrame("ACX Master");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().add(new Main());
		jFrame.pack();
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);

		// Show jFrame
		jFrame.setVisible(true);
	}
}

// Master class
class Master {
	private static String file = "";
	private static String save = "";
	private static String rnnn = "";
	private static String gate = "";
	private static String declick = "";
	private static Boolean stereo = false;
	private static String ii = "";
	private static String itp = "";
	private static String ilra = "";
	private static String it = "";
	private static String to = "";

	// Getters
	public String getII() {return ii;}
	public String getITP() {return itp;}

	// Setters
	public void setFile(String file) {this.file = file;}
	public void setSave(String save) {this.save = save;}
	public void rnnn(Boolean enabled) {
		if (enabled) {rnnn = "arnndn=std.rnnn,";
		} else {rnnn = "";}
	}
	public void gate(Boolean enabled) {
		if (enabled) {gate = "agate,";
		} else {gate = "";}
	}
	public void declick(Boolean enabled) {
		if (enabled) {declick = "adeclick,";
		} else {declick = "";}
	}
	public void stereo(Boolean enabled) {stereo = enabled;}

	// Function to analyze file
	public Boolean analyze(Boolean post) {
		Boolean err = true;
		String filters = "";
		String layout = "mono";
		String file = "";
		if (post) {
			file = this.save;
			if (stereo) {layout = "stereo";}
		} else {
			file = this.file;
			filters = rnnn+gate+declick;
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-i", file, "-vn", "-sn", "-dn", "-af", "aformat=cl="+layout+","+filters+"loudnorm=print_format=summary", "-f", "null", ""};
			Process process = runtime.exec(ffmpeg);
			InputStream stderr = process.getErrorStream();
			InputStreamReader stderrReader = new InputStreamReader(stderr);
			BufferedReader stderrBufferedReader = new BufferedReader(stderrReader);
			String line = null;
			while ((line = stderrBufferedReader.readLine()) != null) {
				if (line.startsWith("Input Integrated:")) {ii = line.split("\\s+")[2];}
				if (line.startsWith("Input True Peak:")) {itp = line.split("\\s+")[3];}
				if (line.startsWith("Input LRA:")) {ilra = line.split("\\s+")[2];}
				if (line.startsWith("Input Threshold:")) {it = line.split("\\s+")[2];}
				if (line.startsWith("Target Offset:")) {to = line.split("\\s+")[2];}
			}
			process.waitFor();
		} catch (Exception exception) {
			new ErrorDialog(exception.getMessage());
			err = false;
		}
		return err;
	}

	// Function to master file
	public void master() {
		String filters = rnnn+gate+declick;
		String splitAndMerge = "";
		if (stereo) {splitAndMerge = ",asplit,amerge";}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", file, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+filters+"loudnorm=i=-20.0:tp=-3:measured_I="+ii+":measured_LRA="+ilra+":measured_tp="+itp+":measured_thresh="+it+":offset="+to+splitAndMerge, "-ar", "44.1k", "-ab", "192k", "-f", "mp3", save};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {}
	}
}

// Generic error dialog
class ErrorDialog {
	public ErrorDialog(String msg) {
		JOptionPane optionPane = new JOptionPane(msg, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Error");
		dialog.setVisible(true);
	}
}