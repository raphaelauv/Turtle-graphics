import moteur.ValueEnvironment;
import moteur.instruction.*;
public class test{
	public static void main(String[] args) throws Exception {
		moteurN1();
	}
	public static void moteurN1()throws Exception{

		ValueEnvironment listeVariable = new ValueEnvironment(false,90,"BLACK",1);
		listeVariable.setModeSansFENETRE(true);
		
		int angle;
		angle = (0 - 90);
		//Debut
		new BasPinceau().exec(listeVariable);
		new Avance(100).exec(listeVariable);
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
		//Fin

	}

}