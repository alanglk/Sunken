package org.modelo;

import org.controlador.FormularioControlador;
import org.modelo.misil.ETipoMisil;

import java.util.Observable;
import java.util.Random;

public class GestorDelJuego extends Observable {

	private static GestorDelJuego miGestorDelJuego;
	private boolean colocandoBarcos = true;
	private boolean juegoTerminado = false;

	private GestorDelJuego() {
		// Inicializar el jugador y el enemigo

		Jugador.getInstance();
		Enemigo.getInstance();
	}

	public static GestorDelJuego getInstance() {
		if(miGestorDelJuego == null) miGestorDelJuego = new GestorDelJuego();
		return miGestorDelJuego;
	}

	public void iniciarPartida() {
		// Se han colocado todos los barcos del jugador.
		// Marcamos el estado como partida iniciada y decidimos el orden de juego.
		Enemigo.getInstance().colocarBarcoEnemigo();

		colocandoBarcos = false;
		Jugador.getInstance().imprimirBarcos();
		Enemigo.getInstance().imprimirBarcos();
		System.out.println("\n========== INICIANDO PARTIDA!! ==========");
		System.out.println("/////////////////////////////////////////////");

		// boolean random que indicará si empieza el enemigo o el jugador
		boolean turnoEnemigo = new Random().nextBoolean();

		if(colocandoBarcos && turnoEnemigo)
			Enemigo.getInstance().realizarDisparo();
	}

	public void notificarCasillaPresionada(FormularioControlador pDatos) throws Exception {
		if(colocandoBarcos){
			if(!pDatos.tableroEnemigo){
				// Los datos son del tablero del Jugador.
				if(pDatos.tipoBarco != null && pDatos.orientacion != null) {
					Jugador.getInstance().colocarBarco(pDatos.posicion, pDatos.tipoBarco, pDatos.orientacion);

					// Comprobamos si estan todos los barcos del Jugador colocados. Si lo estan iniciamos la partida
					if (Jugador.getInstance().estanTodosBarcosColocados())
						iniciarPartida();

				}
			}
			// Si los datos no son del Jugador podemos hacer otra cosa (avisar al usuario o no hacer nada)

		}else if(!colocandoBarcos && !juegoTerminado){
			// Presionamos una casilla para realizar un disparo
			if(pDatos.tableroEnemigo){
				if(pDatos.tipoMisil!=null){
					Jugador.getInstance().realizarDisparo(pDatos.tipoMisil, pDatos.posicion);

					if(!Enemigo.getInstance().hayBarcosSinHundir()){
						juegoTerminado = true;
						System.out.println("GANA EL JUGADOR");
					}else {
						Enemigo.getInstance().realizarDisparo();
					}

				}
			}else{
				System.out.println("NO TE PUEDES DISPARAR A TI MISMO");
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
		return Jugador.getInstance().getEstadoCasilla(pPos);
	}

	public EEstadoCasilla getEstadoCasillaEnemigo(int pPos){
		return Enemigo.getInstance().getEstadoCasilla(pPos);
	}
}