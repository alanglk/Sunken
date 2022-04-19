package org.modelo;

import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;

public interface Entidad {
    public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws Exception;
    public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo);
    public void realizarDisparo(ETipoMisil pTipo, int pPos);
    public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo);
    public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado);

    public boolean estanTodosBarcosColocados();
    public boolean hayBarcosSinHundir();
    public EEstadoCasilla getEstadoCasilla(int pPos);
}
