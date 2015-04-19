package affichage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.lang.reflect.Field;
import java.util.LinkedList;


import javax.swing.JPanel;

public class FenetreDessin extends JPanel implements Nettoyer {
	

	private LinkedList<Trait> listeDesTraits = new LinkedList<Trait>();
	
	private boolean pinceauBas;


	public void dessiner(Trait adessiner) {
		listeDesTraits.add(adessiner);
		this.repaint();
	}

	public void nettoyer(){
		this.listeDesTraits=new LinkedList<Trait>();
		this.pinceauBas=false;
		this.repaint(0,0,this.getWidth(),this.getHeight());
	}
	public FenetreDessin(){
	}
	
	public void paintComponent(Graphics g) {
		
		for(Trait adessiner : listeDesTraits){
			if(adessiner!=null && pinceauBas && adessiner.visible){
				Graphics2D g2d = (Graphics2D)g;
				//System.out.println(adessiner.couleur);
				Color tmp=new Color(0);
				try {
					
					Field field=tmp.getClass().getDeclaredField(adessiner.couleur);
					//System.out.println("field" +field.toString());
					tmp = (Color) field.get(tmp);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				g2d.setColor(tmp);
		        g2d.setStroke(new BasicStroke(adessiner.epaisseur));
		        g2d.draw(new Line2D.Float(adessiner.debut.x, this.getHeight()-adessiner.debut.y, adessiner.fin.x, this.getHeight()-adessiner.fin.y));
			}
		}
	}

	public boolean isPinceauBas() {
		return pinceauBas;
	}

	public void setPinceauBas(boolean pinceauBas) {
		this.pinceauBas = pinceauBas;
	}
		
	}
	
	