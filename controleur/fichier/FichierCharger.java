package controleur.fichier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import affichage.Fenetre;

public class FichierCharger implements ActionListener {

	Fenetre fenetre;
	
	public FichierCharger(Fenetre fenetre){
		this.fenetre=fenetre;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (this.fenetre == null){
			return;
		}
		File file=this.getFile("charger pseudo code",null);
		FileReader reader = null;
         try {
             reader = new FileReader(file);
             
         } catch (Exception ex) {
        	 /*
        	 JOptionPane.showMessageDialog(fenetre,
        			 "Fichier  non trouve","ERREUR",JOptionPane.WARNING_MESSAGE);
        			*/
        	 return;
         }
         try{
        	 fenetre.setAfficherSortie(false);
        	 this.fenetre.dessiner(reader,false);
        	 int x=this.fenetre.getFenetreDessin().getWidth();
        	 int y=this.fenetre.getFenetreDessin().getHeight();
        	 
        	 this.fenetre.getFenetreDessin().repaint(0,0,x,y);
        	 
         } catch (Exception ex) {
        	 //ex.printStackTrace();
        	 JOptionPane.showMessageDialog(fenetre,
        			 ex.getMessage(),"ERREUR",JOptionPane.WARNING_MESSAGE);
         }
		if (reader != null) {
			try {
				reader.close();
			} catch (Exception x) {
				// throw new Exception("Erreur a la fermeture du reader du fichier charger");
				// x.printStackTrace();
				
				JOptionPane.showMessageDialog(fenetre, x.getMessage(),
						"ERREUR", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	protected File getFile(String nameofBox , String extension){
		 JFileChooser chooser = new JFileChooser();
		 chooser.setDialogTitle(nameofBox);
		 if(extension!=null){
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(extension+" FILES", extension);
			 chooser.setFileFilter(filter);
		 }
		 chooser.showOpenDialog(chooser);
         File file = chooser.getSelectedFile();
         if (file == null) {
        	 /*
        	 JOptionPane.showMessageDialog(fenetre,
        			 "Aucun fichier charger","ATTENTION",JOptionPane.WARNING_MESSAGE);*/
        	 return null;
         }
         return file;
	}
}
