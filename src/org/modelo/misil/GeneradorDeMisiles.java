package org.modelo.misil;



import org.modelo.barco.ETipoBarco;
import org.modelo.barco.GeneradorDeBarcos;

import java.util.ArrayList;

public class GeneradorDeMisiles {
    public GeneradorDeMisiles(){

    }

    public Misil generarBarco(ETipoMisil pTipo){
        Misil miMisil= FactoriaMisiles.getInstance().crearMisil(pTipo);
        return miMisil;
    }

    public ArrayList<Misil> generarListaBarcos(){
        ArrayList<Misil> miListaMisiles=new ArrayList<Misil>();
        miListaMisiles.add(this.generarBarco(ETipoMisil.BOMBA));
        return miListaMisiles;
    }
}
