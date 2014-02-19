package consoleGUI;

import java.util.Observable;
import java.util.Scanner;

import controller.GUI_Interface;

public class ConsoleGUI implements GUI_Interface {
	
	private char[][] gameField = {
			{' ','╔','═','╤','═','╤','═','╦','═','╤','═','╤','═','╦','═','╤','═','╤','═','╗'},
			{'1','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'2','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'3','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╠','═','╪','═','╪','═','╬','═','╪','═','╪','═','╬','═','╪','═','╪','═','╣'},
			{'4','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'5','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'6','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╠','═','╪','═','╪','═','╬','═','╪','═','╪','═','╬','═','╪','═','╪','═','╣'},
			{'7','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'8','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╟','─','┼','─','┼','─','╫','─','┼','─','┼','─','╫','─','┼','─','┼','─','╢'},
			{'9','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║',' ','│',' ','│',' ','║'},
			{' ','╚','═','╧','═','╧','═','╩','═','╧','═','╧','═','╩','═','╧','═','╧','═','╝'},
			{' ',' ','1',' ','2',' ','3',' ','4',' ','5',' ','6',' ','7',' ','8',' ','9',' '}//X-Achse
		};
	private boolean gameWon;
	private Scanner scan;
	private int xCoordinate;
	private int yCoordinate;
	private int selectedNumber;
	private ErrorCheck errorCheck;
	private final int[] xCoordinates = {0,2,4,6,8,10,12,14,16,18};
	private final int[] yCoordinates = {0,1,3,5,7,9,11,13,15,17};
	
	public ConsoleGUI() {
		errorCheck = new ErrorCheck();
		gameWon = false;
		scan = new Scanner(System.in);
		runGame();
	}
	
	public void printGameField() {
		for(char[] yField : gameField) {
			for(char field : yField) {
				System.out.print(field);
			}
			System.out.println();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void runGame() {
		while (gameWon == false) {
			System.out.println("Sudoku!");
			printGameField();
			input();
			
		}
	}
	public void input() {
		boolean check = false;
		while(check == false) {
			System.out.print("X-Koordinate: ");
			String x = scan.next();
			System.out.print("Y-Koordinate: ");
			String y = scan.next();
			System.out.print("Einzutragende Zahl: ");
			String input = scan.next();
			if(errorCheck.isAnInteger(x, y, input)) {
				if(errorCheck.isInRange(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(input))) {
					xCoordinate = Integer.parseInt(x);
					yCoordinate = Integer.parseInt(y);
					selectedNumber = Integer.parseInt(input);
				} else {
					System.err.println("Geben sie Zahlen zwischen 1 und 9 ein!");
				}
			} else {
				System.err.print("Geben sie eine Ganzzahl ein!");
			}
		}
	}
}
