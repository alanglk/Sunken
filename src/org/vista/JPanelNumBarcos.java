package org.vista;

import org.ProgramaPrincipal;
import org.controlador.ControladorVentanaMenu;
import org.controlador.ControladorVentanaPrincipal;
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

        numBarcosFragata=new JLabel(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString());
        numBarcosDestructor=new JLabel(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR).toString());
        numBarcosPortaviones=new JLabel(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES).toString());
        numBarcosSubmarino=new JLabel(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO).toString());

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
    }


    @Override
    public void update(Observable o, Object arg) {
        numBarcosFragata.setText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString());
        System.out.println(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString());
        numBarcosDestructor.setText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR).toString());
        numBarcosPortaviones.setText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES).toString());
        numBarcosSubmarino.setText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO).toString());
        repaint();
    }

}
