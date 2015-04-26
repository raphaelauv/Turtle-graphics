package affichage;

import javax.swing.JMenuItem;

public class VariableAfficher  extends JMenuItem{
	
	String variable;
	
	
	public VariableAfficher(String variable, String valeur){
		this.variable=variable;
		this.setText(variable+" actuel : "+valeur);
		this.setEnabled(false);
	}

	public void setValeur(String valeur) {
		this.setText(variable+" actuel : "+valeur);
	}
}
