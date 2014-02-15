package swingGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class LabelKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		JLabel source = (JLabel) arg0.getSource();
		source.setText(String.valueOf(arg0.getKeyChar()));
		System.out.println("Test");
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
