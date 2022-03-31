package org.vista;

import org.controlador.ControladorVentanaPrincipal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PanelBarcos extends JPanel {
    private JRadioButton botonFragata;
    private JRadioButton botonPortaviones;
    private JRadioButton botonSubmarino;
    private JRadioButton botonDestuctor;

    private JDespOrien despegableOrientacion;
    private final ButtonGroup grupoBotonesBarcos;

    public PanelBarcos(){
        setLayout(new GridLayout(5,1,0,0));

        grupoBotonesBarcos =new ButtonGroup();

        botonFragata = getBotonRadio("Fragata");
        botonPortaviones = getBotonRadio("Portaviones");
        botonSubmarino = getBotonRadio("Submarino");
        botonDestuctor = getBotonRadio("Destructor");

        add(botonFragata);
        add(botonPortaviones);
        add(botonSubmarino);
        add(botonDestuctor);

        despegableOrientacion = new JDespOrien();
        add(despegableOrientacion);
    }


    // ------------------------- Componentes -------------------------------
    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesBarcos.add(boton);
        return boton;
    }
}
