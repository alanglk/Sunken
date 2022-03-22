package org.vista;

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
        grupoBotonesMisiles.add(boton);
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}
