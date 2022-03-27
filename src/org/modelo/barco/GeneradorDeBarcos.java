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
        ListaBarcos miListaBarcos=new ListaBarcos();
        int id = 0;

        for(int i = 0; i < 4; i++){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.FRAGATA, id));
            id++;
        }
        for(int i = 0; i < 3; i++){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.DESTRUCTOR, id));
        }
        for(int i = 0; i < 2; i++){
            miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.SUBMARINO, id));
        }

        miListaBarcos.anadirBarco(this.generarBarco(ETipoBarco.PORTAVIONES, id));

        return miListaBarcos;
    }
}
