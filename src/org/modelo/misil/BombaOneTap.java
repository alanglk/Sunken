package org.modelo.misil;

import org.modelo.Comprable;

import java.util.ArrayList;

public class BombaOneTap extends Misil implements Comprable {

    public BombaOneTap() {
        super(0, false, ETipoMisil.BOMBAONETAP);
    }

    // Obtener el area del disparo
    public ArrayList<Integer> obtArea(int pPosicionDisparo, int pAnchuraTablero){
        ArrayList<Integer> listaInt = new ArrayList<Integer>();

        if(sePuedeDisparar()){
            listaInt.add(pPosicionDisparo);

            decrementarNumMisiles();
        }

        return listaInt;
    }

    @Override
    public void comprar() {
        incrementarNumMisiles();
    }
}
