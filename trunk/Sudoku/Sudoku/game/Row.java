package game;

import java.util.ArrayList;

public class Row extends FieldObject {

	private int rowNumber;
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
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
}
