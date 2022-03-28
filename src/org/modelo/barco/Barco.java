package org.modelo.barco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Barco {
	private ETipoBarco tipo = null;

	private Integer[] posicionesBarco;
	private int longitud;
	private int id;

	private boolean colocado;

	public Barco(int pLongitud, ETipoBarco pTipo,int pId) {
		posicionesBarco = new Integer[]{-1, -1, -1, -1};

		this.tipo = pTipo;
		this.longitud = pLongitud;
		this.colocado = false;
		this.id=pId;
	}
	public boolean esBarcoId(int pId){
		return(pId==this.id);
	}
	public int getLongitud(){
		return longitud;
	}

	public int getId(){return this.id;}

	public void anadirCasilla(int pPos){
		boolean term = false;
		int i = 0;

		while(i < longitud && !term){
			if(posicionesBarco[i] == -1){
				posicionesBarco[i] = pPos;
				term = true;
			}else{
				i++;
			}
		}

		if(i >= longitud)
			System.out.println("ERROR: La longitud del barco no se corresponde con el numero de casillas.");
	}

	public void eliminarCasilla(int pCasilla){
		boolean enc = false;
		int i = 0;

		while(i < longitud && !enc){
			if(posicionesBarco[i] == pCasilla){
				posicionesBarco[i] = -1;
				enc = true;
			}
			i++;
		}

		System.out.println("----------");
		System.out.println("Casilla " + pCasilla + " eliminada? -> encontrada: " + enc);
		imprimirPosiciones();
		if(estaHundido())
			System.out.println("HUNDIDO");
	}

	public void actualizarBarcoColocado(){
		this.colocado = true;
	}

	public boolean estaColocado(){
		return this.colocado;
	}

	public boolean esTipo(ETipoBarco tipoBarco) {
		return tipo.equals(tipoBarco);
	}

	public boolean estaHundido(){
		boolean hundido = true;
		int i = 0;

		while(i < longitud && hundido){
			if(posicionesBarco[i] != -1)
				hundido = false;

			i++;
		}

		return hundido;
	}

	// TODO: Eliminar este metodo
	public void imprimirPosiciones(){
		System.out.println("Posiciones del barco " + id + " (" + tipo + ") de longitud " + longitud + ": ");
		for(int i = 0; i < longitud; i++){
			System.out.print("" + posicionesBarco[i] + ", ");
		}
		System.out.println("");
	}
}