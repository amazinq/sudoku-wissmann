package swingGUI;

import game.SingleField;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;

import javax.swing.JFrame;

import controller.GUI_Interface;

//JFrame
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements GUI_Interface {

	private static MainFrame instance;
	private SingleField array[][];

	/**
	 * Create the frame.
	 */
	private MainFrame() {
		// Titel
		setTitle("Sudoku");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		// Setzt das Frame auf die Mitte des Bildschirms
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2
				- getSize().height / 2);
		setVisible(true);
		setContentPane(new MainMenuPanel());
	}

	// Singleton
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	// Update Methode, wird aufgerufen wenn die Observer benachrichtigt werden
	@Override
	public void update(Observable arg0, Object arg1) {
		array = (SingleField[][]) arg1;
	}

	public SingleField[][] getArray() {
		return array;
	}
}
