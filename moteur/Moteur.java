
package moteur;

import java.awt.Dimension;
import java.io.Reader;
import java.util.LinkedList;

import moteur.expression.ProgramPrincipal;
import controleur.ControleurExportToken;
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
	private LinkedList<ProgramPrincipal> programPrincipal;
	
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
		ControleurExportToken tmp=this.look.getControleurTokens();
		this.lexer=new Lexer(reader);
        this.look = new LookAhead1(lexer);
        this.parser = new Parser(look);
        this.setControleurListeToken(tmp);
	}

	public void analyseSynt() throws Exception{
		if (this.parser==null){
			throw new Exception("une analyse est essayer sur un Parseur absent");
		}
		if(programPrincipal==null){
			programPrincipal=new LinkedList<ProgramPrincipal>();
		}
		try {
			this.programPrincipal.add(parser.nontermCode());
		} catch (Exception e) {
			this.look.getControleurTokens().echecParseur();
			throw e;
		}
		this.look.getControleurTokens().succesParseur();
	}
	public void parcourtArbre() throws Exception{
		if (this.programPrincipal==null || this.programPrincipal.isEmpty()){
			throw new Exception("un parcourt est essayer sur un prog absent");
		}
		programPrincipal.getLast().run(listeVariable);
		
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
	public void setControleurListeToken(ControleurExportToken controleurListeToken) throws Exception {
		if(look !=null){
			this.look.setControleurTokens(controleurListeToken);
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
		if(programPrincipal==null){
			return "";
		}
		String tmp="\n\t\t"+"ValueEnvironment listeVariable = new ValueEnvironment(false,90,\"BLACK\",1);"
				+ "\n\t\t"+"listeVariable.setModeSansFENETRE(true);";
		for(ProgramPrincipal pro : programPrincipal){
			tmp=tmp+pro.getString(listeVariable,2);
		}
		return tmp;
	}
	
	public String getFirstString(String nameClass) throws Exception{
		if(programPrincipal==null){
			//throw new Exception("attention impossible d'appeler methode GetFirstString sans appeler analyseSynt avant sur le moteur");
			return"";
		}
		if(nameClass==null || nameClass.length()<1){
			throw new Exception("erreur sur choix du nom de la class");
		}
		String tmp = "import moteur.ValueEnvironment;\n"
				+ "import moteur.instruction.*;\n"
				/*+ "import moteur.instruction.Epaisseur;\n"
				+ "import moteur.instruction.Avance;\n"
				+ "import moteur.instruction.BasPinceau;\n"
				+ "import moteur.instruction.HautPinceau;\n"
				+ "import moteur.instruction.ChangeCouleur;\n"
				+ "import moteur.instruction.Tourne;\n"*/
				+ "public class "+nameClass+"{\n\tpublic static void main(String[] args) throws Exception {\n"
		;
		return tmp;
	}
	
	public String getLastString(){
		if(programPrincipal==null){
			return "";
		}
		return ""+"\n}";
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
}