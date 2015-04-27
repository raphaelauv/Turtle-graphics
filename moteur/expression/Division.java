package moteur.expression;

import moteur.ValueEnvironment;

public class Division extends Expression {
	private Expression left, right;
	public Division(Expression l, Expression r) {
		left = l;
		right = r;
		
	}
	
	public int eval(ValueEnvironment env) throws Exception {
		int droite=right.eval(env);
		if(droite==0){
			throw new Exception("ATTENTION division par 0 \n"
					+ left.getString(env, 0)+" / "
					+ right.getString(env, 0)+" =0 "
					);
		}
		return left.eval(env)/droite;
	}
	public String getString(ValueEnvironment env, int tabulation) {
		return "("+left.getString(env,tabulation)+" / " +right.getString(env,tabulation)+")" ;
	}
}