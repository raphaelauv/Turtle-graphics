package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import moteur.IntToken;
import moteur.Moteur;
import moteur.Token;
import moteur.VarToken;
import controleur.fichier.Enregistrer;
import affichage.Fenetre;
import affichage.Nettoyer;

public class ControleurExportToken implements ActionListener ,Nettoyer {

	LinkedList<Token> listeDesTokens;// liste tokens accepter par le parseur
	LinkedList<Token> listeTMPDesTokens; // liste temporaire
	
	Fenetre fenetre;
	public ControleurExportToken(Fenetre fenetre ){
		this.fenetre=fenetre;
	}
	private String lePseudoCode(){
		String tmp="Debut\n";
		int tabVal=1;
		int nbDebut=0;
			for(Token a: listeDesTokens){
				
				
				if(a instanceof IntToken){
					tmp=tmp+((IntToken)a).getValue()+" ";
				}
				else if(a instanceof VarToken){
					tmp=tmp+((VarToken)a).getValue()+" ";
				}
				else{
					String tmpOfA =a.getSpeudoCode();
					boolean fin=tmpOfA.equals("Fin");
					boolean debut=tmpOfA.equals("Debut");
					
					
					if(fin){
						nbDebut--;
						tabVal--;
					}
					String tabulation=Moteur.stringRepeat("\t",tabVal);
					if(debut){
						nbDebut++;
						tabVal++;
					}
					
					if(
							tmpOfA.equals("Var") ||tmpOfA.equals("Avance") ||
							tmpOfA.equals("Epaisseur") || tmpOfA.equals("Tourne") ||
							tmpOfA.equals("BasPinceau") || debut ||
							tmpOfA.equals("HautPinceau") || tmpOfA.equals("Couleur") ||
							tmpOfA.equals("Taille") || tmpOfA.equals("For") ||
							tmpOfA.equals("Tant que") || tmpOfA.equals("Faire") ||
							tmpOfA.equals("Si") || tmpOfA.equals("Alors") ||
							tmpOfA.equals("Sinon") || tmpOfA.equals("Sinon si")  ||  fin || tmpOfA.equals("Go")
					){
						tmp=tmp+"\n";
					}
					
					if(tmpOfA.equals(";") ||tmpOfA.equals("=") ||tmpOfA.equals("+")||
							tmpOfA.equals("-")||tmpOfA.equals("*")||tmpOfA.equals("/")||
							tmpOfA.equals("(")||tmpOfA.equals(")")||tmpOfA.equals("{")||tmpOfA.equals("}") ){
						tmp=tmp+tmpOfA;
					}
					else{
						tmp=tmp+tabulation+tmpOfA+" ";
					}
					if(fin && nbDebut==0){
						tmp=tmp+";\n";
					}
				}
			}
		return tmp+"\nFin";
	}
	private String letexte(){
		String tmp="";
		for(Token a: listeDesTokens){
			if(a.getLine()!=-1){
				// on ignore les virgule mis en plus par le mode interpret et autres tokens pour le mode export pseudo-code
				tmp=tmp+a.toString()+"\n";
			}
		}
		return tmp;
	}
	
	
	public void nettoyer(){
		this.listeDesTokens=null;
		this.listeTMPDesTokens=null;
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

	public void ajouterToken(Token token){
		if(this.listeDesTokens==null){
			this.listeDesTokens = new LinkedList<Token>();
		}
		this.listeDesTokens.add(token);
	}
	public void ajouterTokenTMP(Token token) {
		if (this.listeTMPDesTokens == null) {
			this.listeTMPDesTokens = new LinkedList<Token>();
		}
		this.listeTMPDesTokens.add(token);
	}

	public void succesParseur(){
		if (this.listeDesTokens == null) {
			this.listeDesTokens = new LinkedList<Token>();
		}
		if(this.listeTMPDesTokens!=null){
			this.listeDesTokens.addAll(this.listeTMPDesTokens);
		}
		this.listeTMPDesTokens=null;
	}
	public void echecParseur(){
		this.listeTMPDesTokens=null;
	}
}
