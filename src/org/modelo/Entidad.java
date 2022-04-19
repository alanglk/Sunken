package org.modelo;

import org.modelo.barco.*;
import org.modelo.excepciones.ImposibleDispararException;
import org.modelo.misil.ETipoMisil;
import org.modelo.misil.GeneradorDeMisiles;
import org.modelo.misil.ListaMisiles;

import java.util.ArrayList;

public abstract class Entidad {
    protected Tablero tablero;
    protected ListaBarcos listaBarcos;
    protected ListaMisiles listaMisiles;

    public Entidad(boolean casillasOcultas) {
        this.tablero=new Tablero(casillasOcultas);
        this.listaBarcos=new GeneradorDeBarcos().generarListaBarcos();
        this.listaMisiles=new GeneradorDeMisiles().generarListaMisiles();
    }

    public void colocarBarco(int pPos, ETipoBarco pTipoBarco, EOrientaconBarco pOrientacion) throws Exception {
        Barco barco = listaBarcos.obtenerBarcoNoColocado(pTipoBarco);

        // Si hay un barco disponible comprobamos si se puede colocar en la posicion
        if(barco != null){
            // Si se puede colocar, lo colocamos y actualizamos el estado del barco
            if(tablero.sePuedeColocar(pPos,pOrientacion,barco)){
                tablero.colocarBarco(pPos,pOrientacion,barco);
                barco.actualizarBarcoColocado();

            }else{
                throw new Exception("ERROR: No se puede colocar en esa posición el barco");
            }

        }else{
            throw new Exception("ERROR: No esta disponible el barco");
        }

    }

    public void dispararBarco(ETipoMisil pTipo, int casillaPos, int pId, boolean pEnemigo){
        Barco aux=null;
        int cont=0;
        boolean enc=false;
        while(cont<this.listaBarcos.size() && !enc){
            if(this.listaBarcos.obtenerBarcoEnPos(cont).esBarcoId(pId)){
                enc=true;
                aux=this.listaBarcos.obtenerBarcoEnPos(cont);
                aux.recibirDisparoBarco(pTipo, casillaPos, pEnemigo);
            }
            cont++;
        }
    }

    private boolean posValida(int pos){
        boolean valida = true;
        if(pos < 0 || 100 <= pos) valida = false;
        if(ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pos).equals(EEstadoCasilla.HUNDIDO)) valida = false;
        if(ListaJugadores.getInstance().getEntidad(1).getEstadoCasilla(pos).equals(EEstadoCasilla.AGUADISPARO)) valida = false;

        return valida;
    }

    public void realizarDisparo(ETipoMisil pTipo, int pPos) throws ImposibleDispararException {
        // Comprobamos si el misil esta disponible
        if (posValida(pPos) && listaMisiles.sePuedeDisparar(pTipo)) {
            ArrayList<Integer> posicionesDisparo = listaMisiles.obtAreaMisil(pTipo, pPos, 10);
            System.out.println(" -> disparando: " + posicionesDisparo.toString());
            ListaJugadores.getInstance().getEntidad(1).recibirDisparo(pTipo, posicionesDisparo);
        }else{
            throw new ImposibleDispararException();
        }
    }
    public void actualizarContorno(ArrayList<Integer> pLista){
        this.tablero.actualizarContorno(pLista);
    }

    public void recibirDisparo(ETipoMisil pTipo, ArrayList<Integer> posicionesDisparo){
        tablero.actualizarCasillasDisparo(pTipo, posicionesDisparo);
    }

    public void actualizarEstadoCasilla(int pCasilla, EEstadoCasilla pEstado){
        tablero.actualizarEstadoCasilla(pCasilla, pEstado);
    }

    public boolean estanTodosBarcosColocados() {
        return listaBarcos.estanTodosBarcosColocados();
    }

    public boolean hayBarcosSinHundir(){
        return listaBarcos.hayBarcosSinHundir();
    }

    public EEstadoCasilla getEstadoCasilla(int pPos){
        return tablero.getEstadoCasilla(pPos);
    }

    // TODO: Eliminar este metodo
    public void imprimirBarcos(){
        System.out.println("--------------------------- JUGADOR ---------------------------");
        listaBarcos.imprimirBarcos();
    }
}
