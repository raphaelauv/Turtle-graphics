package moteur.instruction;

import moteur.ValueEnvironment;

public class HautPinceau extends Instruction {
	public HautPinceau() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.setValPinceau(false);
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		//String tmp=Moteur.stringRepeat("\t", tabulation);
		
		return "new HautPinceau().exec(listeVariable);";
		
	}
}
