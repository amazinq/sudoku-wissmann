package controller;

import game.LogicController;

public class Controller {

	private LogicController logicController;
	private static Controller instance;
	
	private Controller() {
		logicController = new LogicController();
	}
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void generateGameField() {
		logicController.Test();
	}
}