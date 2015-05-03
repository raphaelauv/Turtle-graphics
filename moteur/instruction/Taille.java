package moteur.instruction;

import java.awt.Dimension;

import moteur.ValueEnvironment;
import moteur.expression.Expression;
import moteur.expression.Int;

public class Taille extends Instruction{
	
	Expression e2;
	public Taille(Expression e,Expression e2){
		this.setExp(e);
		this.e2=e2;
	}
	public Taille(int i, int j) {
		this.setExp(new Int(i));
		this.e2=new Int(j);
	}
	
	public void exec(ValueEnvironment env) throws Exception {
		
		int a=this.getExp().eval(env);
		int b=this.e2.eval(env);
		
		if(env.getControleurDimension()!=null){
			env.getControleurDimension().action(new Dimension(a,b));
		}
	}

	public String getString(ValueEnvironment env, int tabulation) {
		String tmp = "new Taille("+this.getExp().getString(env,tabulation)+","+this.e2.getString(env, tabulation)+")"
				
				/*+ "new Trait("+this.getExp().getString(env)
				+ ",listeVariable.getTaille(),listeVariable.getCouleur(),"
				+ "listeVariable.getPositionActuel(),"
				+ "listeVariable.getAngleActuel(),"
				+ "listeVariable.isValPinceau()"*/
				+ ".exec("+env.getNom()+");";
		return tmp;
	}
}
