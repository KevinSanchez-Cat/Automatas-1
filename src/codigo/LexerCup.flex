package codigo;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t\r\n]+
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Tipo de dato String */
( String ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(1);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Tipo de dato");
            Main.lexema.add(nodo); 
            return new Symbol(sym.String, yychar, yyline, yytext());}

/* Tipo de dato int */
( int ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(1);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Tipo de dato");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Int, yychar, yyline, yytext());}

/* Tipo de dato float */
( float) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(1);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Tipo de dato");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Float, yychar, yyline, yytext());}

/* Tipo de dato Boolean */
( boolean ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(1);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Tipo de dato");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Boolean, yychar, yyline, yytext());}

/* Palabra reservada True */
( true ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(20);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada true");
            Main.lexema.add(nodo); 
            return new Symbol(sym.True, yychar, yyline, yytext());}

/* Palabra reservada False */
( false ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(21);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada false");
            Main.lexema.add(nodo); 
            return new Symbol(sym.False, yychar, yyline, yytext());}

/* Palabra reservada If */
( if ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(6);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada if");
            Main.lexema.add(nodo); 
            return new Symbol(sym.If, yychar, yyline, yytext());}

/* Palabra reservada Else */
( else ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(7);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada else ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Else, yychar, yyline, yytext());}

/* Palabra reservada While */
( while ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(12);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada while");
            Main.lexema.add(nodo); 
            return new Symbol(sym.While, yychar, yyline, yytext());}

/* Operador Igual */
( "=" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(3);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Igual");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Igual, yychar, yyline, yytext());}

/* Operadores aritmetico*/
( "+" | "-" | "/" | "*" | "^" | "%" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(4);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Operador aritmético ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Op_aritmetico, yychar, yyline, yytext());}

/*Operadores Relacionales */
( ">" | "<" | "==" | "!=" | ">=" | "<=" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(5);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Operador relacional ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Op_relacional, yychar, yyline, yytext());}

/* Operadores logicos */
( "&&" | "||" | "!" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(18);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Operador lógico ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Op_logico, yychar, yyline, yytext());}

/* Parentesis de apertura */
( "(" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(7);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Paréntesis que abre ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Parentesis_a, yychar, yyline, yytext());}

/* Parentesis de cierre */
( ")" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(8);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Paréntesis que cierra ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Parentesis_c, yychar, yyline, yytext());}

/* Llave de apertura */
( "{" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(9);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Llave que abre ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Llave_a, yychar, yyline, yytext());}

/* Llave de cierre */
( "}" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(10);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Llave que cierra ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Llave_c, yychar, yyline, yytext());}

/* Marcador de inicio de algoritmo */
( "class" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(16);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Comienzo del programa ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Class, yychar, yyline, yytext());}

/* Nombre de la clase  */
#{L}({L}|{D}) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(54);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Nombre de la clase ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Inicio, yychar, yyline, yytext());}

/* Palabra reservada para metodos */
( "void" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(17);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada para metodos ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Void, yychar, yyline, yytext());}

