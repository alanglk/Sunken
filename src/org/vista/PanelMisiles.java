package org.vista;

import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelMisiles extends JPanel {
    private JRadioButton botonBomba;

    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public PanelMisiles(){
        setLayout(new GridLayout(1,4,0,0));
        botonBomba=getBotonRadio("Bomba");

        add(botonBomba);
    }

    private JRadioButton getBotonRadio(String text) {
        JRadioButton boton = new JRadioButton(text);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        grupoBotonesMisiles.add(boton);

        return boton;
    }
}
