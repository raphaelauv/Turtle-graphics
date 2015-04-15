package controleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichage.Fenetre;

public class Enregistrer {

	public static void enregistrer(Fenetre fenetre,String texte,String nomFichierDefaut){
		  JFileChooser chooser = new JFileChooser();
		  chooser.setSelectedFile(new File(nomFichierDefaut));
          if (chooser.showSaveDialog(fenetre) != JFileChooser.APPROVE_OPTION) {
              return;
          }
          FileWriter writer = null;
          try {
        	  File file = chooser.getSelectedFile();
              
        	  writer = new FileWriter(file);
        	  writer.write(texte);
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(fenetre,
                      "Erreur sauvegarde", "ERROR", JOptionPane.ERROR_MESSAGE);
          } finally {
              if (writer != null) {
                  try {
                      writer.close();
                  } catch (IOException x) {
                	  
                  }
              }
          }
	}
}
