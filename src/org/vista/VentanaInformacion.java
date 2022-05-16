package org.vista;


import org.controlador.ControladorVentanaMenu;

import javax.swing.*;
import java.awt.*;

public class VentanaInformacion extends JFrame {

    public VentanaInformacion(String pInformacion){
        super("SUNKEN");
        JPanelImagen imagen=null;

        setSize(new Dimension(400,300));
        if(pInformacion.equals("GANA JUGADOR"))
            imagen=new JPanelImagen("GANA JUGADOR");

        if(pInformacion.equals("GANA ENEMIGO"))
            imagen=new JPanelImagen("GANA ENEMIGO");

        if(pInformacion.equals("TE HAN HUNDIDO UN BARCO D:"))
            imagen=new JPanelImagen("TE HAN HUNDIDO UN BARCO D:");

        if(pInformacion.equals("HAS HUNDIDO UN BARCO :D"))
            imagen=new JPanelImagen("HAS HUNDIDO UN BARCO :D");

        setLocationRelativeTo(null);
        add(imagen,BorderLayout.CENTER);

        setVisible(true);
    }
}
