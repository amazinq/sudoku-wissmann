package game;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

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
	}

	public void generateGameField() {
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
				this.resetAvailableNumbers(singleFieldArray[y][x].getAvailableNumbers());
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
			while (x < 81) {
				SingleField currentField = singleFieldArray[new Double(Math.floor(x / 9)).intValue()][x % 9];
				if (currentField.getAvailableNumbers().size() > 0) {
					currentNumber = currentField.getAvailableNumbers().get(random.nextInt(currentField.getAvailableNumbers().size()));
					if (service
							.conflict(
									rowList[new Double(Math.floor(x / 9)).intValue()],
									columnList[x%9],
									fieldList[new Double(Math.floor((new Double(Math.floor(x / 9)).intValue()) / 3.0))
											.intValue()][new Double(Math
											.floor((x%9) / 3.0)).intValue()],
									currentNumber)) {
						currentField.getAvailableNumbers().remove(currentNumber);

					} else {
						currentField.setValue(currentNumber);
						x++;
					}
				} else {
					this.resetAvailableNumbers(currentField.getAvailableNumbers());
					currentField.setValue(0);
						x--;
				}
			}
		clone.cloneSudokuField(singleFieldArray);
		this.setChanged();
		this.notifyObservers(singleFieldArray);
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
