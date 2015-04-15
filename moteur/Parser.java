package moteur;

import moteur.expression.Difference;
import moteur.expression.Division;
import moteur.expression.Expression;
import moteur.expression.Int;
import moteur.expression.Product;
import moteur.expression.Program;
import moteur.expression.ProgramPrincipal;
import moteur.expression.Sum;
import moteur.expression.Var;
import moteur.instruction.Assignment;
import moteur.instruction.Avance;
import moteur.instruction.BasPinceau;
import moteur.instruction.ChangeCouleur;
import moteur.instruction.Debut;
import moteur.instruction.Declaration;
import moteur.instruction.Epaisseur;
import moteur.instruction.FaireTantQue;
import moteur.instruction.HautPinceau;
import moteur.instruction.Instruction;
import moteur.instruction.SiALors;
import moteur.instruction.SiALorsSinon;
import moteur.instruction.SinonSiAlors;
import moteur.instruction.SinonSiAlorsSinon;
import moteur.instruction.TantQue;
import moteur.instruction.Tourne;

class Parser {
	/*
	 * Code -> Prog$
     * Prog -> e | Inst Prog
     * Inst -> VAR VARIABLE; | var = Exp; | PRINT Exp; | FOR Exp TIMESDO {Prog}
	 * Exp -> int | (Exp ExpSuite)
     * ExpSuite -> + Exp | - Exp | * Exp | / Exp
	 */
    protected LookAhead1 reader;
    
    
    public Parser(LookAhead1 r) {
	   reader=r;
	   
    }
	public ProgramPrincipal nontermCode() throws Exception {
    	
    	Program declarations;
    	if(reader.check(Sym.VAR)){
    		declarations=nontermVar();
    	}
    	else{
    		declarations=new Program(null, null);
    	}
    	Instruction instruction = nontermInst();
        reader.eat(Sym.EOF);
        return new ProgramPrincipal(declarations,instruction);
    }

    public Program nontermVar() throws Exception{
    	
    	if(reader.check(Sym.VAR)){
        	reader.eat(Sym.VAR);
        	
        	String nom=reader.getStringValue();
        	
        	reader.eat(Sym.VARIABLE);
        	
        	reader.eat(Sym.CONCAT);
        	
        	return new Program(new Declaration(nom),this.nontermVar());
        }
    	return new Program(null, null);
    	
    }

	public Program nontermProg() throws Exception {
		if (reader.check(Sym.DEBUT) || reader.check(Sym.SI) || reader.check(Sym.EPAISSEUR)
				|| reader.check(Sym.SINONSI)
				|| reader.check(Sym.TANTQUE) || reader.check(Sym.FAIRE)
				|| reader.check(Sym.AVANCE) || reader.check(Sym.BASPINCEAU)
				|| reader.check(Sym.HAUTPINCEAU) || reader.check(Sym.VARIABLE)
				|| reader.check(Sym.COULEUR) || reader.check(Sym.TOURNE)
				|| reader.check(Sym.FOR)) {
			
			Instruction inst=this.nontermInst();
			
			reader.eat(Sym.CONCAT);
			Program suite =this.nontermProg();

			return new Program(inst, suite);
		}
		return new Program(null, null);
	}
	
