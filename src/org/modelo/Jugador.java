package org.modelo;

import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ListaMisiles;
import org.modelo.misil.Misil;

public class Jugador {

	private String nombre;
	private Tablero tableroJugador;
	private ListaBarcos listaBarcosJ;
	private ListaMisiles listaMisilesJ;

	public Jugador() {
		// TODO - implement Jugador.Jugador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pPos
	 * @param pOrientacion
	 * @param pLongitud
	 */
	public void colocarBarco(int pPos, String pOrientacion, int pLongitud) {
		// TODO - implement Jugador.colocarBarco
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pMisil
	 * @param pCasilla
	 */
	public void recibirDisparo(Misil pMisil, Casilla pCasilla) {
		// TODO - implement Jugador.recibirDisparo
		throw new UnsupportedOperationException();
	}

}