import java.io.IOException;

import controleur.FenetreErreur;

public class testa {

	public static void main(String[] args) throws IOException {
/*
		System.out.println("toto");
		String a = "C:/Users/Raph/Documents/test.java";

		// a.replaceAll("\"", "/");
		String b = "C:/Users/Raph/Documents/dessin.jar";
		Runtime.getRuntime().exec("javac " + a);
		
		System.out.println(System.getProperty("java.class.path"));
		Runtime.getRuntime().exec("java -jar " + b);
*/
		
		FenetreErreur a = new FenetreErreur(null);
		a.afficherErreur("coco");
	}
}

