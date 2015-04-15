package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class SiALorsSinon extends SiALors {
	protected Instruction instruction3;
	public SiALorsSinon(Expression exp, Instruction ex2 , Instruction ex3) {
		super(exp,ex2);
		instruction3 = ex3;
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		if(this.getExp().eval(env)!=0){
			this.getInstruction().exec(env);
		}
		else{
			this.instruction3.exec(env);
		}
	}
	public String getString(ValueEnvironment env, int tabulation){
		String tmp=Moteur.stringRepeat("\t", tabulation);
		
		//String tmp2=super.getString(env, tabulation);
		
		String tmp2="if("+this.getExp().getString(env,0)+"!=0){\n\t"+tmp+this.getInstruction().getString(env,tabulation)+"\n"+tmp+"}";
		
		if(instruction3 instanceof SinonSiAlors || instruction3 instanceof SinonSiAlorsSinon ){
			
			return tmp2=tmp2+"\n"+tmp+"else "+this.instruction3.getString(env, tabulation);
		}
		else{
			return tmp2+"\n"+tmp+"else{\n"+this.instruction3.getString(env,tabulation+1)+tmp+"}";
		}
	}
}