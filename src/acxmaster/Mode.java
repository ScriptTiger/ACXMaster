package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Mode class
class Mode {

	//////////////////
	// Class variables
	//////////////////

	private static Boolean mode = true;
	private static JMenuItem targetsItem;
	private static JMenuItem eqItem;
	private static JMenuItem exportItem;
	private static JMenuItem optionsItem;
	private static JButton saveButton;
	private static JTextField saveChooserTextField;
	private static JButton masterButton;
	private static JTextField fileChooserTextField;

	//////////
	// Getters
	//////////

	public Boolean getMode() {return mode;}

	//////////
	// Setters
	//////////

	public void setMode(Boolean mode) {this.mode = mode;}
	public void initMode(
		JMenuItem targetsItem,
		JMenuItem eqItem,
		JMenuItem exportItem,
		JMenuItem optionsItem,
		JButton saveButton,
		JTextField saveChooserTextField,
		JButton masterButton,
		JTextField fileChooserTextField
	) {
		this.targetsItem = targetsItem;
		this.eqItem = eqItem;
		this.exportItem = exportItem;
		this.optionsItem = optionsItem;
		this.saveButton = saveButton;
		this.saveChooserTextField = saveChooserTextField;
		this.masterButton = masterButton;
		this.fileChooserTextField = fileChooserTextField;
	}

	//////////////////////////
	// Primary class functions
	//////////////////////////

	// Function to paint current mode
	public void paintMode() {
		targetsItem.setEnabled(mode);
		eqItem.setEnabled(mode);
		exportItem.setEnabled(mode);
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
	}
}