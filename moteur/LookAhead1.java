package moteur;

import controleur.ControleurExportToken;

class LookAhead1  {
    /* Simulating a reader class for a stream of Token */
    
    private Token current;
    private Lexer lexer;
    private ControleurExportToken controleurTokens;

	public LookAhead1(Lexer l) throws Exception {
	lexer=l;
	current=lexer.yylex();
    }

    public boolean check(Sym s){
	/* check whether the first character is of type s*/
          return (current.symbol() == s); 
    }
    
    public void eat(Sym s)throws Exception {
	/* consumes a token of type s from the stream,
	   exception when the contents does not start on s.   */
    	
	if (!check(s)) {
	    throw new Exception("\nCan't eat "+s+" current being "+current);
	}
		if(controleurTokens!=null){
			controleurTokens.ajouterToken(current);
		}
        current=lexer.yylex();
    }
    
    public int getIntValue()
    throws Exception {
    // it gives the value of the IntToken, or it rises an exception if not IntToken
    	if (current instanceof IntToken) {
    		IntToken t = (IntToken) current; 
    		return t.getValue();
        } else {
    		throw new Exception("LookAhead error: get value from a non-valued token");
    	}	
    }

    public String getStringValue()
    throws Exception {
    // it gives the value of the VarToken, or it rises an exception if not VarToken
        if (current instanceof VarToken) {
            VarToken t = (VarToken) current;
            return t.getValue();
        } else {
            throw new Exception("LookAhead error: get value from a non-valued token \nUne variable etait attendu");
        }   
    }

    public String getString() {
        return current.toString();
    }
    public int getLine(){
    	return current.line;
    }
    
    public String getStringSym(){
    	return current.getStringSym();
    }
    
    public int getColumn(){
    	return current.column;
    }
    public ControleurExportToken getControleurTokens() {
		return controleurTokens;
	}

	public void setControleurTokens(ControleurExportToken controleur) {
		this.controleurTokens = controleur;
	}

}
