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

	public void colocarBarco(int pPos, String pOrientacion, int pLongitud) {
		if(this.listaBarcosJ.estaDisponible(pLongitud)){
			if(this.tableroJugador.sePuedeColocar(pPos,pOrientacion,pLongitud)){
				this.tableroJugador.colocarBarco(pPos,pOrientacion,pLongitud);
				this.listaBarcosJ.actualizarEstadoBarco(pLongitud);
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