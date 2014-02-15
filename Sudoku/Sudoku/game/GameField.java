package game;

import java.util.ArrayList;

public class GameField extends FieldObject {

	private int fieldCoordinateX;
	private int fieldCoordinateY;
	private ArrayList<SingleField> fieldArray;

	public ArrayList<SingleField> getFieldArray() {
		return fieldArray;
	}
	public void addField(SingleField field) {
		fieldArray.add(field);
	}
	public int getFieldCoordinateY() {
		return fieldCoordinateY;
	}
	public void setFieldCoordinateY(int fieldCoordinateY) {
		this.fieldCoordinateY = fieldCoordinateY;
	}
	public int getFieldCoordinateX() {
		return fieldCoordinateX;
	}
	public void setFieldCoordinateX(int fieldCoordinateX) {
		this.fieldCoordinateX = fieldCoordinateX;
	}
}
