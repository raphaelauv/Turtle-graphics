package controleur.GUI;


import javax.swing.JOptionPane;
import affichage.Fenetre;
import affichage.Trait;

public class ControleurAvance extends ControleurGUI{

	public ControleurAvance(Fenetre fenetre){
		super(fenetre);
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
