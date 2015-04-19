import moteur.ValueEnvironment;
import moteur.instruction.Avance;
import moteur.instruction.BasPinceau;
import moteur.instruction.ChangeCouleur;
import moteur.instruction.Tourne;
public class test{
 	public static void main(String[] args) throws Exception {

		ValueEnvironment listeVariable = new ValueEnvironment();
		listeVariable.setModeSansFENETRE(true);
		
		int pppp;
		pppp = 4;
		ValueEnvironment listeVariable1= (ValueEnvironment) listeVariable.clone();
		for( int i1 =0 ; i1<4;i1++){
			new Avance(100).exec(listeVariable1);
			ValueEnvironment listeVariable2= (ValueEnvironment) listeVariable1.clone();
			for( int i2 =0 ; i2<4;i2++){
				new ChangeCouleur("RED").exec(listeVariable2);
				new Avance(100).exec(listeVariable2);
			}
		}
	}
}