package org.modelo.radar;

import org.modelo.ListaJugadores;

import java.util.ArrayList;
import java.util.Random;

public abstract class Radar {
    private int numUsos;
    protected int posRadar;

    public Radar(int pNumUsosInicial){
        numUsos = pNumUsosInicial;
        posRadar = new Random().nextInt(100);
    }

    public abstract ArrayList<Integer> obtenerPosicionesReveladas(int pAnchuraTablero);

    public void cambiarPosicionRadar(boolean pEnemigo){
        int posRadarAnt = posRadar;
        posRadar = new Random().nextInt(100);

        if(!pEnemigo)
            ListaJugadores.getInstance().getEntidad(1).colocarRadarEnCasilla(posRadarAnt, posRadar);
        else
            ListaJugadores.getInstance().getEntidad(0).colocarRadarEnCasilla(posRadarAnt, posRadar);
    }

    public boolean sePuedeUtilizar() {
        return numUsos > 0;
    }

    protected void decrementarNumUsos() {
        numUsos --;
    }

    protected void incrementarNumUsos() {
        this.numUsos++;
    }

    public int obtenerNumUsos(){
        return numUsos;
    }
}
