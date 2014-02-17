package game;

import java.util.ArrayList;
import java.util.Observable;

import swingGUI.MainFrame;

public class LogicController extends Observable {
	
	private Column[] columnList;
	private Row[] rowList;
	private GameField[][] fieldList;
	private SingleField[][] singleFieldArray;
	
	public LogicController() {
		columnList = new Column[9];
		rowList = new Row[9];
		fieldList = new GameField[3][3];
		singleFieldArray = new SingleField[9][9];
		
		addObserver(MainFrame.getInstance());
	}
	
	public void generateGameField() {
		ArrayList<Integer> availableNumbers = new ArrayList<Integer>();
		for(int y = 0; y < 9; y++) {
			rowList[y] = new Row();
			for(int x = 0; x < 9; x++) {
				if(y == 0) {
					columnList[x] = new Column();
				}
				if(x % 3 == 0 && y % 3 == 0) {
					fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				singleFieldArray[y][x] = new SingleField();
				rowList[y].addField(singleFieldArray[y][x]);
				columnList[x].addField(singleFieldArray[y][x]);
				fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(Math.floor(x / 3.0)).intValue()].addField(singleFieldArray[y][x]);
			}
		}
		for(int i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
		for(SingleField elem : singleFieldArray[0]) {
			Integer currentNumber = availableNumbers.get(new Double(Math.floor(Math.random()*availableNumbers.size())).intValue());
			elem.setValue(currentNumber);
			availableNumbers.remove(currentNumber);
		}
		// Erste zeile generiert 
	}
}
