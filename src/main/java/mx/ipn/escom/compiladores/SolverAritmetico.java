package mx.ipn.escom.compiladores;

import mx.ipn.escom.compiladores.Nodo;
import mx.ipn.escom.compiladores.TipoToken;

public class SolverAritmetico {

    private final Nodo nodo;

    public SolverAritmetico(Nodo nodo) {
        this.nodo = nodo;
    }

    public Object resolver(TablaSimbolos ts){
        return resolver(nodo,ts);
    }
    private Object resolver(Nodo n, TablaSimbolos ts){
        // No tiene hijos, es un operando
        if(n.getHijos() == null){
            if(n.getValue().tipo == TipoToken.NUMERO || n.getValue().tipo == TipoToken.CADENA){
                return n.getValue().literal;
            }
            else if(n.getValue().tipo == TipoToken.IDENTIFICADOR){
                // Ver la tabla de símbolos
                return ts.obtener(n.getValue().lexema);
            }
        }

        //System.out.println(n.getValue().lexema + "rrrrr");
        Nodo izq = n.getHijos().get(0);
        Nodo der = n.getHijos().get(1);

        Object resultadoIzquierdo = resolver(izq,ts);
        Object resultadoDerecho = resolver(der,ts);

        if(resultadoIzquierdo instanceof Double && resultadoDerecho instanceof Double){
            switch (n.getValue().tipo){
                case MAS:
                    return ((Double)resultadoIzquierdo + (Double) resultadoDerecho);
                case GUION_MEDIO:
                    return ((Double)resultadoIzquierdo - (Double) resultadoDerecho);
                case ASTERISCO:
                    return ((Double)resultadoIzquierdo * (Double) resultadoDerecho);
                case BARRA_INCL:
                    return ((Double)resultadoIzquierdo / (Double) resultadoDerecho);
            }
        }
        else if(resultadoIzquierdo instanceof String && resultadoDerecho instanceof String){
           
                
                if (n.getValue().tipo == TipoToken.MAS) {
                // Ejecutar la concatenación
                    return ((String) resultadoIzquierdo + (String) resultadoDerecho);
                
                }
        }
        else{
            // Error por diferencia de tipos
            System.exit(0);
        }

        return null;
    }
}
