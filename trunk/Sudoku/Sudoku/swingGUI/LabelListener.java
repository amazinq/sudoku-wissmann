package swingGUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.KeyStroke;

import controller.Controller;

public class LabelListener extends MouseAdapter {

	private Controller controller;
	private JLabel oldLabel;
	private JLabel source;
	
	public LabelListener() {
		controller = Controller.getInstance();
	}
	@Override
	public void mouseClicked(MouseEvent ME) {
		source = (JLabel) ME.getSource();
		controller.generateGameField();
		source.requestFocusInWindow();
		if (source.equals(oldLabel)) {
			if(source.getBackground().equals(Color.WHITE)) {
				source.setBackground(Color.CYAN);
			} else {
				source.setBackground(Color.WHITE);
			}
		} else if(oldLabel != null) {
			source.setBackground(Color.CYAN);
			oldLabel.setBackground(Color.WHITE);
		} else {
			source.setBackground(Color.CYAN);
		}
		oldLabel = source;
		
	}


}
