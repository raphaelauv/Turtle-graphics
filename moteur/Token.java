package moteur;

public class Token {
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
    
    public String getSpeudoCode(){
    	return this.symbol.getPseudoCode();
    }
    
    public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
}

