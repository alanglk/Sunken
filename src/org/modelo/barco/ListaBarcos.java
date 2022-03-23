package org.modelo.barco;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaBarcos {

	private ArrayList<Barco> lista;

	public ListaBarcos() {
		lista = new ArrayList<Barco>();
	}

	private Iterator<Barco> getIterator() {
		return lista.iterator();
	}

	public Barco obtenerBarcoNoColocado(ETipoBarco tipoBarco) {
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

	public boolean estanTodosBarcosColocados(){
		Iterator<Barco> itr = getIterator();
		boolean todosColocados = true;

		while(itr.hasNext() && todosColocados){
			todosColocados = itr.next().estaColocado();
		}

		return todosColocados;
	}
	
	public int size() {
		return this.lista.size();
	}

	public Barco obtenerBarcoEnPos(int pPos){
		return lista.get(pPos);
	}

	public void anadirBarco(Barco b1){
		lista.add(b1);
	}
}