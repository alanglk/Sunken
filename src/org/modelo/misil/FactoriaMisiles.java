package org.modelo.misil;

public class FactoriaMisiles {
    private static FactoriaMisiles miFactoriaMisiles;

    private  FactoriaMisiles(){

    }
    public static FactoriaMisiles getFactoriaMisiles(){
        if (miFactoriaMisiles==null)

            miFactoriaMisiles=new FactoriaMisiles();

        return miFactoriaMisiles;
    }
    public Misil crearBarco(String pTipo){
        Misil pMisil=null;
        if(pTipo.equals(Misil.BOMBA)) {
            pMisil = new Bomba();
        }
        return pMisil;
        //ELSE IF...
    }

}
