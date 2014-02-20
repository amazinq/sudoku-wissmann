package swingGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

//Keylistener um die Labels zu beschriften
public class LabelKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		JLabel source = (JLabel) arg0.getSource();
		// Lässt sich die eingegebene Ziffer in einen Int casten und befindet
		// sie sich im gültigen Wertebereich, wird sie als text gesetzt
		try {
			int numberPressed = Integer.parseInt(String.valueOf(arg0
					.getKeyChar()));
			if (numberPressed > 0 && numberPressed <= 9) {
				source.setText(String.valueOf(arg0.getKeyChar()));
			}
		} catch (NumberFormatException e) {

		}
		// Wird backspace gedrückt, wird die aktuelle zahl entfernt
		if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			source.setText(" ");
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
