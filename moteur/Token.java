package moteur;

class Token {
    protected Sym symbol;
    protected int line;
    protected int column;
    public Token(Sym s, int line , int column) {
    	
    	symbol=s;
    	this.line=line;
    	this.column=column;
    }
    public Sym symbol() {
    	return symbol;
    }
    public boolean isEqual(Token t){
    	return (t.symbol !=this.symbol);
    }
    public String toString(){
    	
    	String tmp="";
    	tmp=tmp+this.symbol+"\t\t";
    	return tmp+"| Ligne : " +this.line + " \t\t| position : "+this.column ;
    }
    
    public String getStringSym(){
    	return ""+this.symbol;
    }
}

class IntToken extends Token {
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

class VarToken extends Token {
    private String nom;
	public VarToken(Sym s ,int line , int column, String nom){

		super(s,line , column);
		this.nom=nom;
		
	}
	public String getValue() {
		return nom;
		
	}
	
}

