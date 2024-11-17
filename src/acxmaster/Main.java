package acxmaster;

// Standard swing deps
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Main class
public class Main {
	public static void main(String[] args) {

		// Construct and set up jFrame
		JFrame jFrame = new JFrame("ACX Master");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().add(new MainDialog());
		jFrame.pack();
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);

		// Show jFrame
		jFrame.setVisible(true);
	}
}