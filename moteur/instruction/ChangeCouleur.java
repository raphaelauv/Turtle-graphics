package moteur.instruction;

import moteur.Moteur;
import moteur.ValueEnvironment;

public class ChangeCouleur extends Instruction{
	String couleur;
	public ChangeCouleur(String couleur) {
		this.couleur=couleur;
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		env.setCouleur(this.couleur);
	}
	
	public String getString(ValueEnvironment env, int tabulation) {
		String tmp=Moteur.stringRepeat("\t", tabulation);
		
		return "new ChangeCouleur(\""+couleur+"\").exec("+env.getNom()+");";
		
	}
}
