package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import affichage.Fenetre;

public class ControleurInterpreteur  implements ActionListener  {

	JTextField jtext;
	Fenetre fenetre;
	public ControleurInterpreteur(JTextField interpreteur,Fenetre fenetre){
		this.jtext=interpreteur;
		this.fenetre=fenetre;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(jtext==null || jtext.getText()==null || jtext.getText().length()<1){
			return;
		}
		String texte=this.jtext.getText();
		
		this.jtext.requestFocus();
		
		if(this.fenetre==null){
			return;
		}
		boolean succes=true;
		try {
			succes=this.fenetre.dessiner(new StringReader(texte),true);
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(fenetre,
        			 e.getMessage(),"ERREUR",JOptionPane.WARNING_MESSAGE);
		}
		if(succes){
			this.jtext.setText(null);
		}
		this.fenetre.getFenetreDessin().repaint();
	}
}
