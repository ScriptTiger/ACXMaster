package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Graphic EQ controller class
class GraphicEQDialog {
	public GraphicEQDialog(JFrame jFrame, GraphicEQ graphicEQ) {

		// Set dimensions and padding
		final int W = 910;
		final int H = 245;
		final int PAD = 5;

		// Set up the dialog window
		JDialog jDialog = new JDialog(jFrame, "Graphic EQ", true);
		jDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		jDialog.getContentPane().setLayout(null);

		// Graphic EQ label
		JLabel eqLabel = new JLabel("Graphic EQ (Hz)", SwingConstants.CENTER);
		eqLabel.setBounds(0, PAD, W, 25);
		jDialog.add(eqLabel);

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
		jDialog.add(oneLabel);
		jDialog.add(twoLabel);
		jDialog.add(threeLabel);
		jDialog.add(fourLabel);
		jDialog.add(fiveLabel);
		jDialog.add(sixLabel);
		jDialog.add(sevenLabel);
		jDialog.add(eightLabel);
		jDialog.add(nineLabel);
		jDialog.add(tenLabel);
		jDialog.add(elevenLabel);
		jDialog.add(twelveLabel);
		jDialog.add(thirteenLabel);
		jDialog.add(fourteenLabel);
		jDialog.add(fifteenLabel);
		jDialog.add(sixteenLabel);
		jDialog.add(seventeenLabel);
		jDialog.add(eighteenLabel);

		// Set up sliders
		oneSlider.setMinorTickSpacing(1);
		oneSlider.setMajorTickSpacing(5);
		oneSlider.setSnapToTicks(true);
		oneSlider.setPaintTicks(true);
		oneSlider.setPaintLabels(true);
		oneTextField.setText(String.valueOf(oneSlider.getValue()));
		oneSlider.setValue(Integer.parseInt(graphicEQ.getOneBand()));
		oneSlider.addChangeListener(e -> {
			String gain = String.valueOf(oneSlider.getValue());
			oneTextField.setText(gain);
			graphicEQ.setOneBand(gain);
		});
		jDialog.add(oneSlider);

		twoSlider.setMinorTickSpacing(1);
		twoSlider.setMajorTickSpacing(5);
		twoSlider.setSnapToTicks(true);
		twoSlider.setPaintTicks(true);
		twoSlider.setPaintLabels(true);
		twoTextField.setText(String.valueOf(twoSlider.getValue()));
		twoSlider.setValue(Integer.parseInt(graphicEQ.getTwoBand()));
		twoSlider.addChangeListener(e -> {
			String gain = String.valueOf(twoSlider.getValue());
			twoTextField.setText(gain);
			graphicEQ.setTwoBand(gain);
		});
		jDialog.add(twoSlider);

		threeSlider.setMinorTickSpacing(1);
		threeSlider.setMajorTickSpacing(5);
		threeSlider.setSnapToTicks(true);
		threeSlider.setPaintTicks(true);
		threeSlider.setPaintLabels(true);
		threeTextField.setText(String.valueOf(threeSlider.getValue()));
		threeSlider.setValue(Integer.parseInt(graphicEQ.getThreeBand()));
		threeSlider.addChangeListener(e -> {
			String gain = String.valueOf(threeSlider.getValue());
			threeTextField.setText(gain);
			graphicEQ.setThreeBand(gain);
		});
		jDialog.add(threeSlider);

		fourSlider.setMinorTickSpacing(1);
		fourSlider.setMajorTickSpacing(5);
		fourSlider.setSnapToTicks(true);
		fourSlider.setPaintTicks(true);
		fourSlider.setPaintLabels(true);
		fourTextField.setText(String.valueOf(fourSlider.getValue()));
		fourSlider.setValue(Integer.parseInt(graphicEQ.getFourBand()));
		fourSlider.addChangeListener(e -> {
			String gain = String.valueOf(fourSlider.getValue());
			fourTextField.setText(gain);
			graphicEQ.setFourBand(gain);
		});
		jDialog.add(fourSlider);

		fiveSlider.setMinorTickSpacing(1);
		fiveSlider.setMajorTickSpacing(5);
		fiveSlider.setSnapToTicks(true);
		fiveSlider.setPaintTicks(true);
		fiveSlider.setPaintLabels(true);
		fiveTextField.setText(String.valueOf(fiveSlider.getValue()));
		fiveSlider.setValue(Integer.parseInt(graphicEQ.getFiveBand()));
		fiveSlider.addChangeListener(e -> {
			String gain = String.valueOf(fiveSlider.getValue());
			fiveTextField.setText(gain);
			graphicEQ.setFiveBand(gain);
		});
		jDialog.add(fiveSlider);

		sixSlider.setMinorTickSpacing(1);
		sixSlider.setMajorTickSpacing(5);
		sixSlider.setSnapToTicks(true);
		sixSlider.setPaintTicks(true);
		sixSlider.setPaintLabels(true);
		sixTextField.setText(String.valueOf(sixSlider.getValue()));
		sixSlider.setValue(Integer.parseInt(graphicEQ.getSixBand()));
		sixSlider.addChangeListener(e -> {
			String gain = String.valueOf(sixSlider.getValue());
			sixTextField.setText(gain);
			graphicEQ.setSixBand(gain);
		});
		jDialog.add(sixSlider);

		sevenSlider.setMinorTickSpacing(1);
		sevenSlider.setMajorTickSpacing(5);
		sevenSlider.setSnapToTicks(true);
		sevenSlider.setPaintTicks(true);
		sevenSlider.setPaintLabels(true);
		sevenTextField.setText(String.valueOf(sevenSlider.getValue()));
		sevenSlider.setValue(Integer.parseInt(graphicEQ.getSevenBand()));
		sevenSlider.addChangeListener(e -> {
			String gain = String.valueOf(sevenSlider.getValue());
			sevenTextField.setText(gain);
			graphicEQ.setSevenBand(gain);
		});
		jDialog.add(sevenSlider);

		eightSlider.setMinorTickSpacing(1);
		eightSlider.setMajorTickSpacing(5);
		eightSlider.setSnapToTicks(true);
		eightSlider.setPaintTicks(true);
		eightSlider.setPaintLabels(true);
		eightTextField.setText(String.valueOf(eightSlider.getValue()));
		eightSlider.setValue(Integer.parseInt(graphicEQ.getEightBand()));
		eightSlider.addChangeListener(e -> {
			String gain = String.valueOf(eightSlider.getValue());
			eightTextField.setText(gain);
			graphicEQ.setEightBand(gain);
		});
		jDialog.add(eightSlider);

		nineSlider.setMinorTickSpacing(1);
		nineSlider.setMajorTickSpacing(5);
		nineSlider.setSnapToTicks(true);
		nineSlider.setPaintTicks(true);
		nineSlider.setPaintLabels(true);
		nineTextField.setText(String.valueOf(nineSlider.getValue()));
		nineSlider.setValue(Integer.parseInt(graphicEQ.getNineBand()));
		nineSlider.addChangeListener(e -> {
			String gain = String.valueOf(nineSlider.getValue());
			nineTextField.setText(gain);
			graphicEQ.setNineBand(gain);
		});
		jDialog.add(nineSlider);

		tenSlider.setMinorTickSpacing(1);
		tenSlider.setMajorTickSpacing(5);
		tenSlider.setSnapToTicks(true);
		tenSlider.setPaintTicks(true);
		tenSlider.setPaintLabels(true);
		tenTextField.setText(String.valueOf(tenSlider.getValue()));
		tenSlider.setValue(Integer.parseInt(graphicEQ.getTenBand()));
		tenSlider.addChangeListener(e -> {
			String gain = String.valueOf(tenSlider.getValue());
			tenTextField.setText(gain);
			graphicEQ.setTenBand(gain);
		});
		jDialog.add(tenSlider);

		elevenSlider.setMinorTickSpacing(1);
		elevenSlider.setMajorTickSpacing(5);
		elevenSlider.setSnapToTicks(true);
		elevenSlider.setPaintTicks(true);
		elevenSlider.setPaintLabels(true);
		elevenTextField.setText(String.valueOf(elevenSlider.getValue()));
		elevenSlider.setValue(Integer.parseInt(graphicEQ.getElevenBand()));
		elevenSlider.addChangeListener(e -> {
			String gain = String.valueOf(elevenSlider.getValue());
			elevenTextField.setText(gain);
			graphicEQ.setElevenBand(gain);
		});
		jDialog.add(elevenSlider);

		twelveSlider.setMinorTickSpacing(1);
		twelveSlider.setMajorTickSpacing(5);
		twelveSlider.setSnapToTicks(true);
		twelveSlider.setPaintTicks(true);
		twelveSlider.setPaintLabels(true);
		twelveTextField.setText(String.valueOf(twelveSlider.getValue()));
		twelveSlider.setValue(Integer.parseInt(graphicEQ.getTwelveBand()));
		twelveSlider.addChangeListener(e -> {
			String gain = String.valueOf(twelveSlider.getValue());
			twelveTextField.setText(gain);
			graphicEQ.setTwelveBand(gain);
		});
		jDialog.add(twelveSlider);

		thirteenSlider.setMinorTickSpacing(1);
		thirteenSlider.setMajorTickSpacing(5);
		thirteenSlider.setSnapToTicks(true);
		thirteenSlider.setPaintTicks(true);
		thirteenSlider.setPaintLabels(true);
		thirteenTextField.setText(String.valueOf(thirteenSlider.getValue()));
		thirteenSlider.setValue(Integer.parseInt(graphicEQ.getThirteenBand()));
		thirteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(thirteenSlider.getValue());
			thirteenTextField.setText(gain);
			graphicEQ.setThirteenBand(gain);
		});
		jDialog.add(thirteenSlider);

		fourteenSlider.setMinorTickSpacing(1);
		fourteenSlider.setMajorTickSpacing(5);
		fourteenSlider.setSnapToTicks(true);
		fourteenSlider.setPaintTicks(true);
		fourteenSlider.setPaintLabels(true);
		fourteenTextField.setText(String.valueOf(fourteenSlider.getValue()));
		fourteenSlider.setValue(Integer.parseInt(graphicEQ.getFourteenBand()));
		fourteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(fourteenSlider.getValue());
			fourteenTextField.setText(gain);
			graphicEQ.setFourteenBand(gain);
		});
		jDialog.add(fourteenSlider);

		fifteenSlider.setMinorTickSpacing(1);
		fifteenSlider.setMajorTickSpacing(5);
		fifteenSlider.setSnapToTicks(true);
		fifteenSlider.setPaintTicks(true);
		fifteenSlider.setPaintLabels(true);
		fifteenTextField.setText(String.valueOf(fifteenSlider.getValue()));
		fifteenSlider.setValue(Integer.parseInt(graphicEQ.getFifteenBand()));
		fifteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(fifteenSlider.getValue());
			fifteenTextField.setText(gain);
			graphicEQ.setFifteenBand(gain);
		});
		jDialog.add(fifteenSlider);

		sixteenSlider.setMinorTickSpacing(1);
		sixteenSlider.setMajorTickSpacing(5);
		sixteenSlider.setSnapToTicks(true);
		sixteenSlider.setPaintTicks(true);
		sixteenSlider.setPaintLabels(true);
		sixteenTextField.setText(String.valueOf(sixteenSlider.getValue()));
		sixteenSlider.setValue(Integer.parseInt(graphicEQ.getSixteenBand()));
		sixteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(sixteenSlider.getValue());
			sixteenTextField.setText(gain);
			graphicEQ.setSixteenBand(gain);
		});
		jDialog.add(sixteenSlider);

		seventeenSlider.setMinorTickSpacing(1);
		seventeenSlider.setMajorTickSpacing(5);
		seventeenSlider.setSnapToTicks(true);
		seventeenSlider.setPaintTicks(true);
		seventeenSlider.setPaintLabels(true);
		seventeenTextField.setText(String.valueOf(seventeenSlider.getValue()));
		seventeenSlider.setValue(Integer.parseInt(graphicEQ.getSeventeenBand()));
		seventeenSlider.addChangeListener(e -> {
			String gain = String.valueOf(seventeenSlider.getValue());
			seventeenTextField.setText(gain);
			graphicEQ.setSeventeenBand(gain);
		});
		jDialog.add(seventeenSlider);

		eighteenSlider.setMinorTickSpacing(1);
		eighteenSlider.setMajorTickSpacing(5);
		eighteenSlider.setSnapToTicks(true);
		eighteenSlider.setPaintTicks(true);
		eighteenSlider.setPaintLabels(true);
		eighteenTextField.setText(String.valueOf(eighteenSlider.getValue()));
		eighteenSlider.setValue(Integer.parseInt(graphicEQ.getEighteenBand()));
		eighteenSlider.addChangeListener(e -> {
			String gain = String.valueOf(eighteenSlider.getValue());
			eighteenTextField.setText(gain);
			graphicEQ.setEighteenBand(gain);
		});
		jDialog.add(eighteenSlider);

		// Set up slider text fields
		oneTextField.setBackground(Color.WHITE);
		oneTextField.setText(graphicEQ.getOneBand());
		oneTextField.addActionListener(e -> {oneSlider.setValue((int)Math.round(Float.valueOf(oneTextField.getText())));});
		jDialog.add(oneTextField);
		twoTextField.setBackground(Color.WHITE);
		twoTextField.setText(graphicEQ.getTwoBand());
		twoTextField.addActionListener(e -> {twoSlider.setValue((int)Math.round(Float.valueOf(twoTextField.getText())));});
		jDialog.add(twoTextField);
		threeTextField.setBackground(Color.WHITE);
		threeTextField.setText(graphicEQ.getThreeBand());
		threeTextField.addActionListener(e -> {threeSlider.setValue((int)Math.round(Float.valueOf(threeTextField.getText())));});
		jDialog.add(threeTextField);
		fourTextField.setBackground(Color.WHITE);
		fourTextField.setText(graphicEQ.getFourBand());
		fourTextField.addActionListener(e -> {fourSlider.setValue((int)Math.round(Float.valueOf(fourTextField.getText())));});
		jDialog.add(fourTextField);
		fiveTextField.setBackground(Color.WHITE);
		fiveTextField.setText(graphicEQ.getFiveBand());
		fiveTextField.addActionListener(e -> {fiveSlider.setValue((int)Math.round(Float.valueOf(fiveTextField.getText())));});
		jDialog.add(fiveTextField);
		sixTextField.setBackground(Color.WHITE);
		sixTextField.setText(graphicEQ.getSixBand());
		sixTextField.addActionListener(e -> {sixSlider.setValue((int)Math.round(Float.valueOf(sixTextField.getText())));});
		jDialog.add(sixTextField);
		sevenTextField.setBackground(Color.WHITE);
		sevenTextField.setText(graphicEQ.getSevenBand());
		sevenTextField.addActionListener(e -> {sevenSlider.setValue((int)Math.round(Float.valueOf(sevenTextField.getText())));});
		jDialog.add(sevenTextField);
		eightTextField.setBackground(Color.WHITE);
		eightTextField.setText(graphicEQ.getEightBand());
		eightTextField.addActionListener(e -> {eightSlider.setValue((int)Math.round(Float.valueOf(eightTextField.getText())));});
		jDialog.add(eightTextField);
		nineTextField.setBackground(Color.WHITE);
		nineTextField.setText(graphicEQ.getNineBand());
		nineTextField.addActionListener(e -> {nineSlider.setValue((int)Math.round(Float.valueOf(nineTextField.getText())));});
		jDialog.add(nineTextField);
		tenTextField.setBackground(Color.WHITE);
		tenTextField.setText(graphicEQ.getTenBand());
		tenTextField.addActionListener(e -> {tenSlider.setValue((int)Math.round(Float.valueOf(tenTextField.getText())));});
		jDialog.add(tenTextField);
		elevenTextField.setBackground(Color.WHITE);
		elevenTextField.setText(graphicEQ.getElevenBand());
		elevenTextField.addActionListener(e -> {elevenSlider.setValue((int)Math.round(Float.valueOf(elevenTextField.getText())));});
		jDialog.add(elevenTextField);
		twelveTextField.setBackground(Color.WHITE);
		twelveTextField.setText(graphicEQ.getTwelveBand());
		twelveTextField.addActionListener(e -> {twelveSlider.setValue((int)Math.round(Float.valueOf(twelveTextField.getText())));});
		jDialog.add(twelveTextField);
		thirteenTextField.setBackground(Color.WHITE);
		thirteenTextField.setText(graphicEQ.getThirteenBand());
		thirteenTextField.addActionListener(e -> {thirteenSlider.setValue((int)Math.round(Float.valueOf(thirteenTextField.getText())));});
		jDialog.add(thirteenTextField);
		fourteenTextField.setBackground(Color.WHITE);
		fourteenTextField.setText(graphicEQ.getFourteenBand());
		fourteenTextField.addActionListener(e -> {fourteenSlider.setValue((int)Math.round(Float.valueOf(fourteenTextField.getText())));});
		jDialog.add(fourteenTextField);
		fifteenTextField.setBackground(Color.WHITE);
		fifteenTextField.setText(graphicEQ.getFifteenBand());
		fifteenTextField.addActionListener(e -> {fifteenSlider.setValue((int)Math.round(Float.valueOf(fifteenTextField.getText())));});
		jDialog.add(fifteenTextField);
		sixteenTextField.setBackground(Color.WHITE);
		sixteenTextField.setText(graphicEQ.getSixteenBand());
		sixteenTextField.addActionListener(e -> {sixteenSlider.setValue((int)Math.round(Float.valueOf(sixteenTextField.getText())));});
		jDialog.add(sixteenTextField);
		seventeenTextField.setBackground(Color.WHITE);
		seventeenTextField.setText(graphicEQ.getSeventeenBand());
		seventeenTextField.addActionListener(e -> {seventeenSlider.setValue((int)Math.round(Float.valueOf(seventeenTextField.getText())));});
		jDialog.add(seventeenTextField);
		eighteenTextField.setBackground(Color.WHITE);
		eighteenTextField.setText(graphicEQ.getEighteenBand());
		eighteenTextField.addActionListener(e -> {eighteenSlider.setValue((int)Math.round(Float.valueOf(eighteenTextField.getText())));});
		jDialog.add(eighteenTextField);

		// Finish setting up dialog window and show
		jDialog.pack();
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);
		jDialog.setVisible(true);
	}
}