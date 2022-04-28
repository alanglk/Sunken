package org.modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GaleriaIconos {
    private  static GaleriaIconos miGaleria;
    private String path="/org/imagenes/";
    private final String[] listaImagenesBarcos={"fragata.png","destructor.png","submarino.png","portaviones.png"};
    private final String[] listaImagenesHundidos={"explosion.gif"};
    private final String[] listaImagenesInformacion={"juego.jpg","win.png","gameover.jpg"};

    private GaleriaIconos(){
    }
    public static GaleriaIconos getInstance(){
        if(GaleriaIconos.miGaleria==null){
            GaleriaIconos.miGaleria=new GaleriaIconos();
        }
        return GaleriaIconos.miGaleria;
    }

    public Image obtenerImagen(String pTipoVentana){
        Image imagen = null;
        String informacion = null;
        if(pTipoVentana.equals("EMPEZAR PARTIDA")){
            informacion=listaImagenesInformacion[0];
            imagen=this.crearImagen(informacion);
        }
        if(pTipoVentana.equals("GANA JUGADOR")){
            informacion=listaImagenesInformacion[1];
            imagen=this.crearImagen(informacion);
        }
        if(pTipoVentana.equals("GANA ENEMIGO")){
            informacion=listaImagenesInformacion[2];
            imagen=this.crearImagen(informacion);
        }
        /*if(pTipoVentana.equals("BARCO")){

        }*/
        if(pTipoVentana.equals("HUNDIDO")){
            Random r = new Random();
            int random=r.nextInt(listaImagenesHundidos.length);
            informacion = listaImagenesHundidos[random];
            imagen=this.crearImagen(informacion);
        }

        return imagen;
    }

    public Image crearImagen(String pInformacion){
        Image imagen =new ImageIcon(Objects.requireNonNull(getClass().getResource(path + pInformacion))).getImage();
        return imagen;
    }

}
