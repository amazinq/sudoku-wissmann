package game;

import java.util.ArrayList;

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
}
