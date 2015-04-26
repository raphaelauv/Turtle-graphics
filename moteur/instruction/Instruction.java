package moteur.instruction;


import moteur.ValueEnvironment;
import moteur.expression.Expression;

public abstract class Instruction {
	private Expression exp;
	public abstract void exec(ValueEnvironment env)throws Exception;
	public abstract String getString(ValueEnvironment env , int tabulation);

	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
}

