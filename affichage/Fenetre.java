package affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Reader;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controleur.ControleurAvance;
import controleur.ControleurCouleur;
import controleur.ControleurDimension;
import controleur.ControleurEpaisseur;
import controleur.ControleurListeToken;
import controleur.ControleurPinceau;
import controleur.ControleurTourne;
import controleur.FichierProgJava;
import controleur.EditionNettoyer;
import controleur.FenetreErreur;
import controleur.FichierScreen;
import moteur.Moteur;

public class Fenetre extends JFrame implements Nettoyer {

	private Moteur moteur;
	FenetreDessin fenetreDessin;
	private FenetreTortue fenetreTortue;
	private ControleurListeToken controleurListeToken;
	private FichierScreen screenSaver;
	private FenetreErreur fenetreErreur;
	private FichierProgJava controleurProgJava;
	private VariableAfficher angle;
	private JRadioButtonMenuItem boutonBas;
	private JRadioButtonMenuItem boutonHaut;
	private VariableAfficher epaisseur;
	private VariableAfficher couleur;
	private VariableAfficher taille;
	private VariableAfficher position;
	private LinkedList<Moteur> listeInter;
	private ControleurCouleur controleurCouleur;
	private ControleurAvance controleurAvance;
	private ControleurPinceau controleurPinceau;
	private ControleurTourne controleurTourne;
	private ControleurDimension controleurDimension;
	private ControleurEpaisseur controleurEpaisseur;
	private JScrollPane conteneurBas;
	private boolean afficherSortie;
	

	public boolean isAfficherSortie() {
		return afficherSortie;
	}

	public void setAfficherSortie(boolean afficherSortie) {
		this.afficherSortie = afficherSortie;
	}

	public static void creerVue() {
		new Fenetre();
	}

	public Fenetre() {
		super("Dessin ADS4");
		this.afficherSortie=false;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetreDessin = new FenetreDessin();
		this.controleurListeToken = new ControleurListeToken(this);
		this.controleurProgJava = new FichierProgJava(this,listeInter);
		this.fenetreErreur = new FenetreErreur(this);
		this.angle = new VariableAfficher("Angle","90");
		this.epaisseur = new VariableAfficher("Epaisseur","1");
		this.couleur = new VariableAfficher("Couleur","BLACK");
		this.taille = new VariableAfficher("Angle","400x400");
		this.position = new VariableAfficher("Position curseur","0,0");
		this.screenSaver = new FichierScreen(this,fenetreDessin);
		this.afficherSortie=false;
		
		
		//Controleur
		
		this.controleurCouleur= new ControleurCouleur(this);
		this.controleurAvance=new ControleurAvance(this);
		this.controleurPinceau=new ControleurPinceau(this,false);
		this.controleurTourne = new ControleurTourne(this);
		this.controleurDimension=new ControleurDimension(this);
		this.controleurEpaisseur=new ControleurEpaisseur(this);
		
		//Elements du Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier");
		JMenu edition = new JMenu("Edition");
		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem compiler = new JMenuItem("Compiler .java");
		JMenuItem listeTokens = new JMenuItem("Exporter liste Tokens");
		JMenuItem progJava = new JMenuItem("Exporter programme JAVA");
		JMenuItem charger = new JMenuItem("Charger");
		JMenuItem screen = new JMenuItem("Sauvegarder image");
		JMenuItem nettoyer = new JMenuItem("Nettoyer affichage");
		
		// les Boutons radio PINCEAU HAUT ET BAS
		ButtonGroup group = new ButtonGroup();
		this.boutonBas = new JRadioButtonMenuItem("Pinceau Bas");
		this.boutonHaut = new JRadioButtonMenuItem("Pinceau Haut");
		boutonHaut.setSelected(true);
		boutonHaut.setEnabled(false);
		boutonBas.setEnabled(false);
		group.add(boutonBas);
		group.add(boutonHaut);
		
		//ActionListener
		progJava.addActionListener(controleurProgJava);
		listeTokens.addActionListener(controleurListeToken);
		quitter.addActionListener(new controleur.FichierQuitter());
		charger.addActionListener(new controleur.FichierCharger(this));
		screen.addActionListener(this.screenSaver);
		compiler.addActionListener(new controleur.Compiler(this));
		
		nettoyer.addActionListener(new EditionNettoyer(fenetreDessin,controleurListeToken,controleurProgJava,this));
		menuBar.add(file);
		menuBar.add(edition);
		
		
		// Menu
		file.add(charger);
		file.add(listeTokens);
		file.add(progJava);
		file.add(compiler);
		file.add(screen);
		file.add(quitter);
		edition.add(nettoyer);
		edition.addSeparator();
		edition.add(boutonBas);
		edition.add(boutonHaut);
		edition.addSeparator();
		edition.add(angle);
		edition.addSeparator();
		edition.add(couleur);
		edition.addSeparator();
		edition.add(epaisseur);
		edition.addSeparator();
		edition.add(position);
		this.setJMenuBar(menuBar);
		
		
		// INTERPRETEUR
		
		JTextField interpreteur =new JTextField("");
		interpreteur.setFocusable(true);
		
		final JButton ok = new JButton("Interpreter");
		KeyListener keyListener = new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					ok.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
		 };
		
