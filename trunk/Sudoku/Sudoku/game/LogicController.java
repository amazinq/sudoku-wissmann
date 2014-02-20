package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JLabel;

import consoleGUI.ConsoleController;
import consoleGUI.ConsoleGUI;
import dataOperation.SaveAndLoad;
import swingGUI.MainFrame;

//Führt die gesamte Spiellogik aus und ist Observable
public class LogicController extends Observable {

	private Column[] columnList;
	private Row[] rowList;
	private GameField[][] fieldList;
	private SingleField[][] singleFieldArray;

	private Service service;
	private Clone clone;

	public LogicController() {
		columnList = new Column[9];
		rowList = new Row[9];
		fieldList = new GameField[3][3];
		singleFieldArray = new SingleField[9][9];

		service = new Service();
		clone = new Clone();

		addObserver(MainFrame.getInstance());
		addObserver(ConsoleController.getInstance());
	}

	// Generiert ein neues Spielfeld
	public void generateGameField(int difficulty) {
		// Generation der Spielfeld Objekte
		for (int y = 0; y < 9; y++) {
			rowList[y] = new Row();
			for (int x = 0; x < 9; x++) {
				if (y == 0) {
					columnList[x] = new Column();
				}
				if (x % 3 == 0 && y % 3 == 0) {
					fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
							Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				singleFieldArray[y][x] = new SingleField();
				this.resetAvailableNumbers(singleFieldArray[y][x]
						.getAvailableNumbers());
				rowList[y].addField(singleFieldArray[y][x]);
				columnList[x].addField(singleFieldArray[y][x]);
				fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
						Math.floor(x / 3.0)).intValue()]
						.addField(singleFieldArray[y][x]);
			}
		}

