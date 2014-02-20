package swingGUI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//Abstract Panel setzt die Überschrift und allgemeine attribute eines Panels für diese applikation
@SuppressWarnings("serial")
public abstract class GeneralPanel extends JPanel {

	private JLabel mainLabel;

	public GeneralPanel() {
		// Absolute Layout
		setLayout(null);
		setSize(800, 600);

		// Überschriftenlabel
		mainLabel = new JLabel("Sudoku");
		mainLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		mainLabel.setBounds(10, 11, 780, 36);
		add(mainLabel);
	}
}
