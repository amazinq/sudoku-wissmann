package swingGUI;

import javax.swing.JLabel;

public class Converter {

	// Konvertiert die Werte aus dem Labelarray in ein int array
	public Integer[][] toIntArray(JLabel[][] labelArray) {
		Integer[][] field = new Integer[9][9];
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				try {
					field[y][x] = Integer.parseInt(labelArray[y][x].getText());
				} catch (NumberFormatException e) {
					field[y][x] = 0;
				}
			}
		}
		return field;
	}
}
