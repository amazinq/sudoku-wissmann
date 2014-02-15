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
}
