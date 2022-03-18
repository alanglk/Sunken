package org.modelo.barco;

import org.modelo.barco.Barco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ListaBarcos {

	private ArrayList<Barco> lista;

	public ListaBarcos() {
		lista = new ArrayList<Barco>();
	}

	private Iterator<Barco> getIterator() {
		return lista.iterator();
	}

	public boolean estaDisponible(String tipoBarco) {
		return this.buscarBarcoNoColocado(tipoBarco) != null;
	}

	public void actualizarEstadoBarcoColocado(String tipoBarco) {
		buscarBarcoNoColocado(tipoBarco).actualizarBarcoColocado();
	}

	private Barco buscarBarcoNoColocado(String tipoBarco) {
		Iterator<Barco> itr = getIterator();
		boolean encontradoYDisp = false;
		Barco barco = null;

		while(itr.hasNext() && !encontradoYDisp){
			barco = itr.next();

			if(barco.esTipo(tipoBarco)){
				if(!barco.estaColocado())
					encontradoYDisp = true;
			}
		}
		return barco;
	}

}