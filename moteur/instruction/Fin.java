package moteur.instruction;

import moteur.ValueEnvironment;

public class Fin extends Instruction {
	public Fin() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		//env.setVariable("Fin", 1);
	}
	public String getString(ValueEnvironment env, int tabulation) {
		return "";
		
	}
}