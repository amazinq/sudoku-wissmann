package swingGUI;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class MainMenuPanel extends GeneralPanel {

	// Initialisierung aller benötigten Objekte
	private JButton btnExit;
	private JButton playGamebtn;
	private JButton loadSavedGamebtn;
	private FileChooser chooser;
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnHard;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public MainMenuPanel() {

		// Exitbutton
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Schließt bei betätigung des Buttons das frame (und damit auch
				// die applikation) über einen statischen Zugriff
				MainFrame.getInstance().dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(680, 530, 89, 23);
		add(btnExit);
		
		playGamebtn = new JButton("Play");
		playGamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int difficulty = 0;
				if(rdbtnEasy.isSelected()) {
					difficulty = 35;
				} else if(rdbtnMedium.isSelected()) {
					difficulty = 40;
				} else {
					difficulty = 45;
				}
				Controller.getInstance().generateGameField(difficulty);
				MainFrame.getInstance().remove(getPanel());
				MainFrame.getInstance().setContentPane(new GamePanel());
				MainFrame.getInstance().revalidate();
				MainFrame.getInstance().repaint();
			}
		});
		playGamebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		playGamebtn.setSize(250, 40);
		playGamebtn.setLocation(275,200);
				
		
		playGamebtn.setVisible(true);
		add(playGamebtn);
		
		loadSavedGamebtn = new JButton("Load saved Game");
		loadSavedGamebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new FileChooser();
				File file = chooser.generateOpenDialog();
				if(file != null) {
					
				}
			}
		});
		loadSavedGamebtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loadSavedGamebtn.setBounds(275, 300, 250, 40);
		loadSavedGamebtn.setVisible(true);
		add(loadSavedGamebtn);
		
		rdbtnEasy = new JRadioButton("Easy");
		buttonGroup.add(rdbtnEasy);
		rdbtnEasy.setSelected(true);
		rdbtnEasy.setBounds(618, 210, 109, 23);
		add(rdbtnEasy);
		
		rdbtnMedium = new JRadioButton("Medium");
		buttonGroup.add(rdbtnMedium);
		rdbtnMedium.setBounds(618, 250, 109, 23);
		add(rdbtnMedium);
		
		rdbtnHard = new JRadioButton("Hard");
		buttonGroup.add(rdbtnHard);
		rdbtnHard.setBounds(618, 290, 109, 23);
		add(rdbtnHard);
	}
	
	private JPanel getPanel() {
		return this;
	}
}
