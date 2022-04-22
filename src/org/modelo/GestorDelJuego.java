package org.modelo;

import org.controlador.FormularioControlador;
import org.modelo.excepciones.ImposibleColocarBarcoException;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.excepciones.ImposibleUsarRadarException;

import java.util.Observable;
import java.util.Random;

public class GestorDelJuego extends Observable {

	private static GestorDelJuego miGestorDelJuego;
	private boolean colocandoBarcos = true;
	private boolean juegoTerminado = false;

	private GestorDelJuego() {
		// Inicializar el jugador y el enemigo
		ListaJugadores.getInstance();
	}

	public static GestorDelJuego getInstance() {
		if(miGestorDelJuego == null) miGestorDelJuego = new GestorDelJuego();
		return miGestorDelJuego;
	}

	public void iniciarPartida() {
		// Se han colocado todos los barcos del jugador.
		// Marcamos el estado como partida iniciada y decidimos el orden de juego.
		if (ListaJugadores.getInstance().getEntidad(0).estanTodosBarcosColocados()) {
			Enemigo enemigo = (Enemigo) ListaJugadores.getInstance().getEntidad(1);
			enemigo.colocarBarco();
			enemigo.colocarEscudoBarco();

			colocandoBarcos = false;
			ListaJugadores.getInstance().getEntidad(0).imprimirBarcos();
			enemigo.imprimirBarcos();
			System.out.println("\n========== INICIANDO PARTIDA!! ==========");
			System.out.println("/////////////////////////////////////////////");

			// boolean random que indicará si empieza el enemigo o el jugador
			boolean turnoEnemigo = new Random().nextBoolean();

			if (colocandoBarcos && turnoEnemigo)
				enemigo.realizarDisparo();
		}
	}

	public void notificarCasillaPresionada(FormularioControlador pDatos) throws Exception {
		if(colocandoBarcos){
			if(!pDatos.tableroEnemigo){
				// Los datos son del tablero del Jugador.
				if(pDatos.escudo){
					ListaJugadores.getInstance().getEntidad(0).colocarEscudoBarco(pDatos.posicion);
				}else if(pDatos.tipoBarco != null && pDatos.orientacion != null) {
					try{
						ListaJugadores.getInstance().getEntidad(0).colocarBarco(pDatos.posicion, pDatos.tipoBarco, pDatos.orientacion);
					}catch(ImposibleColocarBarcoException e){
						System.err.println("ERROR: No se pudo colocar el barco por una pos invalida o el barco de ese tipo no esta disponible");
					}
				}
			}
			// Si los datos no son del Jugador podemos hacer otra cosa (avisar al usuario o no hacer nada)

		}else if(!colocandoBarcos && !juegoTerminado){
			// Presionamos una casilla para realizar un disparo
			if(pDatos.tableroEnemigo){
				if(pDatos.tipoMisil!=null){
					boolean disparoJugador = false;

					if (!ListaJugadores.getInstance().getEntidad(0).hayBarcosSinHundir()){
						juegoTerminado = true;
						System.out.println("GANA EL ENEMIGO");
					}else{
						try {
							ListaJugadores.getInstance().getEntidad(0).realizarDisparo(pDatos.tipoMisil, pDatos.posicion);
							disparoJugador = true;
						}catch (ImposibleDispararException e){
							System.out.println("IMPOSIBLE DISPARAR EL MISIL");
						}

					}
					// Si el jugador ha hecho un disparo correcto. Puede ser que no haya presionado una casilla valida
					if(disparoJugador){
						juegoTerminado = ListaJugadores.getInstance().getEntidad(1).realizarAccion(juegoTerminado);
					}
				}
			}else{
				System.out.println("NO TE PUEDES DISPARAR A TI MISMO");
			}
		}

		actualizarIntefaz();
	}

	public void notificarBotonRevelarRadarPresionado() {
		if(!colocandoBarcos && !juegoTerminado){
			try{
				ListaJugadores.getInstance().getEntidad(0).usarRadar();
				// Llamar a enemigo.realizarAcción
				juegoTerminado = ListaJugadores.getInstance().getEntidad(1).realizarAccion(juegoTerminado);

			}catch (ImposibleUsarRadarException e){
				System.out.println("IMPOSIBLE USAR EL RADAR");
			}

			if (!ListaJugadores.getInstance().getEntidad(0).hayBarcosSinHundir()){
				juegoTerminado = true;
				System.out.println("GANA EL ENEMIGO");
			}
		}

		actualizarIntefaz();
	}

	public void notificarBotonRecolocarRadarPresionado() {
		if(!colocandoBarcos && !juegoTerminado){
			ListaJugadores.getInstance().getEntidad(0).recolocarRadar();

			// Llamar a enemigo.realizarAcción
			try {
				juegoTerminado = ListaJugadores.getInstance().getEntidad(1).realizarAccion(juegoTerminado);
			} catch (ImposibleUsarRadarException e) {
				System.out.println("IMPOSIBLE USAR EL RADAR");
			}

			if (!ListaJugadores.getInstance().getEntidad(0).hayBarcosSinHundir()){
				juegoTerminado = true;
				System.out.println("GANA EL ENEMIGO");
			}
		}

		actualizarIntefaz();
	}

	// --------- Metodos para enviar datos a la interfaz ---------

	private void actualizarIntefaz(){
		// Avisar a los observers de que se ha producido un cambio
		setChanged();
		notifyObservers();
	}

	public EEstadoCasilla getEstadoCasillaJugador(int pPos){
		return ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pPos);
	}

	public EEstadoCasilla getEstadoCasillaEnemigo(int pPos){
		return ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pPos);
	}

}