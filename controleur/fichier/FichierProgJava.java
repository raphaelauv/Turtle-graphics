package controleur.fichier;

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
	private LinkedList<Moteur> listeInter;
	public FichierProgJava(Fenetre fenetre, LinkedList<Moteur> listeInter) {
		this.fenetre=fenetre;
		this.listeInter=listeInter;
	}
	public void addMoteur(Moteur moteur){
		if(listeMoteurs==null || moteur ==null){
			listeMoteurs=new LinkedList<Moteur>();
		}
		listeMoteurs.add(moteur);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (listeMoteurs == null && listeInter==null) {
			JOptionPane.showMessageDialog(fenetre, "Il n y a rien a exporter",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String debut="";
			try {
				debut=listeMoteurs.getFirst().getFirstString("test");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		String fin=listeMoteurs.getFirst().getLastString();
		String appelSimple="\t\tmoteurN";
		String appelsMethode="";
		String lesMethodes="";
		int compteurdeMoteurs=1;
		
		for(Moteur moteur : listeMoteurs){
			String laMethode="";
			String dansLaMethode="";
			String declarationMethode="\tpublic static void moteurN";
			
			appelsMethode=appelsMethode+appelSimple+compteurdeMoteurs+"();\n";
			
			declarationMethode=declarationMethode+compteurdeMoteurs+"()throws Exception{\n";
        	
			dansLaMethode=moteur.getString();
			laMethode=declarationMethode+dansLaMethode+"\n\t}\n";
			
			lesMethodes=lesMethodes+laMethode;
			compteurdeMoteurs++;
        }
		
		debut=debut+appelsMethode+getStringlisteInter()+"\t}\n"+lesMethodes+fin;
		
		Enregistrer.enregistrer(fenetre, debut, "test.java");
		
	}
	
	public String getStringlisteInter(){
		if(listeInter==null){
			return "";
		}
		String instructions= "\n";
		for(Moteur instruc : listeInter){
			instructions=instructions+instruc.getString()+"\n";
		}
		return instructions;
		
	}
	public void nettoyer() {
		this.listeMoteurs=null;
	}

}
