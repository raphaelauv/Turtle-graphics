package controleur.GUI;

import java.awt.Dimension;

import affichage.Fenetre;

public class ControleurDimension extends ControleurGUI {
	
	public ControleurDimension(Fenetre fenetre) {
		super(fenetre);
	}
	public void action(Dimension dimension) {
		if (fenetre != null) {
			fenetre.changerDimension(dimension);
		}
	}
}