package org.modelo;

import org.modelo.barco.*;
import org.modelo.excepciones.ImposibleColocarBarcoException;
import org.modelo.excepciones.ImposibleColocarEscudoException;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.excepciones.ImposibleUsarRadarException;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;

public interface Entidad {

    // BARCOS --------
    public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws ImposibleColocarBarcoException;
    public void colocarBarco();
    public boolean estanTodosBarcosColocados();
    public boolean hayBarcosSinHundir();
    public void imprimirBarcos();
    public Integer obtenerNumBarcosNoColocados(ETipoBarco tipoBarco);

    // DISPAROS --------
    public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo);
    public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException;
    public void realizarDisparo();
    public int recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo);

    //REALIZAR ACCION --------
    //Esto s�lo lo utiliza el enemigo
    boolean realizarAccion(boolean juegoTerminado) throws ImposibleUsarRadarException;

    public Integer obtenerNumMisilesDisponibles(ETipoMisil tipoMisil);

    // RADAR --------
    public void usarRadar() throws ImposibleUsarRadarException;
    public void revelarCasillasRadar(ArrayList<Integer> posciones);
    public void recolocarRadar();
    public void colocarRadarEnCasilla(int posRadarAnt, int posRadarAct);
    public Integer obtenerNumUsosRadar();

    // CASILLAS --------
    public void actualizarContorno(ArrayList<Integer> pLista);
    public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado);
    public EEstadoCasilla getEstadoCasilla(int pPos);
    public void actualizarEstadoCasillaOneTap(int pCasilla, EEstadoCasilla pEstado);


    // ESCUDOS --------
    public void colocarEscudoBarco(int pCasilla) throws ImposibleColocarEscudoException;
    public void colocarEscudoBarco();
    public Integer obtenerNumEscudos();
}
