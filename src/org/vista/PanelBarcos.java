package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.Entidad;
import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;
import org.modelo.barco.Fragata;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PanelBarcos extends JPanel {
    private JPanelNumBarcos numBarcos;
    private JPanelBotonBarcos botonesBarcos;

    public PanelBarcos(){
        setLayout(new GridLayout(1,2,0,0));

        numBarcos=new JPanelNumBarcos();
        botonesBarcos=new JPanelBotonBarcos();
        add(botonesBarcos,BorderLayout.CENTER);
        add(numBarcos,BorderLayout.CENTER);
    }

}
