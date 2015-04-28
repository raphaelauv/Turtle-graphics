package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import moteur.IntToken;
import moteur.Token;
import moteur.VarToken;
import controleur.fichier.Enregistrer;
import affichage.Fenetre;
import affichage.Nettoyer;

public class ControleurExportToken implements ActionListener ,Nettoyer {

	LinkedList<Token> listeDesTokens;
	
	Fenetre fenetre;
	public ControleurExportToken(Fenetre fenetre ){
		this.fenetre=fenetre;
	}
	private String lePseudoCode(){
		String tmp="";
		for(Token a: listeDesTokens){
			if(a instanceof IntToken){
				tmp=tmp+((IntToken)a).getValue()+" ";
			}
			else if(a instanceof VarToken){
				tmp=tmp+((VarToken)a).getValue()+" ";
			}
			else{
				String tmpOfA =a.getSpeudoCode();
				tmp=tmp+tmpOfA+" ";
				if(tmpOfA.equals(";") ||tmpOfA.equals("Debut")  ||tmpOfA.equals("Fin")  ||tmpOfA.equals("Faire")  ){
					tmp=tmp+"\n";
				}
				
			}
		}
		return tmp;
	}
	private String letexte(){
		String tmp="";
		for(Token a: listeDesTokens){
			tmp=tmp+a.toString()+"\n";
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
			Object[] options = {"Pseudo code",
                    "Liste des tokens"};
			int choix;
			choix = JOptionPane.showOptionDialog(fenetre,
					"Voulez vous exporter l'image en speudo code ou \nla liste des tokens interprete par le programme",
					"Choix enregistrement ?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[0]); //default button title
			if(choix==0){
				Enregistrer.enregistrer(fenetre, this.lePseudoCode(), "peusoCode.txt");
			}
			else if(choix==1){
				Enregistrer.enregistrer(fenetre, this.letexte(), "listeTokens.txt");
			}
		}
	}

	public void ajouterToken(Token token) {
		if (this.listeDesTokens == null) {
			this.listeDesTokens = new LinkedList<Token>();
		}
		this.listeDesTokens.add(token);
	}
}
