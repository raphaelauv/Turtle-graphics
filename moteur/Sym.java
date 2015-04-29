package moteur;

public enum Sym {
	
	//#### SEMANTIQUE ####
	CONCAT (";"), // point virgule
	VIRGULEINTER (";"), // point virgule rajoute par le mode interpret
	GO ("Go"), // pour aller chercher une position directement
    DEBUT ("Debut"),
    FIN ("Fin"),
    EOF (""), // end of file
    
	//#### VARIABLES ####
	VAR ("Var"),
	VARIABLE (""),
	EQ ("="),
	
	//#### EXPRESSIONS ####
	INT (""),
    LPAR ("("), // parenthese gauche
    RPAR (")"), // parenthese droite
    LACC ("{"), // accolade gauche
    RACC ("}"), //  accolade droite
    
    //#### OPERATEURS ####
    PLUS ("+"), // addition
    MINUS ("-"), // soustraction
    TIMES ("*"), //multiplication
    DIV ("/"), //division
    
    //#### BOUCLES ####
    FOR ("For"),
    TANTQUE ("Tant que"), // while
    FAIRE ("Faire"), // do
    
    //#### DESSIN ####
    EPAISSEUR ("Epaisseur"),
    TOURNE ("Tourne"),
    AVANCE ("Avance"),
    BASPINCEAU ("BasPinceau"),
    HAUTPINCEAU ("HautPinceau"),
    COULEUR ("Couleur"),
    TAILLE ("Taille"),
    
    //#### CONDITIONNELLES ####
    SI ("Si"), //if
    ALORS ("Alors"), // then
    SINON ("Sinon"), // else
    SINONSI ("Sinon si"); // elseif
	
	private String sym;
	
	Sym(String Sym){
		this.sym=Sym;
	}
	public String getPseudoCode() {
		return sym;
	}
}
