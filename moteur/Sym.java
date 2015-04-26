package moteur;

public enum Sym {
	
	//#### SEMANTIQUE ####
	CONCAT, // point virgule
    DEBUT,
    FIN,
    EOF, // end of file
    
	//#### VARIABLES ####
	VAR,
	VARIABLE,
	EQ,
	
	//#### EXPRESSIONS ####
	INT,
    LPAR, // parenthese gauche
    RPAR, // parenthese droite
    LACC, // accolade gauche
    RACC, //  accolade droite
    
    //#### OPERATEURS ####
    PLUS, // addition
    MINUS, // soustraction
    TIMES, //multiplication
    DIV, //division
    
    //#### BOUCLES ####
    FOR,
    TANTQUE, // while
    FAIRE, // do
    
    //#### DESSIN ####
    EPAISSEUR,
    TOURNE,
    AVANCE,
    BASPINCEAU,
    HAUTPINCEAU,
    COULEUR,
    PRINT,
    TAILLE,
    
    //#### CONDITIONNELLES ####
    SI, //if
    ALORS, // then
    SINON, // else
    SINONSI; // elseif
	
}
