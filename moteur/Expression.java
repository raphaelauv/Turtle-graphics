package moteur;

import affichage.Trait;

public abstract class Expression {
	abstract int eval(ValueEnvironment env)throws Exception;
}

class Int extends Expression {
	private int value;
	public Int(int i) {
		value = i;
	}
	
	int eval(ValueEnvironment env) throws Exception {
		return value;
		
	}
}
class Var extends Expression {
	private String name;
	public Var(String s) {
		name = s;
	}
	
	int eval(ValueEnvironment env) throws Exception {
		
		return env.getValue(name);
		
		
	}
}
class Sum extends Expression {
	private Expression left, right;
	public Sum(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	int eval(ValueEnvironment env) throws Exception {
		return left.eval(env)+right.eval(env);
	}
}
class Difference extends Expression {
	private Expression left, right;
	public Difference(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	int eval(ValueEnvironment env) throws Exception {
		
		return left.eval(env)-right.eval(env);
	}
}
class Product extends Expression {
	private Expression left, right;
	public Product(Expression l, Expression r) {
		left = l;
		right = r;
	}
	
	int eval(ValueEnvironment env) throws Exception {
		return left.eval(env)*right.eval(env);
	}
}
class Division extends Expression {
	private Expression left, right;
	public Division(Expression l, Expression r) {
		left = l;
		right = r;
		
	}
	
	int eval(ValueEnvironment env) throws Exception {
		int droite=right.eval(env);
		if(droite==0){
			throw new Exception("attention division par 0");
		}
		return left.eval(env)/droite;
	}
}

class Program {
	private Instruction first;
	private Program rest;
	
	public Program(Instruction i, Program p) {
		first = i;
		rest = p;
	}
	public void run(ValueEnvironment env)
	throws Exception {		
		if (first != null) {
			first.exec(env);
			rest.run(env);
		}
		
	} 
}
class ProgramPrincipal{
	
	private Program declarations;
	private Instruction instruction;
	
	public ProgramPrincipal(Program declarations, Instruction instruction2) {
		
		this.declarations = declarations;
		this.instruction = instruction2;
	}
	
	public void run(ValueEnvironment env) throws Exception{
		if(declarations!=null){
			declarations.run(env);
			instruction.exec(env);
		}
	}
	
}
abstract class Instruction {
	Program bloc;
	abstract void exec(ValueEnvironment env)
	
	throws Exception;
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

class Tourne extends Assignment {
	public Tourne(Expression e) {
		super("angle",e);
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

class Avance extends Assignment {
	public Avance(Expression e) {
		super("Avance",e);
	}
	public void exec(ValueEnvironment env)
	throws Exception {
		
		
		Trait tmp = new Trait(exp.eval(env),env.getTaille(),env.getCouleur(),env.getPositionActuel(),env.getAngleActuel(),env.isValPinceau());
		
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