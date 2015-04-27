package controleur.GUI;

import affichage.Fenetre;

public class ControleurEpaisseur extends ControleurGUI {
	public ControleurEpaisseur(Fenetre fenetre) {
		super(fenetre);
	}

	public void action(int angle) {	
		if (fenetre != null) {
			fenetre.changerEpaisseur(angle);
		}
	}
}