/* Palabra reservada de retorno  */
( "return" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(19);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada de retorno ");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Return, yychar, yyline, yytext());}

/* Nombre del método  */
_{L}({L}|{D}) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(55);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Nombre del método");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Metodo, yychar, yyline, yytext());}

/* Punto y coma */
( ";" ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(2);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Punto y coma");
            Main.lexema.add(nodo); 
            return new Symbol(sym.P_coma, yychar, yyline, yytext());}

/* Coma */
"," {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(15);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Coma");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Coma, yychar, yyline, yytext());}

/* Lectura */
( printf ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(13);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada scanf (Lee)");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Escritura, yychar, yyline, yytext());}

/* Escritura */
( scanf ) {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(14);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Palabra reservada printf (Escribe)");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Lectura, yychar, yyline, yytext());}

/* Identificador */
{L}({L}|{D})* { TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(50);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Variable");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Identificador, yychar, yyline, yytext());}

/* Cadena */
("\"")({L} | {D} | " ")*("\"") {TablaSimbolos nodo;
            nodo = new TablaSimbolos();
            nodo.setLexema(yytext());
            nodo.setNumToken(53);
            nodo.setNumLinea(yyline+1);
            nodo.setTipoToken("Cadena");
            Main.lexema.add(nodo); 
            return new Symbol(sym.Cadena, yychar, yyline, yytext());}

/* Numero */
(-?{D}+)  {
            if(yytext().toString().startsWith("-")){
                if (Main.lexema.get(Main.lexema.size()-1).getLexema().equals("=") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("-") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("+") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("*") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("/") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("^") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("%")) {
                    TablaSimbolos nodo;
                    nodo = new TablaSimbolos();
                    nodo.setLexema(yytext());
                    nodo.setNumToken(51);
                    nodo.setNumLinea(yyline+1);
                    nodo.setTipoToken("Entero");
                    Main.lexema.add(nodo);
                    return new Symbol(sym.Numero, yychar, yyline, yytext());
                } else { 
                    TablaSimbolos nodo;
                   nodo = new TablaSimbolos();
                   nodo.setLexema("-");
                   nodo.setNumToken(4);
                   nodo.setNumLinea(yyline+1);
                   nodo.setTipoToken("Operador aritmético ");
                   Main.lexema.add(nodo); 

                   nodo = new TablaSimbolos();
                   nodo.setLexema(yytext().substring(1, yytext().length()));
                   nodo.setNumToken(51);
                   nodo.setNumLinea(yyline+1);
                   nodo.setTipoToken("Entero");
                   Main.lexema.add(nodo);
                   return new Symbol(sym.Numero, yychar, yyline, yytext());
                }
            } else {
                TablaSimbolos nodo;
                nodo = new TablaSimbolos();
                nodo.setLexema(yytext());
                nodo.setNumToken(51);
                nodo.setNumLinea(yyline+1);
                nodo.setTipoToken("Entero");
                Main.lexema.add(nodo);
                return new Symbol(sym.Numero, yychar, yyline, yytext());
            }
}
/* Flotante */
-?{D}+ (".") {D}+ {
            if(yytext().toString().startsWith("-")){
                if (Main.lexema.get(Main.lexema.size()-1).getLexema().equals("-") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("+") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("*") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("/") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("^") || Main.lexema.get(Main.lexema.size()-1).getLexema().equals("%")) {
                    TablaSimbolos nodo;
                    nodo = new TablaSimbolos();
                    nodo.setLexema(yytext());
                    nodo.setNumToken(52);
                    nodo.setNumLinea(yyline+1);
                    nodo.setTipoToken("Flotante");
                    Main.lexema.add(nodo); 
                    return new Symbol(sym.Flotante, yychar, yyline, yytext());
                } else { 
                    TablaSimbolos nodo;
                   nodo = new TablaSimbolos();
                   nodo.setLexema("-");
                   nodo.setNumToken(4);
                   nodo.setNumLinea(yyline+1);
                   nodo.setTipoToken("Operador aritmético ");
                   Main.lexema.add(nodo); 

                    nodo = new TablaSimbolos();
                    nodo.setLexema(yytext().substring(1, yytext().length()));
                    nodo.setNumToken(52);
                    nodo.setNumLinea(yyline+1);
                    nodo.setTipoToken("Flotante");
                    Main.lexema.add(nodo); 
                    return new Symbol(sym.Flotante, yychar, yyline, yytext());
                }
            } else {
                TablaSimbolos nodo;
                nodo = new TablaSimbolos();
                nodo.setLexema(yytext());
                nodo.setNumToken(52);
                nodo.setNumLinea(yyline+1);
                nodo.setTipoToken("Flotante");
                Main.lexema.add(nodo); 
                return new Symbol(sym.Flotante, yychar, yyline, yytext());
            }

            }

/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}
