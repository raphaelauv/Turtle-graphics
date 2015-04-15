package moteur.expression;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.instruction.Instruction;

public abstract class Expression {
	
	public abstract int eval(ValueEnvironment env)throws Exception;
	public abstract String getString(ValueEnvironment env, int tabulation);
}


