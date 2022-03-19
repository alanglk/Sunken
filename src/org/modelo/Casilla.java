package org.modelo;

public class Casilla {

	private EEstadoCasilla estado;

	public Casilla(EEstadoCasilla pEstado) {
		estado = pEstado;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}
}