		Integer currentNumber = 0;
		int x = 0;
		Random random = new Random();
		boolean fieldGenerated = false;
		// Solange kein gültiges Spielfeld generiert ist, werden neue Felder
		// generiert
		while (!fieldGenerated) {
			// 81 Iterationen für 81 Felder
			while (x < 81) {
				// Aktuelles angewähltes Feld
				SingleField currentField = singleFieldArray[new Double(
						Math.floor(x / 9)).intValue()][x % 9];
				// Überprüfung ob noch Nummern für dieses Feld verfügbar sind
				if (currentField.getAvailableNumbers().size() > 0) {
					// Wählt aus den möglichen Nummern eine zufällige aus
					currentNumber = currentField.getAvailableNumbers().get(
							random.nextInt(currentField.getAvailableNumbers()
									.size()));
					// Überprüft ob das einsetzen der ausgewählten nummer zu
					// konflikten führt (Spielregeln!)
					if (service.conflict(
							rowList[new Double(Math.floor(x / 9)).intValue()],
							columnList[x % 9],
							fieldList[new Double(Math.floor((new Double(Math
									.floor(x / 9)).intValue()) / 3.0))
									.intValue()][new Double(Math
									.floor((x % 9) / 3.0)).intValue()],
							currentNumber)) {
						// Entfernt die ziffer aus dem datencontainer weil sie
						// für dieses Feld nicht möglich ist
						currentField.getAvailableNumbers()
								.remove(currentNumber);

					} else {
						// Wenn keine Konflikte auftreten, wird die ziffer für
						// das aktuelle Feld gesetzt und der iterator um 1
						// erhöht
						currentField.setValue(currentNumber);
						x++;
					}
				} else {
					// Wenn keine zahlen mehr vorhanden sind, wird der
					// datencontainer zurückgesetzt und der algorithmus geht ein
					// Feld zurück
					this.resetAvailableNumbers(currentField
							.getAvailableNumbers());
					currentField.setValue(0);
					x--;
				}
			}
			// Feld klonen
			clone.cloneSudokuField(singleFieldArray);
			int index = 0;
			int regenerateIndex = 0;
			int xValue = 0;
			int yValue = 0;
			// Es werden je nach schwierigkeitsgrad zahlen entfernt
			while (index < difficulty) {
				// Zufällige Position
				xValue = random.nextInt(9);
				yValue = random.nextInt(9);
				// Setzt das aktuelle Feld
				SingleField currentField = clone.getClonedSingleFieldArray()[yValue][xValue];
				regenerateIndex++;
				// Prüft ob das Feld schon entfernt wurde
				if (currentField.getValue() != 0) {
					// Speichert die aktuelle variable des Feldes
					int oldValue = currentField.getValue();
					// setzt die Variable des feldes auf 0
					currentField.setValue(0);
					// Prüft ob das entfernte Feld mit den noch vorhanden Zahlen
					// reproduzierbar ist
					if (service
							.fieldIsReproducible(
									clone.getClonedRowArray()[yValue],
									clone.getClonedColumnArray()[xValue],
									clone.getClonedGameFieldArray()[new Double(
											Math.floor((new Double(Math
													.floor(yValue / 3))
													.intValue()) / 3.0))
											.intValue()][new Double(Math
											.floor(xValue / 3.0)).intValue()])) {
						// Nächstes Feld
						index++;
					} else {
						// Wenn nicht, wird der Zustand des Feldes
						// wiederhergestellt
						currentField.setValue(oldValue);
					}
				}
				int oldIndex = index;
				// Nach 1000 Iterationen kann mit fast 100%tiger sicherheit
				// gesagt werden, dass das Spielfeld nicht erzeugt werden kann.
				// In dem Fall wird ein komplett neues Spielfeld erzeugt
				if (regenerateIndex > 1000) {
					index = difficulty;
				}
				// Wenn genügend Felder entfernt wurden, wird die Feldgeneration
				// abgebrochen
				if (oldIndex == difficulty) {
					fieldGenerated = true;
				}
			}

		}
		// Übergabe des generierten Feldes an die View
		this.setChanged();
		this.notifyObservers(clone.getClonedSingleFieldArray());
	}

	// Ladenalgorithmus
	public void loadData(int[][][] field) {
		// Erzeugen der benötigten Spielfeld Objekte
		for (int y = 0; y < 9; y++) {
			rowList[y] = new Row();
			for (int x = 0; x < 9; x++) {
				if (y == 0) {
					columnList[x] = new Column();
				}
				if (x % 3 == 0 && y % 3 == 0) {
					fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
							Math.floor(x / 3.0)).intValue()] = new GameField();
				}
				singleFieldArray[y][x] = new SingleField();
				this.resetAvailableNumbers(singleFieldArray[y][x]
						.getAvailableNumbers());
				rowList[y].addField(singleFieldArray[y][x]);
				columnList[x].addField(singleFieldArray[y][x]);
				fieldList[new Double(Math.floor(y / 3.0)).intValue()][new Double(
						Math.floor(x / 3.0)).intValue()]
						.addField(singleFieldArray[y][x]);
			}
		}
		clone.cloneSudokuField(singleFieldArray);
		// Setzt die Werte für das ungelöste Sudokufeld
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				clone.getClonedSingleFieldArray()[y][x]
						.setValue(field[0][y][x]);
			}
		}
		// Setzt die Werte für das gelöste Sudokufeld
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				singleFieldArray[y][x].setValue(field[1][y][x]);
			}
		}
		// Übergabe des Spielfeldes an die View
		this.setChanged();
		this.notifyObservers(clone.getClonedSingleFieldArray());
	}

	// SaveAlgorithmus
	public void saveData(File file, JLabel[][] labelArray) {
		SaveAndLoad dataOperation = new SaveAndLoad();
		int[][][] data = new int[2][9][9];
		// Übertragen der Werte aus der View in ein integer Array
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				// Wenn der text nicht in einen Int konvertierbar ist, handelt
				// es sich um eine 0
				try {
					data[0][y][x] = Integer
							.parseInt(labelArray[y][x].getText());
				} catch (NumberFormatException e) {
					data[0][y][x] = 0;
				}

			}
		}
		// Übertragen der Werte des gelösten Sudokufeldes in ein integer array
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				data[1][y][x] = singleFieldArray[y][x].getValue();
			}
		}
		// übergabe der Daten an das SaveAndLoad Objekt zur Datenspeicherung
		dataOperation.Save(data, file);
	}

	// Setzt die noch möglichen Nummern zurück
	private void resetAvailableNumbers(ArrayList<Integer> availableNumbers) {
		availableNumbers.clear();
		for (Integer i = 1; i < 10; i++) {
			availableNumbers.add(i);
		}
	}

	// Überprüft ob das Feld gelöst wurde
	public boolean gameIsWon(Integer[][] field) {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!new Integer(singleFieldArray[y][x].getValue())
						.equals(field[y][x])) {
					return false;
				}
			}
		}
		return true;
	}
}
