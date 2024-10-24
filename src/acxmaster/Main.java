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
					case "1b":
						master.setOneBand(tokens[1]);
						break;
					case "2b":
						master.setTwoBand(tokens[1]);
						break;
					case "3b":
						master.setThreeBand(tokens[1]);
						break;
					case "4b":
						master.setFourBand(tokens[1]);
						break;
					case "5b":
						master.setFiveBand(tokens[1]);
						break;
					case "6b":
						master.setSixBand(tokens[1]);
						break;
					case "7b":
						master.setSevenBand(tokens[1]);
						break;
					case "8b":
						master.setEightBand(tokens[1]);
						break;
					case "9b":
						master.setNineBand(tokens[1]);
						break;
					case "10b":
						master.setTenBand(tokens[1]);
						break;
					case "11b":
						master.setElevenBand(tokens[1]);
						break;
					case "12b":
						master.setTwelveBand(tokens[1]);
						break;
					case "13b":
						master.setThirteenBand(tokens[1]);
						break;
					case "14b":
						master.setFourteenBand(tokens[1]);
						break;
					case "15b":
						master.setFifteenBand(tokens[1]);
						break;
					case "16b":
						master.setSixteenBand(tokens[1]);
						break;
					case "17b":
						master.setSeventeenBand(tokens[1]);
						break;
					case "18b":
						master.setEighteenBand(tokens[1]);
						break;
					case "rnnn":
						master.setRnnn(tokens[1]);
						break;
					case "stereo":
						master.setStereo(Boolean.parseBoolean(tokens[1]));
						break;
					case "declick":
						master.setDeclick(tokens[1]);
						break;
					case "gate":
						master.setGate(tokens[1]);
						break;
					case "noise":
						master.setNoise(Boolean.parseBoolean(tokens[1]));
						break;
					case "i":
						master.setI(Float.parseFloat(tokens[1]));
						break;
					case "lra":
						master.setLRA(Float.parseFloat(tokens[1]));
						break;
					case "tp":
						master.setTP(Float.parseFloat(tokens[1]));
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

		// Set up Targets menu item
		JMenuItem targetsItem = new JMenuItem("Targets");
		targetsItem.addActionListener(e -> openTargetsDialog());
		settings.add(targetsItem);

		// Set up Graphic EQ menu item
		JMenuItem eqItem = new JMenuItem("Graphic EQ");
		eqItem.addActionListener(e -> openEQDialog());
		settings.add(eqItem);

		// Set up Additional options menu item
		JMenuItem optionsItem = new JMenuItem("Additional options");
		optionsItem.addActionListener(e -> openOptionsDialog());
		settings.add(optionsItem);

		// Set up Save Settings menu item
		JMenuItem saveSettingsItem = new JMenuItem("Save Settings");
		saveSettingsItem.addActionListener(e -> {
			try {
				FileWriter writer = new FileWriter("acxmaster.conf");
				writer.write(
					"1b="+master.getOneBand()+"\n"+
					"2b="+master.getTwoBand()+"\n"+
					"3b="+master.getThreeBand()+"\n"+
					"4b="+master.getFourBand()+"\n"+
					"5b="+master.getFiveBand()+"\n"+
					"6b="+master.getSixBand()+"\n"+
					"7b="+master.getSevenBand()+"\n"+
					"8b="+master.getEightBand()+"\n"+
					"9b="+master.getNineBand()+"\n"+
					"10b="+master.getTenBand()+"\n"+
					"11b="+master.getElevenBand()+"\n"+
					"12b="+master.getTwelveBand()+"\n"+
					"13b="+master.getThirteenBand()+"\n"+
					"14b="+master.getFourteenBand()+"\n"+
					"15b="+master.getFifteenBand()+"\n"+
					"16b="+master.getSixteenBand()+"\n"+
					"17b="+master.getSeventeenBand()+"\n"+
					"18b="+master.getEighteenBand()+"\n"+
					"rnnn="+master.getRnnn()+"\n"+
					"stereo="+String.valueOf(master.getStereo())+"\n"+
					"declick="+master.getDeclick()+"\n"+
					"gate="+master.getGate()+"\n"+
					"noise="+String.valueOf(master.getNoise())+"\n"+
					"i="+String.valueOf(master.getI())+"\n"+
					"lra="+String.valueOf(master.getLRA())+"\n"+
					"tp="+String.valueOf(master.getTP())
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
			master.setSave(null);
			saveChooserTextField.setText("");
			masterButton.setEnabled(false);
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
			File save = saveChooser.getSelectedFile();
			if (master.isSingle()) {
				if (!save.getName().toLowerCase().endsWith(".mp3")) {
					save = save.getParentFile().toPath().resolve(save.getName()+".mp3").toFile();
				}
			}
			master.setSave(save);
			saveChooserTextField.setText(save.getPath());
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
			master.nextBatch();
			new Thread(
				new Runnable() {
					public void run() {
						if (master.check()) {
							for (File file : master.getFiles()) {
								masterButton.setText("Analyzing source audio...");
								master.nextFile();
								master.analyze(file);

								String warnings = "";
								double rms = master.getRMS();
								double otp = master.getOTP();
								double overallFloor = master.getOverallFloor();
								float sampleFloor = master.getSampleFloor();
								double duration = master.getDuration();
								if (rms < -23 || rms > -18) {
									warnings = warnings+
									"* Your dBRMS should be between -23 and -18, but yours will be "+String.valueOf(rms)+".\n";
								}
								if (otp < -6 || otp > -3) {
									warnings = warnings+
									"* Your true peak should be between -6 dBTP and -3 dBTP, but yours will be "+String.valueOf(otp)+" dBTP.\n";
								}
								if (overallFloor == 0) {
									warnings = warnings+
									"* Your noise floor will be considered unnatural because it contains a value of "+master.getOverallFloorString()+".\n";
								}
								if (sampleFloor != 0 && (sampleFloor < -90 || sampleFloor > -60)) {
									warnings = warnings+
									"* ACX recommends at least 1 second of room tone, and no more than 5 seconds, at the start and end of every audio file\n"+
									"with a noise floor greater than -90 dBRMS and less than -60 dBRMS, but yours will be "+master.getSampleFloorString()+" dBRMS.\n";
								}
								if (duration > 7200) {
									warnings = warnings+
									"* Your audio file will exceed the ACX duration limit of 120 minutes.\n";
								}
								if (!warnings.isEmpty()) {
									warnings = "\""+file.getPath()+"\"\n\n"+warnings+
									"\nWould you like to encode the audio file anyway?\n";
									int choice = JOptionPane.showConfirmDialog(null, warnings, "Warning", JOptionPane.YES_NO_OPTION);
									if(choice != JOptionPane.YES_OPTION){continue;};
								}

								masterButton.setText("Mastering...");
								master.master(file);
								masterButton.setText("Analyzing mastered audio...");
								master.analyze(null);
								iiTextField.setText(String.valueOf(master.getOI())+" LUFS / "+String.valueOf(master.getRMS())+" dBRMS");
								itpTextField.setText(String.valueOf(master.getOTP())+" dBTP");
								floorTextField.setText(master.getSampleFloorString()+" dBRMS");
							}
							masterButton.setText("Mastering complete!");
						} else {masterButton.setText("Ensure FFmpeg is installed in your path!");}
					}
				}
			).start();
			fileChooserTextField.setText("");
			saveChooserTextField.setText("");
			saveButton.setText("Save...");
			chooseButton.setEnabled(true);
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
	}

	private void openTargetsDialog() {

		final int W = 300;
		final int H = 155;
		final int PAD = 5;

		// Set up the dialog window
		JDialog targetsDialog = new JDialog(jFrame, "Targets", true);
		targetsDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		targetsDialog.getContentPane().setLayout(null);

		// Integrated loudness target label
		JLabel iLabel = new JLabel("Integrated loudness target (LUFS):");
		iLabel.setBounds(PAD, PAD, (W/4)*3-PAD, 25);
		targetsDialog.add(iLabel);

		// Integrated loudness target text field
		JTextField iTextField = new JTextField(String.valueOf(master.getI()));
		iTextField.setBounds((W/4)*3, PAD, (W/4)-PAD, 25);
		targetsDialog.add(iTextField);

		// Loudness range target label
		JLabel lraLabel = new JLabel("Loudness range target (LU):");
		lraLabel.setBounds(PAD, PAD*2+25, (W/4)*3-PAD, 25);
		targetsDialog.add(lraLabel);

		// Loudness range target text field
		JTextField lraTextField = new JTextField(String.valueOf(master.getLRA()));
		lraTextField.setBounds((W/4)*3, PAD*2+25, (W/4)-PAD, 25);
		targetsDialog.add(lraTextField);

		// Maximum true peak label
		JLabel tpLabel = new JLabel("Maximum true peak (dBTP):");
		tpLabel.setBounds(PAD, PAD+(PAD+25)*2, (W/4)*3-PAD, 25);
		targetsDialog.add(tpLabel);

		// Maximum true peak text field
		JTextField tpTextField = new JTextField(String.valueOf(master.getTP()));
		tpTextField.setBounds((W/4)*3, PAD+(PAD+25)*2, (W/4)-PAD, 25);
		targetsDialog.add(tpTextField);

		// Revert to defaults button
		JButton defaults = new JButton("Revert to defaults");
		defaults.setBounds(PAD, H-(25+PAD)*2, W-PAD*2, 25);
		defaults.addActionListener(e -> {
			iTextField.setText("-20.0");
			lraTextField.setText("7.0");
			tpTextField.setText("-3.0");
		});
		targetsDialog.add(defaults);

		// Apply button
		JButton apply = new JButton("Apply");
		apply.setBounds(PAD, H-(25+PAD), W-PAD*2, 25);
		apply.addActionListener(e -> {
			float check;
			check = Float.parseFloat(iTextField.getText());
			if (check < -70 || check > -5) {
				new ErrorDialog("Integrated loudness target must be between -70 LUFS and -5 LUFS!");
				return;
			}
			check = Float.parseFloat(lraTextField.getText());
			if (check < 1 || check > 50) {
				new ErrorDialog("Loudness range target must be between 1 LU and 50 LU!");
				return;
			}
			check = Float.parseFloat(tpTextField.getText());
			if (check < -6 || check > -3) {
				new ErrorDialog("Maximum true peak must be between -6 dBTP and -3 dBTP!");
				return;
			}
			master.setI(Float.parseFloat(iTextField.getText()));
			master.setLRA(Float.parseFloat(lraTextField.getText()));
			master.setTP(Float.parseFloat(tpTextField.getText()));
			targetsDialog.setVisible(false);
			targetsDialog.dispose();
		});
		targetsDialog.add(apply);

		// Finish setting up dialog window and show
		targetsDialog.pack();
		targetsDialog.setResizable(false);
		targetsDialog.setLocationRelativeTo(null);
		targetsDialog.setVisible(true);
	}

	private void openEQDialog() {

		final int W = 910;
		final int H = 245;
		final int PAD = 5;

		// Set up the dialog window
		JDialog eqDialog = new JDialog(jFrame, "Graphic EQ", true);
		eqDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		eqDialog.getContentPane().setLayout(null);

		// Graphic EQ label
		JLabel eqLabel = new JLabel("Graphic EQ (Hz)", SwingConstants.CENTER);
		eqLabel.setBounds(0, PAD, W, 25);
		eqDialog.add(eqLabel);

		// Initialize sliders
		JLabel oneLabel = new JLabel("65");
		JSlider oneSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField oneTextField = new JTextField();
		JLabel twoLabel = new JLabel("92");
		JSlider twoSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField twoTextField = new JTextField();
		JLabel threeLabel = new JLabel("131");
		JSlider threeSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField threeTextField = new JTextField();
		JLabel fourLabel = new JLabel("185");
		JSlider fourSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField fourTextField = new JTextField();
		JLabel fiveLabel = new JLabel("262");
		JSlider fiveSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField fiveTextField = new JTextField();
		JLabel sixLabel = new JLabel("370");
		JSlider sixSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField sixTextField = new JTextField();
		JLabel sevenLabel = new JLabel("523");
		JSlider sevenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField sevenTextField = new JTextField();
		JLabel eightLabel = new JLabel("740");
		JSlider eightSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField eightTextField = new JTextField();
		JLabel nineLabel = new JLabel("1.047k");
		JSlider nineSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField nineTextField = new JTextField();
		JLabel tenLabel = new JLabel("1.480k");
		JSlider tenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField tenTextField = new JTextField();
		JLabel elevenLabel = new JLabel("2.093k");
		JSlider elevenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField elevenTextField = new JTextField();
		JLabel twelveLabel = new JLabel("2.960k");
		JSlider twelveSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField twelveTextField = new JTextField();
		JLabel thirteenLabel = new JLabel("4.186k");
		JSlider thirteenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField thirteenTextField = new JTextField();
		JLabel fourteenLabel = new JLabel("5.920k");
		JSlider fourteenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField fourteenTextField = new JTextField();
		JLabel fifteenLabel = new JLabel("8.372k");
		JSlider fifteenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField fifteenTextField = new JTextField();
		JLabel sixteenLabel = new JLabel("11.840k");
		JSlider sixteenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField sixteenTextField = new JTextField();
		JLabel seventeenLabel = new JLabel("16.744k");
		JSlider seventeenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField seventeenTextField = new JTextField();
		JLabel eighteenLabel = new JLabel("20.000k");
		JSlider eighteenSlider = new JSlider(JSlider.VERTICAL, -15, 15, 0);
		JTextField eighteenTextField = new JTextField();

		// Position sliders
		oneLabel.setBounds(PAD, PAD+25, 50, 25);
		oneSlider.setBounds(PAD, (PAD+25)*2, 50, 150);
		oneTextField.setBounds(PAD*2, PAD+(PAD+25)*2+150, 40, 25);
		twoLabel.setBounds(PAD+50, PAD+25, 50, 25);
		twoSlider.setBounds(PAD+50, (PAD+25)*2, 50, 150);
		twoTextField.setBounds(PAD*2+50,PAD+(PAD+25)*2+150, 40, 25);
		threeLabel.setBounds(PAD+50*2, PAD+25, 50, 25);
		threeSlider.setBounds(PAD+50*2, (PAD+25)*2, 50, 150);
		threeTextField.setBounds((PAD+50)*2, PAD+(PAD+25)*2+150, 40, 25);
		fourLabel.setBounds(PAD+50*3, PAD+25, 50, 25);
		fourSlider.setBounds(PAD+50*3, (PAD+25)*2, 50, 150);
		fourTextField.setBounds(PAD*2+50*3, PAD+(PAD+25)*2+150, 40, 25);
		fiveLabel.setBounds(PAD+50*4, PAD+25, 50, 25);
		fiveSlider.setBounds(PAD+50*4, (PAD+25)*2, 50, 150);
		fiveTextField.setBounds(PAD*2+50*4, PAD+(PAD+25)*2+150, 40, 25);
		sixLabel.setBounds(PAD+50*5, PAD+25, 50, 25);
		sixSlider.setBounds(PAD+50*5, (PAD+25)*2, 50, 150);
		sixTextField.setBounds(PAD*2+50*5, PAD+(PAD+25)*2+150, 40, 25);
		sevenLabel.setBounds(PAD+50*6, PAD+25, 50, 25);
		sevenSlider.setBounds(PAD+50*6, (PAD+25)*2, 50, 150);
		sevenTextField.setBounds(PAD*2+50*6, PAD+(PAD+25)*2+150, 40, 25);
		eightLabel.setBounds(PAD+50*7, PAD+25, 50, 25);
		eightSlider.setBounds(PAD+50*7, (PAD+25)*2, 50, 150);
		eightTextField.setBounds(PAD*2+50*7, PAD+(PAD+25)*2+150, 40, 25);
		nineLabel.setBounds(PAD+50*8, PAD+25, 50, 25);
		nineSlider.setBounds(PAD+50*8, (PAD+25)*2, 50, 150);
		nineTextField.setBounds(PAD*2+50*8, PAD+(PAD+25)*2+150, 40, 25);
		tenLabel.setBounds(PAD+50*9, PAD+25, 50, 25);
		tenSlider.setBounds(PAD+50*9, (PAD+25)*2, 50, 150);
		tenTextField.setBounds(PAD*2+50*9, PAD+(PAD+25)*2+150, 40, 25);
		elevenLabel.setBounds(PAD+50*10, PAD+25, 50, 25);
		elevenSlider.setBounds(PAD+50*10, (PAD+25)*2, 50, 150);
		elevenTextField.setBounds(PAD*2+50*10, PAD+(PAD+25)*2+150, 40, 25);
		twelveLabel.setBounds(PAD+50*11, PAD+25, 50, 25);
		twelveSlider.setBounds(PAD+50*11, (PAD+25)*2, 50, 150);
		twelveTextField.setBounds(PAD*2+50*11, PAD+(PAD+25)*2+150, 40, 25);
		thirteenLabel.setBounds(PAD+50*12, PAD+25, 50, 25);
		thirteenSlider.setBounds(PAD+50*12, (PAD+25)*2, 50, 150);
		thirteenTextField.setBounds(PAD*2+50*12, PAD+(PAD+25)*2+150, 40, 25);
		fourteenLabel.setBounds(PAD+50*13, PAD+25, 50, 25);
		fourteenSlider.setBounds(PAD+50*13, (PAD+25)*2, 50, 150);
		fourteenTextField.setBounds(PAD*2+50*13, PAD+(PAD+25)*2+150, 40, 25);
		fifteenLabel.setBounds(PAD+50*14, PAD+25, 50, 25);
		fifteenSlider.setBounds(PAD+50*14, (PAD+25)*2, 50, 150);
		fifteenTextField.setBounds(PAD*2+50*14, PAD+(PAD+25)*2+150, 40, 25);
		sixteenLabel.setBounds(PAD+50*15, PAD+25, 50, 25);
		sixteenSlider.setBounds(PAD+50*15, (PAD+25)*2, 50, 150);
		sixteenTextField.setBounds(PAD*2+50*15, PAD+(PAD+25)*2+150, 40, 25);
		seventeenLabel.setBounds(PAD+50*16, PAD+25, 50, 25);
		seventeenSlider.setBounds(PAD+50*16, (PAD+25)*2, 50, 150);
		seventeenTextField.setBounds(PAD*2+50*16, PAD+(PAD+25)*2+150, 40, 25);
		eighteenLabel.setBounds(PAD+50*17, PAD+25, 50, 25);
		eighteenSlider.setBounds(PAD+50*17, (PAD+25)*2, 50, 150);
		eighteenTextField.setBounds(PAD*2+50*17, PAD+(PAD+25)*2+150, 40, 25);

		// Add slider labels
		eqDialog.add(oneLabel);
		eqDialog.add(twoLabel);
		eqDialog.add(threeLabel);
		eqDialog.add(fourLabel);
		eqDialog.add(fiveLabel);
		eqDialog.add(sixLabel);
		eqDialog.add(sevenLabel);
		eqDialog.add(eightLabel);
		eqDialog.add(nineLabel);
		eqDialog.add(tenLabel);
		eqDialog.add(elevenLabel);
		eqDialog.add(twelveLabel);
		eqDialog.add(thirteenLabel);
		eqDialog.add(fourteenLabel);
		eqDialog.add(fifteenLabel);
		eqDialog.add(sixteenLabel);
		eqDialog.add(seventeenLabel);
		eqDialog.add(eighteenLabel);

		// Set up sliders
		oneSlider.setMinorTickSpacing(1);
		oneSlider.setMajorTickSpacing(5);
		oneSlider.setSnapToTicks(true);
		oneSlider.setPaintTicks(true);
		oneSlider.setPaintLabels(true);
		oneTextField.setText(String.valueOf(oneSlider.getValue()));
		oneSlider.setValue(Integer.parseInt(master.getOneBand()));
		oneSlider.addChangeListener(e -> {
			String gain = String.valueOf(oneSlider.getValue());
			oneTextField.setText(gain);
			master.setOneBand(gain);
		});
		eqDialog.add(oneSlider);

		twoSlider.setMinorTickSpacing(1);
		twoSlider.setMajorTickSpacing(5);
		twoSlider.setSnapToTicks(true);
		twoSlider.setPaintTicks(true);
		twoSlider.setPaintLabels(true);
		twoTextField.setText(String.valueOf(twoSlider.getValue()));
		twoSlider.setValue(Integer.parseInt(master.getTwoBand()));
		twoSlider.addChangeListener(e -> {
			String gain = String.valueOf(twoSlider.getValue());
			twoTextField.setText(gain);
			master.setTwoBand(gain);
		});
		eqDialog.add(twoSlider);

		threeSlider.setMinorTickSpacing(1);
		threeSlider.setMajorTickSpacing(5);
		threeSlider.setSnapToTicks(true);
		threeSlider.setPaintTicks(true);
		threeSlider.setPaintLabels(true);
		threeTextField.setText(String.valueOf(threeSlider.getValue()));
		threeSlider.setValue(Integer.parseInt(master.getThreeBand()));
		threeSlider.addChangeListener(e -> {
			String gain = String.valueOf(threeSlider.getValue());
			threeTextField.setText(gain);
			master.setThreeBand(gain);
		});
		eqDialog.add(threeSlider);

		fourSlider.setMinorTickSpacing(1);
		fourSlider.setMajorTickSpacing(5);
		fourSlider.setSnapToTicks(true);
		fourSlider.setPaintTicks(true);
		fourSlider.setPaintLabels(true);
		fourTextField.setText(String.valueOf(fourSlider.getValue()));
		fourSlider.setValue(Integer.parseInt(master.getFourBand()));
		fourSlider.addChangeListener(e -> {
			String gain = String.valueOf(fourSlider.getValue());
			fourTextField.setText(gain);
			master.setFourBand(gain);
		});
		eqDialog.add(fourSlider);

		fiveSlider.setMinorTickSpacing(1);
		fiveSlider.setMajorTickSpacing(5);
		fiveSlider.setSnapToTicks(true);
		fiveSlider.setPaintTicks(true);
		fiveSlider.setPaintLabels(true);
		fiveTextField.setText(String.valueOf(fiveSlider.getValue()));
		fiveSlider.setValue(Integer.parseInt(master.getFiveBand()));
		fiveSlider.addChangeListener(e -> {
			String gain = String.valueOf(fiveSlider.getValue());
			fiveTextField.setText(gain);
			master.setFiveBand(gain);
		});
		eqDialog.add(fiveSlider);

		sixSlider.setMinorTickSpacing(1);
		sixSlider.setMajorTickSpacing(5);
		sixSlider.setSnapToTicks(true);
		sixSlider.setPaintTicks(true);
		sixSlider.setPaintLabels(true);
		sixTextField.setText(String.valueOf(sixSlider.getValue()));
		sixSlider.setValue(Integer.parseInt(master.getSixBand()));
		sixSlider.addChangeListener(e -> {
			String gain = String.valueOf(sixSlider.getValue());
			sixTextField.setText(gain);
			master.setSixBand(gain);
		});
		eqDialog.add(sixSlider);

		sevenSlider.setMinorTickSpacing(1);
		sevenSlider.setMajorTickSpacing(5);
		sevenSlider.setSnapToTicks(true);
		sevenSlider.setPaintTicks(true);
		sevenSlider.setPaintLabels(true);
		sevenTextField.setText(String.valueOf(sevenSlider.getValue()));
		sevenSlider.setValue(Integer.parseInt(master.getSevenBand()));
		sevenSlider.addChangeListener(e -> {
			String gain = String.valueOf(sevenSlider.getValue());
			sevenTextField.setText(gain);
			master.setSevenBand(gain);
		});
		eqDialog.add(sevenSlider);

		eightSlider.setMinorTickSpacing(1);
		eightSlider.setMajorTickSpacing(5);
		eightSlider.setSnapToTicks(true);
		eightSlider.setPaintTicks(true);
		eightSlider.setPaintLabels(true);
		eightTextField.setText(String.valueOf(eightSlider.getValue()));
		eightSlider.setValue(Integer.parseInt(master.getEightBand()));
		eightSlider.addChangeListener(e -> {
			String gain = String.valueOf(eightSlider.getValue());
			eightTextField.setText(gain);
			master.setEightBand(gain);
		});
		eqDialog.add(eightSlider);

		nineSlider.setMinorTickSpacing(1);
		nineSlider.setMajorTickSpacing(5);
		nineSlider.setSnapToTicks(true);
		nineSlider.setPaintTicks(true);
		nineSlider.setPaintLabels(true);
		nineTextField.setText(String.valueOf(nineSlider.getValue()));
		nineSlider.setValue(Integer.parseInt(master.getNineBand()));
		nineSlider.addChangeListener(e -> {
			String gain = String.valueOf(nineSlider.getValue());
			nineTextField.setText(gain);
			master.setNineBand(gain);
		});
		eqDialog.add(nineSlider);

		tenSlider.setMinorTickSpacing(1);
		tenSlider.setMajorTickSpacing(5);
		tenSlider.setSnapToTicks(true);
		tenSlider.setPaintTicks(true);
		tenSlider.setPaintLabels(true);
		tenTextField.setText(String.valueOf(tenSlider.getValue()));
		tenSlider.setValue(Integer.parseInt(master.getTenBand()));
		tenSlider.addChangeListener(e -> {
			String gain = String.valueOf(tenSlider.getValue());
			tenTextField.setText(gain);
			master.setTenBand(gain);
		});
		eqDialog.add(tenSlider);

		elevenSlider.setMinorTickSpacing(1);
		elevenSlider.setMajorTickSpacing(5);
		elevenSlider.setSnapToTicks(true);
		elevenSlider.setPaintTicks(true);
		elevenSlider.setPaintLabels(true);
		elevenTextField.setText(String.valueOf(elevenSlider.getValue()));
		elevenSlider.setValue(Integer.parseInt(master.getElevenBand()));
		elevenSlider.addChangeListener(e -> {
			String gain = String.valueOf(elevenSlider.getValue());
			elevenTextField.setText(gain);
			master.setElevenBand(gain);
		});
		eqDialog.add(elevenSlider);

		twelveSlider.setMinorTickSpacing(1);
		twelveSlider.setMajorTickSpacing(5);
		twelveSlider.setSnapToTicks(true);
		twelveSlider.setPaintTicks(true);
		twelveSlider.setPaintLabels(true);
		twelveTextField.setText(String.valueOf(twelveSlider.getValue()));
		twelveSlider.setValue(Integer.parseInt(master.getTwelveBand()));
		twelveSlider.addChangeListener(e -> {
			String gain = String.valueOf(twelveSlider.getValue());
			twelveTextField.setText(gain);
			master.setTwelveBand(gain);
		});
		eqDialog.add(twelveSlider);

		thirteenSlider.setMinorTickSpacing(1);
		thirteenSlider.setMajorTickSpacing(5);
		thirteenSlider.setSnapToTicks(true);
		thirteenSlider.setPaintTicks(true);
		thirteenSlider.setPaintLabels(true);
		thirteenTextField.setText(String.valueOf(thirteenSlider.getValue()));
		thirteenSlider.setValue(Integer.parseInt(master.getThirteenBand()));
		thirteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(thirteenSlider.getValue());
			thirteenTextField.setText(gain);
			master.setThirteenBand(gain);
		});
		eqDialog.add(thirteenSlider);

		fourteenSlider.setMinorTickSpacing(1);
		fourteenSlider.setMajorTickSpacing(5);
		fourteenSlider.setSnapToTicks(true);
		fourteenSlider.setPaintTicks(true);
		fourteenSlider.setPaintLabels(true);
		fourteenTextField.setText(String.valueOf(fourteenSlider.getValue()));
		fourteenSlider.setValue(Integer.parseInt(master.getFourteenBand()));
		fourteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(fourteenSlider.getValue());
			fourteenTextField.setText(gain);
			master.setFourteenBand(gain);
		});
		eqDialog.add(fourteenSlider);

		fifteenSlider.setMinorTickSpacing(1);
		fifteenSlider.setMajorTickSpacing(5);
		fifteenSlider.setSnapToTicks(true);
		fifteenSlider.setPaintTicks(true);
		fifteenSlider.setPaintLabels(true);
		fifteenTextField.setText(String.valueOf(fifteenSlider.getValue()));
		fifteenSlider.setValue(Integer.parseInt(master.getFifteenBand()));
		fifteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(fifteenSlider.getValue());
			fifteenTextField.setText(gain);
			master.setFifteenBand(gain);
		});
		eqDialog.add(fifteenSlider);

		sixteenSlider.setMinorTickSpacing(1);
		sixteenSlider.setMajorTickSpacing(5);
		sixteenSlider.setSnapToTicks(true);
		sixteenSlider.setPaintTicks(true);
		sixteenSlider.setPaintLabels(true);
		sixteenTextField.setText(String.valueOf(sixteenSlider.getValue()));
		sixteenSlider.setValue(Integer.parseInt(master.getSixteenBand()));
		sixteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(sixteenSlider.getValue());
			sixteenTextField.setText(gain);
			master.setSixteenBand(gain);
		});
		eqDialog.add(sixteenSlider);

		seventeenSlider.setMinorTickSpacing(1);
		seventeenSlider.setMajorTickSpacing(5);
		seventeenSlider.setSnapToTicks(true);
		seventeenSlider.setPaintTicks(true);
		seventeenSlider.setPaintLabels(true);
		seventeenTextField.setText(String.valueOf(seventeenSlider.getValue()));
		seventeenSlider.setValue(Integer.parseInt(master.getSeventeenBand()));
		seventeenSlider.addChangeListener(e -> {
			String gain = String.valueOf(seventeenSlider.getValue());
			seventeenTextField.setText(gain);
			master.setSeventeenBand(gain);
		});
		eqDialog.add(seventeenSlider);

		eighteenSlider.setMinorTickSpacing(1);
		eighteenSlider.setMajorTickSpacing(5);
		eighteenSlider.setSnapToTicks(true);
		eighteenSlider.setPaintTicks(true);
		eighteenSlider.setPaintLabels(true);
		eighteenTextField.setText(String.valueOf(eighteenSlider.getValue()));
		eighteenSlider.setValue(Integer.parseInt(master.getEighteenBand()));
		eighteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(eighteenSlider.getValue());
			eighteenTextField.setText(gain);
			master.setEighteenBand(gain);
		});
		eqDialog.add(eighteenSlider);

		// Set up slider text fields
		oneTextField.setBackground(Color.WHITE);
		oneTextField.setText(master.getOneBand());
		oneTextField.addActionListener(e -> {oneSlider.setValue((int)Math.round(Float.valueOf(oneTextField.getText())));});
		eqDialog.add(oneTextField);
		twoTextField.setBackground(Color.WHITE);
		twoTextField.setText(master.getTwoBand());
		twoTextField.addActionListener(e -> {twoSlider.setValue((int)Math.round(Float.valueOf(twoTextField.getText())));});
		eqDialog.add(twoTextField);
		threeTextField.setBackground(Color.WHITE);
		threeTextField.setText(master.getThreeBand());
		threeTextField.addActionListener(e -> {threeSlider.setValue((int)Math.round(Float.valueOf(threeTextField.getText())));});
		eqDialog.add(threeTextField);
		fourTextField.setBackground(Color.WHITE);
		fourTextField.setText(master.getFourBand());
		fourTextField.addActionListener(e -> {fourSlider.setValue((int)Math.round(Float.valueOf(fourTextField.getText())));});
		eqDialog.add(fourTextField);
		fiveTextField.setBackground(Color.WHITE);
		fiveTextField.setText(master.getFiveBand());
		fiveTextField.addActionListener(e -> {fiveSlider.setValue((int)Math.round(Float.valueOf(fiveTextField.getText())));});
		eqDialog.add(fiveTextField);
		sixTextField.setBackground(Color.WHITE);
		sixTextField.setText(master.getSixBand());
		sixTextField.addActionListener(e -> {sixSlider.setValue((int)Math.round(Float.valueOf(sixTextField.getText())));});
		eqDialog.add(sixTextField);
		sevenTextField.setBackground(Color.WHITE);
		sevenTextField.setText(master.getSevenBand());
		sevenTextField.addActionListener(e -> {sevenSlider.setValue((int)Math.round(Float.valueOf(sevenTextField.getText())));});
		eqDialog.add(sevenTextField);
		eightTextField.setBackground(Color.WHITE);
		eightTextField.setText(master.getEightBand());
		eightTextField.addActionListener(e -> {eightSlider.setValue((int)Math.round(Float.valueOf(eightTextField.getText())));});
		eqDialog.add(eightTextField);
		nineTextField.setBackground(Color.WHITE);
		nineTextField.setText(master.getNineBand());
		nineTextField.addActionListener(e -> {nineSlider.setValue((int)Math.round(Float.valueOf(nineTextField.getText())));});
		eqDialog.add(nineTextField);
		tenTextField.setBackground(Color.WHITE);
		tenTextField.setText(master.getTenBand());
		tenTextField.addActionListener(e -> {tenSlider.setValue((int)Math.round(Float.valueOf(tenTextField.getText())));});
		eqDialog.add(tenTextField);
		elevenTextField.setBackground(Color.WHITE);
		elevenTextField.setText(master.getElevenBand());
		elevenTextField.addActionListener(e -> {elevenSlider.setValue((int)Math.round(Float.valueOf(elevenTextField.getText())));});
		eqDialog.add(elevenTextField);
		twelveTextField.setBackground(Color.WHITE);
		twelveTextField.setText(master.getTwelveBand());
		twelveTextField.addActionListener(e -> {twelveSlider.setValue((int)Math.round(Float.valueOf(twelveTextField.getText())));});
		eqDialog.add(twelveTextField);
		thirteenTextField.setBackground(Color.WHITE);
		thirteenTextField.setText(master.getThirteenBand());
		thirteenTextField.addActionListener(e -> {thirteenSlider.setValue((int)Math.round(Float.valueOf(thirteenTextField.getText())));});
		eqDialog.add(thirteenTextField);
		fourteenTextField.setBackground(Color.WHITE);
		fourteenTextField.setText(master.getFourteenBand());
		fourteenTextField.addActionListener(e -> {fourteenSlider.setValue((int)Math.round(Float.valueOf(fourteenTextField.getText())));});
		eqDialog.add(fourteenTextField);
		fifteenTextField.setBackground(Color.WHITE);
		fifteenTextField.setText(master.getFifteenBand());
		fifteenTextField.addActionListener(e -> {fifteenSlider.setValue((int)Math.round(Float.valueOf(fifteenTextField.getText())));});
		eqDialog.add(fifteenTextField);
		sixteenTextField.setBackground(Color.WHITE);
		sixteenTextField.setText(master.getSixteenBand());
		sixteenTextField.addActionListener(e -> {sixteenSlider.setValue((int)Math.round(Float.valueOf(sixteenTextField.getText())));});
		eqDialog.add(sixteenTextField);
		seventeenTextField.setBackground(Color.WHITE);
		seventeenTextField.setText(master.getSeventeenBand());
		seventeenTextField.addActionListener(e -> {seventeenSlider.setValue((int)Math.round(Float.valueOf(seventeenTextField.getText())));});
		eqDialog.add(seventeenTextField);
		eighteenTextField.setBackground(Color.WHITE);
		eighteenTextField.setText(master.getEighteenBand());
		eighteenTextField.addActionListener(e -> {eighteenSlider.setValue((int)Math.round(Float.valueOf(eighteenTextField.getText())));});
		eqDialog.add(eighteenTextField);

		// Finish setting up dialog window and show
		eqDialog.pack();
		eqDialog.setResizable(false);
		eqDialog.setLocationRelativeTo(null);
		eqDialog.setVisible(true);
	}

	private void openOptionsDialog() {

		final int W = 275;
		final int H = 90;
		final int PAD = 5;

		// Set up the dialog window
		JDialog optionsDialog = new JDialog(jFrame, "Additional options", true);
		optionsDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		optionsDialog.getContentPane().setLayout(null);

		// Supress noise checkbox
		JCheckBox rnnnCheckbox = new JCheckBox("Supress noise");
		rnnnCheckbox.setBounds(PAD, PAD, (W/2)-PAD, 25);
		if (!master.getRnnn().isEmpty()) {rnnnCheckbox.setSelected(true);}
		rnnnCheckbox.addActionListener(e -> {master.rnnn(rnnnCheckbox.isSelected());});
		optionsDialog.add(rnnnCheckbox);

		// Output in stereo checkbox
		JCheckBox stereoCheckbox = new JCheckBox("Output in stereo");
		stereoCheckbox.setBounds(W/2, PAD, (W/2)-PAD, 25);
		if (master.getStereo()) {stereoCheckbox.setSelected(true);}
		stereoCheckbox.addActionListener(e -> {master.setStereo(stereoCheckbox.isSelected());});
		optionsDialog.add(stereoCheckbox);

		// De-click checkbox
		JCheckBox declickCheckbox = new JCheckBox("De-click");
		declickCheckbox.setBounds(PAD, PAD+25, (W/2)-PAD, 25);
		if (!master.getDeclick().isEmpty()) {declickCheckbox.setSelected(true);}
		declickCheckbox.addActionListener(e -> {master.declick(declickCheckbox.isSelected());});
		optionsDialog.add(declickCheckbox);

		// Noise gate checkbox
		JCheckBox gateCheckbox = new JCheckBox("Noise gate");
		gateCheckbox.setBounds(W/2, PAD+25, (W/2)-PAD, 25);
		if (!master.getGate().isEmpty()) {gateCheckbox.setSelected(true);}
		gateCheckbox.addActionListener(e -> {master.gate(gateCheckbox.isSelected());});
		optionsDialog.add(gateCheckbox);

		// Generate noise checkbox
		JCheckBox noiseCheckbox = new JCheckBox("Generate noise");
		noiseCheckbox.setBounds(PAD, (PAD+25)*2, (W/2)-PAD, 25);
		if (master.getNoise()) {noiseCheckbox.setSelected(true);}
		noiseCheckbox.addActionListener(e -> {master.setNoise(noiseCheckbox.isSelected());});
		optionsDialog.add(noiseCheckbox);

		// Finish setting up dialog window and show
		optionsDialog.pack();
		optionsDialog.setResizable(false);
		optionsDialog.setLocationRelativeTo(null);
		optionsDialog.setVisible(true);
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