package org.modelo;

public class Casilla {

	private EEstadoCasilla estado;
	private int idBarco;
	private boolean tieneBarco;

	public Casilla(EEstadoCasilla pEstado) {
		estado = pEstado;
		idBarco=-1;
		tieneBarco=false;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}

	public boolean tieneBarco(){
		return(this.tieneBarco);

	}

	public void setId(int pIdBarco){
		this.idBarco=pIdBarco;
	}
	public int getIdBarco(){return this.idBarco;}

	public void setEstado(String pEstado){
		this.estado.equals(pEstado);
	}
	
	public void casillaRecibeDisparoJugador(Casilla lugar) {
		//Este m�todo obtiene el barco que est� en esa casilla y comprueba su estado completo y tambi�n llamar� a actualizarCasillaBarco de Jugador
		Jugador.getInstance().obtListaBarcos().obtenerBarcoEnPos(lugar.getIdBarco());
	}


    public void actualizarBarco(int pPosicion) {
		Jugador.getInstance().actualizarCasillaBarco(pPosicion,this.idBarco);
		this.tieneBarco=true;
    }
}