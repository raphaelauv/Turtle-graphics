package moteur;

import java.io.Reader;

import moteur.expression.ProgramPrincipal;
import controleur.ControleurListeToken;
import controleur.FenetreErreur;
import controleur.GUI.ControleurAvance;
import controleur.GUI.ControleurCouleur;
import controleur.GUI.ControleurDimension;
import controleur.GUI.ControleurEpaisseur;
import controleur.GUI.ControleurPinceau;
import controleur.GUI.ControleurTourne;

public class Moteur {
	private Lexer lexer;
	private LookAhead1 look;
    private ValueEnvironment listeVariable;
	private Parser parser;
	private ProgramPrincipal ProgramPrincipal;
	
    public ValueEnvironment getListeVariable() {
		return listeVariable;
	}
	public void setListeVariable(ValueEnvironment listeVariable) {
		this.listeVariable = listeVariable;
	}
	
	
	public Moteur(Reader reader,boolean pinceau, int angle , String couleur , int epaisseur) throws Exception{
		ValueEnvironment listeVariable= new ValueEnvironment(pinceau, angle, couleur, epaisseur);
		this.listeVariable=listeVariable;
		this.lexer = new Lexer(reader);
        this.look = new LookAhead1(lexer);
        this.parser = new Parser(look);
	}
	public Moteur(Reader reader,ValueEnvironment listeVariable) throws Exception{
		this.listeVariable=listeVariable;
		this.lexer = new Lexer(reader);
        this.look = new LookAhead1(lexer);
        this.parser = new Parser(look);
	}
	public void setReader(Reader reader) throws Exception{
		this.lexer=new Lexer(reader);
        this.look = new LookAhead1(lexer);
        this.parser = new Parser(look);
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

	public void setControleurTourne(ControleurTourne controleurTourne) {
		this.listeVariable.setControleurTourne(controleurTourne);
		
	}

	public void setControleurCouleur(ControleurCouleur controleurCouleur) {
		this.listeVariable.setControleurCouleur(controleurCouleur);
		
	}
	public void setControleurPinceau(ControleurPinceau controleurPinceau) {
		this.listeVariable.setPinceau(controleurPinceau);
		
	}
	public void setControleurListeToken(ControleurListeToken controleurListeToken) throws Exception {
		if(look !=null){
			this.look.setControleur(controleurListeToken);
		}
		else{
			throw new Exception("la methode setControleur a été appeler avec un Objet LOOK du Moteur null");
		}
	}
	public void setControleurEpaisseur(ControleurEpaisseur controleurEpaisseur){
		this.listeVariable.setControleurEpaisseur(controleurEpaisseur);
		
	}
	public void setControleurDimension(ControleurDimension controleurDimension){
		this.listeVariable.setControleurDimension(controleurDimension);
	}

	
	public String getString(){
		if(ProgramPrincipal==null){
			return "";
		}
		return ProgramPrincipal.getString(listeVariable,2);
	}
	
	public String getFirstString(String nomClasse) throws Exception{
		if(ProgramPrincipal==null){
			//throw new Exception("attention impossible d'appeler methode GetFirstString sans appeler analyseSynt avant sur le moteur");
			return"";
		}
		return ProgramPrincipal.getFistString(nomClasse);
	}
	
	public String getLastString(){
		if(ProgramPrincipal==null){
			return "";
		}
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
		} else if(multiplicateur == 1)  {
			return chaine;
		}
		else{
			return "";
		}
	}
	
	public ProgramPrincipal getProgramPrincipal() {
		return ProgramPrincipal;
	}
	public void setProgramPrincipal(ProgramPrincipal programPrincipal) {
		ProgramPrincipal = programPrincipal;
	}
}