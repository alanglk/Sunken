package org.modelo;

import org.modelo.misil.ETipoMisil;
import org.modelo.misil.Misil;

import java.util.ArrayList;

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
		ETipoMisil p1;
		int p2;
		p1=null;
		p2=0;
		boolean terminar = false;
		//Todos los barcos de Jugador est�n colocados, colocamos los del enemigo
		this.enemigo.colocarBarcoEnemigo();
		//Disparamos misiles hasta que alg�n jugador gane
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
		//Falta toda la implementaci�n de otras clases
		/*if(this.jugador.misilDisponible(pMisil)) {
			this.enemigo.recibirDisparo(pMisil, pCasilla);
			this.jugador.actualizarListaMisilesJugador(pMisil);
		}*/
	}

	/**
	 * 
	 * @param pMisil
	 * @param pCasilla
	 */
	public void dispararMIsilEnem(Misil pMisil, Casilla pCasilla) {
		//Falta toda la implementaci�n de otras clases
		/*if(this.enemigo.misilDisponible(pMisil)) {
			this.jugador.recibirDisparo(pMisil, pCasilla);
			this.enemigo.actualizarListaMisilesEnemigo(pMisil);
		}*/
	}

}