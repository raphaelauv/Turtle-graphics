package controleur.fichier;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import affichage.Fenetre;

public class Compiler extends FichierCharger implements ActionListener {
	public Compiler(Fenetre fenetre) {
		// TODO Auto-generated constructor stub
		super(fenetre);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if(this.fenetre==null){
			return;
		}
		
		File java = this.getFile("charger un fichier .java exporté avec ce meme programe","JAVA");
		
		if (java ==null || java.getName()==null || !java.getName().endsWith(".java")){
			JOptionPane.showMessageDialog(fenetre,
		   			 "attention ce n'est pas un fichier .java","CONSIGNE",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		File jar = this.getFile("charger le fichier .jar de ce programe","JAR");
		
		
		if (jar ==null || jar.getName()==null  || !jar.getName().endsWith("ADS4.jar")){
			JOptionPane.showMessageDialog(fenetre,
		   			 "attention ce n'est pas le fichier ADS4.jar","CONSIGNE",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.compiler(java,jar);
	}	
	public void compiler(File java , File jar){	
		try{
			Runtime rt = Runtime.getRuntime();
			
			Process proc = rt.exec("javac -cp "+jar.getAbsolutePath() +" "+java.getAbsolutePath());

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			boolean uneErreur=false;
			//  output de la commande
			String erreur="";
			
			erreur=erreur+"Here is the standard output of the command:\n";
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    erreur=erreur+s+"\n";
			    uneErreur=true;
			}
			// erreurs renvoyer par la commande
			erreur=erreur+"Here is the standard error of the command (if any):\n";
			while ((s = stdError.readLine()) != null) {
				erreur=erreur+s+"\n";
			    uneErreur=true;
			}
			
			if(uneErreur){
				
				JTextArea textArea = new JTextArea(erreur);
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
				
				JOptionPane.showMessageDialog(fenetre,
						scrollPane,"ERREUR de compilation",JOptionPane.ERROR_MESSAGE);
				return;
			}
			/*
			else{
				
				JComponent bloc = new JPanel();
				bloc.add(new JButton("oui"));
				int ok;
				ok=JOptionPane.showConfirmDialog(fenetre, "voulez vous executer maintenant ?");
				if(ok==0){
					Runtime rt2 = Runtime.getRuntime();
					String java2=java.getAbsolutePath();
					java2=java2.substring(0, java2.length()-5);
					Process proc2 = rt2.exec("java -cp "+jar.getAbsolutePath() +" "+java2);
					
					
				}*/
			
			}catch(Exception e){
				JOptionPane.showMessageDialog(fenetre,
						e.getMessage(),"ERREUR de compilation",JOptionPane.NO_OPTION);
			}
			return;
	}
}
