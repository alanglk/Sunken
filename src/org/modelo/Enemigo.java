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

public class Enemigo implements Entidad {
	private Tablero tablero;
	private ListaBarcos listaBarcos;
	private ListaMisiles listaMisiles;
	private Radar radar;
	private ArrayList<Integer> listaCasillasAReventar;
	private Queue<Integer> listaCasillasImportantes;
	private int patronMemoria;

	private int onetap;
	private int IA;
	private boolean radarRecolocado = false;

	private int numEscudos = 3;

	public Enemigo() {
		this.tablero = new Tablero(true);
		this.listaBarcos = new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisiles = new GeneradorDeMisiles().generarListaMisiles();
		this.listaCasillasAReventar = new ArrayList<Integer>();
		this.listaCasillasImportantes = (Queue) new LinkedList<Integer>();
		this.onetap = -1;
		this.IA = -1;
		this.patronMemoria=-1;
	}

	private int obtPosBarco() {
		return new Random().nextInt(100);
	}

	private EOrientaconBarco obtOrientacionBarco() {
		//Si random es 0 la orientacion es horizontal y si es 1 vertical
		Random r = new Random();
		int queOrientacion = r.nextInt(2);

		EOrientaconBarco orientacion;
		if (queOrientacion == 0) {
			orientacion = EOrientaconBarco.ESTE;
		} else {
			orientacion = EOrientaconBarco.SUR;
		}

		return orientacion;
	}

	private int obtPosDisparo() {
		Random r = new Random();
		int pos = r.nextInt(100);

		while (!posValidaDisparo(pos))
			pos = r.nextInt(100);

		return pos;
	}

