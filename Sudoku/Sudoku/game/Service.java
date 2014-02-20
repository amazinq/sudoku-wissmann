package game;

import java.util.ArrayList;
import java.util.HashSet;

//Stellt Servicemethoden bereit
public class Service {

	// Überprüft anhand der mit dem einzelnen ausgewählten Feld verbundenen
	// Objekte(gemäß den Sudoku regeln) ob die ausgewählte Zahl konflikte
	// erzeugt
	public boolean conflict(Row row, Column column, GameField gameField,
			Integer currentNumber) {

		// Überprüfung des column objektes
		for (Integer num : column.getInheritedNumbers()) {
			if (currentNumber.equals(num)) {
				return true;
			}
		}
		// überprüfung des gameField objektes
		for (Integer num : gameField.getInheritedNumbers()) {
			if (currentNumber.equals(num)) {
				return true;
			}
		}
		// überprüfung des Row objektes
		for (Integer num : row.getInheritedNumbers()) {
			if (currentNumber.equals(num)) {
				return true;
			}
		}
		return false;
	}

	// Prüft ob ein entferntes feld anhand der Objekte mit denen es verbunden
	// ist reproduzierbar ist
	public boolean fieldIsReproducible(Row row, Column column,
			GameField gameField) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (Integer elem : row.getInheritedNumbers()) {
			if (!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		for (Integer elem : column.getInheritedNumbers()) {
			if (!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		for (Integer elem : gameField.getInheritedNumbers()) {
			if (!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		// Wenn das Karthesische Produkt der enthaltenen Zahlen in den Objekten
		// = 8 ist, ist das Feld eindeutig reproduzierbar
		if (numbers.size() == 8) {
			return true;
		}
		return false;

	}
}
