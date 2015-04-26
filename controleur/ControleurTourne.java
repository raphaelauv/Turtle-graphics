package controleur;

import affichage.Fenetre;
import affichage.Trait;

public class ControleurTourne{
	
	protected Fenetre fenetre;
	
	public ControleurTourne(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void action(int angle){
		if(fenetre!=null){
		fenetre.dessinerTourne(angle);
		}
	}
}
