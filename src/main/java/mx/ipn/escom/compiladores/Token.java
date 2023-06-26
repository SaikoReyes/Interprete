package mx.ipn.escom.compiladores;

import static mx.ipn.escom.compiladores.TipoToken.*;

public class Token {

    final TipoToken tipo;
    final String lexema;

    final Object posicion;

    public Token(TipoToken tipo, String lexema, Object posicion) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = posicion;
    }

    public Token(TipoToken tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.posicion = 0;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Token)) {
            return false;
        }

        if(this.tipo == ((Token)o).tipo){
            return true;
        }

        return false;
    }

    public String toString(){
        return tipo + " " + lexema + " ";
    }
    
    public boolean esOperando(){
        switch (this.tipo){
            case IDENTIFICADOR:
            case NUMERO:
                return true;
            default:
                return false;
        }
    }

    public boolean esOperador(){
        switch (this.tipo){
            case SUMA:
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
            case IGUAL_QUE:
            case MAYOR_QUE:
            case OP_MAYOR_IGUAL_QUE:
            case MENOR_QUE:
            case OP_MENOR_IGUAL_QUE:
                return true;
            default:
                return false;
        }
    }

    public boolean esPalabraReservada(){
        switch (this.tipo){
            case VAR:
            case IF:
            case PRINT:
            case ELSE:
                return true;
            default:
                return false;
        }
    }

    public boolean esEstructuraDeControl(){
        switch (this.tipo){
            case IF:
            case ELSE:
            case WHILE:
                return true;
            default:
                return false;
        }
    }

    public boolean precedenciaMayorIgual(Token t){
        return this.obtenerPrecedencia() >= t.obtenerPrecedencia();
    }

    private int obtenerPrecedencia(){
        switch (this.tipo){
            case MULTIPLICACION:
            case DIVISION:
                return 3;
            case SUMA:
            case RESTA:
                return 2;
            case IGUAL_QUE:
                return 1;
            case MAYOR_QUE:
            case OP_MAYOR_IGUAL_QUE:
            case MENOR_QUE:
            case OP_MENOR_IGUAL_QUE:
                return 1;
        }

        return 0;
    }

    public int aridad(){
        switch (this.tipo) {
            case MULTIPLICACION:
            case DIVISION:
            case SUMA:
            case RESTA:
            case IGUAL_QUE:
            case MAYOR_QUE:
            case OP_MAYOR_IGUAL_QUE:
                return 2;
        }
        return 0;
    }
}
