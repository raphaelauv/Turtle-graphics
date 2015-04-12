package controleur;


import moteur.Expression;
import moteur.ValueEnvironment;
import affichage.Fenetre;
import affichage.Trait;

public class ControleurAvance{

	private Fenetre fenetre;
	
	public ControleurAvance(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void action(Trait tmp){
		fenetre.dessinerAvance(tmp);
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}
	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

}
