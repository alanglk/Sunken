package org.modelo.excepciones;

public class ImposibleComprarException extends Exception{
    public String motivo = null;
    public ImposibleComprarException(String pMotivo){
        super();
        motivo = pMotivo;
    }
}
