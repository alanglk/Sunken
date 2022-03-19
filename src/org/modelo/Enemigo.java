package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ListaMisiles;
import org.modelo.misil.Misil;

import java.util.Iterator;
import java.util.Random;

public class Enemigo {

	private Tablero tableroEnemigo;
	private ListaBarcos listaBarcosE;
	private ListaMisiles listaMisilesE;

	public Enemigo() {
		this.tableroEnemigo=new Tablero();
		this.listaBarcosE=new ListaBarcos();
		this.listaMisilesE=new ListaMisiles();
	}

	public void colocarBarcoEnemigo() {
		Iterator itr=listaBarcosE.getIterator();
		Barco b1;
		while(itr.hasNext()){
			b1= (Barco) itr.next();
			int posicion=this.obtPos();
			EOrientaconBarco orientacion=this.obtOrientacion();
			if(this.tableroEnemigo.sePuedeColocar(posicion,orientacion,b1)){
				this.tableroEnemigo.colocarBarco(posicion,orientacion,b1);
			}
		}
	}

	private int obtPos() {
		Random r=new Random();
		return r.nextInt(100);
	}
	public static void main(String arg[]){
		Random r = new Random();
		for(int i=0;i<100;i++){
			int random = r.nextInt(100);
			System.out.println("Numero aleatorio: " + random);
		}
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

	public void recibirDisparo(Misil pMisil, Casilla pCasillla) {
		// TODO - implement Enemigo.recibirDisparo
		throw new UnsupportedOperationException();
	}

}