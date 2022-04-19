package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.misil.ETipoMisil;

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

	public void actualizarDisparo(ETipoMisil pTipo){
		// TODO: Esto hau que cambiarlo en diseño!!!!!
		if(!enemigo && idBarco != -1)
			ListaJugadores.getInstance().getEntidad(0).dispararBarco(pTipo, pos,this.idBarco, enemigo);
		else if(idBarco != -1)
			ListaJugadores.getInstance().getEntidad(1).dispararBarco(pTipo, pos,this.idBarco, enemigo);

		oculto = false;
		if(estado == EEstadoCasilla.AGUA)
			estado = EEstadoCasilla.AGUADISPARO;
	}

	// Se llama desde barco
	public void actualizarEstadoCasilla(EEstadoCasilla pEstado){
		oculto = false;

		if(estado.equals(EEstadoCasilla.BARCO))
			estado = pEstado;
	}

	public EEstadoCasilla getEstado() {
		if(oculto)
			return EEstadoCasilla.OCULTO;
		return estado;
	}

}