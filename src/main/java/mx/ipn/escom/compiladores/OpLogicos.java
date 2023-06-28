/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.ipn.escom.compiladores;

/**
 *
 * @author saiko
 */
public class OpLogicos {

    private final Nodo nodo;

    public OpLogicos(Nodo nodo) {
        this.nodo = nodo;
    }

    public Object res(TablaSimbolos ts) {
        return res(nodo, ts);
    }

    public Object res(Nodo nn, TablaSimbolos ts) {
        if (nn.getHijos() == null) {
            if (nn.getValue().tipo == TipoToken.NUMERO || nn.getValue().tipo == TipoToken.CADENA) {
                return nn.getValue().literal;
            } else if (nn.getValue().tipo == TipoToken.IDENTIFICADOR) {
                if (ts.existeIdentificador(nn.getValue().lexema)) {
                    return ts.obtener(nn.getValue().lexema);
                }
            }

        }
        Nodo izquierdo = nn.getHijos().get(0);
        Nodo derecho = nn.getHijos().get(1);

        Object resIzq = res(izquierdo, ts);
        Object resDer = res(derecho, ts);

        if (resIzq instanceof Double && resDer instanceof Double) {
            switch (nn.getValue().tipo) {
                case MENOR_QUE:
                    return ((Double) resIzq < (Double) resDer);
                case OP_MENOR_IGUAL_QUE:
                    return ((Double) resIzq <= (Double) resDer);
                case MAYOR_QUE:
                    return ((Double) resIzq > (Double) resDer);
                case OP_MAYOR_IGUAL_QUE:
                    return ((Double) resIzq >= (Double) resDer);
            }

        } else if (resIzq instanceof Boolean && resDer instanceof Boolean) {
            if (nn.getValue().tipo == TipoToken.AND) {
                return ((Boolean) resIzq && (Boolean) resDer);
            } else if (nn.getValue().tipo == TipoToken.OR) {
                return ((Boolean) resIzq || (Boolean) resDer);
            }
        } else {
            System.out.println("Error Tipos incompatibles");
            System.exit(0);
        }
        return null;
    }

}
