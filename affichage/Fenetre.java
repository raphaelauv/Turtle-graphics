package affichage;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import controleur.ControleurAvance;
import controleur.ControleurListeToken;
import controleur.ControleurPinceau;
import controleur.ControleurTourne;
import controleur.EditionNettoyer;
import controleur.FenetreErreur;
import controleur.FichierScreen;
import moteur.Moteur;

public class Fenetre extends JFrame{
	
	Moteur moteur;
	FenetreDessin fenetreDessin;
	FenetreTortue fenetreTortue;
	ControleurListeToken controleurListeToken;
	FichierScreen screenSaver;
	FenetreErreur fenetreErreur;
	
	public static void creerVue(){
		new Fenetre();
	}
	
	public Fenetre(){
		super("Dessin ADS4");
		this.controleurListeToken=new ControleurListeToken(this);
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetreErreur= new FenetreErreur(this);
    	JMenuBar menuBar = new JMenuBar();
    	
    	JMenu file = new JMenu("Fichier");
    	JMenuItem quitter = new JMenuItem("Quitter");
    	JMenuItem listeTokens = new JMenuItem("Exporter liste Tokens");
    	JMenuItem charger = new JMenuItem("Charger");
    	JMenuItem screen = new JMenuItem("Sauvegarder image");

    	JMenu edition = new JMenu("Edition");
    	JMenuItem nettoyer = new JMenuItem("Nettoyer affichage");
    	
    	
    	
    	JPanel conteneurBas=new JPanel();
    	Container contentPane = this.getContentPane();
    	contentPane.add(conteneurBas);
    	
    	conteneurBas.add(fenetreTortue=new FenetreTortue());
    	this.fenetreDessin=new FenetreDessin();
    	fenetreDessin.setBackground(Color.black);
    	conteneurBas.add(this.fenetreDessin);
    	
    	this.screenSaver=new FichierScreen(this,this.fenetreDessin);
    	
    	conteneurBas.setLayout(new OverlayLayout(conteneurBas));//J'ajoute un layout(Horizontal) a mon comteneurGlobal.
    	
    	file.add(charger);
    	file.add(listeTokens);
    	file.add(screen);
    	file.add(quitter);
    	edition.add(nettoyer);
    	listeTokens.addActionListener(controleurListeToken);
    	quitter.addActionListener(new controleur.FichierQuitter());
    	charger.addActionListener(new controleur.FichierCharger(this));
    	screen.addActionListener(this.screenSaver);
    	nettoyer.addActionListener(new EditionNettoyer(fenetreDessin,controleurListeToken));
    	menuBar.add(file);
    	menuBar.add(edition);
    	this.setJMenuBar(menuBar);
    	this.setSize(400, 400);
    	this.setVisible(true);
    	
	}

	public void dessiner(Reader reader)  {
		
		try {
			this.moteur=new Moteur(reader);
			this.moteur.setFenetreErreur(fenetreErreur);
			this.moteur.setControleurAvance(new ControleurAvance(this));
			this.moteur.setControleurPinceau(new ControleurPinceau(this));
			this.moteur.setControleurListeToken(this.controleurListeToken);
			this.moteur.analyseSynt();
			this.moteur.parcourtArbre();
			
		} 
		
		catch (Exception e) {
			fenetreErreur.afficherErreur(e.getMessage());
		}
		
	}
	
	public void dessinerAvance(Trait n){
		this.fenetreDessin.dessiner(n);
		this.fenetreTortue.deplacerTortue(n.fin);
	}

	public void descendrePinceau(Boolean descendre){
		this.fenetreDessin.setPinceauBas(descendre);
	}
	public void dessinerTourne(int angle) {
		 this.fenetreTortue.tournerTortue(angle);
		
	}
	

}
