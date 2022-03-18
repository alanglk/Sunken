package org.modelo.barco;

public class FactoriaBarcos {
    private static FactoriaBarcos mFatoria;

    private FactoriaBarcos(){}

    public static FactoriaBarcos getInstance(){
        if(mFatoria == null) mFatoria = new FactoriaBarcos();
        return mFatoria;
    }

    public Barco crearBarco(String pTipo){
        Barco nuevoBarco = null;

        if(pTipo.equals(Barco.FRAGATA))
            nuevoBarco = new Fragata();

        if (pTipo.equals(Barco.DESTRUCTOR))
            nuevoBarco = new Destructor();

        if(pTipo.equals(Barco.PORTAVIONES))
            nuevoBarco = new Portaviones();

        if(pTipo.equals(Barco.SUBMARINO))
            nuevoBarco = new Submarino();

        return nuevoBarco;
    }
}
