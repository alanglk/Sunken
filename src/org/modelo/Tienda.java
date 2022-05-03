package org.modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class Tienda {
    private static Tienda miTienda = null;
    private HashMap<EObjetoComprable, Integer> precios;
    private ArrayList<EObjetoComprable> stock;

    private Tienda(){
        precios = new HashMap<EObjetoComprable, Integer>();
        stock = new ArrayList<EObjetoComprable>();
    }

    public static Tienda getInstance(){
        if(miTienda == null) miTienda = new Tienda();
        return miTienda;
    }

    public boolean sePuedeComprar(EObjetoComprable pObjeto, int dineroDisponible){
        // PRE: Recibe como parámetros el objeto a comprar y el dinero disponibl
        // POST: Devuelve un booleano indicando si la compra se puede realizar
        int precio = precios.get(pObjeto);
        boolean compraSatisfactoria = precio <= dineroDisponible;

        if(compraSatisfactoria){
            compraSatisfactoria = stock.contains(pObjeto);
        }

        return compraSatisfactoria;
    }

    public int comprar(EObjetoComprable pObjeto, int dineroDisponible){
        // POST: Actualiza el stock y devuelve el dinero después de la compra
        stock.remove(pObjeto);
        return dineroDisponible - precios.get(pObjeto);
    }
}
