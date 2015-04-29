package affichage;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Reader;
import java.util.LinkedList;

import javax.swing.BorderFactory;
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

import controleur.ControleurExportToken;
import controleur.ControleurPositionCurseurCouleur;
import controleur.EditionNettoyer;
import controleur.FenetreErreur;
import controleur.GUI.ControleurAvance;
import controleur.GUI.ControleurCouleur;
import controleur.GUI.ControleurDimension;
import controleur.GUI.ControleurEpaisseur;
import controleur.GUI.ControleurPinceau;
import controleur.GUI.ControleurTourne;
import controleur.fichier.FichierProgJava;
import controleur.fichier.FichierScreen;
import moteur.IntToken;
import moteur.Moteur;
import moteur.Sym;
import moteur.Token;
import moteur.VarToken;

public class Fenetre extends JFrame implements Nettoyer {
	
	private Moteur moteur;
	private LinkedList<Moteur> listeInter;	
	private FenetreDessin fenetreDessin;
	private ControleurExportToken controleurListeToken;
	private FichierScreen screenSaver;
	private FenetreErreur fenetreErreur;
	private FichierProgJava controleurProgJava;
	
	private JRadioButtonMenuItem boutonBas;
	private JRadioButtonMenuItem boutonHaut;
	
	private VariableAfficher angle;
	private VariableAfficher epaisseur;
	private VariableAfficher couleur;
	private VariableAfficher dimension;
	private VariableAfficher position;
	
	private ControleurCouleur controleurCouleur;
	private ControleurAvance controleurAvance;
	private ControleurPinceau controleurPinceau;
	private ControleurTourne controleurTourne;
	private ControleurDimension controleurDimension;
	private ControleurEpaisseur controleurEpaisseur;
	
	private boolean afficherSortie;//Afficher une erreur pour une sortie
	
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
		this.position = new VariableAfficher("Position curseur X:Y","0:0",true);
		this.angle = new VariableAfficher("Angle","90",false);
		this.fenetreDessin = new FenetreDessin(this.position,this.angle);
		
		this.controleurListeToken = new ControleurExportToken(this);
		this.controleurProgJava = new FichierProgJava(this,listeInter);
		this.fenetreErreur = new FenetreErreur(this);
		
		this.epaisseur = new VariableAfficher("Epaisseur","1",false);
		this.couleur = new VariableAfficher("Couleur","BLACK",false);
		this.dimension = new VariableAfficher("Dimension","400x400",false);
		
		this.screenSaver = new FichierScreen(this,fenetreDessin);
		this.setContentPane(new ScrollPane());
		
		
		//Controleur
		
		this.controleurCouleur= new ControleurCouleur(this);
		this.controleurAvance=new ControleurAvance(this);
		this.controleurPinceau=new ControleurPinceau(this,false);
		this.controleurTourne = new ControleurTourne(this);
		this.controleurDimension=new ControleurDimension(this);
		this.controleurEpaisseur=new ControleurEpaisseur(this);
		this.position.addActionListener(new ControleurPositionCurseurCouleur(this.fenetreDessin,this.position));
		
		//Elements du Menu FICHIER
		JMenuItem quitter = new JMenuItem("Quitter");
		JMenuItem compiler = new JMenuItem("Compiler .java");
		JMenuItem listeTokens = new JMenuItem("Exporter SpeudoCode - Tokens");
		JMenuItem progJava = new JMenuItem("Exporter programme JAVA");
		JMenuItem charger = new JMenuItem("Charger");
		JMenuItem screen = new JMenuItem("Sauvegarder image");
		
		
		// les Boutons radio PINCEAU HAUT ET BAS
		JMenuItem nettoyer = new JMenuItem("Nettoyer affichage");
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
		quitter.addActionListener(new controleur.fichier.FichierQuitter());
		charger.addActionListener(new controleur.fichier.FichierCharger(this));
		screen.addActionListener(this.screenSaver);
		compiler.addActionListener(new controleur.fichier.Compiler(this));
		nettoyer.addActionListener(new EditionNettoyer(fenetreDessin,controleurListeToken,controleurProgJava,this));

		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Fichier");
		JMenu edition = new JMenu("Edition");
		menuBar.add(file);
		menuBar.add(edition);
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
		edition.addSeparator();
		edition.add(dimension);
		
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
		JPanel a = new JPanel();
		a.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
		a.setLayout(new CardLayout());
		a.add(this.fenetreDessin);
		
		zoneImage.add(a);
		
		/*
		JPanel haut =new JPanel();
		haut.setLayout(new BoxLayout(haut,BoxLayout.LINE_AXIS));
		haut.setBackground(Color.white);
		haut.add(interpreteur);
		haut.add(ok);
		
		*/
		//this.getContentPane().setLayout(new BorderLayout());
		//getContentPane().add(haut,BorderLayout.PAGE_START);
		
