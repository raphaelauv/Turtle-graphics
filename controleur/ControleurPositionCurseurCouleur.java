package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichage.FenetreDessin;
import affichage.VariableAfficher;

public class ControleurPositionCurseurCouleur implements ActionListener {

	FenetreDessin fenetreDessin;
	VariableAfficher position;
	
	public ControleurPositionCurseurCouleur(FenetreDessin fenetreDessin, VariableAfficher position) {
		super();
		this.fenetreDessin = fenetreDessin;
		this.position=position;
	}

	public void actionPerformed(ActionEvent e) {
		if(this.fenetreDessin!=null){
			Color nouvelle=this.fenetreDessin.changerCouleurCurseur();
			this.position.changerFondCouleur(nouvelle);
			this.fenetreDessin.repaint();
		}
	}

}
