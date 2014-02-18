package game;

import java.util.ArrayList;

public class Service {

	public boolean doesConflict(Row row, Column column, GameField gameField, Integer currentNumber) {

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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//		for(Integer num : row.getInheritedNumbers()) {
//			if(availableNumbers.contains(num)) {
//				availableNumbers.remove(num);
//			}
//		}
//		for(Integer num : column.getInheritedNumbers()) {
//			if(availableNumbers.contains(num)) {
//				availableNumbers.remove(num);
//			}
//		}
//		if(availableNumbers.contains(column.getInheritedNumbers().get(yCoordinate))) {
//			availableNumbers.remove(column.getInheritedNumbers().get(yCoordinate));
//		}
//		for(Integer num : gameField.getInheritedNumbers()) {
//			if(availableNumbers.contains(num)) {
//				availableNumbers.remove(num);
//			}
//		}
	}
}
