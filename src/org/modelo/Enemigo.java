package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.GeneradorDeBarcos;
import org.modelo.barco.ListaBarcos;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.GeneradorDeMisiles;
import org.modelo.misil.ListaMisiles;

import java.util.ArrayList;
import java.util.Random;

public class Enemigo {

	private static Enemigo miEnemigo;
	private Tablero tableroEnemigo;
	private ListaBarcos listaBarcosE;
	private ListaMisiles listaMisilesE;

	public Enemigo() {
		this.tableroEnemigo=new Tablero();
		this.listaBarcosE=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisilesE=new GeneradorDeMisiles().generarListaMisiles();
	}
	
	public static Enemigo getInstance() {
		if(miEnemigo == null) miEnemigo = new Enemigo();
		return miEnemigo;
	}

	public void colocarBarcoEnemigo() {
		int i = 0; Barco b1;
		while((b1 = listaBarcosE.obtenerBarcoEnPos(i)) != null){
			int posicion=this.obtPos();
			EOrientaconBarco orientacion=this.obtOrientacion();
			if(this.tableroEnemigo.sePuedeColocar(posicion,orientacion,b1)){
				this.tableroEnemigo.colocarBarco(posicion,orientacion,b1);
			}

			i++;
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

	public void recibirDisparo(ETipoMisil pMisil, int pPos) {
		ArrayList<Integer> lista= new ArrayList<Integer>();
		if (pMisil.equals(ETipoMisil.BOMBA))
			lista.add(pPos);
		this.tableroEnemigo.disparoRecibidoJugador(lista);
	}
	public boolean tieneBarcosEnemigo() {
		boolean tiene = false;
		if(this.listaBarcosE.size() > 0) {
			tiene= true;
		}
		else {tiene = false;}
		return tiene;
	}

}