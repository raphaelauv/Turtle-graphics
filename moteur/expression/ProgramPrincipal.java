package moteur.expression;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.instruction.Instruction;

public class ProgramPrincipal{
	
	public Instruction i;
	

	public ProgramPrincipal(Instruction instruction) {
		this.i=instruction;
	}
	public void run(ValueEnvironment env) throws Exception{
		if(i!=null){
			i.exec(env);
		}
	}
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp2=Moteur.stringRepeat("\t", tabulation);
		String tmp="\n"+tmp2+"ValueEnvironment listeVariable = new ValueEnvironment(false,90,\"BLACK\",1);"
				+ "\n"+tmp2+"listeVariable.setModeSansFENETRE(true);"
				+"\n"+tmp2;
		env.setNom("listeVariable");
		if (this.i != null) {
			tmp=tmp+"\n"+tmp2+this.i.getString(env,tabulation);
			return tmp;
		}else{
			return "";
		}
	}
	
	public String getFistString(String nameClass) throws Exception {
		
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
		return ""+"\n}";
	}
	
	public Instruction getI() {
		return i;
	}
	public void setI(Instruction i) {
		this.i = i;
	}
}