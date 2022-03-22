package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;

import java.util.ArrayList;

public class Tablero {
	private ArrayList<Casilla> listaCasillas;
	private final int size = 10; // Anchura y altura del tablero

	public Tablero() {
		listaCasillas = new ArrayList<>();

		for(int i = 0; i < size * size; i++){
			Casilla nuevaCasilla = new Casilla(EEstadoCasilla.AGUA);
		}
	}

	private boolean posValida(int pPos){
		if(pPos >= 0 && pPos < listaCasillas.size()) return true;
		return false;
	}

	public boolean sePuedeColocar(int pPos, EOrientaconBarco pOrien, Barco pBarco) {
		// PRE: Una casilla de origen, una orientacion y un barco. Orientacion: NORTE, SUR, ESTE, OESTE
		// POST: Un booleano que indique si hay espacio suficiente para colocar el barco

		boolean sePuede = true;
		int longitud = pBarco.getLongitud();

		if(pOrien.equals(EOrientaconBarco.NORTE)){
			// Desde la posición hacia arriba
			sePuede = sePuedeColocarNorte(pPos, longitud);

		}else if(pOrien.equals(EOrientaconBarco.SUR)){
			// Desde la posición hacia abajo
			sePuede = sePuedeColocarNorte(pPos - (longitud -1) * size, longitud);

		}else if(pOrien.equals(EOrientaconBarco.ESTE)){
			// Desde la posición hacia la derecha
			sePuede = sePuedeColocarEste(pPos, longitud);

		}else if(pOrien.equals(EOrientaconBarco.OESTE)){
			// Desde la posición hacia la izquierda
			sePuede = sePuedeColocarEste(pPos - (longitud -1), longitud);

		}

		return sePuede;
	}

	private boolean sePuedeColocarNorte(int pPos, int pLong){
		boolean sePuede = true;

		// Comprobamos la casilla de abajo. Si esta posicion esta dentro del rango comprobar la casilla
		if(posValida(pPos + size)) sePuede = listaCasillas.get(pPos + size).esAgua();

		// Comprobamos la casilla de arriba. Si esta posicion esta dentro del rango comprobar la casilla
		if(posValida(pPos + pLong*size)) sePuede = listaCasillas.get(pLong*size).esAgua();

		// Comprobamos las casillas de la linea que va a ocupar el barco
		if(sePuede) sePuede = comprobarVertical(pPos, pLong);

		// Comprobamos las casillas de la linea izquierda a la que va a ocupar el barco si no estamos en el extremo izquierdo del tablero
		if(pPos % size != 0 && sePuede) sePuede = comprobarVertical(pPos -1, pLong);

		// Comprobamos las casillas de la linea derecha a la que va a ocupar el barco si no estamos en el extremo derecho del tablero
		if(pPos % size != size -1 && sePuede) sePuede = comprobarVertical(pPos +1, pLong);

		return sePuede;
	}

	private boolean comprobarVertical(int startPos, int pLong){
		boolean sePuede = true;
		int i = 0;

		while(i < pLong && sePuede){
			int pos = startPos - i*size;

			if(!posValida(pos)) sePuede = false;
			else sePuede = listaCasillas.get(pos).esAgua();

			i++;
		}

		return sePuede;
	}

	private boolean sePuedeColocarEste(int pPos, int pLong){
		boolean sePuede = true;

		// Comprobamos la casilla de la izquierda. Si esta posicion esta dentro del rango comprobar la casilla
		if(posValida(pPos -1)) sePuede = listaCasillas.get(pPos -1).esAgua();

		// Comprobamos la casilla de la derecha. Si esta posicion esta dentro del rango comprobar la casilla
		if(posValida(pPos + pLong)) sePuede = listaCasillas.get(pPos + pLong).esAgua();

		// Comprobamos las casillas de la linea que va a ocupar el barco
		if(sePuede) sePuede = comprobarHorizontal(pPos, pLong);

		// Comprobamos las casillas de la linea de abajo a la que va a ocupar el barco si no estamos en el extremo inferior del tablero
		if(pPos / size != size && sePuede) sePuede = comprobarHorizontal(pPos + size, pLong);

		// Comprobamos las casillas de la linea de arriba a la que va a ocupar el barco si no estamos en el extremo superior del tablero
		if(pPos / size != 0 && sePuede) sePuede = comprobarVertical(pPos - size, pLong);

		return sePuede;
	}

	private boolean comprobarHorizontal(int startPos, int pLong){
		boolean sePuede = true;
		int i = 0;

		while(i < pLong && sePuede){
			int pos = startPos + i;

			if(!posValida(pos)) sePuede = false;
			else sePuede = listaCasillas.get(pos).esAgua();

			i++;
		}

		return sePuede;
	}

	public void colocarBarco(int pPos, EOrientaconBarco pOrientacion, Barco pBarco) {
		// PRE: posicion de inicio para colocar el barco, su orientacion y el barco
		// POST: estado de las casillas actualizado
		int cont=0;
		int pos=pPos;
		while (cont < pBarco.getLongitud()){
			this.listaCasillas.get(pos).setId(pBarco.getId());
			if pOrientacion.equals("Norte")){
				pos=pos+10;
			}
			else if(pOrientacion.equals("Sur")){
				pos=pos-10;
			}
			else if (pOrientacion.equals("Oeste")){
				pos--;
			}
			else{
				pos++
			}
			cont++;
		}
	}
	
	public void disparoRecibido(ArrayList<Integer> pAreaDisparo,) {
	int cont=0;
	int dir=0;
	while(cont<pAreaDisparo.size()){
		dir=pAreaDisparo.get(cont);
		if(this.listaCasillas.get(dir).tieneBarco()){
			this.listaCasillas.get(dir).setEstado("AGUA");
		}
		else{
			this.listaCasillas.get(dir).setEstado("BARCO");

		}

	}

	}
	
	
	
}