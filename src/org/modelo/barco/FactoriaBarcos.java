package org.modelo.barco;

public class FactoriaBarcos {
    private static FactoriaBarcos mFactoria;

    private FactoriaBarcos(){}

    public static FactoriaBarcos getInstance(){
        if(mFactoria == null) mFactoria = new FactoriaBarcos();
        return mFactoria;
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
