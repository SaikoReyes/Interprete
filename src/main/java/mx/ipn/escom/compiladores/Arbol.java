package mx.ipn.escom.compiladores;

import java.util.ArrayList;
import java.util.List;

public class Arbol {
    private final Nodo raiz;

    public Arbol(Nodo raiz){
        this.raiz = raiz;
    }

    public void recorrer(TablaSimbolos ts){
        
        for(Nodo n : raiz.getHijos()){
            Token t = n.getValue();
            switch (t.tipo){
                // Operadores aritméticos

                case VAR:
                    if(n.getHijos().size()>1){
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
                   
                
                case IF:
                    Nodo izquierdo02 = n.getHijos().get(0);
                    OpLogicos logic = new OpLogicos(izquierdo02);
                    Object res01 = logic.res(ts);
                    if((Boolean)res01){
                        if(n.getHijos().size()>2){
                            for(int i = 0; i<n.getHijos().size();i++){
                     Nodo der01 = n.getHijos().get(i);
                     switch (der01.getValue().tipo){
                         case PRINT:
                             Nodo izquierda3 = der01.getHijos().get(0);
                             if(ts.existeIdentificador(izquierda3.getValue().lexema)){
                                 System.out.println(ts.obtener(izquierda3.getValue().lexema));
                             }else{
                                 System.out.println(izquierda3.getValue().literal);
                             }
                             break;
                         default:
                             break;
                     }
                            }
                    }else{
                            Nodo der01 = n.getHijos().get(1);
                            switch(der01.getValue().tipo){
                                case PRINT:
                                    Nodo izquierda3 = der01.getHijos().get(0);
                                    if(ts.existeIdentificador(izquierda3.getValue().lexema)){
                                        System.out.println(ts.obtener(izquierda3.getValue().lexema));
                                    }else{
                                        System.out.println(izquierda3.getValue().literal);
                                    }
                                    break;
                                default:
                                    break;
                            }   
                        }
                    }else{
                        Nodo else01 = n.getHijos().get(2);
                        Nodo izquierda_else=else01.getHijos().get(0);
                        switch(izquierda_else.getValue().tipo){
                            case PRINT:
                                Nodo izquierda3 = izquierda_else.getHijos().get(0);
                                if(ts.existeIdentificador(izquierda3.getValue().lexema)){
                                    System.out.println(ts.obtener(izquierda3.getValue().lexema));
                                }else{
                                    System.out.println(izquierda3.getValue().literal);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;

            }
        }
    }
}

