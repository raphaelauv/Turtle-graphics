package moteur.instruction;

import java.awt.Point;

import affichage.Trait;
import moteur.ValueEnvironment;
import moteur.expression.Expression;

public class Go extends Avance{
	private Expression e2;
	public Go(Expression e,Expression e2) {
		super(e);
		this.e2=e2;
	}
	
	public void exec(ValueEnvironment env)throws Exception {
		
		Point a = new Point(this.getExp().eval(env),this.e2.eval(env));
		Trait tmp = new Trait(env.getTaille(),env.getCouleur(), env.getPositionActuel(),a, env.isValPinceau());
		
		if(env.getAvance()!=null){
			boolean paSortie=env.getAvance().action(tmp);
			if(paSortie){
				env.setPositionActuel(tmp.getFin());
			}
			else{
				throw new Exception("Attention vous etes sortie des dimensions");
			}
		}
		if(env.isModeSansFENETRE()){
			System.out.println(tmp);
			System.out.println();
		}
		
	}
	public String getString(ValueEnvironment env, int tabulation) {
		
		String tmp = "new Go("+this.getExp().getString(env,tabulation)+","+this.e2.getString(env,tabulation)+")"				
				/*+ "new Trait("+this.getExp().getString(env)
				+ ",listeVariable.getTaille(),listeVariable.getCouleur(),"
				+ "listeVariable.getPositionActuel(),"
				+ "listeVariable.getAngleActuel(),"
				+ "listeVariable.isValPinceau()"*/
				+ ".exec("+env.getNom()+");";
		return tmp;
	}

}
