package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class SiALors extends Loop {
	public SiALors(Expression e, Instruction ex2) {
		super(e,ex2);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		if(this.getExp().eval(env)!=0){
			this.getInstruction().exec(env);
		}
	}
	
	public String getString(ValueEnvironment env, int tabulation){
		String tmp=Moteur.stringRepeat("\t", tabulation);
		return "if("+this.getExp().getString(env,0)+"!=0){\n\t"+tmp+this.getInstruction().getString(env,tabulation+1)+"\n"+tmp+"}";
	}
	
}