package jGUI;

import java.io.File;
import javax.swing.JFileChooser;

// Filechooser klasse, stellt einen FileChooser jeweils fürs Speichern und Laden von Daten bereit
public class FileChooser {

	// Öffnet einen Filechooser zum speichern von Daten. Gibt ein Fileobjekt
	// zurück
	protected File generateSaveDialog() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				return fileChooser.getSelectedFile();
			}
		} catch (Exception e) {

		}
		return null;
	}

	// Öffnet einen Filechooser zum laden von Daten. Gibt ein Fileobjekt zurück
	protected File generateOpenDialog() {
		try {
			JFileChooser fileChooser = new JFileChooser();
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				return fileChooser.getSelectedFile();
			}
		} catch (Exception e) {

		}
		return null;
	}
}
