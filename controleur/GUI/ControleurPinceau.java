package controleur.GUI;

import affichage.Fenetre;

public class ControleurPinceau extends ControleurGUI{
	
	private boolean pinceau;
	
	public ControleurPinceau(Fenetre fenetre,boolean pinceau){
		super(fenetre);
		this.pinceau=pinceau;
	}
	
	public void action(boolean descendre){
		this.setPinceau(descendre);
		fenetre.descendrePinceau(descendre);
	}
	public boolean isPinceau() {
		return pinceau;
	}

	public void setPinceau(boolean pinceau) {
		this.pinceau = pinceau;
	}

}
