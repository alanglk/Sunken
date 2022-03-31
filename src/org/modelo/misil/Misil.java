package org.modelo.misil;

import org.modelo.barco.ETipoBarco;

import java.util.ArrayList;

public abstract class Misil {
	private ETipoMisil tipo = null;
	private int numMisiles;
	private boolean ilimitado;

	public Misil(int pNumMisilesInicial, boolean pIlimitado, ETipoMisil pTipo){
		this.numMisiles = pNumMisilesInicial;
		this.ilimitado = pIlimitado;
		this.tipo = pTipo;
	}

	public abstract ArrayList<Integer> obtArea(int pPosicionDisparo, int pAnchuraTablero);

	public boolean sePuedeDisparar() {
		boolean sePuedeDisparar = false;

		if(ilimitado) sePuedeDisparar = true;
		else if(numMisiles > 0) sePuedeDisparar = true;


		return sePuedeDisparar;
	}

	protected void decrementarNumMisiles() {
		if(!ilimitado){
			numMisiles--;
			if(numMisiles < 0) numMisiles = 0;
		}
	}
	protected void incrementarNumMisiles() {
			this.numMisiles++;
	}

	public boolean esTipo(ETipoMisil pTipo){
		return tipo.equals(pTipo);
	}
}