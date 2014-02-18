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
		
		for(SingleField elem : singleFieldArray[0]) {
			Integer currentNumber = availableNumbers.get(new Double(Math.floor(Math.random()*availableNumbers.size())).intValue());
			elem.setValue(currentNumber);
			availableNumbers.remove(currentNumber);
		}
		
		this.resetAvailableNumbers();
		for(int y = 1; y < 9; y++) {
			for(int x = 0; x < 9; x++) {
				SingleField currentField = singleFieldArray[y][x];
				System.out.println(availableNumbers.size());
				if(availableNumbers.size() != 0) {
					Integer currentNumber = availableNumbers.get(new Double(Math.floor(Math.random()*availableNumbers.size())).intValue());
					if(service.doesConflict(rowList[y], (y-1), x, columnList[x], fieldList[new Double(Math.floor((y-1) / 3.0)).intValue()][new Double(Math.floor(x / 3.0)).intValue()], currentNumber)) {
						//System.out.println("conflict");
						availableNumbers.remove(currentNumber);
						if(x > 2) {
							x = x-2;
						} else {
							x = 0;
						}
					} else {
						currentField.setValue(currentNumber);
						//System.out.println("no conflict");
						//availableNumbers.remove(currentNumber);
						this.resetAvailableNumbers();
					}
					
				}
//				else {
//					if((x) < 2) {
//						x = 7;
//						if((y) > 1) {
//							y--;
//						}
//					} else {
//						x = x-2;
//					}
//				}
			}
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
