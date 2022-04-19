package org.modelo;

public class Jugador extends Entidad{
	public Jugador() {
		super(false);
	}
	
	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- JUGADOR ---------------------------");
		super.listaBarcos.imprimirBarcos();
	}
}