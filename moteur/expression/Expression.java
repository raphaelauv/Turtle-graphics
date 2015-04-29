package moteur.expression;

import moteur.ValueEnvironment;

public abstract class Expression {
	
	public abstract int eval(ValueEnvironment env)throws Exception;
	public abstract String getString(ValueEnvironment env, int tabulation);
}


