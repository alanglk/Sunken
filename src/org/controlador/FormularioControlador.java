package org.controlador;

import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.misil.ETipoMisil;

public class FormularioControlador {
    public int posicion;

    public ETipoBarco tipoBarco;
    public EOrientaconBarco orientacion;

    public ETipoMisil tipoMisil;

    public FormularioControlador(int pPosicion, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion, ETipoMisil pTipoMisil){
        posicion = pPosicion;
        tipoBarco = pTipoBarco;
        orientacion = pOrientacion;
        tipoMisil = pTipoMisil;
    }
}
