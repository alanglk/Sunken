package org.modelo.barco;

import org.modelo.EEstadoCasilla;
import org.modelo.misil.ETipoMisil;

public class EscudoBarco {
    private int numUsos;

    public EscudoBarco(){
        numUsos = 2;
    }

    public boolean recibirDisparoYSerompeElEscudo(ETipoMisil tipo){
        boolean seRecibeDano = false;
        if(numUsos > 0){
            if(tipo.equals(ETipoMisil.BOMBA))
                numUsos--;
            else if(tipo.equals(ETipoMisil.BOMBAONETAP))
                numUsos -= 2;

        }else{
            seRecibeDano = true;
        }

        return seRecibeDano;
    }

    public EEstadoCasilla obtenerEstadoEscudo(){
        EEstadoCasilla estado = null;
        if(numUsos > 0)
            estado = EEstadoCasilla.ESCUDO;
        else
            estado = EEstadoCasilla.BARCO;

        return estado;
    }

}
