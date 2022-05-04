package org.modelo;

import org.controlador.FormularioControlador;
import org.modelo.barco.ETipoBarco;
import org.modelo.excepciones.ImposibleColocarBarcoException;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.excepciones.ImposibleUsarRadarException;
import org.modelo.misil.ETipoMisil;
import org.vista.VentanaInformacion;

import java.util.ArrayList;
import java.util.HashMap;
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

		actualizarIntefaz();
	}

	public void notificarCasillaPresionada(FormularioControlador pDatos) throws Exception {
		if(colocandoBarcos){
			if(!pDatos.isTableroEnemigo()){
				// Los datos son del tablero del Jugador.
				if(pDatos.isEscudo()){
					ListaJugadores.getInstance().getEntidad(0).colocarEscudoBarco(pDatos.getPosicion());
				}else if(pDatos.getTipoBarco() != null && pDatos.getOrientacion() != null) {
					try{
						ListaJugadores.getInstance().getEntidad(0).colocarBarco(pDatos.getPosicion(), pDatos.getTipoBarco(), pDatos.getOrientacion());
					}catch(ImposibleColocarBarcoException e){
						System.err.println("ERROR: No se pudo colocar el barco por una pos invalida o el barco de ese tipo no esta disponible");
					}
				}
			}
			// Si los datos no son del Jugador podemos hacer otra cosa (avisar al usuario o no hacer nada)

		}else if(!colocandoBarcos && !juegoTerminado){
			// Presionamos una casilla para realizar un disparo
			if(pDatos.isTableroEnemigo()){
				if(pDatos.getTipoMisil()!=null){
					boolean disparoJugador = false;

					if (!ListaJugadores.getInstance().getEntidad(0).hayBarcosSinHundir()){
						juegoTerminado = true;
						System.out.println("GANA EL ENEMIGO");
						new VentanaInformacion("GANA ENEMIGO");
					}else{
						try {
							ListaJugadores.getInstance().getEntidad(0).realizarDisparo(pDatos.getTipoMisil(), pDatos.getPosicion());
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

	public void actualizarIntefaz(){
		// Avisar a los observers de que se ha producido un cambio

		setChanged();
		notifyObservers(getFormulario());
	}

	private FormularioModelo getFormulario(){
		ArrayList<EEstadoCasilla> casillasJugador = new ArrayList<EEstadoCasilla>();
		ArrayList<EEstadoCasilla> casillasEnemigo = new ArrayList<EEstadoCasilla>();
		HashMap<ETipoBarco, Integer> numBarcosNoColocados = new HashMap<ETipoBarco, Integer>();
		HashMap<ETipoBarco, Integer> numBarcosNoHundidos = new HashMap<ETipoBarco, Integer>();
		HashMap<ETipoMisil, Integer> numMisilesJugador = new HashMap<ETipoMisil, Integer>();
		int dineroJugador = ListaJugadores.getInstance().getEntidad(0).obtenerDineroDisponible();
		int dineroEnemigo = ListaJugadores.getInstance().getEntidad(1).obtenerDineroDisponible();
		int numBotonesOneTap = Tienda.getInstance().obtNumArmamento(EObjetoComprable.BOMBAONETAP);
		int numRadar3x3= Tienda.getInstance().obtNumArmamento(EObjetoComprable.RADAR3x3);

		for(int i = 0; i < 100; i++){
			casillasJugador.add(i, ListaJugadores.getInstance().getEntidad(0).getEstadoCasilla(i));
		}

		for(int i = 0; i < 100; i++){
			casillasEnemigo.add(i, ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(i));
		}

		for (ETipoBarco tipo : ETipoBarco.values()) {
			numBarcosNoColocados.put(tipo, ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcosNoColocados(tipo));
		}

		for (ETipoBarco tipo : ETipoBarco.values()) {
			numBarcosNoHundidos.put(tipo, ListaJugadores.getInstance().getEntidad(1).obtenerNumBarcosNoHundidos(tipo));
		}

		for (ETipoMisil tipo : ETipoMisil.values()) {
			numMisilesJugador.put(tipo, ListaJugadores.getInstance().getEntidad(0).obtenerNumMisilesDisponibles(tipo));
		}

		Integer numUsosRadarJugador = ListaJugadores.getInstance().getEntidad(0).obtenerNumUsosRadar();
		Integer numEscudosJugador = ListaJugadores.getInstance().getEntidad(0).obtenerNumEscudos();

		return new FormularioModelo(colocandoBarcos, juegoTerminado, casillasJugador, casillasEnemigo, numBarcosNoColocados, numBarcosNoHundidos, numMisilesJugador, numUsosRadarJugador, numEscudosJugador,dineroJugador,dineroEnemigo,numBotonesOneTap,numRadar3x3);
	}

    public void comprar(EObjetoComprable armamentoSel, int obtenerDineroDisponible) {
		ListaJugadores.getInstance().getEntidad(0).comprarObjeto(armamentoSel);
		actualizarIntefaz();
    }
}