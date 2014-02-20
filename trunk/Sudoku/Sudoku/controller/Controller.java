package controller;

import java.io.File;

import javax.swing.JLabel;

import game.LogicController;

//Controller, Kommunikation von View zu Model erfolgt über ihn
public class Controller {

	private LogicController logicController;
	private static Controller instance;

	// Singleton
	private Controller() {
		logicController = new LogicController();
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	// Weitergabe an die Logic
	public void generateGameField(int difficulty) {
		logicController.generateGameField(difficulty);

	}

	// Weitergabe an die Logic
	public boolean gameIsWon(Integer[][] field) {
		return logicController.gameIsWon(field);
	}

	// Weitergabe an die Logic
	public void loadData(int[][][] field) {
		logicController.loadData(field);
	}

	// Weitergabe an die Logic
	public void saveData(File file, JLabel[][] labelArray) {
		logicController.saveData(file, labelArray);
	}
}