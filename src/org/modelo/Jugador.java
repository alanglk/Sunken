package org.modelo;

import org.modelo.excepciones.ImposibleUsarRadarException;
import org.modelo.radar.Radar3x3;

import java.util.List;

public class Jugador extends Entidad{
	public Jugador() {
		super(false);
	}

	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- JUGADOR ---------------------------");
		super.listaBarcos.imprimirBarcos();
	}

	@Override
	public void usarRadar() throws ImposibleUsarRadarException {
		if(radar.sePuedeUtilizar()){
			ListaJugadores.getInstance().getEntidad(1).revelarCasillasRadar(radar.obtenerPosicionesReveladas(10));
		}else{
			throw new ImposibleUsarRadarException();
		}
	}

	@Override
	public void recolocarRadar() {
		if(radar == null) radar = new Radar3x3();
		radar.cambiarPosicionRadar(false);
	}


}