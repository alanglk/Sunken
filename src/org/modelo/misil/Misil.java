package org.modelo.misil;

import java.util.ArrayList;

public abstract class Misil {
	public static final String BOMBA = "Bomba";


	private String tipo = null;
	private int numMisiles;
	private boolean ilimitado;

	public Misil(int pNumMisilesInicial, boolean pIlimitado, String pTipo){
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

	public boolean esTipo(String pTipo){
		return tipo.equals(pTipo);
	}
}