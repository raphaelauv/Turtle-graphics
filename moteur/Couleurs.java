package moteur;

public enum Couleurs {
	BLACK,
	BLUE,
	CYAN,
	DARK_GRAY,
	GRAY,
	GREEN,
	LIGHT_GRAY,
	MAGENTA,
	ORANGE,
	PINK,
	RED,
	WHITE,
	YELLOW,
	black,
	blue,
	cyan,
	dark_gray,
	gray,
	green,
	light_gray,
	magenta,
	orange,
	pink,
	red,
	white,
	yellow;

	public static String liste(){
		String tmp="";
		for(Couleurs c: Couleurs.values()){
			tmp=tmp+c.toString();
			tmp=tmp+"\n";
		}
		return tmp;
	}
	
	/*
	 * 
	 * 
BLACK,
BLUE,
CYAN,
DARK_GRAY,
GRAY,
GREEN,
LIGHT_GRAY,
MAGENTA,
ORANGE,
PINK,
RED,
WHITE,
YELLOW;

	NOIR,
	GRIS,
	BLEU,
	CYAN,
	GRISFONCE,
	VERT,
	GRISCLAIR,
	MAGENTA,
	ORANGE,
	ROSE,
	ROUGE,
	BLANC,
	JAUNE;
	 * 
	 */
}
