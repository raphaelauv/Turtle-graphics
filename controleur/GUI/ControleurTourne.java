package controleur.GUI;

import affichage.Fenetre;
import affichage.Trait;

public class ControleurTourne extends ControleurGUI{
	
	public ControleurTourne(Fenetre fenetre){
		super(fenetre);
	}
	
	public void action(int angle){
		if(fenetre!=null){
		fenetre.dessinerTourne(angle);
		}
	}
}