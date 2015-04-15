package moteur.instruction;

import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class Loop extends Instruction {
	//for
	private Expression exp;
	private Instruction instruction;
	public Loop(Expression e, Instruction ex2) {
		setExp(e);
		setInstruction(ex2);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		int max=this.exp.eval(env);
		ValueEnvironment env2= (ValueEnvironment) env.clone();
		for (int i =0 ; i<max; i++){
			this.getInstruction().exec(env);
		}
		
		/*
		for (int i =0 ; i<this.getExp().eval(env); i++){
			this.getInstruction().exec(env);
		}
		*/
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		String identi="i";
		while(env.containsKey(identi)){
			identi=identi+"i";
		}
		int max=0;
		try{
			max=exp.eval(env);	
			env.addVariable(identi);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erreur dans while du for de getString");
		}
		return "for"+ "( "+"int "+identi+" =0 ; "+identi+"<"+max+";"+identi+"++){"+getInstruction().getString(env,tabulation+1)+"}";
	}
	
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction ex2) {
		this.instruction = ex2;
	}
	
}