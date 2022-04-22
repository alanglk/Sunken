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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Enemigo implements Entidad{
	private Tablero tablero;
	private ListaBarcos listaBarcos;
	private ListaMisiles listaMisiles;
	private Radar radar;
	private ArrayList<Integer> listaCasillasAReventar;
	private Queue<Integer> listaCasillasImportantes;

	private int onetap;
	private int IA;

	private int numEscudos = 1;

	public Enemigo(){
		this.tablero=new Tablero(true);
		this.listaBarcos=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisiles=new GeneradorDeMisiles().generarListaMisiles();
		this.listaCasillasAReventar=new ArrayList<Integer>();
		this.listaCasillasImportantes=(Queue)new LinkedList<Integer>();
		this.onetap=-1;
		this.IA=-1;
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
			/*int r = new Random().nextInt(2);
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
		int pos;
		boolean disparado;
		disparado=false;
		if (this.onetap != -1) {
			System.out.println("ONETAP INCOMING");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
			posicionesDisparo.add(this.onetap-102);
			System.out.println(listaMisiles.sePuedeDisparar(ETipoMisil.BOMBAONETAP));
			if (listaMisiles.sePuedeDisparar(ETipoMisil.BOMBAONETAP)) {
				posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBAONETAP, onetap-102, 10);
				ListaJugadores.getInstance().getEntidad(0).recibirDisparo(ETipoMisil.BOMBAONETAP, posicionesDisparo);
				this.IA = -1;
				disparado=true;
			} else {
				this.listaCasillasImportantes.add(onetap-102);
				this.IA = -1;
			}
			this.onetap = -1;

		}

		if (this.listaCasillasAReventar.size() != 0) {//SI COLA DE MEMORIA NO VACIA HACER IA
			System.out.println("IA");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();

			// ########INTENTO ARRIBA##############
			if (this.listaCasillasAReventar.get(0) != -1) {//PRIMERO MIRA SI PUEDE DISPARAR ARRIBA
				posicionesDisparo.add(this.listaCasillasAReventar.get(0));
				pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				if (pos == -1) {//SI IMPACTA EN NO BARCO ELIMINAR CAMINO DE ARRIBA
					this.listaCasillasAReventar.set(0, -1);
				} else if (pos != 101 && pos < 101) {//SI IMPACTA ARRIBA ELIMINAR EN HORIZONTAL YA QUE SOLO PUEDE SER VERTICAL
					this.listaCasillasAReventar.set(2, -1);
					this.listaCasillasAReventar.set(3, -1);
					if (((this.listaCasillasAReventar.get(0) / 10 - (this.listaCasillasAReventar.get(0) - 10) / 10) == 1) && (this.listaCasillasAReventar.get(0) - 10) > -1) {//SI IMPACTO ARRIBA SEGUIR MIRANDO UNO MAS ARRIBA
						if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(listaCasillasAReventar.get(0) - 10).equals(EEstadoCasilla.AGUADISPARO)) {
							listaCasillasAReventar.set(0, this.listaCasillasAReventar.get(0) - 10);
						} else {//SI NO ELIMINAR ARRIBA
							this.listaCasillasAReventar.set(0, -1);
						}
					} else {//SI NO ELIMINAR ESTA POSIBILIDAD
						this.listaCasillasAReventar.set(0, -1);
					}
				} else {
					this.listaCasillasAReventar = new ArrayList<Integer>();
				}
			}
			// ########INTENTO ABAJO##############
			else if (this.listaCasillasAReventar.get(1) != -1) {
				posicionesDisparo.add(this.listaCasillasAReventar.get(1));
				pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				if (pos == -1) {
					this.listaCasillasAReventar.set(1, -1);
				} else if (pos != 101 || pos > 101) {
					this.listaCasillasAReventar.set(2, -1);
					this.listaCasillasAReventar.set(3, -1);
					if (((this.listaCasillasAReventar.get(1) / 10 - (this.listaCasillasAReventar.get(1) + 10) / 10) == -1) && (this.listaCasillasAReventar.get(1) + 10) < 100) {
						if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(listaCasillasAReventar.get(1) + 10).equals(EEstadoCasilla.AGUADISPARO)) {
							listaCasillasAReventar.set(1, this.listaCasillasAReventar.get(1) + 10);
						} else {
							this.listaCasillasAReventar.set(1, -1);
						}
					} else {
						this.listaCasillasAReventar.set(1, -1);
					}
				} else {
					this.listaCasillasAReventar = new ArrayList<Integer>();
				}
			}
			// ########INTENTO IZQUIERDA##############
			else if (this.listaCasillasAReventar.get(2) != -1) {
				posicionesDisparo.add(this.listaCasillasAReventar.get(2));
				pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				if (pos == -1) {
					this.listaCasillasAReventar.set(2, -1);
				} else if (pos != 101 || pos > 101) {

					this.listaCasillasAReventar.set(0, -1);
					this.listaCasillasAReventar.set(1, -1);
					if (((this.listaCasillasAReventar.get(2) - 1) / 10 == this.listaCasillasAReventar.get(2) / 10) && (this.listaCasillasAReventar.get(2) - 1) > -1) {
						if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(listaCasillasAReventar.get(2) - 1).equals(EEstadoCasilla.AGUADISPARO)) {
							listaCasillasAReventar.set(2, this.listaCasillasAReventar.get(2) - 1);
						} else {
							this.listaCasillasAReventar.set(2, -1);
						}
					} else {
						this.listaCasillasAReventar.set(2, -1);
					}

				} else {
					this.listaCasillasAReventar = new ArrayList<Integer>();
				}
			}
			// ########INTENTO DERECHA##############
			else if (this.listaCasillasAReventar.get(3) != -1) {
				posicionesDisparo.add(this.listaCasillasAReventar.get(3));
				pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				if (pos == -1) {
					this.listaCasillasAReventar.set(3, -1);
				} else if (pos != 101 || pos > 101) {
					this.listaCasillasAReventar.set(0, -1);
					this.listaCasillasAReventar.set(1, -1);
					if (((this.listaCasillasAReventar.get(3) + 1) / 10 == this.listaCasillasAReventar.get(3) / 10)) {
						if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(listaCasillasAReventar.get(3) + 1).equals(EEstadoCasilla.AGUADISPARO)) {
							listaCasillasAReventar.set(3, this.listaCasillasAReventar.get(3) + 1);
						} else {
							this.listaCasillasAReventar.set(3, -1);
						}
					} else {
						this.listaCasillasAReventar.set(3, -1);
					}
				} else {
					this.listaCasillasAReventar = new ArrayList<Integer>();
				}
			} else {
				this.listaCasillasAReventar = new ArrayList<Integer>();
			}

		} else if (listaMisiles.sePuedeDisparar(tipo)&&!disparado) {
			boolean enc = false;
			if (IA > -1 && IA < 202) {
				System.out.println("IA one tap" + (IA - 102));
				ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, IA - 102, 10);
				int posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				posicion = posicion;
				onetap = posicion - 102;
				enc = true;
				this.onetap = this.IA;

			} else if (this.onetap == -1) {
				int posicion=0;
				ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, obtPosDisparo(), 10);
				System.out.println("ENEMIGO -> disparando: " + posicionesDisparo.toString());
				boolean posDisponible=false;
				while((this.listaCasillasImportantes.size()!=0)&&!posDisponible) {
					if (this.getEstadoCasilla(this.listaCasillasImportantes.peek()).equals(EEstadoCasilla.BARCOHUNDIDO)) {
						this.listaCasillasImportantes.remove();
					} else {

						posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, this.listaCasillasImportantes.remove(), 10);
						posDisponible=true;
						posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
					}
				}
				if(!posDisponible){
					posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				}

				System.out.println(posicion);
				if (posicion != -1 && posicion > 102) {
					System.out.println("onetap" + posicion);
					if (posicion > 100) {
						IA = posicion;
						enc = true;
						System.out.println(enc);
					}
				}else if (posicion>-1&&posicion<101) {
						if ((((posicion / 10 - (posicion - 10) / 10) == 1) && (posicion - 10) > -1) && !enc) {
							if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(posicion - 10).equals(EEstadoCasilla.AGUADISPARO)) {
								listaCasillasAReventar.add(posicion - 10);
							} else {
								listaCasillasAReventar.add(-1);
							}
						} else {
							listaCasillasAReventar.add(-1);
						}

						if ((((posicion / 10 - (posicion + 10) / 10) == -1) && (posicion + 10) < 100) && !enc) {

							if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(posicion + 10).equals(EEstadoCasilla.AGUADISPARO)) {
								listaCasillasAReventar.add(posicion + 10);
							} else {
								listaCasillasAReventar.add(-1);
							}
						} else {
							listaCasillasAReventar.add(-1);
						}
						if ((posicion - 1) / 10 == posicion / 10 && (posicion - 1 > -1) && !enc) {
							if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(posicion - 1).equals(EEstadoCasilla.AGUADISPARO)) {
								listaCasillasAReventar.add(posicion - 1);
							} else {
								listaCasillasAReventar.add(-1);
							}

						} else {
							listaCasillasAReventar.add(-1);
						}
						if (((posicion + 1) / 10 == posicion / 10) && (posicion + 1 < 100)&& !enc) {

							if (!ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(posicion + 1).equals(EEstadoCasilla.AGUADISPARO)) {
								listaCasillasAReventar.add(posicion + 1);
							} else {
								listaCasillasAReventar.add(-1);
							}
						} else {
							listaCasillasAReventar.add(-1);
						}
					}
				}
			}
		}


	@Override
	public int recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo) {
		return(tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo));
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
