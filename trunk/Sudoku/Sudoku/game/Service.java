package game;

import java.util.ArrayList;

public class Service {

	public void generateAvailableNumbers(Row row, int yCoordinate, int xCoordinate, Column column, GameField gameField, ArrayList<Integer> availableNumbers) {
		availableNumbers.clear();
		for(int i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
		for(Integer num : row.getInheritedNumbers()) {
			if(availableNumbers.contains(num)) {
				availableNumbers.remove(num);
			}
		}
		for(Integer num : column.getInheritedNumbers()) {
			if(availableNumbers.contains(num)) {
				availableNumbers.remove(num);
			}
		}
//		if(availableNumbers.contains(column.getInheritedNumbers().get(yCoordinate))) {
//			availableNumbers.remove(column.getInheritedNumbers().get(yCoordinate));
//		}
		for(Integer num : gameField.getInheritedNumbers()) {
			if(availableNumbers.contains(num)) {
				availableNumbers.remove(num);
			}
		}
	}
}
