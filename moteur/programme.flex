%%
%public
%class Lexer
%unicode
%type Token
%line
%column

%{
	
	private Token token(Sym type,int yyline, int yycolumn) {
      return new Token(type,yyline,yycolumn);
  }
	private IntToken intToken(Sym type ,int yyline, int yycolumn, int val){
	  return new IntToken(type, yyline,yycolumn,val);
  }
	private VarToken varToken(Sym type ,int yyline, int yycolumn, String nom){
	  return new VarToken(type,yyline,yycolumn, nom);
  }
	
%}

%yylexthrow{
	LexerException
%yylexthrow}

blank = "\n" | "\r" | " " | "\t"
int   = [1-9][0-9]* | 0
variable = [a-zA-Z][a-zA-Z0-9]*

%%

<YYINITIAL> {
   {int}	      {return  intToken(Sym.INT,yyline, yycolumn, Integer.parseInt(yytext()));}
   "Var"      {return  token(Sym.VAR,yyline, yycolumn);}
   "Couleur"       {return  token(Sym.COULEUR,yyline, yycolumn);}
   "Epaisseur"       {return  token(Sym.EPAISSEUR,yyline, yycolumn);}
   "For"       {return  token(Sym.FOR,yyline, yycolumn);}
   "Faire"     {return  token(Sym.FAIRE,yyline, yycolumn);}
   "PRINT"     {return  token(Sym.PRINT,yyline, yycolumn);}
   "Avance"		{return  token(Sym.AVANCE,yyline, yycolumn);}
   "Si"	{return  token(Sym.SI,yyline, yycolumn);}
   "Alors"	{return  token(Sym.ALORS,yyline, yycolumn);}
   "Sinon"	{return  token(Sym.SINON,yyline, yycolumn);}
   "Sinon si"	{return  token(Sym.SINONSI,yyline, yycolumn);}
   "Tant que"	{return  token(Sym.TANTQUE,yyline, yycolumn);}
   "Tourne"		{return  token(Sym.TOURNE,yyline, yycolumn);}
   "BasPinceau"		{return  token(Sym.BASPINCEAU,yyline, yycolumn);}
   "HautPinceau"	{return  token(Sym.HAUTPINCEAU,yyline, yycolumn);}
   "Debut"			{return  token(Sym.DEBUT,yyline, yycolumn);}
   "Fin"			{return  token(Sym.FIN,yyline, yycolumn);}
   ";"         {return  token(Sym.CONCAT,yyline, yycolumn);}
   "="         {return  token(Sym.EQ,yyline, yycolumn);}
   "+"		   {return  token(Sym.PLUS,yyline, yycolumn);}
   "-"	      {return  token(Sym.MINUS,yyline, yycolumn);}
   "*"		   {return  token(Sym.TIMES,yyline, yycolumn);}
   "/"		   {return  token(Sym.DIV,yyline, yycolumn);}
   "("		   {return  token(Sym.LPAR,yyline, yycolumn);}
   ")"		   {return  token(Sym.RPAR,yyline, yycolumn);}
   "{"         {return  token(Sym.LACC,yyline, yycolumn);}
   "}"         {return  token(Sym.RACC,yyline, yycolumn);}
   {blank}	   {}
   {variable}  {return  varToken(Sym.VARIABLE,yyline, yycolumn, yytext());}
   <<EOF>>	   {return  token(Sym.EOF,yyline, yycolumn);}
   [^]		   {throw new LexerException(yytext(),yyline, yycolumn);}
}