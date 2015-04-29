package affichage;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controleur.FenetreErreur;
import moteur.Couleurs;

public class FenetreDessin extends Canvas implements Nettoyer {

	private LinkedList<Trait> listeDesTraits;
	private boolean isSortie;
	private Random rnd = new Random();
	private boolean afficherCurseur;
	private Color couleurCurseur;

	private VariableAfficher position;
	private VariableAfficher angle;
	
	public FenetreDessin(VariableAfficher position,VariableAfficher angle) {
		this.couleurCurseur=Color.black;
		this.position=position;
		this.angle=angle;
		this.afficherCurseur=true;
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(400,400);
		this.setBackground(Color.WHITE);
		this.nettoyer();
	}

	private Point getPointCurseur(){
		String position=this.position.getValeur();
        String [] valeurs=position.split(":");

        int x=Integer.parseInt(valeurs[0]);
        int y=Integer.parseInt(valeurs[1]);
        return new Point(x,y);
	}
	private int getAngleCurseur(){
		return Integer.parseInt(this.angle.getValeur());
	}
	
	public void dessiner(Trait adessiner) {
		if(listeDesTraits==null){
			listeDesTraits= new LinkedList<Trait>();
		}
		listeDesTraits.add(adessiner);
	}

	public void nettoyer() {
		this.listeDesTraits = new LinkedList<Trait>();
		this.repaint(0, 0, this.getWidth(), this.getHeight());
	}

	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		boolean nouvelleSortie=false;
		if(listeDesTraits==null){
			return;
		}
		for (Trait adessiner : listeDesTraits) {
			try {
				nouvelleSortie=Fenetre.isSortie(adessiner, this.getSize());
			} catch (Exception e1) {
			}
			if (adessiner != null && adessiner.visible && !nouvelleSortie ) {
				
				Color couleur = this.choixCouleur(adessiner);
				
				g2d.setColor(couleur);
				
				g2d.setStroke(new BasicStroke(adessiner.epaisseur));
				
				g2d.draw(new Line2D.Float(
					adessiner.debut.x,
					(this.getHeight()-1)- adessiner.debut.y, 
					adessiner.fin.x,
					(this.getHeight()-1)- adessiner.fin.y));
			}
		}
		
		//Pour prevenir d une sortie pendant un changement de taille de l image
		if(nouvelleSortie){
			this.isSortie=true;
		}
		else{
			this.isSortie=false;
		}
		if(this.afficherCurseur){
			Point centre=this.getPointCurseur();
			g2d.setStroke(new BasicStroke(1));
			g2d.setColor(this.couleurCurseur);			
			g2d.drawOval(centre.x-10, (this.getHeight()-1)- centre.y-10, 20, 20);
			Trait marq =new Trait(10,1,"black",new Point(centre.x,centre.y),this.getAngleCurseur(),true);
			g2d.drawLine(centre.x, (this.getHeight()-1)- centre.y,marq.getFin().x , (this.getHeight()-1)- marq.fin.y);
		}
	}
	/**
	 * 
	 * @param adessiner applique une couleur choisi aleatoirement a l'argument , peut etre null
	 * @return retourne la courleur choisi aleatoirement
	 */
	private Color couleurAleaoire(Trait adessiner) {
		
		Color tmp = new Color(0);
		String couleurChoisi = "aleaoire";
		try {
			Couleurs[] tabCouleurs = moteur.Couleurs.values();
			do {
				// Field [] tabCouleurs =tmp.getClass().getDeclaredFields();
				int nombre = rnd.nextInt(tabCouleurs.length);
				// Field fieldChoisi=tabCouleurs[nombre];
				couleurChoisi = tabCouleurs[nombre].toString();
			} while (couleurChoisi.equals("aleatoire"));
			// ou sinon -1 pour eliminer aleatoire dans le nextInt
			Field fieldChoisi = tmp.getClass().getDeclaredField(couleurChoisi);
			tmp = (Color) fieldChoisi.get(tmp);
		} catch (Exception e) {
			FenetreErreur erreur = new FenetreErreur(null);
			erreur.afficherErreur("une couleur dans Sym.java n'existe pas dans la class Color de java");
		}
		if(adessiner!=null){
			adessiner.couleur=couleurChoisi;// la couleur est conserver pour le trait
		}
		return tmp;
	}
	private Color choixCouleur(Trait adessiner){
		Color tmp = new Color(0);
			if(adessiner.couleur.equals("aleatoire")){
				tmp=couleurAleaoire(adessiner);
			}else{
				try{
					Field field = tmp.getClass().getDeclaredField(adessiner.couleur);
					// System.out.println("field" +field.toString());
					tmp = (Color) field.get(tmp);	
				}
				catch(Exception e){
					
				}
			}
		return tmp;
	}
	
	public boolean isSortie() {
		return isSortie;
	}
	public void setSortie(boolean isSortie) {
		this.isSortie = isSortie;
	}
	public boolean isAfficherCurseur() {
		return afficherCurseur;
	}

	public void setAfficherCurseur(boolean afficherCurseur) {
		this.afficherCurseur = afficherCurseur;
	}

	public Color getCouleurCurseur() {
		return couleurCurseur;
	}

	public Color changerCouleurCurseur() {
		return this.couleurCurseur = this.couleurAleaoire(null);
	}
}