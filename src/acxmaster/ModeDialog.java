package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Mode controller class
class ModeDialog {
	public ModeDialog(JFrame jFrame, Mode mode) {

		// Set dimensions and padding
		final int W = 200;
		final int H = 35;
		final int PAD = 5;

		// Set up the dialog window
		JDialog jDialog = new JDialog(jFrame, "Mode", true);
		jDialog.getContentPane().setPreferredSize(new Dimension(W, H));
		jDialog.getContentPane().setLayout(null);

		// Initalize toggles
		JToggleButton checkToggle = new JToggleButton("Check", !mode.getMode());
		JToggleButton masterToggle = new JToggleButton("Master", mode.getMode());

		// Set up check mode toggle
		checkToggle.setBounds(PAD, PAD, (W/2)-PAD, 25);
		checkToggle.addActionListener(e -> {
			mode.setMode(!checkToggle.isSelected());
			masterToggle.setSelected(mode.getMode());
		});
		jDialog.add(checkToggle);

		// Set up master mode toggle
		masterToggle.setBounds(W/2, PAD, (W/2)-PAD, 25);
		masterToggle.addActionListener(e -> {
			mode.setMode(masterToggle.isSelected());
			checkToggle.setSelected(!mode.getMode());
		});
		jDialog.add(masterToggle);

		// Finish setting up dialog window and show
		jDialog.pack();
		jDialog.setResizable(false);
		jDialog.setLocationRelativeTo(null);
		jDialog.setVisible(true);
	}
}