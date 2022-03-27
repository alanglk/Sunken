package org.modelo.barco;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneradorDeBarcos {
    public GeneradorDeBarcos(){
    }

    public Barco generarBarco(ETipoBarco pTipo,int pId){
        Barco miBarco=FactoriaBarcos.getInstance().crearBarco(pTipo,pId);
        return miBarco;
    }

    public ListaBarcos generarListaBarcos(){
        int cont=1;
        ListaBarcos miListaBarcos=new ListaBarcos();
        while (cont<=4){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.FRAGATA,cont));
            cont++;
        }
        while (cont<=7){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.DESTRUCTOR,cont));
            cont++;
        }
        while (cont<=9){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.SUBMARINO,cont));
            cont++;
        }
        miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.PORTAVIONES,cont));
        return miListaBarcos;
    }
}
