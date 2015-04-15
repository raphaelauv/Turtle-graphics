package moteur.instruction;

import moteur.ValueEnvironment;

public class BasPinceau extends Instruction {
	public BasPinceau() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.setValPinceau(true);
	}
	public String getString(ValueEnvironment env, int tabulation) {
		
		return "new BasPinceau().exec(listeVariable);";
		
	}
	
}
