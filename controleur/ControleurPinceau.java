package controleur;

import affichage.Fenetre;

public class ControleurPinceau {
	
	private Fenetre fenetre;
	
	public ControleurPinceau(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void action(boolean descendre){
		fenetre.descendrePinceau(descendre);
	}
	
	public Fenetre getFenetre() {
		return fenetre;
	}
	public void setFenetre(Fenetre fenetre) {
		this.fenetre = fenetre;
	}

}