	private boolean posValidaDisparo(int pos) {
		boolean valida = true;
		if (pos < 0 || 100 <= pos) valida = false;
		if (valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.HUNDIDO))
			valida = false;
		if (valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.AGUADISPARO))
			valida = false;
		if (valida && ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pos).equals(EEstadoCasilla.BARCOHUNDIDO))
			valida = false;

		return valida;
	}

	// BARCOS --------
	@Override
	public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws ImposibleColocarBarcoException {
	}

	@Override
	public void colocarBarco() {
		int i = 0;
		Barco b1;
		while ((b1 = listaBarcos.obtenerBarcoEnPos(i)) != null) {
			int posicion = this.obtPosBarco();
			EOrientaconBarco orientacion = this.obtOrientacionBarco();

			if (this.tablero.sePuedeColocar(posicion, orientacion, b1)) {
				this.tablero.colocarBarco(posicion, orientacion, b1);
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

	@Override
	public Integer obtenerNumBarcosNoHundidos(ETipoBarco tipoBarco) {
		return listaBarcos.obtenerNumBarcosNoHundidos(tipoBarco);
	}


	//REALIZAR ACCION -------
	@Override
	public boolean realizarAccion(boolean juegoTerminado) throws ImposibleUsarRadarException {

		if (!ListaJugadores.getInstance().getEntidad(1).hayBarcosSinHundir() && !juegoTerminado) {
			juegoTerminado = true;
			System.out.println("GANA EL JUGADOR");
		} else {
			Enemigo enemigo = (Enemigo) ListaJugadores.getInstance().getEntidad(1);
			//Creamos un booleano que dictamine qu� va a hacer el enemigo
			boolean accionRealizada = false;
			if((this.listaCasillasAReventar.size()!=0||this.listaCasillasImportantes.size()!=0)){
				accionRealizada=true;
				enemigo.realizarDisparo();
			}
			while (!accionRealizada) {
				float r = new Random().nextFloat();
				System.out.println(r);
				if ((r < 0.7f) || (this.radar != null && !this.radar.sePuedeUtilizar())) {
					enemigo.realizarDisparo();
					accionRealizada = true;
				} else if (r >= 0.7f && r < 0.8f) {
					enemigo.recolocarRadar();
					accionRealizada = true;
				} else if (radarRecolocado) {
					try {
						enemigo.usarRadar();
						accionRealizada = true;
					} catch (ImposibleUsarRadarException e) {
					}
				}
			}
		}
		return juegoTerminado;
	}

	// DISPAROS --------
	@Override
	public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo) {
		Barco aux = null;
		int cont = 0;
		boolean enc = false;
		while (cont < this.listaBarcos.size() && !enc) {
			if (this.listaBarcos.obtenerBarcoEnPos(cont).esBarcoId(pId)) {
				enc = true;
				aux = this.listaBarcos.obtenerBarcoEnPos(cont);
				aux.recibirDisparoBarco(pTipo, casillaPos, pEnemigo);
			}
			cont++;
		}
	}

	@Override
	public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException {
	}

	@Override
	public void realizarDisparo() {
		// Comprobamos si el misil esta disponible
		ETipoMisil tipo = ETipoMisil.BOMBA;
		int pos;
		boolean disparado;
		disparado = false;
		if (this.onetap != -1) {//SI SE HA REVENTADO ESCUDO REALIZAR EL USO DE BOMBAONETAP SI NO DESTRUCCION NORMAL
			System.out.println("ONETAP INCOMING");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
			posicionesDisparo.add(this.onetap - 102);
			System.out.println(listaMisiles.sePuedeDisparar(ETipoMisil.BOMBAONETAP));
			if (listaMisiles.sePuedeDisparar(ETipoMisil.BOMBAONETAP)) {//SI HAY ONETAP SE USA SI NO SE HACE PROCEDIMIENTO NORMAL
				posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBAONETAP, onetap - 102, 10);
				ListaJugadores.getInstance().getEntidad(0).recibirDisparo(ETipoMisil.BOMBAONETAP, posicionesDisparo);
				this.IA = -1;
				disparado = true;
			} else {
				this.listaCasillasImportantes.add(onetap - 102);
				this.IA = -1;
			}
			this.onetap = -1;

		}

		if (this.listaCasillasAReventar.size() != 0) {//SI COLA DE MEMORIA NO VACIA HACER IA
			int patron=-1;
			if(patronMemoria<1) {//GENERAR UN PATRON SI NO HAY UNO EN USO, SI HAY USAR EL GUARDADO EN MEMORIA
				 Random r = new Random();
				 while(patron<1) {
					 patron = r.nextInt(5);
				 }
				 patronMemoria=patron;

			}
			else{
				patron=this.patronMemoria;
			}
			System.out.println("PATRON = "+patron);
			if (patron ==1) {
				//########PATRON 1#########
				if(!this.guardarPosArriba()){
					if (!this.guardarPosAbajo()){
						if (!this.guardarDerecha()) {
							if(!this.guardarPosIzquierda()){
								this.listaCasillasAReventar = new ArrayList<Integer>();
								patronMemoria=-1;
							}
						}
					}
				}
			}
			else if (patron ==2) {
				//########PATRON 2#########
				if(!this.guardarPosAbajo()){
					if (!this.guardarPosArriba()){
						if (!this.guardarPosIzquierda()) {
							if(!this.guardarDerecha()){
								this.listaCasillasAReventar = new ArrayList<Integer>();
								patronMemoria=-1;
							}
						}
					}
				}
			}
			else if (patron ==3) {
				//########PATRON 3#########
				if(!this.guardarPosIzquierda()){
					if (!this.guardarDerecha()){
						if (!this.guardarPosArriba()) {
							if(!this.guardarPosAbajo()){
								this.listaCasillasAReventar = new ArrayList<Integer>();
								patronMemoria=-1;
							}
						}
					}
				}
			}
			else if (patron == 4) {
				//########PATRON 4#########
				if(!this.guardarDerecha()){
					if (!this.guardarPosIzquierda()){
						if (!this.guardarPosAbajo()) {
							if(!this.guardarPosArriba()){
								this.listaCasillasAReventar = new ArrayList<Integer>();
								patronMemoria=-1;

							}
						}
					}
				}
			}
		} else if (listaMisiles.sePuedeDisparar(tipo) && !disparado) {
			boolean enc = false;
			if (IA > -1 && IA < 202) {//SI ESCUDO DETECTADO SE DESTRUYE ESCUDO
				System.out.println("IA one tap" + (IA - 102));
				ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, IA - 102, 10);
				int posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				posicion = posicion;
				onetap = posicion - 102;
				enc = true;
				this.onetap = this.IA;

			} else if (this.onetap == -1) {//SI NO DISPARO ALEATORIO
				int posicion = 0;
				ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, obtPosDisparo(), 10);
				System.out.println("ENEMIGO -> disparando: " + posicionesDisparo.toString());
				boolean posDisponible = false;
				while ((this.listaCasillasImportantes.size() != 0) && !posDisponible) {//SI HAY CASILLAS CON BARCOS GUARDADO EN MEMORIA SE DESTRUYEN
					if (this.getEstadoCasilla(this.listaCasillasImportantes.peek()).equals(EEstadoCasilla.BARCOHUNDIDO)) {
						this.listaCasillasImportantes.remove();
					} else { //SI NO ALEATORIO

						posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, this.listaCasillasImportantes.remove(), 10);
						posDisponible = true;
						posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
					}
				}
				if (!posDisponible) {
					posicion = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
				}
				//SI POSICION TIENE -1 SIGNIFICA QUE HA DADO EN AGUA SI NO DEVUELVE LA POSICION DEL BARCO DETECTADO
				System.out.println(posicion);
				if (posicion != -1 && posicion > 102) {//SI NO ES -1 Y ES MAYOR QUE 101 SIGNIFICA QUE HA DETECTADO ESCUDO EN POSICION-101(SE SUMA ESO PARA SABER QUE ES ESCUDO)
					System.out.println("onetap" + posicion);
					if (posicion > 100) {
						IA = posicion;
						enc = true;
						System.out.println(enc);
					}
				} else if (posicion > -1 && posicion < 101) {//SI ES POSIBLE SE MIRA LAS POSICIONES ARRIBA ABAJO IZQUIERDA DERECHA SI ESTAN DENTRO DE LOS BORDES O SI NO HAN SIDO IMPACTADAS, SI ES ASI SE AÑADEN A MEMORIA LISTAARREVENTAR, SI NO SE AÑADE -1 EN ESA POSICION
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
					if (((posicion + 1) / 10 == posicion / 10) && (posicion + 1 < 100) && !enc) {

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
		return (tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo));
	}

	@Override
	public Integer obtenerNumMisilesDisponibles(ETipoMisil tipoMisil) {
		return null;
	}

	// RADAR --------
	@Override
	public void usarRadar() throws ImposibleUsarRadarException {
		if (radar != null) {
			if (radar.sePuedeUtilizar()) {
				System.out.println("Enemigo usa radar");
				ArrayList<Integer> listaRadar = radar.obtenerPosicionesReveladas(10, true);
				for (Integer x : listaRadar) {
					System.out.println("pos test" + x);
					if ((ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(x).equals(EEstadoCasilla.BARCO)) || (ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(x).equals(EEstadoCasilla.ESCUDO))) {
						this.listaCasillasImportantes.add(x);
						System.out.println(x);
						this.radarRecolocado = false;
					}
				}

			} else {
				throw new ImposibleUsarRadarException();
			}
		} else {
			throw new ImposibleUsarRadarException();
		}
	}


	@Override
	public void revelarCasillasRadar(ArrayList<Integer> posciones) {
		tablero.revelarCasillasRadar(posciones);
	}

	@Override
	public void recolocarRadar() {
		if (radar == null) radar = new Radar3x3();
		radar.cambiarPosicionRadar(true);
		radarRecolocado = true;
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
	public void colocarEscudoBarco(int pCasilla) throws ImposibleColocarEscudoException {
	}

	@Override
	public void colocarEscudoBarco() {
		while (numEscudos > 0 && !listaBarcos.todosTienenEscudo()) {
			Barco barco = null;
			while (barco == null || barco.tieneEscudo()) {
				barco = listaBarcos.obtenerAleatorioParaEscudo();
			}

			barco.setEscudo(new EscudoBarco());
			numEscudos--;
		}

	}

	@Override
	public Integer obtenerNumEscudos() {
		return numEscudos;
	}

	private boolean guardarPosArriba() {
		boolean disparo=false;//BOOLEANO PARA SABER SI SE HA PODIDO DISPARAR ARRIBA
		if (this.listaCasillasAReventar.get(0) != -1) {//SI HAY POSICION ARRIBA
			disparo=true;
			int pos;
			ETipoMisil tipo = ETipoMisil.BOMBA;
			System.out.println("IA");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
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
				patronMemoria=-1;
			}

		}
		return(disparo);
	}

	private boolean guardarPosAbajo() {//IGUAL QUE ARRIBA PERO ABAJO
		boolean disparo=false;
		if (this.listaCasillasAReventar.get(1) != -1) {
			disparo=true;
			int pos;
			ETipoMisil tipo = ETipoMisil.BOMBA;
			System.out.println("IA");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
			posicionesDisparo.add(this.listaCasillasAReventar.get(1));
			pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
			if (pos == -1) {
				this.listaCasillasAReventar.set(1, -1);
			} else if (pos != 101 || pos < 101) {
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
				patronMemoria=-1;
			}

		}
		return(disparo);
	}

	private boolean guardarPosIzquierda() {//IGUAL QUE LOS ANTERIORES PERO ELIMINA VERTICAL SI IMPACTA A LA IZQUIERDA
		boolean disparo=false;
		if (this.listaCasillasAReventar.get(2) != -1) {
			disparo=true;
			int pos;
			ETipoMisil tipo = ETipoMisil.BOMBA;
			System.out.println("IA");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
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
				patronMemoria=-1;
			}

		}
		return(disparo);

	}

	private boolean guardarDerecha() {//IGUAL QUE EL ANTERIOR PERO CON LA DERECHA
		boolean disparo=false;
		if (this.listaCasillasAReventar.get(3) != -1) {
			int pos;
			ETipoMisil tipo = ETipoMisil.BOMBA;
			System.out.println("IA");
			ArrayList<Integer> posicionesDisparo = new ArrayList<Integer>();
			posicionesDisparo.add(this.listaCasillasAReventar.get(3));
			pos = ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
			disparo=true;
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
				patronMemoria=-1;
			}
		}
		return(disparo);
	}

}


