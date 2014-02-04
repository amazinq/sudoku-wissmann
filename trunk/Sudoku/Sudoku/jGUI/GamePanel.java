package jGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends GeneralPanel {

	private JButton Cancelbtn;
	private JLabel labelArray[][];
	private LabelListener lblListener;

	public GamePanel() {
		labelArray = new JLabel[9][9];
		lblListener = new LabelListener();
		
		Cancelbtn = new JButton("Cancel");
		Cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getInstance().remove(getPanel());
				MainFrame.getInstance().setContentPane(new MainMenuPanel());
				MainFrame.getInstance().revalidate();
				MainFrame.getInstance().repaint();
			}
		});
		Cancelbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		Cancelbtn.setBounds(680, 530, 89, 23);
		add(Cancelbtn);
		
		
		int xPosition = 45;
		int yPosition = 75;
		int distance = 5;
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				JLabel label = new JLabel();
				label.setSize(40, 40);
				if((x + 1) % 3 == 0 && x != 0) {
					distance = 10;
				}
				label.setLocation(xPosition, yPosition);
				label.setOpaque(true);
				label.setBackground(Color.RED);
				label.setVisible(true);
				add(label);
				label.addMouseListener(lblListener);
				xPosition += (40 + distance);
				distance = 5;
				labelArray[y][x] = label;
			}
			if((y + 1) % 3 == 0 && y != 0) {
				distance = 10;
			}
			xPosition = 45;
			yPosition += (40 + distance);
			distance = 5;
		}
	}
	
	private JPanel getPanel() {
		return this;
	}
}
