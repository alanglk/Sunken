package org.modelo;

public class Casilla {

	private EEstadoCasilla estado;
	private int idBarco;

	public Casilla(EEstadoCasilla pEstado) {
		estado = pEstado;
		idBarco=-1;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}

	public boolean tieneBarco(){
		return(this.idBarco==-1);

	}

	public void setId(int pIdBarco){
		this.idBarco=pIdBarco;
	}
	public int getIdBarco(){return this.idBarco;}

	public void setEstado(String pEstado){
		this.estado.equals(pEstado);
	}
	
	public void casillaRecibeDisparoJugador(Casilla lugar) {
		//El método obtiene el barco que está en la posición del tablero. Falta comprobar el estado completo del barco
		Jugador.getInstance().obtListaBarcos().obtenerBarcoEnPos(lugar.getIdBarco());
	}
	
	
}