package moteur.instruction;


import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;
import moteur.expression.Int;
import moteur.expression.Program;
import affichage.Trait;

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

