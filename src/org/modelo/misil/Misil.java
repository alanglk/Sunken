package org.modelo.misil;

import java.util.Collection;

public abstract class Misil {
	public static final String BOMBA = "Bomba";
	private int numMisiles;
	private boolean ilimatado;

	/**
	 * 
	 * @param pPosicionDisparo
	 * @param pAnchuraTablero
	 */
	public abstract Collection<Integer> obtArea(int pPosicionDisparo, int pAnchuraTablero) ;

	public boolean sePuedeDisparar() {
		if (this.numMisiles=0){
			return false;
		}
		return true;
	}

	protected void decrementarNumMisiles() {
		if this.numMisiles==0{

		}
		else{
			this.numMisiles--;
		}
	}
	protected void incrementarNumMisiles() {

			this.numMisiles++;
		}


}