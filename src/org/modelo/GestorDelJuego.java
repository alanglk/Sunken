package org.modelo;

import org.controlador.FormularioControlador;
import org.modelo.excepciones.ImposibleDispararException;

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
		Enemigo enemigo = (Enemigo) ListaJugadores.getInstance().getEntidad(1);
		enemigo.colocarBarco();

		colocandoBarcos = false;
		ListaJugadores.getInstance().getEntidad(0).imprimirBarcos();
		enemigo.imprimirBarcos();
		System.out.println("\n========== INICIANDO PARTIDA!! ==========");
		System.out.println("/////////////////////////////////////////////");

		// boolean random que indicará si empieza el enemigo o el jugador
		boolean turnoEnemigo = new Random().nextBoolean();

		if(colocandoBarcos && turnoEnemigo)
			enemigo.realizarDisparo();
	}

	public void notificarCasillaPresionada(FormularioControlador pDatos) throws Exception {
		if(colocandoBarcos){
			if(!pDatos.tableroEnemigo){
				// Los datos son del tablero del Jugador.
				if(pDatos.tipoBarco != null && pDatos.orientacion != null) {
					ListaJugadores.getInstance().getEntidad(0).colocarBarco(pDatos.posicion, pDatos.tipoBarco, pDatos.orientacion);

					// Comprobamos si estan todos los barcos del Jugador colocados. Si lo estan iniciamos la partida
					if (ListaJugadores.getInstance().getEntidad(0).estanTodosBarcosColocados())
						iniciarPartida();

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
						if(!ListaJugadores.getInstance().getEntidad(1).hayBarcosSinHundir() && !juegoTerminado){
							juegoTerminado = true;
							System.out.println("GANA EL JUGADOR");
						}else {
							Enemigo enemigo = (Enemigo) ListaJugadores.getInstance().getEntidad(1);
							enemigo.realizarDisparo();
						}
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
		return ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(pPos);
	}

	public EEstadoCasilla getEstadoCasillaEnemigo(int pPos){
		return ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pPos);
	}
}