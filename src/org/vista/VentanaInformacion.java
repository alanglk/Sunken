package org.vista;


import org.controlador.ControladorVentanaMenu;

import javax.swing.*;
import java.awt.*;

public class VentanaInformacion extends JFrame {

    public VentanaInformacion(String pInformacion){
        super("SUNKEN");
        JPanelImagen imagen=null;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(500,500));

        if(pInformacion.equals("GANA JUGADOR")){
            imagen=new JPanelImagen("GANA JUGADOR");
        }
        if(pInformacion.equals("GANA ENEMIGO")){
            imagen=new JPanelImagen("GANA ENEMIGO");
        }

        setLocationRelativeTo(null);
        add(imagen,BorderLayout.CENTER);

        setVisible(true);
    }
}
