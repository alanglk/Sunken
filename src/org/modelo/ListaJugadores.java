package org.modelo;

import java.util.ArrayList;

public class ListaJugadores {
    private static ListaJugadores miLista;
    private ArrayList<Entidad> lista;

    private ListaJugadores(){
        lista = new ArrayList<Entidad>();

        lista.add(0, new Jugador());
        lista.add(1, new Enemigo());
    }

    public static ListaJugadores getInstance(){
        if(miLista == null) miLista = new ListaJugadores();
        return miLista;
    }

    public Entidad getEntidad(int pos){
        return lista.get(pos);
    }
}
