package moteur.instruction;

import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class Epaisseur extends Instruction{
	
	public Epaisseur(Expression e) {
		this.setExp(e);
	}
	public void exec(ValueEnvironment env)throws Exception {
		env.setTaille(this.getExp().eval(env));
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		
		return "new Epaisseur("+getExp().getString(env,tabulation)+").exec(listeVariable);";
	}
}
