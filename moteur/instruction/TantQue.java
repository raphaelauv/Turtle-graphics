package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;


public class TantQue extends Loop {
//while
	public TantQue(Expression e, Instruction ex2) {
		super(e, ex2);
	}

	public void exec(ValueEnvironment env) throws Exception {
		int i = 0;
		while (this.getExp().eval(env) != 0) {
			this.getInstruction().exec(env);
			i++;
			if (i > 100) {
				throw new Exception("trop diteration dans la boucle while , superieur a 100");
			}
		}
	}
	
	public String getString(ValueEnvironment env, int tabulation){
		String tmp=Moteur.stringRepeat("\t", tabulation);
		return "while("+this.getExp().getString(env,tabulation) +"!=0){\n\t"+tmp+this.getInstruction().getString(env,tabulation+1)+"\n"+tmp+"}";
	}
}
