package org.modelo.misil;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaMisiles {
    private ArrayList<Misil> lista;

    public ListaMisiles() {
        this.lista=new ArrayList<Misil>();
    }

    public ArrayList<Integer> obtAreaMisil(ETipoMisil pTipoMisil, int pPosicionDisparo, int pAnchuraTablero) {
        return obtMisil(pTipoMisil).obtArea(pPosicionDisparo, pAnchuraTablero);
    }

    public boolean sePuedeDisparar(ETipoMisil pTipoMisil) {
        return obtMisil(pTipoMisil).sePuedeDisparar();
    }

    private Iterator<Misil> getIterador() {
        return (this.lista.iterator());
    }

    private Misil obtMisil(ETipoMisil pTipo) {
        Iterator<Misil> itr = this.getIterador();
        Misil misil = null;
        boolean enc = false;

        while (itr.hasNext() && !enc) {
            misil = itr.next();
            if(misil.esTipo(pTipo))
                enc = true;
        }

        if (!enc) misil = null;
        return (misil);
    }

    public void anadirMisil(Misil pMisil){
        this.lista.add(pMisil);
        pMisil.incrementarNumMisiles();
    }
    
//    public void actualizarMisil(ETipoMisil pTipoMisil) {
//    	
//    }

}