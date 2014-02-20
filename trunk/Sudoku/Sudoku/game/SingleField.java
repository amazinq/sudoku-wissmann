package game;

import java.util.ArrayList;

//Repräsentiert ein einzelnes Feld auf dem Spielfeld, enthält eine Zahl
public class SingleField extends FieldObject {

	private int value;
	private ArrayList<Integer> availableNumbers;

	public SingleField() {
		availableNumbers = new ArrayList<Integer>();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public ArrayList<Integer> getAvailableNumbers() {
		return availableNumbers;
	}
}
