package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import affichage.Fenetre;

public class ControleurListeToken implements ActionListener{

	LinkedList<String> listeDesTokens;
	
	JFrame fenetre;
	public ControleurListeToken(JFrame fenetre ){
		this.fenetre=fenetre;
	}
	private String letexte(){
		String tmp="";
		for(String a: listeDesTokens){
			tmp=tmp+a;
			tmp=tmp+"\n";
		}
		return tmp;
	}
	
	public void nettoyer(){
		this.listeDesTokens=null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(this.listeDesTokens==null){
            JOptionPane.showMessageDialog(fenetre,
                    "Il n y a rien a exporter", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		
		  JFileChooser chooser = new JFileChooser();
		  chooser.setSelectedFile(new File("listeTokens"));
          if (chooser.showSaveDialog(fenetre) != JFileChooser.APPROVE_OPTION) {
              return;
          }
          FileWriter writer = null;
          try {
        	  File file = chooser.getSelectedFile();
              
        	  writer = new FileWriter(file);
        	  writer.write(letexte());
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
	
	public void ajouterToken(String token){
		if(this.listeDesTokens==null){
			this.listeDesTokens=new LinkedList<String>();
		}
		this.listeDesTokens.add(token);
	}

}