		interpreteur.addKeyListener(keyListener);
		
		ok.addActionListener(new controleur.ControleurInterpreteur(interpreteur, this));
		//les JPANEL
		
		 
		JPanel zoneImage =new JPanel();
		zoneImage.setBackground(Color.GRAY);
		JPanel a =new JPanel();
		a.setSize(0, 0);
		a.setBackground(Color.white);
		a.add(this.fenetreDessin);
		zoneImage.add(a);
		
		
		JPanel haut =new JPanel();
		haut.setLayout(new BoxLayout(haut,BoxLayout.LINE_AXIS));
		haut.setBackground(Color.white);
		haut.add(interpreteur);
		haut.add(ok);
		
		this.getContentPane().setLayout(new BorderLayout());
		getContentPane().add(haut,BorderLayout.PAGE_START);
	    getContentPane().add(zoneImage,BorderLayout.CENTER);
	    
	    
		
		this.setMinimumSize(new Dimension(300,300));
		this.pack();
		this.setVisible(true);
		

	} 
	public boolean dessiner(Reader reader,boolean modeInterpreteur) throws Exception {
		
		boolean succes=true;
		
		if(this.moteur==null || !modeInterpreteur){
			this.moteur=new Moteur(reader,false,90,"BLACK",1);
			this.moteur.setFenetreErreur(fenetreErreur);
			this.moteur.setControleurCouleur(this.controleurCouleur);
			this.moteur.setControleurAvance(this.controleurAvance);
			this.moteur.setControleurPinceau(this.controleurPinceau);
			this.moteur.setControleurListeToken(this.controleurListeToken);
			this.moteur.setControleurTourne(this.controleurTourne);
			this.moteur.setControleurDimension(this.controleurDimension);
			this.moteur.setControleurEpaisseur(this.controleurEpaisseur);
		}
		else if(modeInterpreteur){
			this.moteur.setReader(reader);
		}
		try {
			this.moteur.analyseSynt();
			this.moteur.parcourtArbre();
		}
		catch (Exception e) {
			succes=false;
			fenetreErreur.afficherErreur(e.getMessage());
		}
		if(!modeInterpreteur){
			this.controleurProgJava.addMoteur(this.moteur);
		}
		else{
			if(listeInter==null){
				this.listeInter= new LinkedList<Moteur>();
			}
			listeInter.add(this.moteur);
		}
		this.repaint();
		return succes;
		
	}
	public boolean dessinerAvance(Trait n) {
		
		if(fenetreDessin!=null){
			this.fenetreDessin.dessiner(n);
		}
		if(fenetreTortue !=null){
			this.fenetreTortue.deplacerTortue(n.fin);
		}
		if(this.fenetreDessin!=null){
			return this.fenetreDessin.isSortie();
		}
		else return false;
	}
	public void changerCouleur(String couleur){
		this.couleur.setValeur(couleur);
	}
	public void changerEpaisseur(int epaisseur){
		this.epaisseur.setValeur(Integer.toString(epaisseur));
	}
	public void descendrePinceau(Boolean descendre) {
		if(descendre){
			boutonBas.setSelected(true);
		}
		else{
			boutonHaut.setSelected(true);
		}
	}
	public void dessinerTourne(int angle) {
		this.angle.setValeur(Integer.toString(angle));
		if(fenetreTortue !=null){
			this.fenetreTortue.tournerTortue(angle);
		}
	}
	public void nettoyer() {
		this.moteur=null;
		this.boutonHaut.setSelected(true);
		this.angle.setValeur("90");
		this.epaisseur.setValeur("1");
		this.couleur.setValeur("BLACK");
		this.fenetreDessin.nettoyer();
	}
	public void changerDimension(Dimension dimension) {
		if(dimension==null){
			return;
		}
		if(dimension.getHeight()<1 || dimension.getWidth()<1){
			
			JOptionPane.showMessageDialog(this,
					"erreur de choix de dimension\n Height : "+dimension.getHeight()
					+"\n Width : "+dimension.getWidth()
					
					,"ERREUR ",JOptionPane.ERROR_MESSAGE);
		}else{
			this.fenetreDessin.setSize(dimension);
			this.fenetreDessin.setPreferredSize(dimension);
		}
		
		this.repaint();

		if(this.fenetreDessin.isSortie()){
			JOptionPane.showMessageDialog(this,
					"Attention vous etes sortie des dimensions"
					,"ERREUR ",JOptionPane.ERROR_MESSAGE);
		}
		this.pack();
	}
	
}