package org.vista;

import javax.swing.*;

public class PanelMisiles extends JFrame {
    private JRadioButton botonBomba;
    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public PanelMisiles(){
        add(getBotonBomba());
    }

    private JRadioButton getBotonBomba() {
        if (botonBomba == null) {
            botonBomba = new JRadioButton("Bomba");
            grupoBotonesMisiles.add(botonBomba);
            botonBomba.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonBomba;
    }
}
