package dataOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import game.GameField;

//Speichern und Laden von daten
public class SaveAndLoad {

	private BufferedReader reader;
	private FileWriter writer;

	// Speichert das Spielfeld
	public void Save(int[][][] data, File file) {
		try {
			writer = new FileWriter(file + ".txt");
			String lineSeparator = System.getProperty("line.separator");
			// Header zur überprüfung beim laden
			writer.write("Sudoku Saved Data" + lineSeparator);
			// Speichert das momentane und das gelöste spielfeld ab
			for (int[][] data2 : data) {
				for (int[] data1 : data2) {
					for (int num : data1) {
						writer.write(num + ",");
					}
					writer.write(lineSeparator);
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Läd Daten
	public int[][][] Load(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][][] loadedData = new int[2][9][9];
		try {
			// Wenn der Header vorhanden ist, werden das Aktuelle Spielfeld und
			// das gelöste Spielfeld geladen
			if (reader.readLine().equals("Sudoku Saved Data")) {
				int z = 0;
				int y = 0;
				int x = 0;
				for (String lineString = reader.readLine(); lineString != null; lineString = reader
						.readLine(), y++) {
					for (String s : lineString.split(",")) {
						loadedData[z][y][x] = Integer.parseInt(s);
						x++;
					}
					x = 0;
					if (y == 8) {
						y = -1;
						z++;
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Wrong File chosen!",
						"Fehler", JOptionPane.ERROR_MESSAGE);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loadedData;
	}
}
