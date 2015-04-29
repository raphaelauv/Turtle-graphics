package controleur.GUI;

import affichage.Fenetre;
import affichage.Trait;

public class ControleurAvance extends ControleurGUI{

	public ControleurAvance(Fenetre fenetre){
		super(fenetre);
	}
	
	public boolean action(Trait tmp) throws Exception{
		if(fenetre !=null){
			boolean pasSortieCanvas=fenetre.dessinerAvance(tmp);
			if(pasSortieCanvas){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
}