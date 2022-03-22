package org.modelo.barco;

import java.util.ArrayList;

public abstract class Barco {
	private ETipoBarco tipo = null;

	private ArrayList<Integer> posicionesBarco;
	private int longitud;
	private int id;

	private boolean colocado;
	private boolean hundido;

	public Barco(int pLongitud, ETipoBarco pTipo,int pId) {
		posicionesBarco = new ArrayList<Integer>();

		this.tipo = pTipo;
		this.longitud = pLongitud;
		this.hundido = false;
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

	public void actualizarBarcoColocado(){
		this.colocado = true;
	}

	public boolean estaColocado(){
		return this.colocado;
	}

	public boolean esTipo(ETipoBarco tipoBarco) {
		return tipo.equals(tipoBarco);
	}

}