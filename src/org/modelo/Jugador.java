package org.modelo;

import org.modelo.excepciones.ImposibleUsarRadarException;

import java.util.List;

public class Jugador extends Entidad{
	public Jugador() {
		super(false);
	}

	@Override
	public void usarRadar() throws ImposibleUsarRadarException {
		if(radar.sePuedeUtilizar()){
			ListaJugadores.getInstance().getEntidad(1).revelarCasillasRadar(radar.obtenerPosicionesReveladas(10));
		}else{
			throw new ImposibleUsarRadarException();
		}
	}

	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- JUGADOR ---------------------------");
		super.listaBarcos.imprimirBarcos();
	}

	@Override
	public void recolocarRadar() {
		radar.cambiarPosicionRadar(false);
	}


}