package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;
import moteur.expression.Program;

public class Declaration extends Instruction {
	private String varName;
	private Program prog;
	public Declaration(String s,Program prog) {
		this.varName = s;
		this.prog=prog;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		
		if(this.varName!=null){
			
			if(env.containsKey(varName)){
				throw new Exception("le nom de variable "+varName +"est deja utilise ");
			}
			env.addVariable(varName);
			if(prog!=null){
				prog.run(env);
			}
		}
		
	} 
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp2=Moteur.stringRepeat("\t", tabulation);
		if(varName!=null){
			String tmp=tmp2+"int "+varName+";";
			if(prog!=null){
				return tmp+prog.getString(env, tabulation);
			}
			return tmp;
		}
		return "";
	} 
}