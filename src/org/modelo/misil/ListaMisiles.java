package org.modelo.misil;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaMisiles {

    private ArrayList<Misil> lista;

    public ListaMisiles() {
        new ArrayList<Misil>();
    }

    private ArrayList<Integer> obtAreaMisil(String pTipoMisil, int pPosicionDisparo, int pAnchuraTablero) {
        return obtMisil(pTipoMisil).obtArea(pPosicionDisparo, pAnchuraTablero);
    }

    public boolean sePuedeDisparar(String pTipoMisil) {
        return obtMisil(pTipoMisil).sePuedeDisparar();
    }

    private Iterator<Misil> getIterador() {
        return (this.lista.iterator());
    }

    private Misil obtMisil(String pTipo) {
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

    public void añadirMisil(String pTipoMisil){
        Misil misil = FactoriaMisiles.getInstance().crearMisil(pTipoMisil);
        this.lista.add(misil);
        misil.incrementarNumMisiles();
    }

}