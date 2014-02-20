package game;

import java.util.ArrayList;

//Column Class, Beinhaltet SingleFields und repräsentiert eine Spalte
public class Column extends FieldObject {

	private ArrayList<SingleField> fieldArray;

	public Column() {
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
		for (SingleField elem : fieldArray) {
			inheritedNumbers.add(elem.getValue());
		}
		return inheritedNumbers;
	}
}
