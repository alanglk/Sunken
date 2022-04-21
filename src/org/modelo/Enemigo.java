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
import java.util.Random;

public class Enemigo implements Entidad{
	private Tablero tablero;
	private ListaBarcos listaBarcos;
	private ListaMisiles listaMisiles;
	private Radar radar;

	private int numEscudos = 1;

	public Enemigo(){
		this.tablero=new Tablero(true);
		this.listaBarcos=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisiles=new GeneradorDeMisiles().generarListaMisiles();
	}

	private int obtPosBarco() {
		return new Random().nextInt(100);
	}

	private EOrientaconBarco obtOrientacionBarco() {
		//Si random es 0 la orientacion es horizontal y si es 1 vertical
		Random r=new Random();
		int queOrientacion=r.nextInt(2);

		EOrientaconBarco orientacion;
		if(queOrientacion==0){
			orientacion = EOrientaconBarco.ESTE;
		} else{
			orientacion = EOrientaconBarco.SUR;
		}

		return orientacion;
	}

	private int obtPosDisparo() {
		Random r=new Random();
		int pos = r.nextInt(100);

		while (!posValidaDisparo(pos))
			pos = r.nextInt(100);

		return pos;
	}

	private boolean posValidaDisparo(int pos){
		boolean valida = true;
		if(pos < 0 || 100 <= pos) valida = false;
		if(valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.HUNDIDO)) valida = false;
		if(valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.AGUADISPARO)) valida = false;
		if(valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.BARCOHUNDIDO)) valida = false;

		return valida;
	}

	// BARCOS --------
	@Override
	public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws ImposibleColocarBarcoException{}

	@Override
	public void colocarBarco() {
		int i = 0; Barco b1;
		while((b1 = listaBarcos.obtenerBarcoEnPos(i)) != null){
			int posicion=this.obtPosBarco();
			EOrientaconBarco orientacion=this.obtOrientacionBarco();

			if(this.tablero.sePuedeColocar(posicion,orientacion,b1)){
				this.tablero.colocarBarco(posicion,orientacion,b1);
				i++;
			}
		}
	}

	@Override
	public boolean estanTodosBarcosColocados() {
		return false;
	}

	@Override
	public boolean hayBarcosSinHundir() {
		return listaBarcos.hayBarcosSinHundir();
	}

	@Override
	public void imprimirBarcos() {
		System.out.println("--------------------------- ENEMIGO ---------------------------");
		listaBarcos.imprimirBarcos();
	}

	@Override
	public Integer obtenerNumBarcosNoColocados(ETipoBarco tipoBarco) {
		return null;
	}
	
	//REALIZAR ACCION -------
	@Override
	public boolean realizarAccion(boolean juegoTerminado) throws ImposibleUsarRadarException {
		if(!ListaJugadores.getInstance().getEntidad(1).hayBarcosSinHundir() && !juegoTerminado){
			juegoTerminado = true;
			System.out.println("GANA EL JUGADOR");
		}else {
			Enemigo enemigo = (Enemigo) ListaJugadores.getInstance().getEntidad(1);
			//Creamos un booleano que dictamine qu� va a hacer el enemigo
			enemigo.realizarDisparo();
			/*
			int r = new Random().nextInt(3);
			if(r == 1) {
				enemigo.realizarDisparo();
			}
			else if(r == 2) {
				enemigo.recolocarRadar();
			}
			else {
				enemigo.usarRadar();
			}*/
		}
		return juegoTerminado;
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
	public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException {}

	@Override
	public void realizarDisparo() {
		// Comprobamos si el misil esta disponible
		ETipoMisil tipo = ETipoMisil.BOMBA;
		if(listaMisiles.sePuedeDisparar(tipo)){
			ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, obtPosDisparo(), 10);
			System.out.println("ENEMIGO -> disparando: " + posicionesDisparo.toString());
			ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
		}
	}

	@Override
	public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo) {
		tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo);
	}

	@Override
	public Integer obtenerNumMisilesDisponibles(ETipoMisil tipoMisil) {
		return null;
	}

	// RADAR --------
	@Override
	public void usarRadar() throws ImposibleUsarRadarException {
		// TODO: Implement this
	}

	@Override
	public void revelarCasillasRadar(ArrayList<Integer> posciones) {
		tablero.revelarCasillasRadar(posciones);
	}

	@Override
	public void recolocarRadar() {
		if(radar == null) radar = new Radar3x3();
		radar.cambiarPosicionRadar(true);
	}

	@Override
	public void colocarRadarEnCasilla(int posRadarAnt, int posRadarAct) {
		tablero.quitarRadarEnCasilla(posRadarAnt);
		tablero.colocarRadarEnCasilla(posRadarAct);
	}

	@Override
	public Integer obtenerNumUsosRadar() {
		return null;
	}

	// CASILLAS --------
	@Override
	public void actualizarContorno(ArrayList<Integer> pLista) {
		tablero.actualizarContorno(pLista);
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
	public void colocarEscudoBarco(int pCasilla) throws ImposibleColocarEscudoException {}

	@Override
	public Integer obtenerNumEscudos() {
		return numEscudos;
	}
}
