package consoleGUI;

import java.util.Observable;

import controller.GUI_Interface;
import game.SingleField;

public class ConsoleController implements GUI_Interface {
	
	private SingleField [][] singleFieldArray;
	private static ConsoleController instance;
	
	
	public static ConsoleController getInstance() {
		if(instance == null) {
			instance = new ConsoleController();
		}
		return instance;
	}

	protected SingleField[][] getSingleFieldArray() {
		return singleFieldArray;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		singleFieldArray = (SingleField[][]) arg1;
		
	} 
	
	
	
	
}
