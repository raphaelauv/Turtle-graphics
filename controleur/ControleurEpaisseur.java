package controleur;

import affichage.Fenetre;

public class ControleurEpaisseur extends ControleurTourne {
	public ControleurEpaisseur(Fenetre fenetre) {
		
		super(fenetre);
	}

	public void action(int angle) {
		
		if (fenetre != null) {
			fenetre.changerEpaisseur(angle);
		}
	}
}
