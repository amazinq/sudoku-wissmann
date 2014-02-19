package consoleGUI;

public class ErrorCheck {

	public boolean isAnInteger(String x, String y, String input) {
		try {
			Integer.parseInt(x);
			Integer.parseInt(y);
			Integer.parseInt(input);
			return true;
		} catch(NumberFormatException e ) {
			return false;
		}
	}
	public boolean isInRange(int x, int y, int input) {
		if(x < 1 || x > 9 || y < 1 || y > 9 || input < 1 || input > 9) {
			return false;
		}
		return true;
	}
}
