package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import affichage.Fenetre;

public class FichierCharger implements ActionListener {

	JFrame fenetre;
	
	public FichierCharger(JFrame fenetre){
		this.fenetre=fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (this.fenetre == null){
			
		}
		 JFileChooser chooser = new JFileChooser();
		 chooser.showOpenDialog(chooser);
         File file = chooser.getSelectedFile();
         if (file == null) {
        	 JOptionPane.showMessageDialog(fenetre,
        			 "Aucun fichier charger","ATTENTION",JOptionPane.WARNING_MESSAGE);
        	 return;
         }
         FileReader reader = null;
         try {
             reader = new FileReader(file);
             
         } catch (Exception ex) {
        	 JOptionPane.showMessageDialog(fenetre,
        			 "Fichier  non trouve","ERREUR",JOptionPane.WARNING_MESSAGE);
         }
         try{
        	 ((Fenetre) this.fenetre).dessiner(reader);
        	 if (reader != null) {
                 try {
                     reader.close();
                 } catch (Exception x) {
                	 x.printStackTrace();
                 }
             }
         } catch (Exception ex) {
        	 ex.printStackTrace();
         }
		
	}

}
