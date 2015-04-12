package controleur;

import javax.swing.JOptionPane;

import affichage.Fenetre;

public class FenetreErreur {

	Fenetre fenetre;
	
	public FenetreErreur(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void afficherErreur(String texte){
		if(this.fenetre==null){
			System.out.println(texte);
			System.out.println("########################");
			System.out.println("La fenetre JFame pour les erreurs est inactive");
		}
		else{
		 JOptionPane.showMessageDialog(fenetre,
    			 texte,"ERREUR",JOptionPane.WARNING_MESSAGE);
		}
	}
}
