package moteur;

public class ParserException extends Exception {
	public ParserException(String s) {
		super("'" + s + "' not expected.");
	}
}