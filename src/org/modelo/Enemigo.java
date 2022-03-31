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
		this.tableroEnemigo=new Tablero(true);
		this.listaBarcosE=new GeneradorDeBarcos().generarListaBarcos();
		this.listaMisilesE=new GeneradorDeMisiles().generarListaMisiles();
	}
	
	public static Enemigo getInstance() {
		if(miEnemigo == null) miEnemigo = new Enemigo();
		return miEnemigo;
	}

	public void eliminarCasillaBarco(int casillaPos, int pId){
		Barco aux=null;
		int cont=0;
		boolean enc=false;
		while(cont<this.listaBarcosE.size() && !enc){
			if(this.listaBarcosE.obtenerBarcoEnPos(cont).esBarcoId(pId)){
				enc=true;
				aux=this.listaBarcosE.obtenerBarcoEnPos(cont);
				aux.eliminarCasilla(casillaPos);
			}
			cont++;
		}
	}

	public void colocarBarcoEnemigo() {
		int i = 0; Barco b1;
		while((b1 = listaBarcosE.obtenerBarcoEnPos(i)) != null){
			int posicion=this.obtPos();
			EOrientaconBarco orientacion=this.obtOrientacion();
			if(this.tableroEnemigo.sePuedeColocar(posicion,orientacion,b1)){
				this.tableroEnemigo.colocarBarco(posicion,orientacion,b1);
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
		return listaBarcosE.hayBarcosSinHundir();
	}

	public void realizarDisparo(){
		// Comprobamos si el misil esta disponible
		if(listaMisilesE.sePuedeDisparar(ETipoMisil.BOMBA)){
			ArrayList<Integer> posicionesDisparo = listaMisilesE.obtAreaMisil(ETipoMisil.BOMBA, obtPos(), 10);
			System.out.println("ENEMIGO -> disparando: " + posicionesDisparo.toString());
			Jugador.getInstance().recibirDisparo(posicionesDisparo);
		}
	}

	public void recibirDisparo(ArrayList<Integer> posicionesDisparo){
		tableroEnemigo.actualizarCasillasDisparo(posicionesDisparo);
	}

	public EEstadoCasilla getEstadoCasilla(int pPos){
		return tableroEnemigo.getEstadoCasilla(pPos);
	}

	public boolean esAgua(int pPos){
		return tableroEnemigo.esAgua(pPos);
	}

	// TODO: Eliminar este metodo
	public void imprimirBarcos(){
		System.out.println("--------------------------- ENEMIGO ---------------------------");
		listaBarcosE.imprimirBarcos();
	}
}