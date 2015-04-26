package affichage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Line2D;
import java.lang.reflect.Field;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FenetreDessin extends JPanel implements Nettoyer {

	private LinkedList<Trait> listeDesTraits;
	private boolean isSortie;



	public FenetreDessin() {
		this.setPreferredSize(new Dimension(400,400));
		this.repaint();
	}

	public void dessiner(Trait adessiner) {
		if(listeDesTraits==null){
			listeDesTraits= new LinkedList<Trait>();
		}
		listeDesTraits.add(adessiner);
		this.repaint();
		
	}

	public void nettoyer() {
		this.listeDesTraits = new LinkedList<Trait>();
		this.repaint(0, 0, this.getWidth(), this.getHeight());
	}

	public void paintComponent(Graphics g) {
		if(listeDesTraits==null){
			return;
		}
		boolean nouvelleSortie=false;
		for (Trait adessiner : listeDesTraits) {
			if (adessiner != null && adessiner.visible) {
				Graphics2D g2d = (Graphics2D) g;
				Color tmp = new Color(0);
				try {
					Field field = tmp.getClass().getDeclaredField(adessiner.couleur);
					// System.out.println("field" +field.toString());
					tmp = (Color) field.get(tmp);
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				if(adessiner.fin.x>this.getWidth() ||adessiner.fin.x<0 || this.getHeight()
						- adessiner.fin.y<0 || this.getHeight()
						- adessiner.fin.y>this.getHeight()){
					nouvelleSortie=true;
				}
				g2d.setColor(tmp);
				g2d.setStroke(new BasicStroke(adessiner.epaisseur));
				g2d.draw(new Line2D.Float(adessiner.debut.x, this.getHeight()
						- adessiner.debut.y, adessiner.fin.x, this.getHeight()
						- adessiner.fin.y));
			}
		}
		if(nouvelleSortie){
			this.isSortie=true;
		}
		else{
			this.isSortie=false;
		}
		
	}
	public boolean isSortie() {
		return isSortie;
	}

	public void setSortie(boolean isSortie) {
		this.isSortie = isSortie;
	}
}