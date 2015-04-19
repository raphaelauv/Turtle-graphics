package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class Assignment extends Instruction {
	private String varName;
	protected Expression exp;
	public Assignment(String s, Expression e) {
		varName = s;
		setExp(e);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		if(varName==null || exp==null){
			return;
		}
		int valeur=getExp().eval(env);
		
		if(!env.containsKey(varName)){
			throw new Exception("la variable "+varName +" n a pas ete declarer avant son initialisation");
		}
    	env.setVariable(varName,valeur);
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp=Moteur.stringRepeat("\t", tabulation);
		tmp =varName+" = "+exp.getString(env,tabulation) +";";
		return tmp;
	}
	
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
}
