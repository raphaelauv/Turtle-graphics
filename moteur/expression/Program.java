package moteur.expression;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.instruction.Instruction;

public class Program {
	protected Instruction first;
	protected Program rest;
	
	public Program(Instruction i, Program p) {
		first = i;
		rest = p;
	}
	public void run(ValueEnvironment env)
	throws Exception {		
		if (first != null) {
			first.exec(env);
		}
		if(rest!=null){
			rest.run(env);
		}
	}
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp2=Moteur.stringRepeat("\t", tabulation);
		String tmp="\n";
		if (first != null) {
			tmp=tmp+tmp2+first.getString(env,tabulation);
		}
		if(rest!=null){
			tmp=tmp+rest.getString(env, tabulation);
		}
		
		return tmp;
		
	}
}