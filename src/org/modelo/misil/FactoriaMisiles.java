package org.modelo.misil;

import org.modelo.barco.*;

public class FactoriaMisiles {
    private static FactoriaMisiles miFactoriaMisiles;

    private FactoriaMisiles(){}

    public static FactoriaMisiles getInstance(){
        if (miFactoriaMisiles==null) miFactoriaMisiles = new FactoriaMisiles();
        return miFactoriaMisiles;
    }

    public Misil crearMisil(String pTipo){
        Misil nuevoMisil = null;

        if(pTipo.equals(Misil.BOMBA))
            nuevoMisil = new Bomba();

        return nuevoMisil;
    }

}
