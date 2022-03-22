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

	public boolean tieneBarco(){
		return(this.id==-1);

	}

	public void setId(int pId){
		this.id=pId;
	}
	public int getId(){return this.id;}

	public void setEstado(String pEstado){
		this.estado.equals(pEstado);
	}
}