package moteur;

import java.io.Reader;

import moteur.expression.ProgramPrincipal;
import controleur.ControleurAvance;
import controleur.ControleurListeToken;
import controleur.ControleurPinceau;
import controleur.ControleurTourne;
import controleur.FenetreErreur;

public class Moteur {
	private Lexer lexer;
	private LookAhead1 look;
    private ValueEnvironment listeVariable = new ValueEnvironment();
    private Parser parser;
	private ProgramPrincipal ProgramPrincipal;
	protected FenetreErreur fenetreErreur;
	
	public Moteur(Reader reader) throws Exception{
		lexer = new Lexer(reader);
        look = new LookAhead1(lexer);
        parser = new Parser(look);
        
	}

	public void analyseSynt() throws Exception{
		if (this.parser==null){
			throw new Exception("une analyse est essayer sur un Parseur absent");
		}
		this.ProgramPrincipal = parser.nontermCode();
		
	}
	
	public void parcourtArbre() throws Exception{
		if (this.ProgramPrincipal==null){
			throw new Exception("un parcourt est essayer sur un prog absent");
		}
			ProgramPrincipal.run(listeVariable);
		
		
	}

	public String getCouleur(){
		return this.listeVariable.getCouleur();
	}
	
	public int angle() throws Exception{
		return this.listeVariable.getAngleActuel();
	}
	
	public void setControleurAvance(ControleurAvance avance){
		this.listeVariable.setAvance(avance);
	}

	public void setControleurPinceau(ControleurPinceau controleurPinceau) {
		this.listeVariable.setPinceau(controleurPinceau);
		
	}
	public void setControleurListeToken(
			ControleurListeToken controleurListeToken) {
		this.look.setControleur(controleurListeToken);
		
	}

	public void setFenetreErreur(FenetreErreur fenetreErreur) {
		this.fenetreErreur=fenetreErreur;
	}
	
	public String getString(){
		return ProgramPrincipal.getString(listeVariable,2);
	}
	
	public String getFirstString(String nomClasse) throws Exception{
		return ProgramPrincipal.getFistString(nomClasse);
	}
	
	public String getLastString(){
		return ProgramPrincipal.getLastString();
	}
	public static String stringRepeat(String chaine, int multiplicateur){
		if (multiplicateur > 1) {
			String tmp=chaine;
			String tmp2 = chaine;
			for (int i = 1; i < multiplicateur; i++) {
				tmp = tmp.concat(tmp2);
			}
			return tmp;
		} else  {
			return chaine;
		}
	}
	
}
