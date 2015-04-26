package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class SinonSiAlorsSinon extends SinonSiAlors{

	public SinonSiAlorsSinon(Expression e, Instruction ex2, Instruction ex3) {
		super(e, ex2, ex3);
	}
	
	public String getString(ValueEnvironment env, int tabulation){
		String tmp=Moteur.stringRepeat("\t", tabulation);
		//String tmp2=super.getString(env, tabulation);
		
		String tmp2="if("+this.getExp().getString(env,0)+"!=0){\n\t"+tmp
				+this.getInstruction().getString(env,tabulation+1)+"\n"+tmp+"}";
		return tmp2+"\n"+tmp+"else{\n\t"+tmp+this.instruction3.getString(env,tabulation+1)+"\n"+tmp+"}";
	}
	
}