package game;

import java.util.ArrayList;

public class Row extends FieldObject {

	private ArrayList<SingleField> fieldArray;

	public Row() {
		fieldArray = new ArrayList<SingleField>();
	}
	public ArrayList<SingleField> getFieldArray() {
		return fieldArray;
	}
	public void addField(SingleField field) {
		fieldArray.add(field);
	}
}