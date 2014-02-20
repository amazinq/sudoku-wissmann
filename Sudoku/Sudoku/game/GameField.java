package game;

import java.util.ArrayList;

//Repräsentiert jeweils ein 3x3 Feld auf dem Spielfeld, enthält Singlefields
public class GameField extends FieldObject {

	private ArrayList<SingleField> fieldArray;
	
	public GameField() {
		fieldArray = new ArrayList<SingleField>();
	}

	public ArrayList<SingleField> getFieldArray() {
		return fieldArray;
	}
	public void addField(SingleField field) {
		fieldArray.add(field);
	}
	public ArrayList<Integer> getInheritedNumbers() {
		ArrayList<Integer> inheritedNumbers = new ArrayList<Integer>();
		for(SingleField elem : fieldArray) {
			inheritedNumbers.add(elem.getValue());
		}
		return inheritedNumbers;
	}
}
