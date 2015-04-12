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