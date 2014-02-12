package game;

import java.util.ArrayList;

public class Row {

	private ArrayList<SingleField> fieldArray;

	public ArrayList<SingleField> getFieldArray() {
		return fieldArray;
	}
	public void addField(SingleField field) {
		fieldArray.add(field);
	}
}
