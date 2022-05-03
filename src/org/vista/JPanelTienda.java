package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.GestorDelJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class JPanelTienda extends JPanel implements Observer {
    private JRadioButton misilOneTap;
    private JRadioButton radar;
    private JLabel numMisilesOneTap;
    private JLabel numRadares;
    private final ButtonGroup grupoBotonesArmamento;


    public JPanelTienda() {
        super();
        GestorDelJuego.getInstance().addObserver(this);

        setLayout(new GridLayout(2,2,0,0));

        grupoBotonesArmamento=new ButtonGroup();

        misilOneTap=getBotonRadio("Bomba Tap");
        add(misilOneTap);
        numMisilesOneTap=new JLabel();
        numMisilesOneTap.setText("5 restantes");
        add(numMisilesOneTap);

        radar=getBotonRadio("Radar 3x3");
        add(radar);
        numRadares=new JLabel();
        numRadares.setText("5 restantes");
        add(numRadares);

        setVisible(true);

    }

    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesArmamento.add(boton);
        return boton;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
