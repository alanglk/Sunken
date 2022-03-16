package org.modelo;

public class GestorDelJuego {

	private static GestorDelJuego miGestorDelJuego;
	private Jugador jugador;
	private Jugador enemigo;

	private GestorDelJuego() {
		// TODO - implement GestorDelJuego.GestorDelJuego
		throw new UnsupportedOperationException();
	}

	public static GestorDelJuego getMiGestorDelJuego() {
		if(miGestorDelJuego == null) miGestorDelJuego = new GestorDelJuego();
		return miGestorDelJuego;
	}

	public void colocarBarcos() {
		// TODO - implement GestorDelJuego.colocarBarcos
		throw new UnsupportedOperationException();
	}

	public void jugarPartida() {
		// TODO - implement GestorDelJuego.jugarPartida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pMIsil
	 * @param pCasilla
	 */
	public void dispararMisilJugador(Misil pMIsil, Casilla pCasilla) {
		// TODO - implement GestorDelJuego.dispararMisilJugador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pMisil
	 * @param pCasilla
	 */
	public void dispararMIsilEnem(Misil pMisil, Casilla pCasilla) {
		// TODO - implement GestorDelJuego.dispararMIsilEnem
		throw new UnsupportedOperationException();
	}

}