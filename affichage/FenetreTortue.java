package affichage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FenetreTortue extends JPanel {
	
	private BufferedImage image = null;

	private int angle;
	private Point position;
	private boolean afficherImage=true;
	
	FenetreTortue() throws Exception{
		
		this.position=new Point(0,0);
		try {
			image = ImageIO.read(this.getClass().getResource("image.png"));

		} catch (Exception e) {
			throw new Exception("Une image na pas etait trouver.");
		}
	}
	
	protected void paintComponent(Graphics g) {
		
		if(afficherImage){
		
		Graphics2D g2d = (Graphics2D)g;
			g2d.rotate(angle);
		    int x = (this.position.x - image.getWidth(null)) / 2;
		    int y = (this.position.y - image.getHeight(null)) / 2;
		    g2d.drawImage(image, x, y, null);
		}
		
    }
	
	public void tournerTortue(int angle){
		this.angle=angle;
	}

	public boolean isAfficherImage() {
		return afficherImage;
	}

	public void setAfficherImage(boolean afficherImage) {
		this.afficherImage = afficherImage;
	}

	public void deplacerTortue(Point fin) {
		this.position=fin;
		
	}

}
