package org.modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GaleriaIconos {
    private  static GaleriaIconos miGaleria;
    private String path="/org/imagenes/";

    private final String[] listaPathTeHunden={"hundido_1.gif", "hundido_2.gif", "hundido_3.gif", "hundido_4.gif"};
    private final String[] listaPathHundesTu={"burla_1.gif", "burla_2.gif"};
    private final String[] listaPahtInformacion={"juego.jpg","win.png","gameover.png"};

    private final ArrayList<ImageIcon> listaImagenesTeHunden;
    private final ArrayList<ImageIcon> listaImagenesHundesTu;
    private final ArrayList<ImageIcon> listaImagenesInformacion;

    private GaleriaIconos(){
        listaImagenesTeHunden = new ArrayList<ImageIcon>();
        listaImagenesHundesTu = new ArrayList<ImageIcon>();
        listaImagenesInformacion = new ArrayList<ImageIcon>();

        for (String elm: listaPathTeHunden) {
            listaImagenesTeHunden.add(crearImagen(elm));
        }

        for (String elm: listaPathHundesTu) {
            listaImagenesHundesTu.add(crearImagen(elm));
        }

        for (String elm: listaPahtInformacion) {
            listaImagenesInformacion.add(crearImagen(elm));
        }
    }

    public static GaleriaIconos getInstance(){
        if(GaleriaIconos.miGaleria==null){
            GaleriaIconos.miGaleria=new GaleriaIconos();
        }
        return GaleriaIconos.miGaleria;
    }

    public ImageIcon obtenerImagen(String pTipoVentana){
        ImageIcon imagen = null;

        if(pTipoVentana.equals("EMPEZAR PARTIDA")){
            imagen=listaImagenesInformacion.get(0);
        }
        if(pTipoVentana.equals("GANA JUGADOR")){
            imagen=listaImagenesInformacion.get(1);
        }
        if(pTipoVentana.equals("GANA ENEMIGO")){
            imagen=listaImagenesInformacion.get(2);
        }

        if(pTipoVentana.equals("HAS HUNDIDO UN BARCO :D")){
            Random r = new Random();
            int random=r.nextInt(listaImagenesHundesTu.size());
            imagen = listaImagenesHundesTu.get(random);
        }

        if(pTipoVentana.equals("TE HAN HUNDIDO UN BARCO D:")){
            Random r = new Random();
            int random=r.nextInt(listaImagenesTeHunden.size());
            imagen = listaImagenesTeHunden.get(random);
        }

        return imagen;
    }

    public ImageIcon crearImagen(String pInformacion){
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(path + pInformacion)));
    }

}
