package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import affichage.FenetreDessin;

public class EditionNettoyer implements ActionListener{

	FenetreDessin fenetreDessin;
	ControleurListeToken controleurListeToken;
	FichierProgJava controleurProgJava;
	public EditionNettoyer(FenetreDessin fenetreDessin, ControleurListeToken controleurListeToken, FichierProgJava controleurProgJava){
		this.fenetreDessin=fenetreDessin;
		this.controleurListeToken=controleurListeToken;
		this.controleurProgJava=controleurProgJava;
	}
	public void actionPerformed(ActionEvent e) {
		if(fenetreDessin!=null){
			this.fenetreDessin.nettoyer();
		}
		if(controleurListeToken!=null){
			controleurListeToken.nettoyer();
		}
		if(controleurProgJava!=null){
			controleurProgJava.nettoyer();
		}
	}
}