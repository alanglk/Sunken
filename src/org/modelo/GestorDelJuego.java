package org.modelo;

import org.controlador.FormularioControlador;
import org.modelo.misil.ETipoMisil;

import java.util.Observable;

public class GestorDelJuego extends Observable {

	private static GestorDelJuego miGestorDelJuego;
	private boolean juegoIniciado = false;

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
		//Enemigo.getInstance().colocarBarcoEnemigo();

		// Numero random que indicará si empieza el enemigo o el jugador
		int random = 1;

		if(!juegoIniciado && random == 1)
			realizarDisparoEnemigo();

		juegoIniciado = true;
	}

	private void realizarDisparoJugador(int pPos, ETipoMisil pMisil) {
		Jugador jugador = Jugador.getInstance();
		Enemigo enemigo = Enemigo.getInstance();
		if(jugador.misilDisponible(pMisil)) {
			enemigo.recibirDisparo(pMisil, pPos);
		}
	}

	private void realizarDisparoEnemigo() {
		// TODO: Implementar realizarDisparo() en Enemigo
		//Enemigo.getInstance().realizarDisparo();
	}

	public void notificarCasillaPresionada(FormularioControlador pDatos) throws Exception {
		if(!juegoIniciado ){
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

		}else{
			// Presionamos una casilla para realizar un disparo
			System.out.println("--- DISPARO ---");
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