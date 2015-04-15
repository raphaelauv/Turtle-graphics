package moteur.instruction;

import moteur.ValueEnvironment;
import moteur.expression.Expression;
import moteur.expression.Int;

public class Tourne extends Instruction {
	public Tourne(Expression e) {
		this.setExp(e);
	}
	public Tourne(int i) {
		this.setExp(new Int(i));
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		int valeur=this.getExp().eval(env);
		int angleactuel=env.getAngleActuel();
		int resultat;
		if(valeur>360){
			valeur=valeur%360;
		}
		resultat=valeur+angleactuel;
		resultat=resultat%360;
		if(resultat<0){
			resultat=360+resultat;
		}
    	env.setAngleActuel(resultat);
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
	
		return "new Tourne("+getExp().getString(env,tabulation)+").exec(listeVariable);";
	} 
}