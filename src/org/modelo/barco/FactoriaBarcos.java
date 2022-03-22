package org.modelo.barco;

public class FactoriaBarcos {
    private static FactoriaBarcos mFatoria;

    private FactoriaBarcos(){}

    public static FactoriaBarcos getInstance(){
        if(mFatoria == null) mFatoria = new FactoriaBarcos();
        return mFatoria;
    }

    public Barco crearBarco(ETipoBarco pTipo){
        Barco nuevoBarco = null;

        if(pTipo.equals(ETipoBarco.FRAGATA))
            nuevoBarco = new Fragata();

        if (pTipo.equals(ETipoBarco.DESTRUCTOR))
            nuevoBarco = new Destructor();

        if(pTipo.equals(ETipoBarco.PORTAVIONES))
            nuevoBarco = new Portaviones();

        if(pTipo.equals(ETipoBarco.SUBMARINO))
            nuevoBarco = new Submarino();

        return nuevoBarco;
    }
}
