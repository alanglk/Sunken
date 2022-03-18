package org.modelo.barco;

import java.util.ArrayList;

public abstract class Barco {
	public static final String FRAGATA = "fragata";
	public static final String DESTRUCTOR = "desturctor";
	public static final String PORTAVIONES = "portaviones";
	public static final String SUBMARINO = "submarino";

	private String tipo = null;

	private ArrayList<Integer> posicionesBarco;
	private int longitud;

	private boolean colocado;
	private boolean hundido;

	public Barco(int pLongitud, String pTipo) {
		posicionesBarco = new ArrayList<Integer>();

		this.tipo = pTipo;
		this.longitud = pLongitud;
		this.hundido = false;
		this.colocado = false;
	}

	public void actualizarBarcoColocado(){
		this.colocado = true;
	}

	public boolean estaColocado(){
		return this.colocado;
	}

	public boolean esTipo(String tipoBarco) {
		return tipo.equals(tipoBarco);
	}
}