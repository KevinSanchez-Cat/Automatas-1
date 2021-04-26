/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import estructurasDatos.ColaD;
import estructurasDatos.Nodo;
import estructurasDatos.PilaD;
import java.util.ArrayList;

/**
 *
 * @author Kevin_Sanchez
 */
public class Intermedio {

    static String operaciones = "";
    
    public static void recibeTokens(ArrayList<Lexema> lx, ArrayList<TablaSimbolos> tSimbolos) {
        //
        String tipo = "";
        String id = "";

        String tr = "************************* EXPRESION *************************\n";
        for (int i = 0; i < lx.size(); i++) {
            tr += lx.get(i).getLexema();
        }
        tr += "\n************************* CUADRUPLOS *************************\n";
        operaciones += tr;
        
        ColaD colaOperaciones = new ColaD();
        for (int i = 0; i < lx.size(); i++) {
            if (lx.get(i).getTipoToken() == null) {
                Nodo n = new Nodo(lx.get(i).getLexema(), null);
                colaOperaciones.inserta(n, null);
            } else {
                
                Nodo n = new Nodo(lx.get(i).getLexema(), lx.get(i).getTipoToken());
                colaOperaciones.inserta(n, null);
            }
        }
        ColaD notacionPostfija = Intermedio.posfijo(colaOperaciones);
        //
        ColaD colaPost = new ColaD();
        ColaD colaPost2 = new ColaD();
        System.out.println("************* Asi se encuentra la pila: *************");
        while (notacionPostfija.getA() != null) {
            Nodo n = notacionPostfija.elimina(null);
            colaPost.inserta(n, null);
            System.out.println("" + n.getS());
        }
        System.out.println("*****************************************************");
        while (colaPost.getA() != null) {
            Nodo n = colaPost.elimina(null);
            if (n.getTipo() != "null") {
                colaPost2.inserta(new Nodo(n.getS(), n.getTipo()), null);
                notacionPostfija.inserta(new Nodo(n.getS(), n.getTipo()), null);                
            } else {
                colaPost2.inserta(new Nodo(n.getS(), null), null);
                notacionPostfija.inserta(new Nodo(n.getS(), null), null);
    
            }
        }
        //

        Intermedio.operacionesIntermedio(colaPost2);
        
        String valor = String.valueOf(Intermedio.operaciones(notacionPostfija));
        
        
        String tipoDatoValor = Intermedio.tipoDato(valor);
        if (Intermedio.asignacion(tipo, tipoDatoValor)) {
            System.out.println(id + " = " + valor);
        } else {
            System.out.println("ERROR DE ASIGNACION");
        }
        /*
        if (tSimbolos != null) {
            if (Intermedio.asignacion(id, valor)) {
                //Insertar el valor en la tabla de simbolos
                for (int i = 0; i < tSimbolos.size(); i++) {
                    if (tSimbolos.get(i).getLexema().equals(id)) {
                        tSimbolos.get(i).setValor(valor);
                    }
                }
            }
        }*/
    }

    public static boolean isOperadorS(String s) {
        return s.equals("=") || s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=") || s.equals("(") || s.equals(")");
    }

