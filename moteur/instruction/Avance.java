package moteur.instruction;
import moteur.ValueEnvironment;
import moteur.expression.Expression;
import moteur.expression.Int;
import affichage.Trait;

public class Avance extends Instruction {
	public Avance(Expression e) {
		this.setExp(e);
	}
	public Avance(int i) {
		this.setExp(new Int(i));
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		Trait tmp = new Trait(this.getExp().eval(env), env.getTaille(),
				env.getCouleur(), env.getPositionActuel(),
				env.getAngleActuel(), env.isValPinceau());
		
		env.setPositionActuel(tmp.getFin());
			
		if(env.getAvance()!=null){
			env.getAvance().action(tmp);
		}
		if(env.isModeSansFENETRE()){
			System.out.println(tmp);
			System.out.println();
		}
		
	}
	public String getString(ValueEnvironment env, int tabulation) {
		
		String tmp = "new Avance("+this.getExp().getString(env,tabulation)+")"
				
				/*+ "new Trait("+this.getExp().getString(env)
				+ ",listeVariable.getTaille(),listeVariable.getCouleur(),"
				+ "listeVariable.getPositionActuel(),"
				+ "listeVariable.getAngleActuel(),"
				+ "listeVariable.isValPinceau()"*/
				+ ".exec(listeVariable);";
		return tmp;
	}
	
	
	
}