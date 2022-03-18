package org.modelo.misil;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaMisiles {

    private Collection<Misil> lista;

    public ListaMisiles() {
        new ArrayList<Misil>();
    }

    /**
     * @param pMisil
     * @param pPosicionDisparo
     * @param pAnchuraTablero
     */
    private Collection<Integer> obtAreaMisil(Misil pMisil, int pPosicionDisparo, int pAnchuraTablero) {
        return pMisil.obtArea(pPosicionDisparo, pAnchuraTablero);
    }

    /**
     * @param pMisil
     */
    public boolean sePuedeDisparar(Misil pMisil) {
        return (pMisil.sePuedeDisparar());
    }

    private Iterator<Misil> getIterador() {
        return (this.lista.iterator());

    }

    /**
     * @param pMisil
     */
    private Misil obtMisil(String pTipo) {
        Iterator<Misil> itr = this.getIterador();
        Misil aux = null;
        boolean enc = false;

        while (itr.hasNext() && !enc) {
            aux = itr.next();
            if (pTipo.equals(Misil.BOMBA)) {
                if (aux instanceof Bomba)
                    enc = true;
            }
        }

        if (!enc)
            aux = null;

        return (aux);
    }

    public void añadirMisil(Misil pMisil){
        this.lista.add(pMisil);
        pMisil.incrementarNumMisiles();
    }

}