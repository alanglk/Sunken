package org.modelo;

import org.modelo.barco.ETipoBarco;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;
import java.util.HashMap;

public class FormularioModelo {
    public boolean colocandoBarcos;
    public boolean juegoTerminado;
    public ArrayList<EEstadoCasilla> casillasJugador;
    public ArrayList<EEstadoCasilla> casillasEnemigo;
    public HashMap<ETipoBarco, Integer> numBarcosNoColocados;
    public HashMap<ETipoBarco, Integer> numBarcosNoHundidos;
    public HashMap<ETipoMisil, Integer> numMisilesJugador;
    public Integer numUsosRadarJugador;
    public Integer numEscudosJugador;
    public int dineroJugador;
    public int dineroEnemigo;

    public FormularioModelo(boolean pColocandoBarcos, boolean pJuegoTerminado,ArrayList<EEstadoCasilla> pCasillasJugador, ArrayList<EEstadoCasilla> pCasillasEnemigo, HashMap<ETipoBarco, Integer> pNumBarcosNoColocados, HashMap<ETipoBarco, Integer> pNumBarcosNoHundidos, HashMap<ETipoMisil, Integer> pNumMisilesJugador,Integer pNumUsosRadarJugador, Integer pNumEscudosJugador,int pDineroJugador,int pDineroEnemigo){
        colocandoBarcos = pColocandoBarcos;
        juegoTerminado = pJuegoTerminado;
        casillasJugador = pCasillasJugador;
        casillasEnemigo = pCasillasEnemigo;
        numBarcosNoColocados = pNumBarcosNoColocados;
        numBarcosNoHundidos = pNumBarcosNoHundidos;
        numMisilesJugador = pNumMisilesJugador;
        numUsosRadarJugador = pNumUsosRadarJugador;
        numEscudosJugador = pNumEscudosJugador;
        dineroJugador=pDineroJugador;
        dineroEnemigo=pDineroEnemigo;
    }
}
