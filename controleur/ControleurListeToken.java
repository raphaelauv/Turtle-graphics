package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import affichage.Fenetre;
import affichage.Nettoyer;

public class ControleurListeToken implements ActionListener ,Nettoyer{

	LinkedList<String> listeDesTokens;
	
	Fenetre fenetre;
	public ControleurListeToken(Fenetre fenetre ){
		this.fenetre=fenetre;
	}
	private String letexte(){
		String tmp="";
		for(String a: listeDesTokens){
			tmp=tmp+a;
			tmp=tmp+"\n";
		}
		return tmp;
	}
	
	public void nettoyer(){
		this.listeDesTokens=null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.listeDesTokens==null){
            JOptionPane.showMessageDialog(fenetre,
                    "Il n y a rien a exporter", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		else{
			Enregistrer.enregistrer(fenetre, letexte(), "listeTokens.txt");
		}
		
	}
	
	public void ajouterToken(String token){
		if(this.listeDesTokens==null){
			this.listeDesTokens=new LinkedList<String>();
		}
		this.listeDesTokens.add(token);
	}

}
