package org.modelo;

import java.util.Collection;

public abstract class Misil {

	private int numMisiles;
	private boolean ilimatado;

	/**
	 * 
	 * @param pPosicionDisparo
	 * @param pAnchuraTablero
	 */
	public Collection<Integer> obtArea(int pPosicionDisparo, int pAnchuraTablero) {
		// TODO - implement Misil.obtArea
		throw new UnsupportedOperationException();
	}

	public boolean sePuedeDisparar() {
		// TODO - implement Misil.sePuedeDisparar
		throw new UnsupportedOperationException();
	}

	protected void decrementarNumMisiles() {
		// TODO - implement Misil.decrementarNumMisiles
		throw new UnsupportedOperationException();
	}

}