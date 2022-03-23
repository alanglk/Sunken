package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.ListaMisiles;
import org.modelo.misil.Misil;

import java.util.Iterator;

public class Jugador {

	private static Jugador miJugador;
	private Tablero tableroJugador;
	private ListaBarcos listaBarcosJ;
	private ListaMisiles listaMisilesJ;

	public Jugador() {
		this.tableroJugador=new Tablero();
		this.listaBarcosJ=new ListaBarcos();
		this.listaMisilesJ=new ListaMisiles();
	}
	
	public static Jugador getInstance() {
		if(miJugador == null) miJugador = new Jugador();
		return miJugador;
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

	public void actualizarCasillaBarco(int casillaPos, int pId){
		int cont=0;
		int cont2=0;
		Barco aux=null;
		boolean enc=false;
		while(cont<this.listaBarcosJ.size() && !enc){
			if(this.listaBarcosJ.obtenerBarcoEnPos(cont).esBarcoId(pId)){
				enc=true;
				aux=this.listaBarcosJ.obtenerBarcoEnPos(cont);
				aux.eliminarCasilla(casillaPos);
			}
		}
	}
	
	public boolean tieneBarcosJugador() {
		boolean tiene = false;
		if(this.listaBarcosJ.size() > 0) {
			tiene= true;
		}
		else {tiene = false;}
		return tiene;
	}
	
	public boolean misilDisponible(ETipoMisil pMisil) {
		return (listaMisilesJ.sePuedeDisparar(pMisil));
	}
	
	public ListaBarcos obtListaBarcos() {
		return this.listaBarcosJ;
	}

	

//	public void actualizarListaMisilesJugador(Misil pMisil) {
//		
//	}
}