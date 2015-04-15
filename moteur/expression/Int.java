package moteur.expression;

import moteur.ValueEnvironment;

public class Int extends Expression {
	private int value;
	public Int(int i) {
		value = i;
	}
	
	public int eval(ValueEnvironment env) throws Exception {
		return value;
		
	}
	public String getString(ValueEnvironment env, int tabulation) {
		return ""+value;
	}
}
