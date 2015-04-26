package affichage;

import java.awt.Point;

public class Trait {
	
	int taille;
	int epaisseur;
	String couleur;
	Point debut;
	Point fin;
	int angle;
	boolean visible;
	
	public Trait(int taille , int epaisseur , String couleur , Point debut , int angle, boolean visible){
		this.taille=taille;
		this.epaisseur=epaisseur;
		this.couleur=couleur;
		this.debut=debut;
		this.angle=angle;
		this.visible=visible;
		
		if(angle<0){
			System.out.println("ERREUR d'angle");
		}
		
		/*System.out.println("angle : "+angle);
		System.out.println("taille : "+taille);
		System.out.println("epaisseur : "+epaisseur);
		System.out.println("couleur : "+couleur);
		System.out.println("point de debut : "+debut);
		*/
		this.fin=new Point();
		this.fin.x=(int) (debut.x+taille*Math.cos(Math.toRadians(angle)));
		this.fin.y=(int) (debut.y+taille*Math.sin(Math.toRadians(angle)));
		
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
