package org.controlador;

import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.misil.ETipoMisil;

public class FormularioControlador {
    private int posicion;
    private boolean tableroEnemigo;
    private ETipoBarco tipoBarco;
    private boolean escudo;
    private EOrientaconBarco orientacion;

    private ETipoMisil tipoMisil;

    public FormularioControlador(int pPosicion, boolean pTaableroEnemigo, ETipoBarco pTipoBarco, boolean pEscudo, EOrientaconBarco pOrientacion, ETipoMisil pTipoMisil){
        posicion = pPosicion;
        tipoBarco = pTipoBarco;
        orientacion = pOrientacion;
        tipoMisil = pTipoMisil;
        tableroEnemigo = pTaableroEnemigo;
        escudo = pEscudo;
    }

    public int getPosicion() {
        return posicion;
    }

    public boolean isTableroEnemigo() {
        return tableroEnemigo;
    }

    public ETipoBarco getTipoBarco() {
        return tipoBarco;
    }

    public boolean isEscudo() {
        return escudo;
    }

    public EOrientaconBarco getOrientacion() {
        return orientacion;
    }

    public ETipoMisil getTipoMisil() {
        return tipoMisil;
    }
}
