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
public class MainDialog extends JPanel {
	private static JFrame jFrame;
	private String lastOpenDirectory = getLastDirPath();

	// Main controller constructor
	public MainDialog() {

		// Singletons
		final Mode mode = new Mode();
		final Master master = new Master();
		final GraphicEQ graphicEQ = master.getGraphicEQ();
		final Targets targets = master.getTargets();
		final Export export = master.getExport();
		final Options options = master.getOptions();

		// Set dimensions and padding
		final int W = 400;
		final int H = 270;
		final int PAD = 5;

		// Store look and feels
		LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
		String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();

		// JPanel properties
		setPreferredSize(new Dimension(W, H));
		setLayout(null);

		// Read and load settings from acxmaster.conf file if it exists
		new Settings(false, mode, master, graphicEQ, options, targets, export);

		// Set up menu bar
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.setBounds(0, 0, W, 25);
		add(jMenuBar);

		// Set up settings menu
		JMenu settings = new JMenu("Settings");
		jMenuBar.add(settings);

		// Initialize mode menu item
		JMenuItem modeItem = new JMenuItem("Mode");
		modeItem.addActionListener(e -> {
			Boolean previousMode = mode.getMode();
			new ModeDialog(jFrame, mode);
			if (!previousMode.equals(mode.getMode())) {mode.paintMode();}
		});
		settings.add(modeItem);

		// Set up FFmpeg menu item
		JMenuItem ffmpegItem = new JMenuItem("FFmpeg");
		ffmpegItem.addActionListener(e -> {
			JFileChooser ffmpegChooser = null;
			try {
				UIManager.setLookAndFeel(systemLookAndFeel);
				ffmpegChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			ffmpegChooser.showDialog(jFrame, "Choose FFmpeg executable...");
			master.setFFmpegPath(ffmpegChooser.getSelectedFile().getPath());
		});
		settings.add(ffmpegItem);

		// Set up Targets menu item
		JMenuItem targetsItem = new JMenuItem("Targets");
		targetsItem.addActionListener(e -> new TargetsDialog(jFrame, targets));
		settings.add(targetsItem);

		// Set up Graphic EQ menu item
		JMenuItem eqItem = new JMenuItem("Graphic EQ");
		eqItem.addActionListener(e -> new GraphicEQDialog(jFrame, graphicEQ));
		settings.add(eqItem);

		// Initialize Export Options menu item
		JMenuItem exportItem = new JMenuItem("Export Options");
		settings.add(exportItem);

		// Set up Additional options menu item
		JMenuItem optionsItem = new JMenuItem("Additional options");
		optionsItem.addActionListener(e -> new OptionsDialog(jFrame, options));
		settings.add(optionsItem);

		// Set up Save Settings menu item
		JMenuItem saveSettingsItem = new JMenuItem("Save Settings");
		saveSettingsItem.addActionListener(e -> new Settings(true, mode, master, graphicEQ, options, targets, export));
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
          try {
            UIManager.setLookAndFeel(systemLookAndFeel);
            JFileChooser fileChooser = new JFileChooser();
            if (!lastOpenDirectory.isEmpty()) {
              fileChooser.setCurrentDirectory(new File(lastOpenDirectory));
            }
            fileChooser.setMultiSelectionEnabled(true);
            UIManager.setLookAndFeel(lookAndFeel);

            int result = fileChooser.showDialog(jFrame, "Choose audio files...");
            if (result == JFileChooser.APPROVE_OPTION) {
              File[] selectedFiles = fileChooser.getSelectedFiles();
              if (selectedFiles != null && selectedFiles.length > 0) {
                master.setFiles(selectedFiles);
                lastOpenDirectory = selectedFiles[0].getParent();
                saveLastDirPath(lastOpenDirectory);

                int fileCount = selectedFiles.length;
                if (fileCount == 1) {
                  fileChooserTextField.setText(selectedFiles[0].getPath());
                  saveButton.setText("Save as...");
                  master.setIsSingle(true);
                } else {
                  fileChooserTextField.setText(fileCount + " files selected");
                  saveButton.setText("Save to...");
                  master.setIsSingle(false);
                }
                saveButton.setEnabled(true);
                master.setSaveFile(null);
                saveChooserTextField.setText("");
                if (mode.getMode()) {
                  masterButton.setEnabled(false);
                } else {
                  masterButton.setText("Check!");
                  masterButton.setEnabled(true);
                }
              }
            }
          } catch (Exception err) {
            // Handle exception if needed
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
				UIManager.setLookAndFeel(systemLookAndFeel);
				saveChooser = new JFileChooser();
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (Exception err) {};
			String saveButtonText;
			if (master.isSingle()) {
				FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(export.getExtension()+" file", export.getExtension());
				saveChooser.addChoosableFileFilter(fileNameExtensionFilter);
				saveButtonText = "Save as...";				
			} else {
				saveChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				saveButtonText = "Save to...";
			}
			if (!export.getCurrentDirectory().isEmpty()) {saveChooser.setCurrentDirectory(new File(export.getCurrentDirectory()));}
			saveChooser.setAcceptAllFileFilterUsed(false);
			saveChooser.showDialog(jFrame, saveButtonText);
			File saveFile = saveChooser.getSelectedFile();
			if (master.isSingle()) {
				if (!saveFile.getName().toLowerCase().endsWith("."+export.getExtension())) {
					saveFile = saveFile.getParentFile().toPath().resolve(saveFile.getName()+"."+export.getExtension()).toFile();
				}
				export.setCurrentDirectory(saveFile.getParentFile().getPath());
			} else {export.setCurrentDirectory(saveFile.getPath());}
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
			ffmpegItem.setEnabled(false);
			targetsItem.setEnabled(false);
			eqItem.setEnabled(false);
			exportItem.setEnabled(false);
			optionsItem.setEnabled(false);
			new Thread(
				new Runnable() {
					public void run() {
						if (master.ffCheck()) {
							for (File file : master.getFiles()) {
								if (mode.getMode()) {
									masterButton.setText("Analyzing source audio...");
									master.analyze(file);
								} else {
									masterButton.setText("Checking audio for problems...");
									master.check(file);
								}
								AudioInfo audioInfo = master.getAudioInfo();
								if (!mode.getMode() || !options.getNoWarn()) {
									if (mode.getMode()) {
										masterButton.setText("Predicting problems...");
										master.predict(file);
									}
									if ((new Check(audioInfo, mode.getMode(), file.getPath(), master.isSameLayout())).getSkip()) {continue;}
								}
								String rmsString = String.valueOf((float)Math.round((float)audioInfo.getRMS()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1));
								String floorString;
								if (audioInfo.getOverallFloor() == 0) {floorString = audioInfo.getOverallFloorString();
								} else if (audioInfo.getSampleFloor() == 0) {floorString = audioInfo.getSampleFloorString();
								} else {floorString = String.valueOf((float)Math.round((float)audioInfo.getSampleFloor()*(float)Math.pow(10, 1))/(float)Math.pow(10, 1));}
								if (mode.getMode()) {
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
							if (mode.getMode()) {masterButton.setText("Mastering complete!");
							} else {masterButton.setText("Checking complete!");}
						} else {masterButton.setText("Ensure the path to FFmpeg is set correctly!");}
						fileChooserTextField.setText("");
						saveChooserTextField.setText("");
						saveButton.setText("Save...");
						chooseButton.setEnabled(true);
						modeItem.setEnabled(true);
						ffmpegItem.setEnabled(true);
						if (mode.getMode()) {
							targetsItem.setEnabled(true);
							eqItem.setEnabled(true);
							exportItem.setEnabled(true);
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

		// Set up Export Options menu item action listener
		exportItem.addActionListener(e -> {
			Boolean reset = new ExportDialog(jFrame, export).getReset();
			if (reset && master.isSingle()) {
				saveChooserTextField.setText("");
				masterButton.setEnabled(false);
			}
		});

		// Initialize mode and paint
		mode.initMode(
			targetsItem,
			eqItem,
			exportItem,
			optionsItem,
			saveButton,
			saveChooserTextField,
			masterButton,
			fileChooserTextField
		);
		mode.paintMode();
	}

	private String getLastDirPath() {
		File file = new File(System.getProperty("user.home"), ".acxMasterConfig.txt");
		if (file.exists()) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				return reader.readLine();
			} catch (IOException e) {}
		}
		return "";
	}

	private void saveLastDirPath(String path) {
		File file = new File(System.getProperty("user.home"), ".acxMasterConfig.txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(path);
		} catch (IOException e) {}
	}
}
