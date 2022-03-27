package org.modelo;

import org.modelo.barco.*;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.GeneradorDeMisiles;
import org.modelo.misil.ListaMisiles;
import org.modelo.misil.Misil;

import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {

	private static Jugador miJugador;
	private Tablero tableroJugador;
	private ListaBarcos listaBarcosJ;
	private ListaMisiles listaMisilesJ;

	public Jugador() {
		this.tableroJugador=new Tablero();
		this.listaBarcosJ=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisilesJ=new GeneradorDeMisiles().generarListaMisiles();
	}
	
	public static Jugador getInstance() {
		if(miJugador == null) miJugador = new Jugador();
		return miJugador;
	}

	public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws Exception {
		Barco barco = listaBarcosJ.obtenerBarcoNoColocado(pTipoBarco);
		System.out.println(barco.toString());
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


	public void recibirDisparo(ETipoMisil pMisil, int pPos) {
		ArrayList<Integer> lista= new ArrayList<Integer>();
		if (pMisil.equals(ETipoMisil.BOMBA))
			lista.add(pPos);
			this.tableroJugador.disparoRecibidoJugador(lista);
		}


	public void actualizarCasillaBarco(int casillaPos, int pId){
		int cont=0;
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
	
	public boolean estanTodosBarcosColocados() {
		return listaBarcosJ.estanTodosBarcosColocados();
	}
	
	public boolean misilDisponible(ETipoMisil pMisil) {
		return (listaMisilesJ.sePuedeDisparar(pMisil));
	}
	
	public ListaBarcos obtListaBarcos() {
		return this.listaBarcosJ;
	}

	public EEstadoCasilla getEstadoCasilla(int pPos){
		return tableroJugador.getEstadoCasilla(pPos);
	}
}