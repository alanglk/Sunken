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

    private JTextField numFragata;
    private JTextField numPortaviones;
    private JTextField numSubmarino;
    private JTextField numDestructor;

    private JDespOrien despegableOrientacion;
    private final ButtonGroup grupoBotonesBarcos;

    public PanelBarcos(){
        setLayout(new GridLayout(5,2,0,0));

        grupoBotonesBarcos =new ButtonGroup();

        botonFragata = getBotonRadio("Fragata");
        botonPortaviones = getBotonRadio("Portaviones");
        botonSubmarino = getBotonRadio("Submarino");
        botonDestuctor = getBotonRadio("Destructor");

        numFragata=getText("1");
        numPortaviones=getText("1");
        numSubmarino=getText("1");
        numDestructor=getText("1");

        add(botonFragata);
        add(numFragata);

        add(botonPortaviones);
        add(numPortaviones);

        add(botonSubmarino);
        add(numSubmarino);

        add(botonDestuctor);
        add(numDestructor);

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

    private JTextField getText(String text){
        JTextField texto = new JTextField(text);
        texto.setHorizontalAlignment(SwingConstants.CENTER);

        return texto;
    }


}
