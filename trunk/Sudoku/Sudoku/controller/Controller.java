package controller;

import java.io.File;

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
	
	public void generateGameField(int difficulty) {
		logicController.generateGameField(difficulty);
		
	}
	
	public boolean gameIsWon(Integer[][] field) {
		return logicController.gameIsWon(field);
	}
	
	public void loadData(int[][][] field) {
		logicController.loadData(field);
	}
	
	public void saveData(File file) {
		logicController.saveData(file);
	}
}