    public static ColaD posfijo(ColaD cola) {

        ColaD pResultado = new ColaD();
        PilaD pOperadores = new PilaD();
        while (cola.getF() != null) {
            String s = cola.elimina(null).getS();
            if (isOperadorS(s)) {
                if (isOperador(s)) {
                    if (pOperadores.getTope() != null) {
                        String s2 = pOperadores.elimina(null).getS();
                        if (isOperador(s2)) {
                            if (prioridad(s2) >= prioridad(s)) {
                                pOperadores.inserta(new Nodo(s, null), null);
                                pResultado.inserta(new Nodo(s2, null), null);
                            } else if (prioridad(s2) < prioridad(s)) {
                                pOperadores.inserta(new Nodo(s2, null), null);
                                pOperadores.inserta(new Nodo(s, null), null);
                            } else {
                                pOperadores.inserta(new Nodo(s2, null), null);
                                pOperadores.inserta(new Nodo(s, null), null);
                            }
                        } else {
                            pOperadores.inserta(new Nodo(s2, null), null);
                            pOperadores.inserta(new Nodo(s, null), null);
                        }
                    } else {
                        pOperadores.inserta(new Nodo(s, null), null);
                    }
                } else if (s.equals("(")) {
                    pOperadores.inserta(new Nodo(s, null), null);
                } else if (s.equals(")")) {
                    while (pOperadores.getTope() != null) {
                        if (pOperadores.getTope().getS().equals("(")) {
                            pOperadores.elimina(null);
                            break;
                        } else {
                            String op = pOperadores.elimina(null).getS();
                            pResultado.inserta(new Nodo(op, null), null);
                        }
                    }
                }
            } else {
                pResultado.inserta(new Nodo(s, null), null);
            }
        }
        while (pOperadores.getTope() != null) {
            String op = pOperadores.elimina(null).getS();
            pResultado.inserta(new Nodo(op, null), null);
        }

        return pResultado;
    }

    private static boolean isOperador(String s) {
        return s.equals("=") || s.equals("%") || s.equals("*") || s.equals("^") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("!") || s.equals("&&") || s.equals("||") || s.equals("<") || s.equals(">") || s.equals(">=") || s.equals("<=") || s.equals("==") || s.equals("!=");
    }

    private static int prioridad(String operador) {
        switch (operador) {
            case "=":
                return 1;
            case "^":
                return 6;
            case "/":
            case "*":
            case "%":
                return 5;
            case "+":
            case "-":
                return 4;
            case ">":
            case "<":
            case ">=":
            case "<=":
            case "==":
            case "!=":
                return 3;
            case "&&":
            case "||":
            case "!":
                return 2;
            default:
                break;
        }
        return 1;
    }
    static int temp = 0;
    static int Auxtemp = 0;
    static String operador1;
    static String operador2;

    /**
     * Metodo que realiza la operación, segun la prioridad de las operaciones
     *
     * @param pResultado
     * @return
     */
    public static Object operaciones(ColaD pResultado) {
        PilaD pOperacion = new PilaD();
        Object resultado = 0;
        String res[];
        while (pResultado.getF() != null) {
            String s = pResultado.elimina(null).getS();
            if (isOperador(s)) {
                String op2 = pOperacion.elimina(null).getS();
                String op1 = pOperacion.elimina(null).getS();

                String tipoOp1 = tipoDato(op1);
                String tipoOp2 = tipoDato(op2);

                if (!"NOT".equals(tablaResultados(tipoOp1, tipoOp2, s))) {

                    res = expresionFinal(op1, op2, s, tipoOp1, tipoOp2);

                    if (s.equals("=")) {

                    }
                    if ("TRUE".equals(res[0])) {
                        pOperacion.inserta(new Nodo(String.valueOf(res[1]), null), null);
//                        System.out.println("Resultado: " + res[1]);
                        resultado = res[1];
                    } else {
                        //ERROR NO SE PUEDE REALIZAR LA OPERACION
                        System.out.println("Error de operacion");
                        break;
                    }

                } else {
                    //MARCAR UN ERROR DE INCOMPATIBILIDAD DE OPERACIONES
                    System.out.println("Compatilibidad de operaciones err");
                    break;
                }
            } else {
                pOperacion.inserta(new Nodo(s, null), null);
            }
        }
        operaciones += "Resultado: " + resultado + "\n\n";
        return resultado;
    }

