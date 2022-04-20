package org.modelo;

import org.modelo.barco.Barco;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.misil.ETipoMisil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Tablero {
	private ArrayList<Casilla> listaCasillas;
	private final int size = 10; // Anchura y altura del tablero

	public Tablero(boolean pOculto) {
		listaCasillas = new ArrayList<>();

		for(int i = 0; i < size * size; i++){
			Casilla nuevaCasilla = new Casilla(i, EEstadoCasilla.AGUA, pOculto);
			listaCasillas.add(nuevaCasilla);
		}
	}

	private boolean posValida(int pPos){
		if(pPos >= 0 && pPos < listaCasillas.size()) return true;
		return false;
	}

	public boolean sePuedeColocar(int pPos, EOrientaconBarco pOrien, Barco pBarco) {
		// PRE: Una casilla de origen, una orientacion y un barco. Orientacion: NORTE, SUR, ESTE, OESTE
		// POST: Un booleano que indique si hay espacio suficiente para colocar el barco.

		boolean sePuede = true;
		int longitud = pBarco.getLongitud();

		if(pOrien.equals(EOrientaconBarco.NORTE)){
			// Desde la posición hacia arriba
			sePuede = sePuedeColocarNorte(pPos, longitud);

		}else if(pOrien.equals(EOrientaconBarco.SUR)){
			// Desde la posición hacia abajo
			sePuede = sePuedeColocarNorte(pPos + (longitud -1) * size, longitud);

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
		if(posValida(pPos + size) && sePuede) sePuede = listaCasillas.get(pPos + size).esAgua();

		// Comprobamos la casilla de arriba. Si esta posicion esta dentro del rango comprobar la casilla
		if(posValida(pPos - pLong*size) && sePuede) sePuede = listaCasillas.get(pPos - pLong*size).esAgua();

		// Comprobamos las casillas de la linea que va a ocupar el barco
		if(sePuede) sePuede = comprobarVertical(pPos, pLong);

		// Comprobamos las casillas de la linea izquierda a la que va a ocupar el barco si no estamos en el extremo izquierdo del tablero
		if(pPos % size != 0 && sePuede) sePuede = comprobarVertical(pPos -1, pLong);

		// Comprobar las casilla izquierda inferior y superior
		if(pPos % size != 0 && posValida(pPos -1 + size) && sePuede) sePuede = listaCasillas.get(pPos -1 + size).esAgua();
		if(pPos % size != 0 && posValida(pPos -1 -pLong * size) && sePuede) sePuede = listaCasillas.get(pPos -1 -pLong * size).esAgua();

		// Comprobamos las casillas de la linea derecha a la que va a ocupar el barco si no estamos en el extremo derecho del tablero
		if(pPos % size != size -1 && sePuede) sePuede = comprobarVertical(pPos +1, pLong);

		// Comprobar las casilla izquierda inferior y superior
		if(pPos % size != size -1 && posValida(pPos +1 + size) && sePuede) sePuede = listaCasillas.get(pPos +1 + size).esAgua();
		if(pPos % size != size -1 && posValida(pPos +1 -pLong * size) && sePuede) sePuede = listaCasillas.get(pPos +1 -pLong * size).esAgua();

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

		// Comprobamos la casilla de la izquierda. Si esta posicion esta dentro del rango comprobar la casilla y si se encuentra en la misma linea
		if(posValida(pPos -1) && ((pPos-1) % size < pPos % size) && sePuede) sePuede = listaCasillas.get(pPos -1).esAgua();

		// Comprobamos las diagonales de la izquierda
		if(posValida(pPos +pLong - size) && ((pPos +pLong - size) % size > pPos % size) && sePuede) sePuede = listaCasillas.get(pPos +pLong - size).esAgua();
		if(posValida(pPos +pLong + size) && ((pPos +pLong + size) % size > pPos % size) && sePuede) sePuede = listaCasillas.get(pPos +pLong + size).esAgua();

		// Comprobamos la casilla de la derecha. Si esta posicion esta dentro del rango comprobar la casilla y si se encuentra en la misma linea
		if(posValida(pPos + pLong) && ((pPos+pLong) % size > pPos % size) && sePuede) sePuede = listaCasillas.get(pPos + pLong).esAgua();

		// Comprobamos las diagonales de la derecha
		if(posValida(pPos -1 - size) && ((pPos -1 - size) % size < pPos % size) && sePuede) sePuede = listaCasillas.get(pPos -1 - size).esAgua();
		if(posValida(pPos -1 + size) && ((pPos -1 + size) % size < pPos % size) && sePuede) sePuede = listaCasillas.get(pPos -1 + size).esAgua();

		// Comprobamos las casillas de la linea que va a ocupar el barco
		if(sePuede) sePuede = comprobarHorizontal(pPos, pLong);

		// Comprobamos las casillas de la linea de abajo a la que va a ocupar el barco si no estamos en el extremo inferior del tablero
		if(pPos / size != size-1 && sePuede) sePuede = comprobarHorizontal(pPos + size, pLong);

		// Comprobamos las casillas de la linea de arriba a la que va a ocupar el barco si no estamos en el extremo superior del tablero
		if(pPos / size != 0 && sePuede) sePuede = comprobarHorizontal(pPos - size, pLong);


		return sePuede;
	}

	private boolean comprobarHorizontal(int startPos, int pLong){
		boolean sePuede = true;
		int i = 0;

		while(i < pLong && sePuede){
			int pos = startPos + i;

			if(!posValida(pos) || (pos % size < startPos %size)) sePuede = false;
			else sePuede = listaCasillas.get(pos).esAgua();

			i++;
		}

		return sePuede;
	}

	public void colocarBarco(int pPos, EOrientaconBarco pOrientacion, Barco pBarco) {
		// PRE: posicion de inicio para colocar el barco, su orientacion y el barco
		// POST: estado de las casillas actualizado

		if(pOrientacion.equals(EOrientaconBarco.NORTE)){
			colocarBarcoNorte(pPos, pBarco);
		}
		else if (pOrientacion.equals(EOrientaconBarco.SUR)){
			colocarBarcoNorte(pPos + size * (pBarco.getLongitud() -1), pBarco);
		}
		else if(pOrientacion.equals(EOrientaconBarco.ESTE)){
			colocarBarcoEste(pPos, pBarco);
		}
		else if(pOrientacion.equals(EOrientaconBarco.OESTE)){
			colocarBarcoEste(pPos - pBarco.getLongitud()+1, pBarco);
		}

	}

	private void colocarBarcoNorte(int pPos, Barco pBarco){
		// PRE: posicion de inicio para colocar el barco, su orientacion y el barco
		// POST: estado de las casillas actualizado.
		int longitud = pBarco.getLongitud();

		for(int i = 0; i < longitud; i++){
			listaCasillas.get(pPos - i* size).ponerBarco(pBarco);
		}

	}

	private void colocarBarcoEste(int pPos, Barco pBarco){
		// PRE: posicion de inicio para colocar el barco, su orientacion y el barco
		// POST: estado de las casillas actualizado

		int longitud = pBarco.getLongitud();

		for(int i = 0; i < longitud; i++){
			listaCasillas.get(pPos + i).ponerBarco(pBarco);
		}

	}

	public void actualizarCasillasDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo){
		for (int pos: posicionesDisparo) {
			if(posValida(pos)){
				listaCasillas.get(pos).actualizarDisparo(pTipo);
			}
		}
	}

	public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado){
		if(posValida(pCasilla))
			listaCasillas.get(pCasilla).actualizarEstadoCasilla(pEstado);
	}

    public EEstadoCasilla getEstadoCasilla(int pPos) {
		if(pPos < listaCasillas.size()){
			return listaCasillas.get(pPos).getEstado();
		}

		return null;
    }

	public boolean esAgua(int pPos) {
		if(pPos < listaCasillas.size()){
			return listaCasillas.get(pPos).esAgua();
		}

		return false;
	}

	public void actualizarContorno(ArrayList<Integer> posicionesDisparo){
		System.out.println("a");
		int i=0;
		ArrayList<Integer> posicionesRevelar= new ArrayList<Integer>();
		for (int pos: posicionesDisparo){
			System.out.println("a"+pos);
			if(posValida(pos-10)&&(pos/10-(pos-10)/10)==1){
				if(!posicionesDisparo.contains(pos-10)){
					posicionesRevelar.add(pos-10);
				}
			}
			if((posValida(pos+10)&&(pos/10-(pos+10)/10)==-1)){
				if(!posicionesDisparo.contains(pos+10)){
					posicionesRevelar.add(pos+10);
				}
			}if(posValida(pos-1)&&((pos-1)/10==pos/10)){
				if(!posicionesDisparo.contains(pos-1)){
					posicionesRevelar.add(pos-1);
				}
			}
			if(posValida(pos+1)&&((pos+1)/10==pos/10)){
				if(!posicionesDisparo.contains(pos+1)){
					posicionesRevelar.add(pos+1);
				}
			}
			if((posValida(pos+11)&&(pos/11-(pos+11)/11)==-1)&&pos%10!=9){
				if(!posicionesDisparo.contains(pos+11)){
					posicionesRevelar.add(pos+11);
				}
			}
			if((posValida(pos-11)&&(pos/11-(pos-11)/11)==1&&pos%10!=0)){
				if(!posicionesDisparo.contains(pos-11)){
					posicionesRevelar.add(pos-11);
				}
			}
			if((posValida(pos-9)&&(pos/9-(pos-9)/9)==1)&&pos%10!=9){
				if(!posicionesDisparo.contains(pos-9)){
					posicionesRevelar.add(pos-9);
				}
			}
			if((posValida(pos+9)&&(pos/9-(pos+9)/9)==-1)&&pos%10!=0){
				if(!posicionesDisparo.contains(pos+9)){
					posicionesRevelar.add(pos+9);
				}
			}

		}
		for (int pos: posicionesRevelar) {
			listaCasillas.get(pos).revelar();
		}

		for (int pos: posicionesDisparo) {
			listaCasillas.get(pos).actualizarBarcoHundido();
		}

	}


}