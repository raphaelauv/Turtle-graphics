package moteur;

public class LexerException extends Exception {
	public LexerException(String character ,int line, int column) {
		super("caractere innatendu   "+character +"   at line " + line + " column " + column + ".");
	}
	
	public LexerException(int line, int column , String erreur) {
		super(erreur+" at line " + line + " column " + column + ".");
	}
}