package game;

import java.util.Observable;

import swingGUI.MainFrame;

public class LogicController extends Observable {
	
	public LogicController() {
		this.addObserver(MainFrame.getInstance());
	}

	public void Test() {
		this.setChanged();
		this.notifyObservers("Testausgabe");
	}
	
	public void generateGameField() {
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				
			}
		}
	}
}
