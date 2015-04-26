package controleur;

import affichage.Fenetre;

public class ControleurCouleur {
	private Fenetre fenetre;
	public ControleurCouleur(Fenetre fenetre){
		this.fenetre=fenetre;	
	}
	public void action(String couleur) {
		
		if(fenetre==null){
			return;
		}
		fenetre.changerCouleur(couleur);
		
	}

}
