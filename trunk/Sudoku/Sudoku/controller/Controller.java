package controller;

public class Controller {

	private static Controller instance;
	
	private Controller() {
		
	}
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public String[] generateGameField() {
		
		return null;
	}
}