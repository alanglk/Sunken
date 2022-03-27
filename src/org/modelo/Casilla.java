package org.modelo;

public class Casilla {

	private EEstadoCasilla estado;
	private int idBarco;
	private boolean oculto;

	public Casilla(EEstadoCasilla pEstado) {
		estado = pEstado;
		idBarco=-1;
		oculto=true;
	}

	public boolean esAgua(){
		return estado.equals(EEstadoCasilla.AGUA);
	}

	public boolean tieneBarco() {
		boolean tieneBarco = false;

		if (this.idBarco != -1) {
			tieneBarco = true;
		}
		return (true);
	}

	public void setId(int pIdBarco){
		this.idBarco=pIdBarco;
	}

	public int getIdBarco(){
		return this.idBarco;
	}

	public void setEstado(String pEstado){
		this.estado.equals(pEstado);
	}
	public void actualizarOculto(boolean pOculto){
		oculto = pOculto;
	}

	public void casillaRecibeDisparoJugador() {
		//Este m�todo obtiene el barco que est� en esa casilla y comprueba su estado completo y tambi�n llamar� a actualizarCasillaBarco de Jugador
		Jugador.getInstance().obtListaBarcos().obtenerBarcoEnPos(this.getIdBarco());

	}


    public void actualizarBarco(int pPosicion) {
		Jugador.getInstance().actualizarCasillaBarco(pPosicion,this.idBarco);

    }

	public EEstadoCasilla getEstado() {
		System.out.println("-> Casilla.Estado: " + estado);
		if(oculto)
			return EEstadoCasilla.OCULTO;

		return estado;
	}
}