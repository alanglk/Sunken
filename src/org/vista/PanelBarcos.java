package org.vista;

import javax.swing.*;
import java.awt.*;

public class PanelBarcos extends JPanel {
    private JRadioButton botonFragata;
    private JRadioButton botonPortaviones;
    private JRadioButton botonSubmarino;
    private JRadioButton botonDestuctor;

    private JComboBox<String> despegableOrientacion;
    private final ButtonGroup grupoBotonesBarcos =new ButtonGroup();

    public PanelBarcos(){
        setLayout(new GridLayout(1,5,0,0));
        botonFragata = getBotonRadio("Fragata");
        botonPortaviones = getBotonRadio("Portaviones");
        botonSubmarino = getBotonRadio("Submarino");
        botonDestuctor = getBotonRadio("Destructor");

        add(botonFragata);
        add(botonPortaviones);
        add(botonSubmarino);
        add(botonDestuctor);

        despegableOrientacion = getDespegableOrientacion();
        add(despegableOrientacion);
    }


    // ------------------------- Componentes -------------------------------
    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        grupoBotonesBarcos.add(boton);
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }

    private JComboBox<String> getDespegableOrientacion(){
        String[] opciones={"Norte","Sur","Este","Oeste"};
        if(despegableOrientacion==null){
            despegableOrientacion=new JComboBox<>(opciones);
        }
        return despegableOrientacion;
    }
}
