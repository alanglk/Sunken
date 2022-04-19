package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.misil.ETipoMisil;
import java.util.ArrayList;
import java.util.Random;

public class Enemigo extends Entidad{

	public Enemigo() {
		super(true);
	}

	public void colocarBarco() {
		int i = 0; Barco b1;
		while((b1 = listaBarcos.obtenerBarcoEnPos(i)) != null){
			int posicion=this.obtPos();
			EOrientaconBarco orientacion=this.obtOrientacion();
			if(this.tablero.sePuedeColocar(posicion,orientacion,b1)){
				this.tablero.colocarBarco(posicion,orientacion,b1);
				i++;
			}
		}
	}

	private int obtPos() {
		Random r=new Random();
		return r.nextInt(100);
	}

	private EOrientaconBarco obtOrientacion() {
		//Si random es 0 la orientacion es horizontal y si es 1 vertical
		Random r=new Random();
		int queOrientacion=r.nextInt(2);

		EOrientaconBarco orientacion;
		if(queOrientacion==0){
			orientacion = EOrientaconBarco.ESTE;
		} else{
			orientacion = EOrientaconBarco.SUR;
		}

		return orientacion;
	}

	public boolean hayBarcosSinHundir() {
		return listaBarcos.hayBarcosSinHundir();
	}

	public void realizarDisparo(){
		// Comprobamos si el misil esta disponible
		ETipoMisil tipo = ETipoMisil.BOMBA;
		if(listaMisiles.sePuedeDisparar(tipo)){
			ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(ETipoMisil.BOMBA, obtPos(), 10);
			System.out.println("ENEMIGO -> disparando: " + posicionesDisparo.toString());
			ListaJugadores.getInstance().getEntidad(0).recibirDisparo(tipo, posicionesDisparo);
		}
	}

	public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo){
		tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo);
	}

	public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado){
		tablero.actualizarEstadoCasilla(pCasilla, pEstado);
	}

	public EEstadoCasilla getEstadoCasilla(int pPos){
		return tablero.getEstadoCasilla(pPos);
	}

	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- ENEMIGO ---------------------------");
		listaBarcos.imprimirBarcos();
	}
}