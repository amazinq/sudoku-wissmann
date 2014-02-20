package consoleGUI;

import game.SingleField;

import java.util.Observable;
import java.util.Scanner;

import controller.Controller;
import controller.GUI_Interface;

//Konsolen GUI
public class ConsoleGUI {

	// SpielFeld
	private char[][] gameField = {
			{ ' ', '╔', '═', '╤', '═', '╤', '═', '╦', '═', '╤', '═', '╤', '═',
					'╦', '═', '╤', '═', '╤', '═', '╗' },
			{ '1', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '2', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '3', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╠', '═', '╪', '═', '╪', '═', '╬', '═', '╪', '═', '╪', '═',
					'╬', '═', '╪', '═', '╪', '═', '╣' },
			{ '4', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '5', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '6', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╠', '═', '╪', '═', '╪', '═', '╬', '═', '╪', '═', '╪', '═',
					'╬', '═', '╪', '═', '╪', '═', '╣' },
			{ '7', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '8', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╟', '─', '┼', '─', '┼', '─', '╫', '─', '┼', '─', '┼', '─',
					'╫', '─', '┼', '─', '┼', '─', '╢' },
			{ '9', '║', ' ', '│', ' ', '│', ' ', '║', ' ', '│', ' ', '│', ' ',
					'║', ' ', '│', ' ', '│', ' ', '║' },
			{ ' ', '╚', '═', '╧', '═', '╧', '═', '╩', '═', '╧', '═', '╧', '═',
					'╩', '═', '╧', '═', '╧', '═', '╝' },
			{ ' ', ' ', '1', ' ', '2', ' ', '3', ' ', '4', ' ', '5', ' ', '6',
					' ', '7', ' ', '8', ' ', '9', ' ' } // X-Achse
	};
	private boolean gameWon;
	private Scanner scan;
	private int xCoordinate;
	private int yCoordinate;
	private int selectedNumber;
	private int difficulty;
	private ErrorCheck errorCheck;
	// Koordinaten auf dem Spielfeld für die entsprechenden Eingaben des nutzers
	private final int[] xCoordinates = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18 };
	private final int[] yCoordinates = { 0, 1, 3, 5, 7, 9, 11, 13, 15, 17 };

	private Controller controller;
	private ConsoleController consoleController;

	public ConsoleGUI() {
		errorCheck = new ErrorCheck();
		gameWon = false;
		scan = new Scanner(System.in);
		consoleController = ConsoleController.getInstance();
		// Spiel starten
		runGame();
	}

	public void printGameField() {
		// Ausgabe des Spielfeldes auf der Konsole
		for (char[] yField : gameField) {
			for (char field : yField) {
				System.out.print(field);
			}
			System.out.println();
		}
	}

	public void runGame() {
		controller = Controller.getInstance();
		System.out.println("Willkommen!");
		boolean diffCheck = false;
		// Fordert eine korrekte Eingabe vom Nutzer
		while (diffCheck == false) {
			System.out
					.println("Bitte wählen sie einen Schwierigkeitsgrad aus: \n 1 = Leicht \n 2 = Normal \n 3 = Schwer");
			String diff = scan.next();
			try {
				difficulty = Integer.parseInt(diff);
				if (difficulty > 0 && difficulty < 4) {
					diffCheck = true;
				} else {
					System.err.println("Geben sie eine Zahl von 1 bis 3 an!");
				}
			} catch (NumberFormatException e) {
				System.err.println("Geben sie eine Zahl von 1 bis 3 an!");
			}
		}
		// Setzt für die ausgewählte Schwierigkeitsstufe die anzahl der zu
		// entfernenden Felder
		switch (difficulty) {
		case (1):
			difficulty = 35;
			break;
		case (2):
			difficulty = 40;
			break;
		case (3):
			difficulty = 50;
			break;
		}
		// Lässt das spielfeld generieren
		controller.generateGameField(difficulty);
		SingleField[][] singleFieldArray = consoleController
				.getSingleFieldArray();
		// Setzt die generierten Zahlen auf das Spielfeld
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (singleFieldArray[y][x].getValue() != 0) {
					gameField[yCoordinates[y + 1]][xCoordinates[x + 1]] = (char) (singleFieldArray[y][x]
							.getValue() + 48);
				}
			}
		}
		// Solange das Spiel nicht gewonnen ist, wird weitergespielt
		while (gameWon == false) {
			printGameField();
			// Fordert eine Eingabe vom Nutzer
			input();
			gameField[yCoordinates[yCoordinate]][xCoordinates[xCoordinate]] = (char) (selectedNumber + 48);
			Integer[][] winStateCheckArray = new Integer[9][9];
			// Erzeugt ein Integer array zur gewinnüberprüfung
			for (int y = 0; y < 9; y++) {
				for (int x = 0; x < 9; x++) {
					if (gameField[yCoordinates[y + 1]][xCoordinates[x + 1]] != ' ') {
						winStateCheckArray[y][x] = new Integer(
								(int) (gameField[yCoordinates[y + 1]][xCoordinates[x + 1]]) - 48);
					}
				}
			}
			// Führt die gewinnüberprüfung durch
			if (controller.gameIsWon(winStateCheckArray)) {
				gameWon = true;
				System.out.println("SIE HABEN GEWONNEN!");
			}
		}
	}

	public void input() {
		boolean check = false;
		// Fordert solange eine Eingabe vom nutzer, bis eine gültige getätigt
		// wurde
		while (check == false) {
			System.out.print("X-Koordinate: ");
			String x = scan.next();
			System.out.print("Y-Koordinate: ");
			String y = scan.next();
			System.out.print("Einzutragende Zahl: ");
			String input = scan.next();
			if (errorCheck.isAnInteger(x, y, input)) {
				if (errorCheck.isInRange(Integer.parseInt(x),
						Integer.parseInt(y), Integer.parseInt(input))) {
					xCoordinate = Integer.parseInt(x);
					yCoordinate = Integer.parseInt(y);
					selectedNumber = Integer.parseInt(input);
					check = true;
				} else {
					System.err
							.println("Geben sie Zahlen zwischen 1 und 9 ein!");
				}
			} else {
				System.err.print("Geben sie eine Ganzzahl ein!");
			}
		}
	}
}