    public static Object operacionesIntermedio(ColaD pResultado) {
        PilaD pOperacion = new PilaD();
        Object resultado = 0;
        String res[];
        while (pResultado.getF() != null) {
            Nodo s = pResultado.elimina(null);
            Cuaduplos cI = new Cuaduplos();
            if (isOperador(s.getS())) {
                Nodo nOp1 = pOperacion.elimina(null);
                Nodo nOp2 = pOperacion.elimina(null);
                String op2 = "";
                String op1 = "";
                System.out.println("..-");
                System.out.println(nOp1.getTipo() +" - "+nOp1.getS());
                System.out.println(nOp2.getTipo() + " - " + nOp2.getS());
                System.out.println("..-");
                if (nOp1.getTipo() != null) {
                    //L buscas la variable y lo retornas en
                    op1=valor(AnalisisSemantico.tablaSimbolos, nOp1.getS());
                    System.out.println("OP1 " + op1);
                } else {
                    op1 = nOp1.getS();
                }
                if (nOp2.getTipo() != null) {
                    op2=valor(AnalisisSemantico.tablaSimbolos, nOp2.getS());
                    System.out.println("OP2 " + op2);
                } else {
                    op2 = nOp2.getS();
                }
                
                cI.setOp(s.getS());
                cI.setOp1(op1);
                cI.setOp2(op2);
                if (s.getS().equals("=")) {
                    cI.setTemp(" ");
                } else {
                    cI.setTemp("Temp" + temp);
                }

                String op22 = op2;
                String op11 = op1;

                String tipoOp1 = tipoDato(op11);
                String tipoOp2 = tipoDato(op22);
                if (s.getS().equals("=")) {
                    cI.setRes((String) resultado);
                } else {
                    res = expresionFinal(op11, op22, s.getS(), tipoOp1, tipoOp2);

                    if ("TRUE".equals(res[0])) {
                        pOperacion.inserta(new Nodo(String.valueOf(res[1]), null), null);
                        System.out.println("Resultado: " + res[1]);
                        resultado = res[1];
                    } else {
                        //ERROR NO SE PUEDE REALIZAR LA OPERACION
                        System.out.println("Error de operacion");
                        break;
                    }
                    
                    cI.setRes(res[1]);
                }

                pOperacion.inserta(new Nodo("Temp" + temp, null), null);
                temp++;

                operaciones += cI.getOp() + " " + cI.getOp1() + " " + cI.getOp2() + " " + cI.getTemp() + "\n";

            } else {
                System.out.println("----------------> " + s.getTipo());
                if (s.getTipo() != null) {
                    pOperacion.inserta(new Nodo(s.getS(), s.getTipo()), null);
                } else {
                    pOperacion.inserta(new Nodo(s.getS(), null), null);
                }
            }

            }
        return resultado;
    }


