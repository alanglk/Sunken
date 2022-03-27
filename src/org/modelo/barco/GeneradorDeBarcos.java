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

    public ArrayList<Barco> generarListaBarcos(){
        int cont=1;
        ArrayList<Barco> miListaBarcos=new ArrayList<Barco>();
        while (cont<=4){
            miListaBarcos.add(this.generarBarco(ETipoBarco.FRAGATA,cont));
            cont++;
        }
        while (cont<=7){
            miListaBarcos.add(this.generarBarco(ETipoBarco.DESTRUCTOR,cont));
            cont++;
        }
        while (cont<=9){
            miListaBarcos.add(this.generarBarco(ETipoBarco.SUBMARINO,cont));
            cont++;
        }
        miListaBarcos.add(this.generarBarco(ETipoBarco.PORTAVIONES,cont));
        return miListaBarcos;
    }
}
