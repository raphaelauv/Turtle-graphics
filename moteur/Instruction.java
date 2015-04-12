package moteur;

import affichage.Trait;

public abstract class Instruction {
	protected Program bloc;
	private Expression exp;
	abstract void exec(ValueEnvironment env)
	
	throws Exception;
	
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
}
class Declaration extends Instruction {
	private String varName;
	public Declaration(String s) {
		varName = s;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.addVariable(varName);
	} 
}
class Assignment extends Instruction {
	private String varName;
	protected Expression exp;
	public Assignment(String s, Expression e) {
		varName = s;
		setExp(e);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		int valeur=getExp().eval(env);
    	env.setVariable(varName,valeur);
	}
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
}

class Tourne extends Instruction {
	public Tourne(Expression e) {
		this.setExp(e);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		int valeur=this.getExp().eval(env);
		int angleactuel=env.getAngleActuel();
		int resultat;
		if(valeur>360){
			valeur=valeur%360;
		}
		resultat=valeur+angleactuel;
		resultat=resultat%360;
		if(resultat<0){
			resultat=360+resultat;
		}
    	env.setAngleActuel(resultat);
	}
}

class Epaisseur extends Instruction{
	
	public Epaisseur(Expression e) {
		this.setExp(e);
	}
	public void exec(ValueEnvironment env)throws Exception {
		env.setTaille(this.getExp().eval(env));
	}
}

class Avance extends Instruction {
	public Avance(Expression e) {
		this.setExp(e);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		Trait tmp = new Trait(this.getExp().eval(env),env.getTaille(),env.getCouleur(),
				env.getPositionActuel(),env.getAngleActuel(),env.isValPinceau());
		
		env.setPositionActuel(tmp.getFin());
			
		env.getAvance().action(tmp);
	}
}

class ChangeCouleur extends Instruction{
	String couleur;
	public ChangeCouleur(String couleur) {
		this.couleur=couleur;
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		env.setCouleur(this.couleur);
	}
}
class BasPinceau extends Instruction {
	public BasPinceau() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.setValPinceau(true);
	}
}
class HautPinceau extends Instruction {
	public HautPinceau() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		env.setValPinceau(false);
	}
}

class Debut extends Instruction {
	public Debut(Program lebloc) {
		this.bloc=lebloc;
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		if(this.bloc!=null){
			this.bloc.run(env);
		}
		//env.setVariable("Debut", 1);
	}
}
class Fin extends Instruction {
	public Fin() {
	}
	public void exec(ValueEnvironment env) 
	throws Exception {
		//env.setVariable("Fin", 1);
	}
}


class Loop extends Instruction {
	//for
	private Expression exp;
	private Instruction instruction;
	public Loop(Expression e, Instruction ex2) {
		setExp(e);
		setInstruction(ex2);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		for (int i =0 ; i<this.getExp().eval(env); i++){
			this.getInstruction().exec(env);
		}
	}
	public Expression getExp() {
		return exp;
	}
	public void setExp(Expression exp) {
		this.exp = exp;
	}
	public Instruction getInstruction() {
		return instruction;
	}
	public void setInstruction(Instruction ex2) {
		this.instruction = ex2;
	}
	
}

class TantQue extends Loop {
//while
	public TantQue(Expression e, Instruction ex2) {
		super(e, ex2);
	}

	public void exec(ValueEnvironment env) throws Exception {
		int i = 0;
		while (this.getExp().eval(env) == 0) {
			this.getInstruction().exec(env);
			i++;
			if (i > 100) {
				throw new Exception("trop diteration dans la boucle while");
			}
		}
	}
}


class FaireTantQue extends TantQue {
//do while
	public FaireTantQue(Expression e, Instruction ex2) {
		super(e, ex2);
	}

	public void exec(ValueEnvironment env) throws Exception {
		exec2(env);
		super.exec(env);
	}
	
	public void exec2(ValueEnvironment env) throws Exception {
		this.getInstruction().exec(env);
	}
}

class SiALors extends Loop {
	public SiALors(Expression e, Instruction ex2) {
		super(e,ex2);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		if(this.getExp().eval(env)==0){
			this.getInstruction().exec(env);
		}
	}
}

class SiALorsSinon extends SiALors {
	private Instruction instruction2;
	public SiALorsSinon(Expression exp, Instruction ex2 , Instruction ex3) {
		super(exp,ex2);
		instruction2 = ex2;
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		if(this.getExp().eval(env)==0){
			System.out.println("ici");
			this.getInstruction().exec(env);
		}
		else{
			System.out.println("la");
			this.instruction2.exec(env);
		}
	}
}