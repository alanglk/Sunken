package org.modelo.barco;

public class FactoriaBarcos {
    private static FactoriaBarcos mFatoria;

    private FactoriaBarcos(){}

    public static FactoriaBarcos getInstance(){
        if(mFatoria == null) mFatoria = new FactoriaBarcos();
        return mFatoria;
    }

    public Barco crearBarco(ETipoBarco pTipo,int pId){
        Barco nuevoBarco = null;

        if(pTipo.equals(ETipoBarco.FRAGATA))
            nuevoBarco = new Fragata(pId);

        if (pTipo.equals(ETipoBarco.DESTRUCTOR))
            nuevoBarco = new Destructor(pId);

        if(pTipo.equals(ETipoBarco.PORTAVIONES)){
            nuevoBarco = new Portaviones(pId);
            //TODO: Testear el escudo
            nuevoBarco.setEscudo(new EscudoBarco());
        }


        if(pTipo.equals(ETipoBarco.SUBMARINO))
            nuevoBarco = new Submarino(pId);

        return nuevoBarco;
    }
}
