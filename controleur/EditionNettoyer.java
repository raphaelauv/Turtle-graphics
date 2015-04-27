package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controleur.fichier.FichierProgJava;
import affichage.Fenetre;
import affichage.FenetreDessin;
import affichage.Nettoyer;

public class EditionNettoyer implements ActionListener{

	FenetreDessin fenetreDessin;
	ControleurListeToken controleurListeToken;
	FichierProgJava controleurProgJava;
	Fenetre fenetre;
	public EditionNettoyer(FenetreDessin fenetreDessin, ControleurListeToken controleurListeToken, FichierProgJava controleurProgJava,Fenetre fenetre){
		this.fenetreDessin=fenetreDessin;
		this.controleurListeToken=controleurListeToken;
		this.controleurProgJava=controleurProgJava;
		this.fenetre=fenetre;
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
		if(this.fenetre!=null){
			fenetre.nettoyer();
		}
	}
}