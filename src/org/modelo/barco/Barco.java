package org.modelo.barco;

import org.modelo.EEstadoCasilla;
import org.modelo.Enemigo;
import org.modelo.Jugador;
import org.modelo.ListaJugadores;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Barco {
	private ETipoBarco tipo = null;

	private Integer[] posicionesBarco;
	private ArrayList<Integer> posicionesBarcoDestr;
	private int longitud;
	private int id;

	private EscudoBarco escudo = null;

	private boolean colocado;

	public Barco(int pLongitud, ETipoBarco pTipo,int pId) {
		posicionesBarco = new Integer[]{-1, -1, -1, -1};
		posicionesBarcoDestr = new ArrayList<Integer>();

		this.tipo = pTipo;
		this.longitud = pLongitud;
		this.colocado = false;
		this.id=pId;
	}
	public boolean esBarcoId(int pId){
		return(pId==this.id);
	}
	public int getLongitud(){
		return longitud;
	}

	public int getId(){return this.id;}

	public EEstadoCasilla getEstadoCasillas(){
		EEstadoCasilla estado = null;
		if(escudo != null)
			estado = escudo.obtenerEstadoEscudo();
		else
			estado = EEstadoCasilla.BARCO;

		return estado;
	}

	public boolean tieneEscudo(){
		return escudo != null;
	}

	public void anadirCasilla(int pPos){
		boolean term = false;
		int i = 0;

		while(i < longitud && !term){
			if(posicionesBarco[i] == -1){
				posicionesBarco[i] = pPos;
				term = true;
			}else{
				i++;
			}
		}

		if(i >= longitud)
			System.out.println("ERROR: La longitud del barco no se corresponde con el numero de casillas.");
	}

	private void eliminarCasilla(int pCasilla){
		boolean enc = false;
		int i = 0;
		while(i < longitud && !enc ){
			if (posicionesBarco[i] == pCasilla) {
				posicionesBarco[i] = -1;
				enc = true;
			}
			i++;
		}
		i=0;
		enc=false;
		if(!this.posicionesBarcoDestr.contains(pCasilla)){
			this.posicionesBarcoDestr.add(pCasilla);
		}

		System.out.println("----------");
		System.out.println("Casilla " + pCasilla + " eliminada? -> encontrada: " + enc);
		imprimirPosiciones();

	}

	public void recibirDisparoBarco(ETipoMisil pTipo, int pCasilla, boolean pEnemigo){
		boolean seRecibeDano = false;
		if(escudo != null){
			for (int i = 0; i < posicionesBarco.length; i++){
				System.out.print("/// CASILLA " + posicionesBarco[i] + " se actualiza con estado: " + getEstadoCasillas());
				if (!pEnemigo)
					ListaJugadores.getInstance().getEntidad(0).actualizarEstadoCasilla(posicionesBarco[i], getEstadoCasillas());
				else
					ListaJugadores.getInstance().getEntidad(1).actualizarEstadoCasilla(posicionesBarco[i], getEstadoCasillas());
			}
		}

		if(escudo == null) seRecibeDano = true;
		else seRecibeDano = escudo.recibirDisparoYSerompeElEscudo(pTipo);

		if(seRecibeDano) {
			if (pTipo == ETipoMisil.BOMBA) {
				eliminarCasilla(pCasilla);
				if (!pEnemigo)
					ListaJugadores.getInstance().getEntidad(0).actualizarEstadoCasilla(pCasilla, EEstadoCasilla.HUNDIDO);
				else
					ListaJugadores.getInstance().getEntidad(1).actualizarEstadoCasilla(pCasilla, EEstadoCasilla.HUNDIDO);

			}

			if (pTipo == ETipoMisil.BOMBAONETAP) {
				boolean enc=false;
				for (int i = 0; i < posicionesBarco.length; i++) {
					enc=false;
					if (posicionesBarco[i] == -1) {
						enc = true;
					}
					if (!enc) {
						int pos = posicionesBarco[i];
						eliminarCasilla(pos);
						if (!pEnemigo)
							ListaJugadores.getInstance().getEntidad(0).actualizarEstadoCasillaOneTap(pos, EEstadoCasilla.HUNDIDO);
						else
							ListaJugadores.getInstance().getEntidad(1).actualizarEstadoCasillaOneTap(pos, EEstadoCasilla.HUNDIDO);
					}
				}
			}

		}

		//SE DESVELAN LAS CASILLAS DE ALREDEDOR

		if(estaHundido()) {
			System.out.println("HUNDIDO");
			if(!pEnemigo)
				ListaJugadores.getInstance().getEntidad(0).actualizarContorno(posicionesBarcoDestr);
			else
				ListaJugadores.getInstance().getEntidad(1).actualizarContorno(posicionesBarcoDestr);

		}

	}

	public void actualizarBarcoColocado(){
		this.colocado = true;
	}

	public boolean estaColocado(){
		return this.colocado;
	}

	public boolean esTipo(ETipoBarco tipoBarco) {
		return tipo.equals(tipoBarco);
	}

	public boolean estaHundido(){
		boolean hundido = true;
		int i = 0;

		while(i < longitud && hundido){
			if(posicionesBarco[i] != -1)
				hundido = false;

			i++;
		}

		return hundido;
	}

	public void setEscudo(EscudoBarco pEscudo){
		escudo = pEscudo;
	}

	// TODO: Eliminar este metodo
	public void imprimirPosiciones(){
		System.out.println("Posiciones del barco " + id + " (" + tipo + ") de longitud " + longitud + ": ");
		for(int i = 0; i < longitud; i++){
			System.out.print("" + posicionesBarco[i] + ", ");
		}
		System.out.println("");
	}

	// Se usa cuando se pulsa un barco del jugador para ponerle un escudo. El enemigo añade los escudos antes de colocar los barcos
	public void actualizarCasillasEscudo(boolean pEnemigo){
		if(escudo != null){
			for (int i = 0; i < posicionesBarco.length; i++){
				System.out.print("/// CASILLA " + posicionesBarco[i] + " se actualiza con estado: " + getEstadoCasillas());
				if (!pEnemigo)
					ListaJugadores.getInstance().getEntidad(0).actualizarEstadoCasilla(posicionesBarco[i], getEstadoCasillas());
				else
					ListaJugadores.getInstance().getEntidad(1).actualizarEstadoCasilla(posicionesBarco[i], getEstadoCasillas());
			}
		}
	}

	public void repararPos(int pPos, boolean pEnemigo){
		int i=0;
		boolean enc=false;
		while (i<this.longitud&&!enc){
			if(this.posicionesBarcoDestr.get(i) ==pPos){
				enc=true;
			}
			else{
				i++;
			}
		}
		if(enc){
			boolean enc2=false;
			int buscarPosLibre=0;
			while(buscarPosLibre<this.longitud-1&&!enc2){
				if(this.posicionesBarco[buscarPosLibre]==-1){
					enc2=true;
				}
				else{
					buscarPosLibre++;
				}
			}
			if(enc){
				this.posicionesBarco[buscarPosLibre]=pPos;
				if (!pEnemigo) {
					ListaJugadores.getInstance().getEntidad(0).actualizarEstadoCasilla(pPos,EEstadoCasilla.BARCO);
				}
				else{
					ListaJugadores.getInstance().getEntidad(1).actualizarEstadoCasilla(pPos, EEstadoCasilla.BARCO);
				}
			}
			else{
				System.out.println("No es posible repararlo");
			}
		}
		else{
			System.out.println("No es posible repararlo");
		}
	}
}