    public Instruction nontermInst()
    throws Exception {
    	
    	if(reader.check(Sym.DEBUT)){
    		reader.eat(Sym.DEBUT);
    		Program lebloc=this.nontermProg();
    		reader.eat(Sym.FIN);
    		return new Debut(lebloc);
    		
    	}
        if (reader.check(Sym.VARIABLE)) {
        	
        	String nomVariable=reader.getStringValue();
        	
        	reader.eat(Sym.VARIABLE);
        	
        	reader.eat(Sym.EQ);
        	
        	Expression ex =this.nontermExp();
        	
        	return new Assignment(nomVariable, ex);
        }
        
        else if(reader.check(Sym.FAIRE)){
        	reader.eat(Sym.FAIRE);
        	Instruction ex2 =this.nontermInst();
        	reader.eat(Sym.TANTQUE);
        	Expression ex = this.nontermExp();
        	
        	return new FaireTantQue(ex,ex2);
        }
        else if (reader.check(Sym.TANTQUE)) {
        	
        	reader.eat(Sym.TANTQUE);
        	Expression ex = this.nontermExp();
        	reader.eat(Sym.FAIRE);
        	Instruction ex2 =this.nontermInst();
        	
        	return new TantQue(ex ,ex2);
        }
        else if (reader.check(Sym.BASPINCEAU)) {
        	
        	reader.eat(Sym.BASPINCEAU);
        	
        	return new BasPinceau();
        }
        else if (reader.check(Sym.HAUTPINCEAU)) {
        	
        	reader.eat(Sym.HAUTPINCEAU);
        	
        	return new HautPinceau();
        }
        else if (reader.check(Sym.TOURNE)) {
        	
        	reader.eat(Sym.TOURNE);
        	Expression a=this.nontermExp();
        	
        	return new Tourne(a);
        }
        else if (reader.check(Sym.AVANCE)) {
        	
        	reader.eat(Sym.AVANCE);
        	Expression a=this.nontermExp();
        	
        	return new Avance(a);
        }
        
        else if (reader.check(Sym.COULEUR)) {
        	
        	
        	reader.eat(Sym.COULEUR);
        	
        	if (!reader.check(Sym.VARIABLE)){
        		
        		throw new LexerException(reader.getLine(), reader.getColumn(), "attention il faut seulement un espace entre "
        				+ "Couleur et le nom de la couleur \n et non pas un "+reader.getStringSym());
        	
        	}
        	String couleur =reader.getStringValue();        	
        	for(Couleurs e: Couleurs.values()){
        		if(couleur.equals(e.toString())){
        			reader.eat(Sym.VARIABLE);
        			
        			return new ChangeCouleur(couleur);
        		}
        	}
        	throw new LexerException(reader.getLine(), reader.getColumn(), "erreur de choix de couleur "+
        	"choissisez parmis  \n"+Couleurs.liste());
        			
        }
        else if(reader.check(Sym.EPAISSEUR)){
        	reader.eat(Sym.EPAISSEUR);
        	
        	if (!reader.check(Sym.VARIABLE) || reader.check(Sym.INT) || reader.check(Sym.LPAR)){
        		throw new LexerException(reader.getLine(), reader.getColumn(), "attention il faut seulement un espace entre "
        				+ "EPAISSEUR et la valeur de l epaisseur \n et non pas un "+reader.getStringSym());
        	
        	}
        	Expression a=this.nontermExp();
        	
        	return new Epaisseur(a);
        	
        }
        else if (reader.check(Sym.SI)) {
        	reader.eat(Sym.SI);
        	Expression ex=this.nontermExp();
        	reader.eat(Sym.ALORS);
        	Instruction ex2 =this.nontermInst();
        	
        	if(reader.check(Sym.SINON)){
            	reader.eat(Sym.SINON);
            	Instruction ex3 =this.nontermInst();
            	return new SiALorsSinon(ex,ex2,ex3);
        	}
        	else if(reader.check(Sym.SINONSI)){
        		return new SiALorsSinon(ex,ex2,this.nontermInst());
        	}
        	else{
        		return new SiALors(ex,ex2);
        	}
        	
        }
        
        else if (reader.check(Sym.SINONSI)) {
        	reader.eat(Sym.SINONSI);
        	Expression ex=this.nontermExp();
        	reader.eat(Sym.ALORS);
        	Instruction ex2 =this.nontermInst();
        	return nontermSinonSi(ex,ex2);
        } 
        throw new LexerException(reader.getLine(), reader.getColumn(), "LEXER exception , a ete trouve :  "
        +reader.getStringSym()+"   \n , etait attendu une instruction etait attendu");
    }


    
    public Instruction nontermSinonSi(Expression ex , Instruction ex2) throws Exception{
    	if(reader.check(Sym.SINON)){
        	reader.eat(Sym.SINON);
        	Instruction ex3 =this.nontermInst();
        	return new SinonSiAlorsSinon(ex,ex2,ex3);
    	}
    	else if(reader.check(Sym.SINONSI)){
    		return new SinonSiAlors(ex,ex2,this.nontermInst());
    	}
    	else{
    		return new SinonSiAlors(ex,ex2,null);
    	}
    }
    
    public Expression nontermExp() throws Exception {
		
    	if (reader.check(Sym.INT)) {
    		
    		int val=reader.getIntValue();
			
			reader.eat(Sym.INT);
				
			return this.nonTermExpSuite(new Int(val));
			
		}
    	else if (reader.check(Sym.VARIABLE)){
    		
    		String val=reader.getStringValue();
    		String position ="ligne : "+reader.getLine()+"  cologne : "+reader.getColumn();
			 
			reader.eat(Sym.VARIABLE);
			
			return this.nonTermExpSuite(new Var(val,position));
    		
    	}
		else if (reader.check(Sym.LPAR)){
			
			reader.eat(Sym.LPAR);
			Expression first=this.nontermExp();
			reader.eat(Sym.RPAR);
			return this.nonTermExpSuite(first);
		}
		else{
			
			throw new Exception("reader erreur a la ligne : "+reader.getLine() +" en position "+reader.getColumn());
		}
    	
    	}
    
    private Expression nonTermExpSuite(Expression first) throws Exception{
    	
    	if(reader.check(Sym.PLUS) || reader.check(Sym.MINUS)|| reader.check(Sym.DIV)|| reader.check(Sym.TIMES)){
			
			return nonTermExpSuite(this.operateur(first));
		}
    	else {
    		return first;
    	}
    }

	private Expression operateur(Expression first) throws Exception {
		if (reader.check(Sym.PLUS)) {
			reader.eat(Sym.PLUS);
			
			return new Sum(first ,this.nontermExp());
		}
		else if (reader.check(Sym.MINUS)) {
			reader.eat(Sym.MINUS);
				
			return new Difference(first ,this.nontermExp());
		}
		else if (reader.check(Sym.DIV)) {
			reader.eat(Sym.DIV);
			
			return new Division(first ,this.nontermExp());
		}
		else if (reader.check(Sym.TIMES)) {
			reader.eat(Sym.TIMES);
			
			return new Product(first ,this.nontermExp());
		}
		else{
			throw new Exception("reader erreur a la ligne : "+reader.getLine() +" en position "+reader.getColumn());
		}
	}
    }