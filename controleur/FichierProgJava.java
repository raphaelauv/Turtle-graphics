package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import moteur.Moteur;
import affichage.Fenetre;
import affichage.Nettoyer;

public class FichierProgJava implements ActionListener ,Nettoyer{

	Fenetre fenetre;
	private LinkedList<Moteur> listeMoteurs;
	public FichierProgJava(Fenetre fenetre) {
		this.fenetre=fenetre;
	}
	public void addMoteur(Moteur moteur){
		if(listeMoteurs==null){
			listeMoteurs=new LinkedList<Moteur>();
		}
		listeMoteurs.add(moteur);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (listeMoteurs == null) {
			JOptionPane.showMessageDialog(fenetre, "Il n y a rien a exporter",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String tmp="";
		try {
			tmp=listeMoteurs.getFirst().getFirstString("test");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(listeMoteurs==null){
			return ;
		}
		for(Moteur mot : listeMoteurs){
        	tmp=tmp+mot.getString();
        }
		tmp=tmp+listeMoteurs.getFirst().getLastString();
		
		
		Enregistrer.enregistrer(fenetre, tmp, "test.java");
		
	}
	public void nettoyer() {
		this.listeMoteurs=null;
	}

}
