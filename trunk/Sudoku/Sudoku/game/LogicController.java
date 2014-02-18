package game;

import java.util.ArrayList;
import java.util.Observable;

import swingGUI.MainFrame;

public class LogicController extends Observable {
	
	private Column[] columnList;
	private Row[] rowList;
	private GameField[][] fieldList;
	private SingleField[][] singleFieldArray;
	private ArrayList<Integer> availableNumbers;
	
	private Service service;
	
	public LogicController() {
		columnList = new Column[9];
		rowList = new Row[9];
		fieldList = new GameField[3][3];
		singleFieldArray = new SingleField[9][9];
		
		service = new Service();
		
		addObserver(MainFrame.getInstance());
	}
	
	public void generateGameField() {
		availableNumbers = new ArrayList<Integer>();
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
			availableNumbers.add(y + 1);
		}
		
//		for(SingleField elem : singleFieldArray[0]) {
//			Integer currentNumber = availableNumbers.get(new Double(Math.floor(Math.random()*availableNumbers.size())).intValue());
//			elem.setValue(currentNumber);
//			availableNumbers.remove(currentNumber);
//		}
		
		this.resetAvailableNumbers();
		Integer currentNumber = 0;
		int g = 0;
		int y = 0;
		int x = 0;
		while (y < 9) {
			while (x < 9) {
				SingleField currentField = singleFieldArray[y][x];
				if(availableNumbers.size() != 0) {
					currentNumber = availableNumbers.get(new Double(Math.floor(Math.random()*availableNumbers.size())).intValue());
					if(service.doesConflict(rowList[y], columnList[x], fieldList[new Double(Math.floor((y) / 3.0)).intValue()][new Double(Math.floor(x / 3.0)).intValue()], currentNumber)) {
						availableNumbers.remove(currentNumber);
						
							
					} else {
						currentField.setValue(currentNumber);
						//availableNumbers.remove(currentNumber);
						this.resetAvailableNumbers();
						x++;
					}
				} else { 
					if(x >= 1) {
						x--;
					} else {
						x = 8;
						if(y > 0) {
							y--;	
						} else {
							y = 0;
						}
					}
					this.resetAvailableNumbers();
				}
				if(g > 1000000) {
					x = 9;
					y = 9;
				}
				g++;
			}
			y++;
			x = 0;
		}
		this.setChanged();
		this.notifyObservers(singleFieldArray);
	}
	private void resetAvailableNumbers() {
		availableNumbers.clear();
		for (Integer i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
	}
}
