package moteur.instruction;

import moteur.Moteur;
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
			this.getInstruction().exec(env2);
		}
		
		/*
		for (int i =0 ; i<this.getExp().eval(env); i++){
			this.getInstruction().exec(env);
		}
		*/
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		String identi="i";
		int val=1;
		while(env.containsKey(identi+val)){
			val++;
		}
		Integer valOld;
		String identiOld="listeVariable";
		if(val==1){
			
		}else{
			valOld=val-1;
			identiOld=identiOld+valOld;
		}
		identi=identi+val;
		int max=0;
		try{
			max=exp.eval(env);
			env.addVariable(identi);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("erreur dans while du for de getString");
		}
		String tmp2=Moteur.stringRepeat("\t", tabulation);
		String tmp3="ValueEnvironment listeVariable"+val+"= (ValueEnvironment) "+identiOld+".clone();\n"+tmp2;
		
		ValueEnvironment env2= (ValueEnvironment) env.clone();
		String nomVariable="listeVariable".concat(""+val);
		System.out.println(nomVariable);
		env2.setNom(nomVariable);
		return tmp3+"for"+ "( "+"int "+identi+" =0 ; "+identi+"<"+max+";"+identi+"++){\n\t"+tmp2+getInstruction().getString(env2,tabulation+1)+"\n"+tmp2+"}\n";
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