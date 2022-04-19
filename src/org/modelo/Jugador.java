package org.modelo;

import org.modelo.barco.*;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.GeneradorDeMisiles;
import org.modelo.misil.ListaMisiles;
import java.util.ArrayList;

public class Jugador implements Entidad{
	private static Jugador miJugador;
	private Tablero tableroJugador;
	private ListaBarcos listaBarcosJ;
	private ListaMisiles listaMisilesJ;

	public Jugador() {
		this.tableroJugador=new Tablero(false);
		this.listaBarcosJ=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisilesJ=new GeneradorDeMisiles().generarListaMisiles();
	}
	
	public static Jugador getInstance() {
		if(miJugador == null) miJugador = new Jugador();
		return miJugador;
	}

	public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws Exception {
		Barco barco = listaBarcosJ.obtenerBarcoNoColocado(pTipoBarco);

		// Si hay un barco disponible comprobamos si se puede colocar en la posicion
		if(barco != null){
			// Si se puede colocar, lo colocamos y actualizamos el estado del barco
			if(tableroJugador.sePuedeColocar(pPos,pOrientacion,barco)){
				tableroJugador.colocarBarco(pPos,pOrientacion,barco);
				barco.actualizarBarcoColocado();

			}else{
				throw new Exception("ERROR: No se puede colocar en esa posición el barco");
			}

		}else{
			throw new Exception("ERROR: No esta disponible el barco");
		}

	}

	public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo){
		Barco aux=null;
		int cont=0;
		boolean enc=false;
		while(cont<this.listaBarcosJ.size() && !enc){
			if(this.listaBarcosJ.obtenerBarcoEnPos(cont).esBarcoId(pId)){
				enc=true;
				aux=this.listaBarcosJ.obtenerBarcoEnPos(cont);
				aux.recibirDisparoBarco(pTipo, casillaPos, pEnemigo);
			}
			cont++;
		}
	}

	public void realizarDisparo(ETipoMisil pTipo, int pPos) {
		// Comprobamos si el misil esta disponible
		if (listaMisilesJ.sePuedeDisparar(pTipo)) {
				ArrayList<Integer> posicionesDisparo = listaMisilesJ.obtAreaMisil(pTipo, pPos, 10);
				System.out.println("JUGADOR -> disparando: " + posicionesDisparo.toString());
				Enemigo.getInstance().recibirDisparo(pTipo, posicionesDisparo);
		}
	}

	public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo){
		tableroJugador.actualizarCasillasDisparo(pTipo, posicionesDisparo);
	}

	public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado){
		tableroJugador.actualizarEstadoCasilla(pCasilla, pEstado);
	}

	public boolean estanTodosBarcosColocados() {
		return listaBarcosJ.estanTodosBarcosColocados();
	}

	public boolean hayBarcosSinHundir(){
		return listaBarcosJ.hayBarcosSinHundir();
	}

	public EEstadoCasilla getEstadoCasilla(int pPos){
		return tableroJugador.getEstadoCasilla(pPos);
	}

	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- JUGADOR ---------------------------");
		listaBarcosJ.imprimirBarcos();
	}
}