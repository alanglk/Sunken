package org.vista;

import javax.swing.*;
import java.awt.*;

public class PanelMisiles extends JPanel {
    private JRadioButton botonBomba;
    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public PanelMisiles(){
        setLayout(new GridLayout(1,4,0,0));
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
