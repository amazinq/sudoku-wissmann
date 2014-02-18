package game;

import java.util.ArrayList;

public class Service {

	public void generateAvailableNumbers(Row row, int yCoordinate, int xCoordinate, Column column, GameField gameField, ArrayList<Integer> availableNumbers) {
		availableNumbers.clear();
		for(int i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
		if(availableNumbers.contains(row.getInheritedNumbers().get(xCoordinate))) {
			availableNumbers.remove(row.getInheritedNumbers().get(xCoordinate));
		}
	}
}
