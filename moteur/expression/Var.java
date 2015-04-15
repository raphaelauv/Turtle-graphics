package moteur.expression;

import moteur.ValueEnvironment;

public class Var extends Expression {
	private String name;
	private String position;
	public Var(String s,String position) {
		name = s;
		this.position=position;
	}
	public int eval(ValueEnvironment env) throws Exception {
		Integer tmp=env.getValue(name);
		if(tmp==null){
			throw new Exception("Erreur la variable "+name+"  "+this.position+"\n n a pas ete initialise"); 
		}
		return tmp;
	}
	public String getString(ValueEnvironment env, int tabulation) {
		return name;
	}
}