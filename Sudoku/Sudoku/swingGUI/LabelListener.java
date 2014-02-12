package swingGUI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class LabelListener extends MouseAdapter {

	private JLabel oldLabel;
	private JLabel source;
	@Override
	public void mouseClicked(MouseEvent ME) {
		source = (JLabel) ME.getSource();
		
		if (source.equals(oldLabel)) {
			if(source.getBackground().equals(Color.RED)) {
				source.setBackground(Color.BLUE);
			} else {
				source.setBackground(Color.RED);
			}
		} else if(oldLabel != null) {
			source.setBackground(Color.BLUE);
			oldLabel.setBackground(Color.RED);
		} else {
			source.setBackground(Color.BLUE);
		}
		oldLabel = source;
		
	}


}
