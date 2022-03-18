package org.modelo.misil;

public class FactoriaMisiles {
    private static FactoriaMisiles miFactoriaMisiles;

    private FactoriaMisiles FactoriaMisiles(){

    }
    public static FactoriaMisiles getFactoriaMisiles(){
        if (miFactoriaMisiles==null)

            miFactoriaMisiles=new FactoriaMisiles();

        return miFactoriaMisiles;
    }
    public Misil crearBarco(String pTipo){
        if(pTipo.equals(Misil.BOMBA))
            return new Bomba();
        //ELSE IF...
    }

}
