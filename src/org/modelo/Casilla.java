package org.modelo;

public class Casilla {

	private EEstadoCasilla estado;
	private int id;

	public Casilla(EEstadoCasilla pEstado) {
		estado = pEstado;
		id=-1;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}

	public void setId(int pId){
		this.id=pId;
	}
}