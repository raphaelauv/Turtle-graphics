package moteur;


public class IntToken extends Token {
    private int value;
	
	public IntToken(Sym s ,int line , int column, int v){
		
		super(s,line,column);
		this.value=v;
}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	
}
}


