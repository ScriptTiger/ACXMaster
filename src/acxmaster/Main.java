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

		final int W = 910;
		final int H = 490;
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
					"gate="+master.getGate()
				);
				writer.close();
			} catch (Exception err) {}
		});
		settings.add(saveSettingsItem);

		// Graphic EQ label
		JLabel eqLabel = new JLabel("Graphic EQ (Hz)", SwingConstants.CENTER);
		eqLabel.setBounds(0, PAD+25, W, 25);
		add(eqLabel);

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
		oneLabel.setBounds(PAD, (PAD+25)*2, 50, 25);
		oneSlider.setBounds(PAD, (PAD+25)*3, 50, 150);
		oneTextField.setBounds(PAD*2, H-25*8-PAD*8, 40, 25);
		twoLabel.setBounds(PAD+50, (PAD+25)*2, 50, 25);
		twoSlider.setBounds(PAD+50, (PAD+25)*3, 50, 150);
		twoTextField.setBounds(PAD*2+50, H-25*8-PAD*8, 40, 25);
		threeLabel.setBounds(PAD+50*2, (PAD+25)*2, 50, 25);
		threeSlider.setBounds(PAD+50*2, (PAD+25)*3, 50, 150);
		threeTextField.setBounds((PAD+50)*2, H-25*8-PAD*8, 40, 25);
		fourLabel.setBounds(PAD+50*3, (PAD+25)*2, 50, 25);
		fourSlider.setBounds(PAD+50*3, (PAD+25)*3, 50, 150);
		fourTextField.setBounds(PAD*2+50*3, H-25*8-PAD*8, 40, 25);
		fiveLabel.setBounds(PAD+50*4, (PAD+25)*2, 50, 25);
		fiveSlider.setBounds(PAD+50*4, (PAD+25)*3, 50, 150);
		fiveTextField.setBounds(PAD*2+50*4, H-25*8-PAD*8, 40, 25);
		sixLabel.setBounds(PAD+50*5, (PAD+25)*2, 50, 25);
		sixSlider.setBounds(PAD+50*5, (PAD+25)*3, 50, 150);
		sixTextField.setBounds(PAD*2+50*5, H-25*8-PAD*8, 40, 25);
		sevenLabel.setBounds(PAD+50*6, (PAD+25)*2, 50, 25);
		sevenSlider.setBounds(PAD+50*6, (PAD+25)*3, 50, 150);
		sevenTextField.setBounds(PAD*2+50*6, H-25*8-PAD*8, 40, 25);
		eightLabel.setBounds(PAD+50*7, (PAD+25)*2, 50, 25);
		eightSlider.setBounds(PAD+50*7, (PAD+25)*3, 50, 150);
		eightTextField.setBounds(PAD*2+50*7, H-25*8-PAD*8, 40, 25);
		nineLabel.setBounds(PAD+50*8, (PAD+25)*2, 50, 25);
		nineSlider.setBounds(PAD+50*8, (PAD+25)*3, 50, 150);
		nineTextField.setBounds(PAD*2+50*8, H-25*8-PAD*8, 40, 25);
		tenLabel.setBounds(PAD+50*9, (PAD+25)*2, 50, 25);
		tenSlider.setBounds(PAD+50*9, (PAD+25)*3, 50, 150);
		tenTextField.setBounds(PAD*2+50*9, H-25*8-PAD*8, 40, 25);
		elevenLabel.setBounds(PAD+50*10, (PAD+25)*2, 50, 25);
		elevenSlider.setBounds(PAD+50*10, (PAD+25)*3, 50, 150);
		elevenTextField.setBounds(PAD*2+50*10, H-25*8-PAD*8, 40, 25);
		twelveLabel.setBounds(PAD+50*11, (PAD+25)*2, 50, 25);
		twelveSlider.setBounds(PAD+50*11, (PAD+25)*3, 50, 150);
		twelveTextField.setBounds(PAD*2+50*11, H-25*8-PAD*8, 40, 25);
		thirteenLabel.setBounds(PAD+50*12, (PAD+25)*2, 50, 25);
		thirteenSlider.setBounds(PAD+50*12, (PAD+25)*3, 50, 150);
		thirteenTextField.setBounds(PAD*2+50*12, H-25*8-PAD*8, 40, 25);
		fourteenLabel.setBounds(PAD+50*13, (PAD+25)*2, 50, 25);
		fourteenSlider.setBounds(PAD+50*13, (PAD+25)*3, 50, 150);
		fourteenTextField.setBounds(PAD*2+50*13, H-25*8-PAD*8, 40, 25);
		fifteenLabel.setBounds(PAD+50*14, (PAD+25)*2, 50, 25);
		fifteenSlider.setBounds(PAD+50*14, (PAD+25)*3, 50, 150);
		fifteenTextField.setBounds(PAD*2+50*14, H-25*8-PAD*8, 40, 25);
		sixteenLabel.setBounds(PAD+50*15, (PAD+25)*2, 50, 25);
		sixteenSlider.setBounds(PAD+50*15, (PAD+25)*3, 50, 150);
		sixteenTextField.setBounds(PAD*2+50*15, H-25*8-PAD*8, 40, 25);
		seventeenLabel.setBounds(PAD+50*16, (PAD+25)*2, 50, 25);
		seventeenSlider.setBounds(PAD+50*16, (PAD+25)*3, 50, 150);
		seventeenTextField.setBounds(PAD*2+50*16, H-25*8-PAD*8, 40, 25);
		eighteenLabel.setBounds(PAD+50*17, (PAD+25)*2, 50, 25);
		eighteenSlider.setBounds(PAD+50*17, (PAD+25)*3, 50, 150);
		eighteenTextField.setBounds(PAD*2+50*17, H-25*8-PAD*8, 40, 25);

		// Add slider labels
		add(oneLabel);
		add(twoLabel);
		add(threeLabel);
		add(fourLabel);
		add(fiveLabel);
		add(sixLabel);
		add(sevenLabel);
		add(eightLabel);
		add(nineLabel);
		add(tenLabel);
		add(elevenLabel);
		add(twelveLabel);
		add(thirteenLabel);
		add(fourteenLabel);
		add(fifteenLabel);
		add(sixteenLabel);
		add(seventeenLabel);
		add(eighteenLabel);

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
		add(oneSlider);

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
		add(twoSlider);

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
		add(threeSlider);

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
		add(fourSlider);

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
		add(fiveSlider);

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
		add(sixSlider);

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
		add(sevenSlider);

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
		add(eightSlider);

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
		add(nineSlider);

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
		add(tenSlider);

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
		add(elevenSlider);

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
		add(twelveSlider);

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
		add(thirteenSlider);

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
		add(fourteenSlider);

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
		add(fifteenSlider);

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
		add(sixteenSlider);

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
		add(seventeenSlider);

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
		add(eighteenSlider);

		// Set up slider text fields
		oneTextField.setBackground(Color.WHITE);
		oneTextField.setText(master.getOneBand());
		oneTextField.addActionListener(e -> {oneSlider.setValue((int)Math.round(Double.valueOf(oneTextField.getText())));});
		add(oneTextField);
		twoTextField.setBackground(Color.WHITE);
		twoTextField.setText(master.getTwoBand());
		twoTextField.addActionListener(e -> {twoSlider.setValue((int)Math.round(Double.valueOf(twoTextField.getText())));});
		add(twoTextField);
		threeTextField.setBackground(Color.WHITE);
		threeTextField.setText(master.getThreeBand());
		threeTextField.addActionListener(e -> {threeSlider.setValue((int)Math.round(Double.valueOf(threeTextField.getText())));});
		add(threeTextField);
		fourTextField.setBackground(Color.WHITE);
		fourTextField.setText(master.getFourBand());
		fourTextField.addActionListener(e -> {fourSlider.setValue((int)Math.round(Double.valueOf(fourTextField.getText())));});
		add(fourTextField);
		fiveTextField.setBackground(Color.WHITE);
		fiveTextField.setText(master.getFiveBand());
		fiveTextField.addActionListener(e -> {fiveSlider.setValue((int)Math.round(Double.valueOf(fiveTextField.getText())));});
		add(fiveTextField);
		sixTextField.setBackground(Color.WHITE);
		sixTextField.setText(master.getSixBand());
		sixTextField.addActionListener(e -> {sixSlider.setValue((int)Math.round(Double.valueOf(sixTextField.getText())));});
		add(sixTextField);
		sevenTextField.setBackground(Color.WHITE);
		sevenTextField.setText(master.getSevenBand());
		sevenTextField.addActionListener(e -> {sevenSlider.setValue((int)Math.round(Double.valueOf(sevenTextField.getText())));});
		add(sevenTextField);
		eightTextField.setBackground(Color.WHITE);
		eightTextField.setText(master.getEightBand());
		eightTextField.addActionListener(e -> {eightSlider.setValue((int)Math.round(Double.valueOf(eightTextField.getText())));});
		add(eightTextField);
		nineTextField.setBackground(Color.WHITE);
		nineTextField.setText(master.getNineBand());
		nineTextField.addActionListener(e -> {nineSlider.setValue((int)Math.round(Double.valueOf(nineTextField.getText())));});
		add(nineTextField);
		tenTextField.setBackground(Color.WHITE);
		tenTextField.setText(master.getTenBand());
		tenTextField.addActionListener(e -> {tenSlider.setValue((int)Math.round(Double.valueOf(tenTextField.getText())));});
		add(tenTextField);
		elevenTextField.setBackground(Color.WHITE);
		elevenTextField.setText(master.getElevenBand());
		elevenTextField.addActionListener(e -> {elevenSlider.setValue((int)Math.round(Double.valueOf(elevenTextField.getText())));});
		add(elevenTextField);
		twelveTextField.setBackground(Color.WHITE);
		twelveTextField.setText(master.getTwelveBand());
		twelveTextField.addActionListener(e -> {twelveSlider.setValue((int)Math.round(Double.valueOf(twelveTextField.getText())));});
		add(twelveTextField);
		thirteenTextField.setBackground(Color.WHITE);
		thirteenTextField.setText(master.getThirteenBand());
		thirteenTextField.addActionListener(e -> {thirteenSlider.setValue((int)Math.round(Double.valueOf(thirteenTextField.getText())));});
		add(thirteenTextField);
		fourteenTextField.setBackground(Color.WHITE);
		fourteenTextField.setText(master.getFourteenBand());
		fourteenTextField.addActionListener(e -> {fourteenSlider.setValue((int)Math.round(Double.valueOf(fourteenTextField.getText())));});
		add(fourteenTextField);
		fifteenTextField.setBackground(Color.WHITE);
		fifteenTextField.setText(master.getFifteenBand());
		fifteenTextField.addActionListener(e -> {fifteenSlider.setValue((int)Math.round(Double.valueOf(fifteenTextField.getText())));});
		add(fifteenTextField);
		sixteenTextField.setBackground(Color.WHITE);
		sixteenTextField.setText(master.getSixteenBand());
		sixteenTextField.addActionListener(e -> {sixteenSlider.setValue((int)Math.round(Double.valueOf(sixteenTextField.getText())));});
		add(sixteenTextField);
		seventeenTextField.setBackground(Color.WHITE);
		seventeenTextField.setText(master.getSeventeenBand());
		seventeenTextField.addActionListener(e -> {seventeenSlider.setValue((int)Math.round(Double.valueOf(seventeenTextField.getText())));});
		add(seventeenTextField);
		eighteenTextField.setBackground(Color.WHITE);
		eighteenTextField.setText(master.getEighteenBand());
		eighteenTextField.addActionListener(e -> {eighteenSlider.setValue((int)Math.round(Double.valueOf(eighteenTextField.getText())));});
		add(eighteenTextField);

		// Noise suppression checkbox
		JCheckBox rnnnCheckbox = new JCheckBox("Noise suppression");
		rnnnCheckbox.setBounds(W/10, H-25*7-PAD*7, (W/5)-PAD, 25);
		if (!master.getRnnn().isEmpty()) {rnnnCheckbox.setSelected(true);}
		rnnnCheckbox.addActionListener(e -> {master.rnnn(rnnnCheckbox.isSelected());});
		add(rnnnCheckbox);

		// Stereo checkbox
		JCheckBox stereoCheckbox = new JCheckBox("Stereo");
		stereoCheckbox.setBounds((W/10)*4, H-25*7-PAD*7, (W/5)-PAD, 25);
		if (master.getStereo()) {stereoCheckbox.setSelected(true);}
		stereoCheckbox.addActionListener(e -> {master.setStereo(stereoCheckbox.isSelected());});
		add(stereoCheckbox);

		// Declick checkbox
		JCheckBox declickCheckbox = new JCheckBox("Declick");
		declickCheckbox.setBounds((W/10)*6, H-25*7-PAD*7, (W/5)-PAD, 25);
		if (!master.getDeclick().isEmpty()) {declickCheckbox.setSelected(true);}
		declickCheckbox.addActionListener(e -> {master.declick(declickCheckbox.isSelected());});
		add(declickCheckbox);

		// Gate checkbox
		JCheckBox gateCheckbox = new JCheckBox("Noise gate");
		gateCheckbox.setBounds((W/10)*8, H-25*7-PAD*7, (W/5)-PAD, 25);
		if (!master.getGate().isEmpty()) {gateCheckbox.setSelected(true);}
		gateCheckbox.addActionListener(e -> {master.gate(gateCheckbox.isSelected());});
		add(gateCheckbox);

		// Initialize buttons
		JButton chooseButton = new JButton("Choose audio files...");
		JTextField fileChooserTextField = new JTextField();
		JButton saveButton = new JButton("Save...");
		JTextField saveChooserTextField = new JTextField();
		JButton masterButton = new JButton("Master!");
		JLabel iiLabel = new JLabel("Integrated loudness:");
		JTextField iiTextField = new JTextField();
		JLabel itpLabel = new JLabel("True peak:");
		JTextField itpTextField = new JTextField();

		// Position buttons
		chooseButton.setBounds(PAD, H-25*6-PAD*6, W-PAD*2, 25);
		fileChooserTextField.setBounds(PAD, H-25*5-PAD*5, W-PAD*2, 25);
		saveButton.setBounds(PAD, H-25*4-PAD*4, W-PAD*2, 25);
		saveChooserTextField.setBounds(PAD, H-25*3-PAD*3, W-PAD*2, 25);
		masterButton.setBounds(PAD,H-25*2-PAD*2, W-PAD*2, 25);
		iiLabel.setBounds(PAD, H-25-PAD, W/6, 25);
		iiTextField.setBounds(W/6, H-25-PAD, (W/6)-PAD, 25);
		itpLabel.setBounds((W/6)*4, H-25-PAD, W/6, 25);
		itpTextField.setBounds((W/6)*5, H-25-PAD, W/6, 25);

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
			Integer fileCount = master.getFileCount();
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
			masterButton.setEnabled(false);
			chooseButton.setEnabled(false);
			saveButton.setEnabled(false);
			new Thread(
				new Runnable() {
					public void run() {
						if (master.check()) {
							for (File file : master.getFiles()) {
								masterButton.setText("Analyzing source audio...");
								master.analyze(file);
								masterButton.setText("Mastering...");
								master.master(file);
								masterButton.setText("Analyzing mastered audio...");
								master.analyze(null);
								iiTextField.setText(master.getII()+" LUFS");
								itpTextField.setText(master.getITP()+" dBTP");
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

		// Add labels for integrated loudness and true peak
		add(iiLabel);
		add(itpLabel);

		// Set up loudness text field
		iiTextField.setEditable(false);
		iiTextField.setBackground(Color.WHITE);
		iiTextField.setHorizontalAlignment(JLabel.CENTER);
		add(iiTextField);

		// Set up true peak text field
		itpTextField.setEditable(false);
		itpTextField.setBackground(Color.WHITE);
		itpTextField.setHorizontalAlignment(JLabel.CENTER);
		add(itpTextField);
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

	//////////////////
	// Class variables
	//////////////////

	// Files
	private static File[] files;
	private static Boolean isSingle;
	private static File save;
	private static String saveString;

	// 18-band EQ
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

	// Loudnorm stats
	private static String ii;
	private static String itp;
	private static String ilra;
	private static String it;
	private static String to;

	// Additional filters
	private static String rnnn = "";
	private static String gate = "";
	private static String declick = "";
	private static Boolean stereo = false;

	//////////
	// Getters
	//////////

	// Files
	public File[] getFiles() {return files;}
	public Integer getFileCount() {return files.length;}
	public Boolean isSingle() {return isSingle;}

	// 18-band EQ
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

	// Loudnorm stats
	public String getII() {return ii;}
	public String getITP() {return itp;}

	// Additional filters
	public String getRnnn() {return rnnn;}
	public String getGate() {return gate;}
	public String getDeclick() {return declick;}
	public Boolean getStereo() {return stereo;}

	//////////
	// Setters
	//////////

	// Files
	public void setFiles(File[] files) {this.files = files;}
	public void setIsSingle(Boolean isSingle) {this.isSingle = isSingle;}
	public void setSave(File save) {this.save = save;}
	private void setSaveString(File file) {
		if (isSingle) {
			saveString = save.getPath();
			return;
		}
		File saveFile;
		String base;
		String[] elements = file.getName().split("\\.");
		Integer count = elements.length;
		if (count > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < count-1; i++) {
				stringBuilder.append(elements[i]);
				if (i+1 < count-1) {stringBuilder.append(".");}
			}
			base = stringBuilder.toString();
		} else {base = file.getName();}
		saveFile = save.toPath().resolve(base+".mp3").toFile();
		for (int i = 2;; i++) {
			if (!saveFile.exists()) {break;}
			saveFile = save.toPath().resolve(base+" ("+String.valueOf(i)+").mp3").toFile();
		}
		saveString = saveFile.getPath();
	}

	// 18-band EQ
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

	// Additional filters
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

	//////////////////////////
	// Primary class functions
	//////////////////////////

	// Function to check ffmpeg
	public Boolean check() {
		Boolean err = true;
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-version"};
			Process process = runtime.exec(ffmpeg);
			process.waitFor();
		} catch (Exception exception) {
			new ErrorDialog(exception.getMessage());
			err = false;
		}
		return err;
	}

	// Function to analyze files
	public void analyze(File file) {
		Boolean post = false;
		if (file == null) {post = true;}
		String filters = "";
		String layout = "mono";
		String fileString;
		if (post) {
			fileString = saveString;
			if (stereo) {layout = "stereo";}
		} else {
			setSaveString(file);
			fileString = file.getPath();
			filters = rnnn+gate+declick+
				"superequalizer="+
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
				eighteenBand+"dB,";
		}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-i", fileString, "-vn", "-sn", "-dn", "-af", "aformat=cl="+layout+","+filters+"loudnorm=print_format=summary", "-f", "null", ""};
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
		} catch (Exception exception) {}
	}

	// Function to master files
	public void master(File file) {
		String fileString = file.getPath();
		String filters = rnnn+gate+declick+
			"superequalizer="+
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
			eighteenBand+"dB,";
		String splitAndMerge = "";
		if (stereo) {splitAndMerge = ",asplit,amerge";}
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] ffmpeg = {"ffmpeg", "-hide_banner", "-y", "-i", fileString, "-vn", "-sn", "-dn", "-filter_complex", "aformat=cl=mono,"+filters+"loudnorm=i=-20.0:tp=-3:measured_I="+ii+":measured_LRA="+ilra+":measured_tp="+itp+":measured_thresh="+it+":offset="+to+splitAndMerge, "-ar", "44.1k", "-ab", "192k", "-f", "mp3", saveString};
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