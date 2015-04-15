package moteur.expression;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.instruction.Instruction;

public class ProgramPrincipal extends Program{
	
	public ProgramPrincipal(Program p, Instruction i) {
		super(i,p);
	}
	public void run(ValueEnvironment env) throws Exception{
		if(super.rest!=null){
			super.rest.run(env);
		}
		if(super.first !=null){
			super.first.exec(env);
		}
	}
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp2=Moteur.stringRepeat("\t", tabulation);
		String tmp="\n"+tmp2;
		if (super.rest != null) {
			tmp="\n"+super.rest.getString(env,tabulation);
		}
		else{
			tmp=tmp+"null,";
		}
		if(super.first!=null){
			tmp=tmp+super.first.getString(env,tabulation);
		}
		else{
			tmp=tmp+"null";
		}
		return tmp;
	}
	
	public String getFistString(String nameClass) throws Exception {
		
		if(nameClass==null || nameClass.length()<1){
			throw new Exception("erreur sur choix du nom de la class");
		}
		String tmp = "import moteur.ValueEnvironment;\n"
				+ "import moteur.instruction.Avance;\n"
				+ "import moteur.instruction.BasPinceau;\n"
				+ "import moteur.instruction.ChangeCouleur;\n"
				+ "import moteur.instruction.Tourne;\n"
				+ "class "+nameClass+"{\n \tpublic static void main(String[] args) throws Exception {\n"
				+ "\t\tValueEnvironment listeVariable = new ValueEnvironment();"
				+ "\n\t\tlisteVariable.setModeSansFENETRE(true);"
		;
		return tmp;

	}
	public String getLastString(){
		return ""+"\t}\n}";
	}
	
}