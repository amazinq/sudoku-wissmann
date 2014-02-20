package swingGUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.KeyStroke;

import controller.Controller;

//Fokussiert bzw deFokussiert die Labels mit mausklicks
public class LabelListener extends MouseAdapter {

	private JLabel oldLabel;
	private JLabel source;

	public LabelListener() {

	}

	@Override
	// Wird ein Label angeklickt, wird es Fokussiert und markiert. Wird es
	// nocheinmal angeklickt wird der Focus zurück an das Panel gegeben und das
	// Label demakiert. Wird ein anderes Label gedrückt, wird der Fokus an das
	// angeklickte Label übergeben
	public void mouseClicked(MouseEvent ME) {
		source = (JLabel) ME.getSource();
		source.requestFocusInWindow();
		if (source.equals(oldLabel)) {
			if (source.getBackground().equals(Color.WHITE)) {
				source.setBackground(Color.CYAN);
			} else {
				source.setBackground(Color.WHITE);
				source.transferFocusBackward();
			}
		} else if (oldLabel != null) {
			source.setBackground(Color.CYAN);
			oldLabel.setBackground(Color.WHITE);
		} else {
			source.setBackground(Color.CYAN);
		}
		oldLabel = source;

	}

}
