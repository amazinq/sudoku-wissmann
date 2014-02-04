package jGUI;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuPanel extends GeneralPanel {

	// Initialisierung aller benötigten Objekte
	private JButton btnExit;
	private JButton playGamebtn;
	private JButton loadSavedGamebtn;
	private FileChooser chooser;

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
	}
	
	private JPanel getPanel() {
		return this;
	}
}
