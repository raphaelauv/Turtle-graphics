package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Program;

public class Debut extends Instruction {
	protected Program bloc;
	public Debut(Program lebloc) {
		this.bloc=lebloc;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		if(this.bloc!=null){
			this.bloc.run(env);
		}
		//env.setVariable("Debut", 1);
	}
	
	public String getString(ValueEnvironment env,int tabulation) {
		String tmp=Moteur.stringRepeat("\t", tabulation);
		return "//Debut"+this.bloc.getString(env,tabulation)+tmp+"//Fin\n";
	}
	
}