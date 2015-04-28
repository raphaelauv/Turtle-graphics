package controleur.GUI;
import affichage.Fenetre;

public abstract class ControleurGUI {
	protected Fenetre fenetre;
	public ControleurGUI(Fenetre fenetre) {
		super();
		this.fenetre = fenetre;
	}
}
