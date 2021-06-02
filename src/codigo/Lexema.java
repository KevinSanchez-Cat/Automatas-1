/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

/**
 *
 * @author aloso
 */
public class Lexema {
    
    String lexema;
    int numToken;
    String nombreToken;
    int renglon;

    public int getNumLinea() {
        return renglon;
    }

    public void setNumLinea(int numLinea) {
        this.renglon = numLinea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getNumToken() {
        return numToken;
    }

    public void setNumToken(int numToken) {
        this.numToken = numToken;
    }

    public String getTipoToken() {
        return nombreToken;
    }

    public void setTipoToken(String tipoToken) {
        this.nombreToken = tipoToken;
    }
    
    
}
