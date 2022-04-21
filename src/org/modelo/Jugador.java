package org.modelo;

import org.modelo.barco.*;
import org.modelo.excepciones.ImposibleColocarBarcoException;
import org.modelo.excepciones.ImposibleColocarEscudoException;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.excepciones.ImposibleUsarRadarException;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.GeneradorDeMisiles;
import org.modelo.misil.ListaMisiles;
import org.modelo.radar.Radar;
import org.modelo.radar.Radar3x3;

import java.util.ArrayList;

public class Jugador implements Entidad{
	private Tablero tablero;
	private ListaBarcos listaBarcos;
	private ListaMisiles listaMisiles;
	private Radar radar;

	private int numEscudos = 3;

	public Jugador() {
		this.tablero=new Tablero(false);
		this.listaBarcos=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisiles=new GeneradorDeMisiles().generarListaMisiles();
	}

	private boolean posValidaDisparo(int pos){
		boolean valida = true;
		if(pos< 0||100<=pos)valida =false;
		if(valida &&ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pos).equals(EEstadoCasilla.HUNDIDO))valida =false;
		if(valida &&ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pos).equals(EEstadoCasilla.AGUADISPARO))valida =false;
		if(valida &&ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pos).equals(EEstadoCasilla.BARCOHUNDIDO))valida =false;

		return valida;
	}

	// BARCOS --------
	@Override
	public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws ImposibleColocarBarcoException {
		Barco barco = listaBarcos.obtenerBarcoNoColocado(pTipoBarco);

		// Si hay un barco disponible comprobamos si se puede colocar en la posicion
		if(barco != null){
			// Si se puede colocar, lo colocamos y actualizamos el estado del barco
			if(tablero.sePuedeColocar(pPos,pOrientacion,barco)){
				tablero.colocarBarco(pPos,pOrientacion,barco);
				barco.actualizarBarcoColocado();

			}else{
				throw new ImposibleColocarBarcoException();
			}

		}else{
			throw new ImposibleColocarBarcoException();
		}
	}

	@Override
	public void colocarBarco() {}

	@Override
	public boolean estanTodosBarcosColocados() {
		return listaBarcos.estanTodosBarcosColocados();
	}

	@Override
	public boolean hayBarcosSinHundir() {
		return listaBarcos.hayBarcosSinHundir();
	}

	@Override
	public void imprimirBarcos() {
		System.out.println("--------------------------- JUGADOR ---------------------------");
		listaBarcos.imprimirBarcos();
	}

	@Override
	public Integer obtenerNumBarcosNoColocados(ETipoBarco tipoBarco) {
		return listaBarcos.obtenerNumBarcosNoColocados(tipoBarco);
	}

	// DISPAROS --------
	@Override
	public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo) {
		Barco aux=null;
		int cont=0;
		boolean enc=false;
		while(cont<this.listaBarcos.size() && !enc){
			if(this.listaBarcos.obtenerBarcoEnPos(cont).esBarcoId(pId)){
				enc=true;
				aux=this.listaBarcos.obtenerBarcoEnPos(cont);
				aux.recibirDisparoBarco(pTipo, casillaPos, pEnemigo);
			}
			cont++;
		}
	}

	@Override
	public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException {
		// Comprobamos si el misil esta disponible
		if (posValidaDisparo(pPos) && listaMisiles.sePuedeDisparar(pTipo)) {
			ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(pTipo, pPos, 10);
			System.out.println(" -> disparando: " + posicionesDisparo.toString());
			ListaJugadores.getInstance().getEntidad(1).recibirDisparo(pTipo, posicionesDisparo);
		}else{
			throw new ImposibleDispararException();
		}
	}

	@Override
	public void realizarDisparo() {}

	@Override
	public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo) {
		tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo);
	}

	@Override
	public Integer obtenerNumMisilesDisponibles(ETipoMisil tipoMisil) {
		return listaMisiles.obtenerNumMisilesDisponibles(tipoMisil);
	}

	// RADAR --------
	@Override
	public void usarRadar() throws ImposibleUsarRadarException {
		if(radar.sePuedeUtilizar()){
			ListaJugadores.getInstance().getEntidad(1).revelarCasillasRadar(radar.obtenerPosicionesReveladas(10));
		}else{
			throw new ImposibleUsarRadarException();
		}
	}

	@Override
	public void revelarCasillasRadar(ArrayList<Integer> posciones) {
		tablero.revelarCasillasRadar(posciones);
	}

	@Override
	public void recolocarRadar() {
		if(radar == null) radar = new Radar3x3();
		radar.cambiarPosicionRadar(false);
	}

	@Override
	public void colocarRadarEnCasilla(int posRadarAnt, int posRadarAct) {
		tablero.quitarRadarEnCasilla(posRadarAnt);
		tablero.colocarRadarEnCasilla(posRadarAct);
	}

	@Override
	public Integer obtenerNumUsosRadar() {
		if(radar == null)
			return -1;
		return radar.obtenerNumUsos();
	}

	// CASILLAS --------
	@Override
	public void actualizarContorno(ArrayList<Integer> pLista) {
		this.tablero.actualizarContorno(pLista);
	}

	@Override
	public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado) {
		tablero.actualizarEstadoCasilla(pCasilla, pEstado);
	}

	@Override
	public EEstadoCasilla getEstadoCasilla(int pPos) {
		return tablero.getEstadoCasilla(pPos);
	}

	public void actualizarEstadoCasillaOneTap(int pCasilla, EEstadoCasilla pEstado) {
		tablero.actualizarEstadoCasillaOneTap(pCasilla, pEstado);
	}

	// ESCUDOS --------
	@Override
	public void colocarEscudoBarco(int pCasilla) throws ImposibleColocarEscudoException {
		if(numEscudos > 0){
			Integer idBarco = tablero.obtenerIdBarcoCasilla(pCasilla);

			if(idBarco == null){
				throw new ImposibleColocarEscudoException();
			} else{
				Barco barco = listaBarcos.obtenerBarco(idBarco);
				if(barco == null){
					throw new ImposibleColocarEscudoException();
				} else{
					barco.setEscudo(new EscudoBarco());
					barco.actualizarCasillasEscudo(false);
					numEscudos--;
				}
			}

		}
		else
			throw new ImposibleColocarEscudoException();
	}

	@Override
	public Integer obtenerNumEscudos() {
		return numEscudos;
	}
}
