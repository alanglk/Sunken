package org.vista;


import org.controlador.ControladorVentanaPrincipal;
import org.modelo.GestorDelJuego;
import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class JPanelNumBarcos extends JPanel implements Observer {
    private JPanel panelNumFragata;
    private JPanel panelNumPortaviones;
    private JPanel panelNumSubmarino;
    private JPanel panelNumDestructor;

    private JLabel numBarcosFragata;
    private JLabel numBarcosPortaviones;
    private JLabel numBarcosSubmarino;
    private JLabel numBarcosDestructor;


    private JButton botonReiniciar;




    public JPanelNumBarcos(){
        setLayout(new GridLayout(5,1,0,0));

        panelNumFragata=new JPanel();
        panelNumPortaviones=new JPanel();
        panelNumSubmarino=new JPanel();
        panelNumDestructor=new JPanel();

        numBarcosFragata=new JLabel("4");
        numBarcosDestructor=new JLabel("3");
        numBarcosPortaviones=new JLabel("1");
        numBarcosSubmarino=new JLabel("2");

        panelNumFragata.add(numBarcosFragata);
        panelNumPortaviones.add(numBarcosPortaviones);
        panelNumSubmarino.add(numBarcosSubmarino);
        panelNumDestructor.add(numBarcosDestructor);


        add(panelNumFragata);
        add(panelNumPortaviones);
        add(panelNumSubmarino);
        add(panelNumDestructor);

        botonReiniciar=new JButton("REINICIAR PARTIDA");
        add(botonReiniciar);
        addMouseListener(ControladorVentanaPrincipal.getInstance());
        GestorDelJuego.getInstance().addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        numBarcosFragata.setText(String.valueOf(4- ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA)));
        System.out.println(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString());
        numBarcosDestructor.setText(String.valueOf(3- ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR)));
        numBarcosPortaviones.setText(String.valueOf(1- ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES)));
        numBarcosSubmarino.setText(String.valueOf(2- ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO)));
        repaint();
    }

}
