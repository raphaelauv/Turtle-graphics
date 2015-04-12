package controleur;

import affichage.Fenetre;
import affichage.Trait;

public class ControleurTourne {
	
	private Fenetre fenetre;
	
	public ControleurTourne(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void action(int angle){
		fenetre.dessinerTourne(angle);
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}
	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

}
