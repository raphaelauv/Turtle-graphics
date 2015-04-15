import moteur.ValueEnvironment;
import moteur.instruction.Avance;
import moteur.instruction.BasPinceau;
import moteur.instruction.ChangeCouleur;
import moteur.instruction.Tourne;
class test{
 	public static void main(String[] args) throws Exception {
		ValueEnvironment listeVariable = new ValueEnvironment();
		listeVariable.setModeSansFENETRE(true);
		int angle;
		int angles;
		int anglese;
		angle = (0 - 90);
		new BasPinceau().exec(listeVariable);
		new Avance(100).exec(listeVariable);
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
		angles = 100;
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
		new Tourne(angle).exec(listeVariable);
		new Avance(100).exec(listeVariable);
	}
}