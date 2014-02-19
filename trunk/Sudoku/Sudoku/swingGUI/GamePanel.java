package swingGUI;

import game.SingleField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import controller.Controller;
import controller.GUI_Interface;


@SuppressWarnings("serial")
public class GamePanel extends GeneralPanel {

	private JButton Cancelbtn;
	private JButton checkBtn;
	private JLabel labelArray[][];
	private LabelListener lblListener;
	private LabelKeyListener keyListener;
	private MainFrame mainFrame;
	private SingleField[][] fieldArray;
	private Controller controller;
	private Converter converter;

	public GamePanel() {
		labelArray = new JLabel[9][9];
		lblListener = new LabelListener();
		keyListener = new LabelKeyListener();
		mainFrame = MainFrame.getInstance();
		fieldArray = mainFrame.getArray();
		controller = Controller.getInstance();
		converter = new Converter();

		Cancelbtn = new JButton("Cancel");
		Cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.remove(getPanel());
				mainFrame.setContentPane(new MainMenuPanel());
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		});
		Cancelbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		Cancelbtn.setBounds(680, 530, 89, 23);
		add(Cancelbtn);
		
		checkBtn = new JButton("Check");
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(controller.gameIsWon(converter.toIntArray(labelArray))) {
					JOptionPane
					.showMessageDialog(
							null,
							"Game Won!",
							"WIN", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane
					.showMessageDialog(
							null,
							"Not Finished yet",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		checkBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkBtn.setBounds(550, 250, 160, 33);
		add(checkBtn);
		
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
				if(fieldArray[y][x].getValue() == 0) {
					label.setText(" ");
				} else {
					label.setText(Integer.toString(fieldArray[y][x].getValue()));
				}
				label.setBackground(Color.WHITE);
				label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				label.setVisible(true);
				label.setHorizontalTextPosition(SwingConstants.CENTER);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Tahoma", Font.BOLD, 15));
				add(label);
				label.addMouseListener(lblListener);
				label.addKeyListener(keyListener);
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
