package moteur.instruction;

import moteur.ValueEnvironment;

public class Declaration extends Instruction {
	private String varName;
	public Declaration(String s) {
		varName = s;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.addVariable(varName);
	} 
	public String getString(ValueEnvironment env, int tabulation) {
		return "int "+varName+";";
	} 
}