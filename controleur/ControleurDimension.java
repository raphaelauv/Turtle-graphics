package controleur;

import java.awt.Dimension;
import java.awt.Point;

import affichage.Fenetre;

public class ControleurDimension extends ControleurTourne {
	public ControleurDimension(Fenetre fenetre) {
		super(fenetre);
		// TODO Auto-generated constructor stub
	}

	public void action(Dimension dimension) {
		if (fenetre != null) {
			fenetre.changerDimension(dimension);
		}
	}
}