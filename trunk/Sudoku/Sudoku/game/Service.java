package game;

import java.util.ArrayList;
import java.util.HashSet;

public class Service {

	public boolean conflict(Row row, Column column, GameField gameField, Integer currentNumber) {

		for(Integer num : column.getInheritedNumbers()) {
			if(currentNumber.equals(num)) {
				return true;
			}
		}
		for(Integer num : gameField.getInheritedNumbers()) {
			if(currentNumber.equals(num)) {
				return true;
			}
		}
		for(Integer num : row.getInheritedNumbers()) {
			if(currentNumber.equals(num)) {
				return true;
			}
		}
		return false;
	}
	public boolean fieldIsReproducible(Row row, Column column, GameField gameField) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(Integer elem : row.getInheritedNumbers()) {
			if(!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		for(Integer elem : column.getInheritedNumbers()) {
			if(!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		for(Integer elem : gameField.getInheritedNumbers()) {
			if(!numbers.contains(elem) && elem != 0) {
				numbers.add(elem);
			}
		}
		
		if(numbers.size() == 8) {
			return true;
		}
		return false;
		
		
	}
}
