package org.modelo;

import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ListaMisiles;
import org.modelo.misil.Misil;

public class Jugador {

	private String nombre;
	private Tablero tableroJugador;
	private ListaBarcos listaBarcosJ;
	private ListaMisiles listaMisilesJ;

	public Jugador(String pNombre) {
		this.nombre=pNombre;
		this.tableroJugador=new Tablero();
		this.listaBarcosJ=new ListaBarcos();
		this.listaMisilesJ=new ListaMisiles();
	}

	public void colocarBarco(int pPos, String pOrientacion, String pTipoBarco) {
		if(this.listaBarcosJ.estaDisponible(pTipoBarco)){
			if(this.tableroJugador.sePuedeColocar(pPos,pOrientacion,pTipoBarco)){
				this.tableroJugador.colocarBarco(pPos,pOrientacion,pTipoBarco);
				this.listaBarcosJ.actualizarEstadoBarco(pTipoBarco);
				if (this.listaBarcosJ.estanTodosBarcosColocados()){
					GestorDelJuego.getMiGestorDelJuego().jugarPartida();
				}
			}
			else{
				System.err.println("No se puede colocar en esa posición el barco");
			}
		}
		else{
			System.err.println("No esta disponible el barco");
		}
	}


	public void recibirDisparo(Misil pMisil, Casilla pCasilla) {

	}

}