		menuBar.add(interpreteur);
		menuBar.add(ok);
	    getContentPane().add(zoneImage,BorderLayout.CENTER);
	    
		
		this.setMinimumSize(new Dimension(300,300));
		this.setPreferredSize(new Dimension(500,500));
		this.pack();
		this.setVisible(true);
		

	} 
	private boolean moteurAnalyseAndParcourt(Moteur moteur,boolean modeInterpreteur){
		try {
			moteur.analyseSynt();
			moteur.parcourtArbre();
		}
		catch (Exception e) {
			fenetreErreur.afficherErreur(e.getMessage());
			return false;
		}
		if(modeInterpreteur){
			this.controleurListeToken.ajouterToken(new Token(Sym.VIRGULEINTER,-1,-1));
		}
		return true;
		
	}
	private void ajouterTokensMiseAzero(){
		this.controleurListeToken.ajouterToken(new Token(Sym.HAUTPINCEAU, -1, -1));
		this.controleurListeToken.ajouterToken(new Token(Sym.CONCAT, -1, -1));
		this.controleurListeToken.ajouterToken(new Token(Sym.GO, -1, -1));
		this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1 ,0));
		this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1, 0));
		this.controleurListeToken.ajouterToken(new Token(Sym.CONCAT, -1, -1));
		this.controleurListeToken.ajouterToken(new Token(Sym.COULEUR, -1, -1));
		this.controleurListeToken.ajouterToken(new VarToken(Sym.VARIABLE, -1, -1,"black"));
		this.controleurListeToken.ajouterToken(new Token(Sym.CONCAT, -1, -1));
		this.controleurListeToken.ajouterToken(new Token(Sym.EPAISSEUR, -1, -1));
		this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1, 1));
		this.controleurListeToken.ajouterToken(new Token(Sym.CONCAT, -1, -1));
		int angleActuel=Integer.parseInt(this.angle.getValeur());
		int difference=angleActuel-90;
		if(angleActuel<difference){
			difference=difference*-1;
		}
		this.controleurListeToken.ajouterToken(new Token(Sym.TOURNE, -1, -1));
		this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1 ,0));
		if(difference<0){
			this.controleurListeToken.ajouterToken(new Token(Sym.PLUS, -1, -1));
			this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1 ,difference*-1));
		}else{
			this.controleurListeToken.ajouterToken(new Token(Sym.MINUS, -1, -1));
			this.controleurListeToken.ajouterToken(new IntToken(Sym.INT, -1, -1 ,difference));
		}
		this.controleurListeToken.ajouterToken(new Token(Sym.CONCAT, -1, -1));
	}
	public boolean dessiner(Reader reader,boolean modeInterpreteur) throws Exception {
		
		boolean succes=true;// si la commende n'a pas emis derreur
		if(this.moteur==null || !modeInterpreteur){
			if(this.moteur!=null){
				this.ajouterTokensMiseAzero();
			}
			this.moteur=new Moteur(reader,false,90,"BLACK",1);
			this.moteur.setControleurCouleur(this.controleurCouleur);
			this.moteur.setControleurAvance(this.controleurAvance);
			this.moteur.setControleurPinceau(this.controleurPinceau);
			this.moteur.setControleurListeToken(this.controleurListeToken);
			this.moteur.setControleurTourne(this.controleurTourne);
			this.moteur.setControleurDimension(this.controleurDimension);
			this.moteur.setControleurEpaisseur(this.controleurEpaisseur);
			succes=this.moteurAnalyseAndParcourt(this.moteur,modeInterpreteur);
			
			this.controleurProgJava.addMoteur(this.moteur);
		}
		else if(modeInterpreteur){
			
			//this.moteur.getListeVariable();
			this.moteur.setReader(reader);
			//Moteur moteurInter = new Moteur(reader,tmp);
			succes=this.moteurAnalyseAndParcourt(this.moteur,modeInterpreteur);
			/*
			ValueEnvironment tmp=this.moteur.getListeVariable();
			Moteur moteurInter = new Moteur(reader,tmp);
			succes=this.moteurAnalyseAndParcourt(moteurInter);
			this.controleurProgJava.addMoteur(moteurInter);
			*/
		}
		return succes;
	}
	public boolean dessinerAvance(Trait adessiner) throws Exception {
		if(fenetreDessin!=null && !Fenetre.isSortie(adessiner,this.fenetreDessin.getSize()) ){
			this.fenetreDessin.dessiner(adessiner);
			this.position.setValeur(adessiner.fin.x +":"+adessiner.fin.y);
			return true;
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
	}
	public void nettoyer() {
		this.moteur=null;
		this.boutonHaut.setSelected(true);
		this.angle.setValeur("90");
		this.epaisseur.setValeur("1");
		this.couleur.setValeur("BLACK");
		this.position.setValeur("0:0");
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
			int x= (int)dimension.getHeight();
			int y= (int)dimension.getWidth();
			this.dimension.setValeur(x+"x"+y);
			this.fenetreDessin.repaint();
			
		}
		if(this.fenetreDessin.isSortie()){
			JOptionPane.showMessageDialog(this,
					"Attention vous etes sortie des dimensions"
					,"ERREUR ",JOptionPane.ERROR_MESSAGE);
		}
		this.validate();
	}

	public static boolean isSortie(Trait adessiner ,Dimension fenetre) throws Exception{
		if(adessiner==null || fenetre==null){
			throw new Exception("Appel avec argument(s) null");
		}
		if(adessiner.fin.x>fenetre.getWidth() ||adessiner.fin.x<0 || 
				(fenetre.getHeight()-1)- adessiner.fin.y<0 || 
				(fenetre.getHeight()-1)- adessiner.fin.y>fenetre.getHeight()){
			return true;
		}
		return false;
	}
	public FenetreDessin getFenetreDessin() {
		return fenetreDessin;
	}

	public void setFenetreDessin(FenetreDessin fenetreDessin) {
		this.fenetreDessin = fenetreDessin;
	}
}