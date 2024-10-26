package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Options controller class
class OptionsDialog {
	public OptionsDialog(JFrame jFrame, Options options) {

		// Set dimensions and padding
		final int W = 285;
		final int H = 90;
		final int PAD = 5;

		// Set up the dialog window
		JDialog jDialog = new JDialog(jFrame, "Additional options", true);
		jDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		jDialog.getContentPane().setLayout(null);

		// Suppress noise checkbox
		JCheckBox rnnnCheckbox = new JCheckBox("Suppress noise");
		rnnnCheckbox.setBounds(PAD, PAD, (W/2)-PAD, 25);
		if (!options.getRnnn().isEmpty()) {rnnnCheckbox.setSelected(true);}
		rnnnCheckbox.addActionListener(e -> {options.rnnn(rnnnCheckbox.isSelected());});
		jDialog.add(rnnnCheckbox);

		// Output in stereo checkbox
		JCheckBox stereoCheckbox = new JCheckBox("Output in stereo");
		stereoCheckbox.setBounds(W/2, PAD, (W/2)-PAD, 25);
		if (options.getStereo()) {stereoCheckbox.setSelected(true);}
		stereoCheckbox.addActionListener(e -> {options.setStereo(stereoCheckbox.isSelected());});
		jDialog.add(stereoCheckbox);

		// De-click checkbox
		JCheckBox declickCheckbox = new JCheckBox("De-click");
		declickCheckbox.setBounds(PAD, PAD+25, (W/2)-PAD, 25);
		if (!options.getDeclick().isEmpty()) {declickCheckbox.setSelected(true);}
		declickCheckbox.addActionListener(e -> {options.declick(declickCheckbox.isSelected());});
		jDialog.add(declickCheckbox);

		// Noise gate checkbox
		JCheckBox gateCheckbox = new JCheckBox("Noise gate");
		gateCheckbox.setBounds(W/2, PAD+25, (W/2)-PAD, 25);
		if (!options.getGate().isEmpty()) {gateCheckbox.setSelected(true);}
		gateCheckbox.addActionListener(e -> {options.gate(gateCheckbox.isSelected());});
		jDialog.add(gateCheckbox);

		// Generate noise checkbox
		JCheckBox noiseCheckbox = new JCheckBox("Generate noise");
		noiseCheckbox.setBounds(PAD, (PAD+25)*2, (W/2)-PAD, 25);
		if (!options.getNoise().isEmpty()) {noiseCheckbox.setSelected(true);}
		noiseCheckbox.addActionListener(e -> {options.noise(noiseCheckbox.isSelected());});
		jDialog.add(noiseCheckbox);

		// Suppress warnings checkbox
		JCheckBox noWarnCheckbox = new JCheckBox("Suppress warnings");
		noWarnCheckbox.setBounds(W/2, (PAD+25)*2, (W/2)-PAD, 25);
		if (options.getNoWarn()) {noWarnCheckbox.setSelected(true);}
		noWarnCheckbox.addActionListener(e -> {options.setNoWarn(noWarnCheckbox.isSelected());});
		jDialog.add(noWarnCheckbox);

		// Finish setting up dialog window and show
		jDialog.pack();
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);
		jDialog.setVisible(true);
	}
}