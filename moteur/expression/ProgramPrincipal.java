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
		String tmp="\n";
		env.setNom("listeVariable");
		if (this.i != null) {
			tmp=tmp+"\n"+tmp2+this.i.getString(env,tabulation);
			return tmp;
		}else{
			return "";
		}
	}
	
	public Instruction getI() {
		return i;
	}
	public void setI(Instruction i) {
		this.i = i;
	}
}