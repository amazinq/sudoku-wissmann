package game;


public class Clone {
	
	private SingleField[][] clonedSingleFieldArray;
	private Row[] clonedRowArray;
	private Column[] clonedColumnArray;
	private GameField[][] clonedGameFieldArray;
	
	
	public Clone() {
		clonedSingleFieldArray = new SingleField[9][9];
		clonedRowArray = new Row[9];
		clonedColumnArray = new Column[9];
		clonedGameFieldArray = new GameField[3][3];
	}

	public void cloneSudokuField(SingleField[][] singleFieldArray) {
		for (int y = 0; y < 9; y++) {
			clonedRowArray[y] = new Row();
			for (int x = 0; x < 9; x++) {
				if (y == 0) {
					clonedColumnArray[x] = new Column();
				}
				if (x % 3 == 0 && y % 3 == 0) {
					clonedGameFieldArray[new Double(Math.floor(y / 3.0)).intValue()][new Double(
							Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				clonedSingleFieldArray[y][x] = new SingleField();
				clonedRowArray[y].addField(clonedSingleFieldArray[y][x]);
				clonedColumnArray[x].addField(clonedSingleFieldArray[y][x]);
				clonedGameFieldArray[new Double(Math.floor(y / 3.0)).intValue()][new Double(
						Math.floor(x / 3.0)).intValue()]
						.addField(clonedSingleFieldArray[y][x]);
				clonedSingleFieldArray[y][x].setValue(singleFieldArray[y][x].getValue());
			}
		}
	}

	protected SingleField[][] getClonedSingleFieldArray() {
		return clonedSingleFieldArray;
	}

	protected Row[] getClonedRowArray() {
		return clonedRowArray;
	}

	protected Column[] getClonedColumnArray() {
		return clonedColumnArray;
	}

	protected GameField[][] getClonedGameFieldArray() {
		return clonedGameFieldArray;
	}
}
