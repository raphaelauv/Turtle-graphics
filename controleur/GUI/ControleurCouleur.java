package controleur.GUI;

import affichage.Fenetre;

public class ControleurCouleur extends ControleurGUI {
	public ControleurCouleur(Fenetre fenetre){
		super(fenetre);
	}
	public void action(String couleur) {		
		if(fenetre==null){
			return;
		}
		fenetre.changerCouleur(couleur);
		
	}

}
