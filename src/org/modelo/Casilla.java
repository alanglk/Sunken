package org.modelo;

import org.modelo.barco.Barco;

public class Casilla {

	private int pos;
	private EEstadoCasilla estado;
	private int idBarco;
	private boolean enemigo = false;
	private boolean oculto = false;

	public Casilla(int pPos, EEstadoCasilla pEstado, boolean pEnemigo) {
		pos = pPos;
		estado = pEstado;
		idBarco=-1;
		enemigo = pEnemigo;

		if(pEnemigo) oculto=true;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}

	public void ponerBarco(Barco pBarco){
		this.idBarco= pBarco.getId();
		this.estado=EEstadoCasilla.BARCO;

		pBarco.anadirCasilla(pos);
	}

	public void actualizarDisparo(){
		// TODO: Esto hau que cambiarlo en diseño!!!!!
		if(!enemigo && idBarco != -1)
			Jugador.getInstance().eliminarCasillaBarco(pos,this.idBarco);
		else if(idBarco != -1)
			Enemigo.getInstance().eliminarCasillaBarco(pos,this.idBarco);

		oculto = false;
		if(estado == EEstadoCasilla.BARCO)
			estado = EEstadoCasilla.HUNDIDO;
		else if(estado == EEstadoCasilla.AGUA)
			estado = EEstadoCasilla.AGUADISPARO;
	}
	public EEstadoCasilla getEstado() {
		if(oculto)
			return EEstadoCasilla.OCULTO;

		return estado;
	}
}