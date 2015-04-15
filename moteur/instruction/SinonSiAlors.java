package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class SinonSiAlors extends SiALorsSinon{
	
	public SinonSiAlors(Expression e, Instruction ex2, Instruction ex3) {
		super(e, ex2,ex3);
	}
	
	public String getString(ValueEnvironment env, int tabulation){
		String tmp=Moteur.stringRepeat("\t", tabulation);
		
		//String tmp2=new SiALors(this.getExp(), this.getInstruction()).getString(env, tabulation);
		
		String tmp2="if("+this.getExp().getString(env,0)+"!=0){\n"+this.getInstruction().getString(env,tabulation+1)+"\n"+tmp+"}";
		
		if(this.instruction3 instanceof SinonSiAlors || this.instruction3 instanceof SinonSiAlorsSinon){
			return tmp2+"\n"+tmp+"else "+this.instruction3.getString(env, tabulation);
		}
		
		return tmp2;
		
	}
}