    /**
     * Método que regresa el valor resultante, comparando primero los tipos de
     * datos asociados, para realizar el parseo entre operandos La operación es
     * binaria
     *
     * @param op1
     * @param op2
     * @param operador
     * @param tipoDato1
     * @param tipoDato2
     * @return un arreglo de tipo String [0] - regresa si es valido hacer la
     * operacion [1] - retorna el resultado final
     */
    private static String[] expresionFinal(String op1, String op2, String operador, String tipoDato1, String tipoDato2) {
        String resultados[] = new String[3];

        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = var1 % var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        int var1 = Integer.parseInt(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 - var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "*":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 * var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "/":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 / var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "^":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = Math.pow(var2, var1) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "%":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 % var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 > var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case ">=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 >= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 < var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "<=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 <= var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "==":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 == var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        float var1 = Float.parseFloat(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = (var1 != var2) + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        int var2 = Integer.parseInt(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        float var2 = Float.parseFloat(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (NumberFormatException e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = var1 + var2 + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                    resultados[0] = "FALSE";
                    break;
                case "==":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = (var1.equals(var2)) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "!=":
                    try {
                        String var1 = String.valueOf(op1);
                        String var2 = String.valueOf(op2);

                        resultados[1] = (!var1.equals(var2)) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    try {
                        String var1 = String.valueOf(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 + var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }

                    break;
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                case "&&":
                case "||":
                    resultados[0] = "FALSE";
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "%":
                case ">":
                case ">=":
                case "<":
                case "<=":
                case "==":
                case "!=":
                    resultados[0] = "FALSE";
                    break;
                case "&&":
                    try {
                        boolean var1 = Boolean.parseBoolean(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 && var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                case "||":
                    try {
                        boolean var1 = Boolean.parseBoolean(op1);
                        boolean var2 = Boolean.parseBoolean(op2);

                        resultados[1] = (var1 || var2) + "";
                        resultados[0] = "TRUE";
                    } catch (Exception e) {
                        resultados[0] = "FALSE";
                    }
                    break;
                default:
                    resultados[0] = "FALSE";
                    break;
            }

        } else {
            resultados[0] = "FALSE";
        }

        System.out.println(resultados[1]);
        return resultados;
    }

    /**
     * Método que verifica que tipo de dato es el que tiene una expresión
     *
     * @param s
     * @return
     */
    public static String tipoDato(String s) {

        try {
            int S1 = Integer.parseInt(s);
            return "INTEGER";
        } catch (NumberFormatException e2) {
            try {
                float S2 = Float.parseFloat(s);
                return "FLOAT";
            } catch (NumberFormatException e3) {
                if (s.equals("true") || s.equals("false")) {
                    return "BOOLEAN";
                } else {
                    return "STRING";
                }
            }
        }
    }

    /**
     * Método que revisa la compatibilidad de los tipos de datos con el operando
     * para retornar el tipo de dato que resultará
     *
     * @param tipoDato1
     * @param tipoDato2
     * @param operador
     * @return
     */
    private static String tablaResultados(String tipoDato1, String tipoDato2, String operador) {
        String bandera = "BOOLEAN";
        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "INTEGER";
                case "-":
                    return "INTEGER";
                case "*":
                    return "INTEGER";
                case "/":
                    return "FLOAT";
                case "%":
                    return "INTEGER";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "FLOAT";
                case "-":
                    return "FLOAT";
                case "*":
                    return "FLOAT";
                case "/":
                    return "FLOAT";
                case "%":
                    return "FLOAT";
                case "^":
                    return "FLOAT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }

        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                    return "NOT";
                case "!=":
                case "==":
                    return "BOOLEAN";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "STRING";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "NOT";
                default:
                    return "NOT";
            }
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN")) {
            switch (operador) {
                case "+":
                    return "NOT";
                case "-":
                    return "NOT";
                case "*":
                    return "NOT";
                case "/":
                    return "NOT";
                case "%":
                    return "NOT";
                case "^":
                    return "NOT";
                case ">":
                case "<":
                case ">=":
                case "<=":
                case "!=":
                case "==":
                    return "NOT";
                case "&&":
                case "||":
                    return "BOOLEAN";
                default:
                    return "NOT";
            }
        }

        return bandera;
    }

    /**
     * Método que verifica que a una variable se le pueda asignar el tipo de
     * dato, dependiendo si es compatible con su mismo tipo de dato de la
     * variable
     *
     * @param tipoDato1
     * @param tipoDato2
     * @return
     */
    public static boolean asignacion(String tipoDato1, String tipoDato2) {
        if (tipoDato1.equals("INTEGER") && tipoDato2.equals("INTEGER")) {
            return true;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("INTEGER") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("INTEGER")) {
            return true;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("FLOAT")) {
            return true;
        } else if (tipoDato1.equals("FLOAT") && tipoDato2.equals("STRING")) {
            return false;
        } else if (tipoDato1.equals("FLOTANTE") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("INTEGER")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("STRING")) {
            return true;
        } else if (tipoDato1.equals("STRING") && tipoDato2.equals("BOOLEAN")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("INTEGER")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("FLOAT")) {
            return false;
        } else if (tipoDato1.equals("BOOLEAN") && tipoDato2.equals("STRING")) {
            return false;
        } else {
            return tipoDato1.equals("BOOLEAN") && tipoDato2.equals("BOOLEAN");
        }

    }
    
    private static final int VARIABLE = 50;
    private static final int TIPO_DATO = 1;
    private static final int PUNTO_Y_COMA = 2;
    private static final int IGUAL = 3;
    private static final int OPERADOR_ARITMETICO = 4;
    private static final int OPERADOR_RELACIONAL = 5;
    private static final int OPERADOR_LOGICO = 18;
    
    private static TablaSimbolos lastToken(ArrayList<TablaSimbolos> tokens, TablaSimbolos token) {
        return tokens.get(tokens.indexOf(token) - 1);
    }
    
    private static TablaSimbolos nextToken(ArrayList<TablaSimbolos> tokens, TablaSimbolos token) {
        return tokens.get(tokens.indexOf(token) + 1);
    }
    
    private static String valor(ArrayList<Variable> tablaSimbolos, String nombreVariable) {
        String valor = null;
        for (Variable variable : tablaSimbolos) {
            if (variable.getVariable().equals(nombreVariable)) {
                valor = variable.getValor();
            }
        }

        return valor;
    }
    
    public static void intermedio(ArrayList<TablaSimbolos> lexema){
        
        for (TablaSimbolos tokenActual : lexema) {
            ArrayList<Lexema> arr = new ArrayList<Lexema>();
            switch (tokenActual.getNumToken()) {
                case VARIABLE: 
                    Lexema a = new Lexema();
                    a.setLexema(tokenActual.lexema);
                    arr.add(a);
                    TablaSimbolos tokenSiguiente = nextToken(lexema, tokenActual);
                    switch (tokenSiguiente.getNumToken()) {
                        case IGUAL:
                            Lexema a4 = new Lexema();
                            a4.setLexema(tokenSiguiente.lexema);
                            arr.add(a4);
                            tokenSiguiente = nextToken(lexema, tokenSiguiente);
                            boolean agrega = true, hacer=true;
                            TablaSimbolos t = null;
                            while(agrega){ 
                                if (tokenSiguiente.getNumToken() == VARIABLE) {
                                    Lexema a3 = new Lexema();
                                    a3.setLexema(tokenSiguiente.lexema);
                                    a3.setTipoToken(valor(AnalisisSemantico.tablaSimbolos, tokenSiguiente.lexema));
                                    System.out.println(valor(AnalisisSemantico.tablaSimbolos, tokenSiguiente.lexema) + " VA");
                                    arr.add(a3);
                                } else {            
                                    Lexema a1 = new Lexema();
                                    a1.setLexema(tokenSiguiente.lexema);
                                    arr.add(a1);
                                }
                                t = tokenSiguiente;
                                tokenSiguiente = nextToken(lexema, tokenSiguiente);
                                if (tokenSiguiente.numToken == PUNTO_Y_COMA) {
                                    agrega = false;
                                    if (t.getNumToken() == OPERADOR_ARITMETICO || t.getNumToken() == OPERADOR_LOGICO || t.getNumToken() == OPERADOR_RELACIONAL) {
                                        hacer = false;
                                    }
                                }
                            }
                            if (hacer) {
                                recibeTokens(arr, null);
                            }
                        break;
                    }
            }
        }
    }
    
                                
    public static void main(String[] args) {
        ArrayList<Lexema> arr = new ArrayList<>();
        Lexema a = new Lexema();
        a.setLexema("var");
        arr.add(a);
        Lexema a2 = new Lexema();
        a2.setLexema("=");
        arr.add(a2);
        Lexema a3 = new Lexema();
        a3.setLexema("54");
        arr.add(a3);
        Lexema a4 = new Lexema();
        a4.setLexema("+");
        arr.add(a4);
        Lexema a5 = new Lexema();
        a5.setLexema("6");
        arr.add(a5);
        Lexema a11 = new Lexema();
        a11.setLexema("-");
        arr.add(a11);
        Lexema a6 = new Lexema();
        a6.setLexema("(");
        arr.add(a6);
        Lexema a7 = new Lexema();
        a7.setLexema("2");
        arr.add(a7);
        Lexema a8 = new Lexema();
        a8.setLexema("+");
        arr.add(a8);
        Lexema a9 = new Lexema();
        a9.setLexema("5");
        arr.add(a9);
        Lexema a10 = new Lexema();
        a10.setLexema(")");
        arr.add(a10);
        Intermedio.recibeTokens(arr, null);
        System.out.println("---");
        System.out.println(operaciones);
    }
}
