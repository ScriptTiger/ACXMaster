package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Targets controller class
class TargetsDialog {
	public TargetsDialog(JFrame jFrame, Targets targets) {

		// Set dimensions and padding
		final int W = 300;
		final int H = 155;
		final int PAD = 5;

		// Set up the dialog window
		JDialog jDialog = new JDialog(jFrame, "Targets", true);
		jDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		jDialog.getContentPane().setLayout(null);

		// Integrated loudness target label
		JLabel iLabel = new JLabel("Integrated loudness target (LUFS):");
		iLabel.setBounds(PAD, PAD, (W/4)*3-PAD, 25);
		jDialog.add(iLabel);

		// Integrated loudness target text field
		JTextField iTextField = new JTextField(String.valueOf(targets.getI()));
		iTextField.setBounds((W/4)*3, PAD, (W/4)-PAD, 25);
		jDialog.add(iTextField);

		// Loudness range target label
		JLabel lraLabel = new JLabel("Loudness range target (LU):");
		lraLabel.setBounds(PAD, PAD*2+25, (W/4)*3-PAD, 25);
		jDialog.add(lraLabel);

		// Loudness range target text field
		JTextField lraTextField = new JTextField(String.valueOf(targets.getLRA()));
		lraTextField.setBounds((W/4)*3, PAD*2+25, (W/4)-PAD, 25);
		jDialog.add(lraTextField);

		// Maximum true peak label
		JLabel tpLabel = new JLabel("Maximum true peak (dBTP):");
		tpLabel.setBounds(PAD, PAD+(PAD+25)*2, (W/4)*3-PAD, 25);
		jDialog.add(tpLabel);

		// Maximum true peak text field
		JTextField tpTextField = new JTextField(String.valueOf(targets.getTP()));
		tpTextField.setBounds((W/4)*3, PAD+(PAD+25)*2, (W/4)-PAD, 25);
		jDialog.add(tpTextField);

		// Revert to defaults button
		JButton defaults = new JButton("Revert to defaults");
		defaults.setBounds(PAD, H-(25+PAD)*2, W-PAD*2, 25);
		defaults.addActionListener(e -> {
			iTextField.setText("-20.0");
			lraTextField.setText("7.0");
			tpTextField.setText("-3.0");
		});
		jDialog.add(defaults);

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
			targets.setI(Float.parseFloat(iTextField.getText()));
			targets.setLRA(Float.parseFloat(lraTextField.getText()));
			targets.setTP(Float.parseFloat(tpTextField.getText()));
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