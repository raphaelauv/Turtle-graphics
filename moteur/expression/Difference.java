package moteur.expression;

import moteur.ValueEnvironment;

public class Difference extends Expression {
	private Expression left, right;
	public Difference(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	public int eval(ValueEnvironment env) throws Exception {
		return left.eval(env)-right.eval(env);
	}
	public String getString(ValueEnvironment env, int tabulation) {
		return "("+left.getString(env,tabulation)+" - " +right.getString(env,tabulation)+")" ;
	}
}