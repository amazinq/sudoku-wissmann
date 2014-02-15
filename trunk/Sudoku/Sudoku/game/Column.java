package game;

import java.util.ArrayList;

public class Column extends FieldObject {

	private int columnNumber;
	private ArrayList<SingleField> fieldArray;

	public ArrayList<SingleField> getFieldArray() {
		return fieldArray;
	}
	public void addField(SingleField field) {
		fieldArray.add(field);
	}
	public int getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}
}
