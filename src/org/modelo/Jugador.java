package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ETipoMisil;
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

	public void colocarBarco(int pPos, EOrientaconBarco pOrientacion, ETipoBarco pTipoBarco) throws Exception {
		Barco barco = listaBarcosJ.obtenerBarcoNoColocado(pTipoBarco);

		// Si hay un barco disponible comprobamos si se puede colocar en la posicion
		if(barco != null){

			// Si se puede colocar, lo colocamos y actualizamos el estado del barco
			if(tableroJugador.sePuedeColocar(pPos,pOrientacion,barco)){
				tableroJugador.colocarBarco(pPos,pOrientacion,barco);
				barco.actualizarBarcoColocado();

				// Comprobamos si estan todos los barcos colocados. Si lo estan iniciamos la partida
				if (listaBarcosJ.estanTodosBarcosColocados()){
					GestorDelJuego.getInstance().jugarPartida();
				}

			} else{
				throw new Exception("ERROR: No se puede colocar en esa posición el barco");
			}

		}else{
			throw new Exception("ERROR: No esta disponible el barco");
		}
	}


	public void recibirDisparo(ETipoMisil pMisil, int pPos) {

	}
	
	public boolean tieneBarcosJugador() {
		boolean tiene = false;
		if(this.listaBarcosJ.size() > 0) {
			tiene= true;
		}
		else {tiene = false;}
		return tiene;
	}
	
//	public boolean misilDisponible(Misil pMisil) {
//		return this.listaMisilesJ.sePuedeDisparar(pMisil);
//	}

//	public void actualizarListaMisilesJugador(Misil pMisil) {
//		
//	}
}