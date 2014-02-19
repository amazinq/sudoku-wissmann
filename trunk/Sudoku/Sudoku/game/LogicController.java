package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import consoleGUI.ConsoleController;
import consoleGUI.ConsoleGUI;
import dataOperation.SaveAndLoad;
import swingGUI.MainFrame;

public class LogicController extends Observable {

	private Column[] columnList;
	private Row[] rowList;
	private GameField[][] fieldList;
	private SingleField[][] singleFieldArray;

	private Service service;
	private Clone clone;

	public LogicController() {
		columnList = new Column[9];
		rowList = new Row[9];
		fieldList = new GameField[3][3];
		singleFieldArray = new SingleField[9][9];

		service = new Service();
		clone = new Clone();

		addObserver(MainFrame.getInstance());
		addObserver(ConsoleController.getInstance());
	}

	public void generateGameField(int difficulty) {
		for (int y = 0; y < 9; y++) {
			rowList[y] = new Row();
			for (int x = 0; x < 9; x++) {
				if (y == 0) {
					columnList[x] = new Column();
				}
				if (x % 3 == 0 && y % 3 == 0) {
					fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
							Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				singleFieldArray[y][x] = new SingleField();
				this.resetAvailableNumbers(singleFieldArray[y][x]
						.getAvailableNumbers());
				rowList[y].addField(singleFieldArray[y][x]);
				columnList[x].addField(singleFieldArray[y][x]);
				fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
						Math.floor(x / 3.0)).intValue()]
						.addField(singleFieldArray[y][x]);
			}
		}

		Integer currentNumber = 0;
		int x = 0;
		Random random = new Random();
		boolean fieldGenerated = false;
		while (!fieldGenerated) {
			while (x < 81) {
				SingleField currentField = singleFieldArray[new Double(
						Math.floor(x / 9)).intValue()][x % 9];
				if (currentField.getAvailableNumbers().size() > 0) {
					currentNumber = currentField.getAvailableNumbers().get(
							random.nextInt(currentField.getAvailableNumbers()
									.size()));
					if (service.conflict(
							rowList[new Double(Math.floor(x / 9)).intValue()],
							columnList[x % 9],
							fieldList[new Double(Math.floor((new Double(Math
									.floor(x / 9)).intValue()) / 3.0))
									.intValue()][new Double(Math
									.floor((x % 9) / 3.0)).intValue()],
							currentNumber)) {
						currentField.getAvailableNumbers()
								.remove(currentNumber);

					} else {
						currentField.setValue(currentNumber);
						x++;
					}
				} else {
					this.resetAvailableNumbers(currentField
							.getAvailableNumbers());
					currentField.setValue(0);
					x--;
				}
			}
			clone.cloneSudokuField(singleFieldArray);
			int index = 0;
			int regenerateIndex = 0;
			int xValue = 0;
			int yValue = 0;
			while (index < difficulty) {
				xValue = random.nextInt(9);
				yValue = random.nextInt(9);
				SingleField currentField = clone.getClonedSingleFieldArray()[yValue][xValue];
				regenerateIndex++;
				//System.out.println(regenerateIndex);
				if (currentField.getValue() != 0) {
					int oldValue = currentField.getValue();
					currentField.setValue(0);
					if (service
							.fieldIsReproducible(
									clone.getClonedRowArray()[yValue],
									clone.getClonedColumnArray()[xValue],
									clone.getClonedGameFieldArray()[new Double(
											Math.floor((new Double(Math
													.floor(yValue / 3))
													.intValue()) / 3.0))
											.intValue()][new Double(Math
											.floor(xValue / 3.0)).intValue()])) {
						index++;
					} else {
						currentField.setValue(oldValue);
					}
				}
				int oldIndex = index;
				if (regenerateIndex > 1000) {
					index = difficulty;
				}
				if (oldIndex == difficulty) {
					fieldGenerated = true;
				}
			}

		}
		this.setChanged();
		this.notifyObservers(clone.getClonedSingleFieldArray());
	}
	
	public void loadData(int[][][] field) {
		for (int y = 0; y < 9; y++) {
			rowList[y] = new Row();
			for (int x = 0; x < 9; x++) {
				if (y == 0) {
					columnList[x] = new Column();
				}
				if (x % 3 == 0 && y % 3 == 0) {
					fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
							Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				singleFieldArray[y][x] = new SingleField();
				this.resetAvailableNumbers(singleFieldArray[y][x]
						.getAvailableNumbers());
				rowList[y].addField(singleFieldArray[y][x]);
				columnList[x].addField(singleFieldArray[y][x]);
				fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
						Math.floor(x / 3.0)).intValue()]
						.addField(singleFieldArray[y][x]);
			}
		}
		clone.cloneSudokuField(singleFieldArray);
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				clone.getClonedSingleFieldArray()[y][x].setValue(field[0][y][x]);
			}
		}
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				singleFieldArray[y][x].setValue(field[1][y][x]);
			}
		}
		this.setChanged();
		this.notifyObservers(clone.getClonedSingleFieldArray());
	}
	
	public void saveData(File file) {
		SaveAndLoad dataOperation = new SaveAndLoad();
		int[][][] data = new int[2][9][9];
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				data[0][y][x] = clone.getClonedSingleFieldArray()[y][x].getValue();
			}
		}
		for(int y = 0; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				data[1][y][x] = singleFieldArray[y][x].getValue();
			}
		}
		dataOperation.Save(data, file);
	}

	private void resetAvailableNumbers(ArrayList<Integer> availableNumbers) {
		availableNumbers.clear();
		for (Integer i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
	}

	public boolean gameIsWon(Integer[][] field) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!new Integer(singleFieldArray[y][x].getValue())
						.equals(field[y][x])) {
					return false;
				}
			}
		}
		return true;
	}
}
