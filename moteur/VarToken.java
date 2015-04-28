package moteur;

public class VarToken extends Token {
    private String nom;
	public VarToken(Sym s ,int line , int column, String nom){
		super(s,line , column);
		this.nom=nom;
		
	}
	public String getValue() {
		return nom;
	}
	
}