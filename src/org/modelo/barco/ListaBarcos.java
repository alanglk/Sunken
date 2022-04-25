package org.modelo.barco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaBarcos {

	private ArrayList<Barco> lista;

	public ListaBarcos() {
		lista = new ArrayList<Barco>();
	}

	private Iterator<Barco> getIterator() {
		return lista.iterator();
	}

	public Barco obtenerBarco(int pId){
		return lista.stream().filter(p -> p.esBarcoId(pId)).findFirst().get();
	}

	public Barco obtenerBarcoNoColocado(ETipoBarco tipoBarco) {
		Iterator<Barco> itr = getIterator();
		boolean encontradoYDisp = false;
		Barco barco = null;

		while(itr.hasNext() && !encontradoYDisp) {
			barco = itr.next();
			if (barco.esTipo(tipoBarco)) {
				if (!barco.estaColocado())
					encontradoYDisp = true;
			}
		}
		if(encontradoYDisp)
			return barco;

		return null;
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
		if(pPos >= lista.size()) return null;
		return lista.get(pPos);
	}

	public void anadirBarco(Barco b1){
		lista.add(b1);
	}

	public boolean hayBarcosSinHundir(){
		Iterator<Barco> itr = getIterator();
		boolean unBarcoSinHundir = false;

		while(itr.hasNext() && !unBarcoSinHundir){
			if(!itr.next().estaHundido())
				unBarcoSinHundir = true;
		}

		return unBarcoSinHundir;
	}

	//TODO: Eliminar este metodo
	public void imprimirBarcos() {
		System.out.println("/////////////////////////////////////////////");
		for (Barco barco : lista) {
			barco.imprimirPosiciones();
		}
	}

	public Integer obtenerNumBarcosNoColocados(ETipoBarco tipoBarco) {
		Iterator<Barco> itr = getIterator();
		Barco barco = null;
		int numBarcos=0;

		while(itr.hasNext()) {
			barco = itr.next();
			if (barco.esTipo(tipoBarco)) {
				if(!barco.estaColocado())
					numBarcos++;
			}
		}
		return numBarcos;
	}

	public Barco obtenerAleatorioParaEscudo(){
		int i=0;
		boolean enc=false;
		float r = new Random().nextFloat();
		if(r<=0.6){
			while(!enc) {
				int random = new Random().nextInt(lista.size());
				i=random;
				if((lista.get(random) instanceof  Fragata)&&!lista.get(random).estaColocado()){
					enc=true;
				}
				System.out.println(i);
			}

		}
		else if(r>0.6&&r<=0.85){
			while(!enc) {
				int random = new Random().nextInt(lista.size());
				i=random;
				if((lista.get(random) instanceof  Destructor)&&!lista.get(random).estaColocado()){
					enc=true;
				}
				System.out.println(i);
			}


		}
		else if(r>0.85&&r<0.95){
			while(!enc) {
				int random = new Random().nextInt(lista.size());
				i=random;
				if((lista.get(random) instanceof  Submarino)&&!lista.get(random).estaColocado()){
					enc=true;
				}
				System.out.println(i);
			}


		}
		else{
			while(!enc) {
				int random = new Random().nextInt(lista.size());
				i=random;
				if((lista.get(random) instanceof  Portaviones)&&!lista.get(random).estaColocado()){
					enc=true;
				}
				System.out.println(i);
			}


		}
		System.out.println(i);
		return lista.get(i);

	}

	public boolean todosTienenEscudo(){
		Iterator<Barco> itr =getIterator();
		boolean todosEscudo = true;

		while(itr.hasNext() && todosEscudo){
			todosEscudo = itr.next().tieneEscudo();
		}

		return todosEscudo;
	}
}

