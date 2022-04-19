package org.modelo.misil;


public class GeneradorDeMisiles {
    public GeneradorDeMisiles(){

    }

    public Misil generarMisil(ETipoMisil pTipo){
        Misil miMisil= FactoriaMisiles.getInstance().crearMisil(pTipo);
        return miMisil;
    }

    public ListaMisiles generarListaMisiles(){
        ListaMisiles miListaMisiles=new ListaMisiles();
        miListaMisiles.anadirMisil(this.generarMisil(ETipoMisil.BOMBA));

        // Testear el funcionamiento de la bomba one tap
        miListaMisiles.anadirMisil(this.generarMisil(ETipoMisil.BOMBAONETAP));

        return miListaMisiles;
    }
}
