package org.modelo;

import org.modelo.misil.Misil;

public class GestorDelJuego {

	private static GestorDelJuego miGestorDelJuego;
	private Jugador jugador;
	private Enemigo enemigo;

	private GestorDelJuego() {
		// TODO - implement GestorDelJuego.GestorDelJuego
		throw new UnsupportedOperationException();
	}

	public static GestorDelJuego getInstance() {
		if(miGestorDelJuego == null) miGestorDelJuego = new GestorDelJuego();
		return miGestorDelJuego;
	}

	public void jugarPartida() {
		boolean terminar = false;
		//Todos los barcos de Jugador están colocados, colocamos los del enemigo
		this.enemigo.colocarBarcoEnemigo();
		//Disparamos misiles hasta que algún jugador gane
		while(!terminar) {
			if(this.enemigo.tieneBarcosEnemigo()) {
				this.dispararMisilJugador(p1, p2);
				this.enemigo.recibirDisparo(p1, p2);
			}
			else {terminar = true;}
			if(this.jugador.tieneBarcosJugador() && !terminar) {
				this.dispararMIsilEnem(p1, p2);
				this.jugador.recibirDisparo(p1, p2);
			}
			else {terminar = true;}
		}
	}

	/**
	 * 
	 * @param pMIsil
	 * @param pCasilla
	 */
	public void dispararMisilJugador(Misil pMisil, Casilla pCasilla) {
		//Falta toda la implementación de otras clases
		if(this.jugador.misilDisponible(pMisil)) {
			this.enemigo.recibirDisparo(pMisil, pCasilla);
			this.jugador.actualizarListaMisilesJugador(pMisil);
		}
	}

	/**
	 * 
	 * @param pMisil
	 * @param pCasilla
	 */
	public void dispararMIsilEnem(Misil pMisil, Casilla pCasilla) {
		//Falta toda la implementación de otras clases
		if(this.enemigo.misilDisponible(pMisil)) {
			this.jugador.recibirDisparo(pMisil, pCasilla);
			this.enemigo.actualizarListaMisilesEnemigo(pMisil);
		}
	}

}