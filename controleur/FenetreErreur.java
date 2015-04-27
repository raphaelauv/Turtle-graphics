package controleur;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import affichage.Fenetre;

public class FenetreErreur {

	Fenetre fenetre;
	
	public FenetreErreur(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void afficherErreur(String texte){
		if(this.fenetre==null){
			JTextField c= new JTextField(texte);
			JPanel b= new JPanel();
			b.add(c);
			JFrame a=new JFrame();
			a.add(b);
			a.setVisible(true);
			a.pack();
		}
		else{
		 JOptionPane.showMessageDialog(fenetre,
    			 texte,"ERREUR",JOptionPane.WARNING_MESSAGE);
		}
	}
}
