package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// I/O and buffered I/O deps
import java.io.*;

// File chooser filter dep
import javax.swing.filechooser.FileNameExtensionFilter;

// Main controller class
public class Main extends JPanel {
	private static JFrame jFrame;
	private static Boolean mode = true;

	// Main controller constructor
	private Main() {

		// Singletons
		final Master master = new Master();
		final GraphicEQ graphicEQ = master.getGraphicEQ();
		final Targets targets = master.getTargets();
		final Options options = master.getOptions();

		// Set dimensions and padding
		final int W = 400;
		final int H = 270;
		final int PAD = 5;

		// Store current look and feel
		LookAndFeel lookAndFeel = UIManager.getLookAndFeel();

		// JPanel properties
		setPreferredSize(new Dimension(W, H));
		setLayout(null);

		// Read configuration from acxmaster.conf file if it exists
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("acxmaster.conf")));
			String configLine;
			while ((configLine = reader.readLine()) != null) {
				String[] tokens = configLine.split("=", 2);
				switch (tokens[0].toLowerCase()) {
					case "mode":
						try {mode = Boolean.parseBoolean(tokens[1]);} catch (Exception exception) {}
						break;
					case "1b":
						graphicEQ.setOneBand(tokens[1]);
						break;
					case "2b":
						graphicEQ.setTwoBand(tokens[1]);
						break;
					case "3b":
						graphicEQ.setThreeBand(tokens[1]);
						break;
					case "4b":
						graphicEQ.setFourBand(tokens[1]);
						break;
					case "5b":
						graphicEQ.setFiveBand(tokens[1]);
						break;
					case "6b":
						graphicEQ.setSixBand(tokens[1]);
						break;
					case "7b":
						graphicEQ.setSevenBand(tokens[1]);
						break;
					case "8b":
						graphicEQ.setEightBand(tokens[1]);
						break;
					case "9b":
						graphicEQ.setNineBand(tokens[1]);
						break;
					case "10b":
						graphicEQ.setTenBand(tokens[1]);
						break;
					case "11b":
						graphicEQ.setElevenBand(tokens[1]);
						break;
					case "12b":
						graphicEQ.setTwelveBand(tokens[1]);
						break;
					case "13b":
						graphicEQ.setThirteenBand(tokens[1]);
						break;
					case "14b":
						graphicEQ.setFourteenBand(tokens[1]);
						break;
					case "15b":
						graphicEQ.setFifteenBand(tokens[1]);
						break;
					case "16b":
						graphicEQ.setSixteenBand(tokens[1]);
						break;
					case "17b":
						graphicEQ.setSeventeenBand(tokens[1]);
						break;
					case "18b":
						graphicEQ.setEighteenBand(tokens[1]);
						break;
					case "rnnn":
						options.setRnnn(tokens[1]);
						break;
					case "stereo":
						try {options.setStereo(Boolean.parseBoolean(tokens[1]));} catch (Exception exception) {}
						break;
					case "declick":
						options.setDeclick(tokens[1]);
						break;
					case "gate":
						options.setGate(tokens[1]);
						break;
					case "noise":
						options.setNoise(tokens[1]);
						break;
					case "nowarn":
						try {options.setNoWarn(Boolean.parseBoolean(tokens[1]));} catch (Exception exception) {}
						break;
					case "custom":
						options.setCustom(tokens[1]);
						break;
					case "i":
						try {targets.setI(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
						break;
					case "lra":
						try {targets.setLRA(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
						break;
					case "tp":
						try {targets.setTP(Float.parseFloat(tokens[1]));} catch (Exception exception) {}
						break;
				}
			}
			reader.close();
		} catch (Exception err) {}

		// Set up menu bar
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setBounds(0, 0, W, 25);
		add(jMenuBar);

		// Set up settings menu
		JMenu settings = new JMenu("Settings");
		jMenuBar.add(settings);

		// Initialize mode menu item
		JMenuItem modeItem = new JMenuItem("Mode");
		settings.add(modeItem);

		// Set up Targets menu item
		JMenuItem targetsItem = new JMenuItem("Targets");
		targetsItem.addActionListener(e -> new TargetsDialog(jFrame, targets));
		settings.add(targetsItem);

		// Set up Graphic EQ menu item
		JMenuItem eqItem = new JMenuItem("Graphic EQ");
		eqItem.addActionListener(e -> new GraphicEQDialog(jFrame, graphicEQ));
		settings.add(eqItem);

		// Set up Additional options menu item
		JMenuItem optionsItem = new JMenuItem("Additional options");
		optionsItem.addActionListener(e -> new OptionsDialog(jFrame, options));
		settings.add(optionsItem);

		// Set up Save Settings menu item
		JMenuItem saveSettingsItem = new JMenuItem("Save Settings");
		saveSettingsItem.addActionListener(e -> {
			try {
				FileWriter writer = new FileWriter("acxmaster.conf");
				writer.write(
					"mode="+String.valueOf(mode)+"\n"+
					"1b="+graphicEQ.getOneBand()+"\n"+
					"2b="+graphicEQ.getTwoBand()+"\n"+
					"3b="+graphicEQ.getThreeBand()+"\n"+
					"4b="+graphicEQ.getFourBand()+"\n"+
					"5b="+graphicEQ.getFiveBand()+"\n"+
					"6b="+graphicEQ.getSixBand()+"\n"+
					"7b="+graphicEQ.getSevenBand()+"\n"+
					"8b="+graphicEQ.getEightBand()+"\n"+
					"9b="+graphicEQ.getNineBand()+"\n"+
					"10b="+graphicEQ.getTenBand()+"\n"+
					"11b="+graphicEQ.getElevenBand()+"\n"+
					"12b="+graphicEQ.getTwelveBand()+"\n"+
					"13b="+graphicEQ.getThirteenBand()+"\n"+
					"14b="+graphicEQ.getFourteenBand()+"\n"+
					"15b="+graphicEQ.getFifteenBand()+"\n"+
					"16b="+graphicEQ.getSixteenBand()+"\n"+
					"17b="+graphicEQ.getSeventeenBand()+"\n"+
					"18b="+graphicEQ.getEighteenBand()+"\n"+
					"rnnn="+options.getRnnn()+"\n"+
					"stereo="+String.valueOf(options.getStereo())+"\n"+
					"declick="+options.getDeclick()+"\n"+
					"gate="+options.getGate()+"\n"+
					"noise="+options.getNoise()+"\n"+
					"nowarn="+String.valueOf(options.getNoWarn())+"\n"+
					"custom="+options.getCustom()+"\n"+
					"i="+String.valueOf(targets.getI())+"\n"+
					"lra="+String.valueOf(targets.getLRA())+"\n"+
					"tp="+String.valueOf(targets.getTP())
				);
				writer.close();
			} catch (Exception err) {}
		});
		settings.add(saveSettingsItem);

		// Initialize buttons and text fields
		JButton chooseButton = new JButton("Choose audio files...");
		JTextField fileChooserTextField = new JTextField();
		JButton saveButton = new JButton("Save...");
		JTextField saveChooserTextField = new JTextField();
		JButton masterButton = new JButton("Master!");
		JLabel iiLabel = new JLabel("Integrated loudness:");
		JTextField iiTextField = new JTextField();
		JLabel itpLabel = new JLabel("True peak:");
		JTextField itpTextField = new JTextField();
		JLabel floorLabel = new JLabel("Noise floor:");
		JTextField floorTextField = new JTextField();

		// Position buttons and text fields
		chooseButton.setBounds(PAD, PAD+25, W-PAD*2, 25);
		fileChooserTextField.setBounds(PAD, (PAD+25)*2, W-PAD*2, 25);
		saveButton.setBounds(PAD, (PAD+25)*3, W-PAD*2, 25);
		saveChooserTextField.setBounds(PAD, (PAD+25)*4, W-PAD*2, 25);
		masterButton.setBounds(PAD, (PAD+25)*5, W-PAD*2, 25);
		iiLabel.setBounds(PAD, (PAD+25)*6, (W/2)-PAD, 25);
		iiTextField.setBounds(W/2, (PAD+25)*6, (W/2)-PAD, 25);
		itpLabel.setBounds(PAD, (PAD+25)*7, (W/2)-PAD, 25);
		itpTextField.setBounds(W/2, (PAD+25)*7, (W/2)-PAD, 25);
		floorLabel.setBounds(PAD, (PAD+25)*8, (W/2)-PAD, 25);
		floorTextField.setBounds(W/2, (PAD+25)*8, (W/2)-PAD, 25);

		// Set up file chooser text field
		fileChooserTextField.setEditable(false);
		fileChooserTextField.setBackground(Color.WHITE);
		add(fileChooserTextField);

		// Set up file chooser
		chooseButton.addActionListener(e -> {
			JFileChooser fileChooser = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				fileChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			fileChooser.setMultiSelectionEnabled(true);
			fileChooser.showDialog(jFrame, "Choose audio files...");
			master.setFiles(fileChooser.getSelectedFiles());
			int fileCount = master.getFileCount();
			if (fileCount == 0) {return;}
			if (fileCount == 1) {
				fileChooserTextField.setText(master.getFiles()[0].getPath());
				saveButton.setText("Save as...");
				master.setIsSingle(true);
			} else {
				fileChooserTextField.setText(String.valueOf(fileCount)+" files selected");
				saveButton.setText("Save to...");
				master.setIsSingle(false);
			}
			saveButton.setEnabled(true);
			master.setSaveFile(null);
			saveChooserTextField.setText("");
			if (mode) {
				masterButton.setEnabled(false);
			} else {
				masterButton.setText("Check!");
				masterButton.setEnabled(true);
			}
		});
		add(chooseButton);

		// Set up save chooser text field
		saveChooserTextField.setEditable(false);
		saveChooserTextField.setBackground(Color.WHITE);
		add(saveChooserTextField);

		// Set up save chooser
		saveButton.setEnabled(false);
		saveButton.addActionListener(e -> {
			JFileChooser saveChooser = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				saveChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			String saveButtonText;
			if (master.isSingle()) {
				FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("MP3 File", "mp3");
				saveChooser.addChoosableFileFilter(fileNameExtensionFilter);
				saveButtonText = "Save as...";				
			} else {
				saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				saveButtonText = "Save to...";
			}
			saveChooser.setAcceptAllFileFilterUsed(false);
			saveChooser.showDialog(jFrame, saveButtonText);
			File saveFile = saveChooser.getSelectedFile();
			if (master.isSingle()) {
				if (!saveFile.getName().toLowerCase().endsWith(".mp3")) {
					saveFile = saveFile.getParentFile().toPath().resolve(saveFile.getName()+".mp3").toFile();
				}
			}
			master.setSaveFile(saveFile);
			saveChooserTextField.setText(saveFile.getPath());
			masterButton.setEnabled(true);
			masterButton.setText("Master!");
		});
		add(saveButton);

		// Set up master button
		masterButton.setEnabled(false);
		masterButton.addActionListener(e -> {
			masterButton.setText("Checking for dependencies...");
			masterButton.setEnabled(false);
			chooseButton.setEnabled(false);
			saveButton.setEnabled(false);
			modeItem.setEnabled(false);
			targetsItem.setEnabled(false);
			eqItem.setEnabled(false);
			optionsItem.setEnabled(false);
			new Thread(
				new Runnable() {
					public void run() {
						if (master.ffCheck()) {
							for (File file : master.getFiles()) {
								if (mode) {
									masterButton.setText("Analyzing source audio...");
									master.analyze(file);
								} else {
									masterButton.setText("Checking audio for problems...");
									master.check(file);
								}

								AudioInfo audioInfo = master.getAudioInfo();

								if (!mode || !options.getNoWarn()) {
									float otp;
									double duration;
									if (mode) {
										masterButton.setText("Predicting problems...");
										master.predict(file);
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
									if (!warnings.isEmpty()) {
										if (mode) {
											warnings = "\""+file.getPath()+"\"\n\n"+warnings+
											"\nWould you like to encode the audio file anyway?\n";
											int choice = JOptionPane.showConfirmDialog(null, warnings, "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
											if(choice != JOptionPane.YES_OPTION){continue;};
										} else {
											warnings = "\""+file.getPath()+"\"\n\n"+warnings;
											int choice = JOptionPane.showConfirmDialog(null, warnings, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
										}
									}
								}

								String rmsString = String.valueOf((float)Math.round((float)audioInfo.getRMS()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1));
								String floorString;
								if (audioInfo.getOverallFloor() == 0) {floorString = audioInfo.getOverallFloorString();
								} else if (audioInfo.getSampleFloor() == 0) {floorString = audioInfo.getSampleFloorString();
								} else {floorString = String.valueOf((float)Math.round((float)audioInfo.getSampleFloor()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1));}
								if (mode) {
									masterButton.setText("Mastering...");
									master.master(file);
									iiTextField.setText(String.valueOf(audioInfo.getOI())+" LUFS / "+rmsString+" dBRMS");
									itpTextField.setText(String.valueOf(audioInfo.getROTP())+" dBTP");
								} else {
									String otp = String.valueOf((float)Math.round((float)audioInfo.getOTP()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1));
									iiTextField.setText(rmsString+" dBRMS");
									itpTextField.setText(otp+" dBTP");
								}
								floorTextField.setText(floorString+" dBRMS");
							}
							if (mode) {masterButton.setText("Mastering complete!");
							} else {masterButton.setText("Checking complete!");}
						} else {masterButton.setText("Ensure FFmpeg is installed in your path!");}
						fileChooserTextField.setText("");
						saveChooserTextField.setText("");
						saveButton.setText("Save...");
						chooseButton.setEnabled(true);
						modeItem.setEnabled(true);
						if (mode) {
							targetsItem.setEnabled(true);
							eqItem.setEnabled(true);
							optionsItem.setEnabled(true);
						}
					}
				}
			).start();
		});
		add(masterButton);

		// Add stats labels
		add(iiLabel);
		add(itpLabel);
		add(floorLabel);

		// Set up loudness text field
		iiTextField.setEditable(false);
		iiTextField.setBackground(Color.WHITE);
		add(iiTextField);

		// Set up true peak text field
		itpTextField.setEditable(false);
		itpTextField.setBackground(Color.WHITE);
		add(itpTextField);

		// Set up noise floor text field
		floorTextField.setEditable(false);
		floorTextField.setBackground(Color.WHITE);
		add(floorTextField);

		// Set up mode menu item
		modeItem.addActionListener(e -> {
			ModeDialog modeDialog = new ModeDialog(jFrame, mode);
			mode = modeDialog.getMode();
			targetsItem.setEnabled(mode);
			eqItem.setEnabled(mode);
			optionsItem.setEnabled(mode);
			saveButton.setVisible(mode);
			saveChooserTextField.setVisible(mode);
			if (mode) {
				masterButton.setText("Master!");
				masterButton.setEnabled(false);
			} else {
				masterButton.setText("Check!");
				if (!fileChooserTextField.getText().isEmpty()) {masterButton.setEnabled(true);}
			}
		});
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