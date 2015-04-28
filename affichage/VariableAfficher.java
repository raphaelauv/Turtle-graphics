package affichage;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class VariableAfficher  extends JMenuItem{
	
	private String variable;
	private String valeur;
	private Color couleurDeFond;
	
	public VariableAfficher(String variable, String valeur, boolean enabled){
		this.variable=variable;
		this.valeur=valeur;
		this.setText(variable+" actuel : "+valeur);
		this.setEnabled(enabled);
		
		
	}

	public void setValeur(String valeur) {
		this.valeur=valeur;
		this.setText(variable+" actuel : "+valeur);
	}
	
	public String getValeur(){
		return valeur;
	}
	
	public void changerFondCouleur(Color couleurDeFond){
		this.couleurDeFond=couleurDeFond;
		if(!this.couleurDeFond.equals(Color.BLACK)){
			this.setBackground(couleurDeFond);
		}
		
		
	}
}
