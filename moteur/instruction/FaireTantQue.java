package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class FaireTantQue extends TantQue {
	//do while
		public FaireTantQue(Expression e, Instruction ex2) {
			super(e, ex2);
		}

		public void exec(ValueEnvironment env) throws Exception {
			exec2(env);
			super.exec(env);
		}
		
		public void exec2(ValueEnvironment env) throws Exception {
			this.getInstruction().exec(env);
		}
		
		public String getString(ValueEnvironment env, int tabulation){
			String tmp=Moteur.stringRepeat("\t", tabulation);
			return "do{\n"+this.getInstruction().getString(env,tabulation)+"\n}while("+this.getInstruction().getString(env,tabulation)+")";
			
		}
	}