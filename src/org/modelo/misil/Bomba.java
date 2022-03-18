package org.modelo.misil;

import org.modelo.misil.Misil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bomba extends Misil {

    /**
     * @param pPosicion
     * @param pAnchuraTablero
     */
    public ArrayList<Integer> obtArea(int pPosicion) {
        ArrayList<Integer> listaInt = new ArrayList<Integer>();
        listaInt.add(pPosicion);
		return listaInt;
    }


}