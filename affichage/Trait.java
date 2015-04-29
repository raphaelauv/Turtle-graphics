package affichage;

import java.awt.Point;

public class Trait {
	
	protected int taille;
	protected int epaisseur;
	protected String couleur;
	protected Point debut;
	protected Point fin;
	protected int angle;
	protected boolean visible;
	public Trait(int epaisseur , String couleur , Point debut,Point fin, boolean visible){
		this.epaisseur=epaisseur;
		this.couleur=couleur;
		this.debut=debut;
		this.fin=fin;
		this.visible=visible;
		//System.out.println(debut);
		//System.out.println(fin);
		if(debut.x==0){
			this.angle=90;
		}
		else{
			double angle = Math.atan((fin.y - debut.y) / (fin.x - debut.x));
			angle=Math.toDegrees(angle);
			this.angle=(int)angle;
		}
	}
	
	public Trait(int taille , int epaisseur , String couleur , Point debut , int angle, boolean visible){
		this.taille=taille;
		this.epaisseur=epaisseur;
		this.couleur=couleur;
		this.debut=debut;
		this.angle=angle;
		this.visible=visible;
		
		/*System.out.println("angle : "+angle);
		System.out.println("taille : "+taille);
		System.out.println("epaisseur : "+epaisseur);
		System.out.println("couleur : "+couleur);
		System.out.println("point de debut : "+debut);
		*/
		this.fin=new Point();
		
		if(angle==270){
			this.fin.x=debut.x;
			this.fin.y=debut.y-taille;
		}else{
			this.fin.x=(int) (debut.x+taille*Math.cos(Math.toRadians(angle)));
			this.fin.y=(int) (debut.y+taille*Math.sin(Math.toRadians(angle)));
		}
	}

	@Override
	public String toString() {
		return "Trait [taille=" + taille + ", epaisseur=" + epaisseur
				+ ", couleur=" + couleur + ", debut=" + debut + ", fin=" + fin
				+ ", angle=" + angle + ", visible=" + visible + "]";
	}

	public Point getFin() {
		return fin;
	}

	
}
