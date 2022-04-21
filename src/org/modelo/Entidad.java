package org.modelo;

import org.modelo.barco.*;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.excepciones.ImposibleUsarRadarException;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;

public interface Entidad {

    // BARCOS --------
    public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws Exception;
    public void colocarBarco();
    public boolean estanTodosBarcosColocados();
    public boolean hayBarcosSinHundir();
    public void imprimirBarcos();
    public Integer obtenerNumBarcos(ETipoBarco tipoBarco);
    
    //REALIZAR ACCION --------
    public boolean realizarAccion(boolean juegoTerminado) throws ImposibleUsarRadarException;

    // DISPAROS --------
    public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo);
    public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException;
    public void realizarDisparo();
    public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo);

    // RADAR --------
    public void usarRadar() throws ImposibleUsarRadarException;
    public void revelarCasillasRadar(ArrayList<Integer> posciones);
    public void recolocarRadar();
    public void colocarRadarEnCasilla(int posRadarAnt, int posRadarAct);

    // CASILLAS --------
    public void actualizarContorno(ArrayList<Integer> pLista);
    public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado);
    public EEstadoCasilla getEstadoCasilla(int pPos);
    public void actualizarEstadoCasillaOneTap(int pCasilla, EEstadoCasilla pEstado);


}
