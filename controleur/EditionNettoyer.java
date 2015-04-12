package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import affichage.FenetreDessin;

public class EditionNettoyer implements ActionListener{

	FenetreDessin fenetreDessin;
	ControleurListeToken controleurListeToken;
	public EditionNettoyer(FenetreDessin fenetreDessin, ControleurListeToken controleurListeToken){
		this.fenetreDessin=fenetreDessin;
		this.controleurListeToken=controleurListeToken;
	}
	public void actionPerformed(ActionEvent e) {
		if(fenetreDessin!=null){
			
			this.fenetreDessin.nettoyer();
			
		}
		if(controleurListeToken!=null){
			controleurListeToken.nettoyer();
		}
	}

}
