package controleur;


import javax.swing.JOptionPane;

import moteur.ValueEnvironment;
import moteur.expression.Expression;
import affichage.Fenetre;
import affichage.Trait;

public class ControleurAvance{

	private Fenetre fenetre;
	
	public ControleurAvance(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	public void action(Trait tmp){
		if(fenetre !=null){
			boolean sortie=fenetre.dessinerAvance(tmp);
			if(!fenetre.isAfficherSortie() && sortie){
				JOptionPane.showMessageDialog(fenetre,
						"Attention vous etes sortie des dimensions"
						,"ERREUR ",JOptionPane.ERROR_MESSAGE);
				
				fenetre.setAfficherSortie(true);
			}
			
		}
	}
}
