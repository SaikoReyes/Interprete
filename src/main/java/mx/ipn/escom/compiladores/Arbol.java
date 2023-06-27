package mx.ipn.escom.compiladores;

import java.util.ArrayList;
import java.util.List;

public class Arbol {
    private final Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }

    public void recorrer(){
        TablaSimbolos ts= new TablaSimbolos();
        for(Nodo n : raiz.getHijos()){
            Token t = n.getValue();
            switch (t.tipo){
                // Operadores aritméticos

                case VAR:
                    if(n.getHijos().size()>=2){
                        Nodo izquierdo = n.getHijos().get(0);

                        Nodo derecho = n.getHijos().get(1);

                        if(ts.existeIdentificador(izquierdo.getValue().lexema)){
                            Principal.error(izquierdo.getValue().posicion, "Variable Existente");
                        }else{
                            ts.asignar(izquierdo.getValue().lexema, derecho.getValue().literal);
                        }
                    }else{
                        Nodo izquierdo = n.getHijos().get(0);
                        if(ts.existeIdentificador(izquierdo.getValue().lexema)){
                            Principal.error(izquierdo.getValue().posicion, "Variable Existente");
                        }else{
                            ts.asignar(izquierdo.getValue().lexema, null);
                        }
                    }
                    
                    break;
                case PRINT:
                    Nodo izquierdo01 = n.getHijos().get(0);
                    if(ts.existeIdentificador(izquierdo01.getValue().lexema)){
                        System.out.println(ts.obtener(izquierdo01.getValue().lexema));
                    }else{
                        System.out.println(izquierdo01.getValue().literal);
                    }
                    break;

            }
        }
    }

}

