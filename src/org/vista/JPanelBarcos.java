package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.GestorDelJuego;
import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class JPanelBarcos extends JPanel implements Observer {
    private JRadioButton botonFragata;
    private JLabel numBarcosFragata;

    private JRadioButton botonPortaviones;
    private JLabel numBarcosPortaviones;

    private JRadioButton botonSubmarino;
    private JLabel numBarcosSubmarino;

    private JRadioButton botonDestuctor;
    private JLabel numBarcosDestructor;

    private JDespOrien despegableOrientacion;

    private JRadioButton escudo;
    private JLabel numEscudos;
    private final ButtonGroup grupoBotonesBarcos;

    public JPanelBarcos(){
        super();
        GestorDelJuego.getInstance().addObserver(this);

        setLayout(new GridLayout(6,2,0,0));

        grupoBotonesBarcos =new ButtonGroup();

        botonFragata = getBotonRadio("Fragata");
        numBarcosFragata = new JLabel();
        add(botonFragata);
        add(numBarcosFragata);

        botonPortaviones = getBotonRadio("Portaviones");
        numBarcosPortaviones = new JLabel();
        add(botonPortaviones);
        add(numBarcosPortaviones);

        botonSubmarino = getBotonRadio("Submarino");
        numBarcosSubmarino = new JLabel();
        add(botonSubmarino);
        add(numBarcosSubmarino);

        botonDestuctor = getBotonRadio("Destructor");
        numBarcosDestructor = new JLabel();
        add(botonDestuctor);
        add(numBarcosDestructor);

        escudo = getBotonRadio("Poner escudo");
        numEscudos = new JLabel();
        add(escudo);
        add(numEscudos);

        despegableOrientacion = new JDespOrien();
        add(despegableOrientacion);

        actualizarNumBarcos();
        actualizarNumEscudos();
    }


    // ------------------------- Componentes -------------------------------
    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesBarcos.add(boton);
        return boton;
    }

    private String getNumBarcos(ETipoBarco tipo){
        return ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcosNoColocados(tipo).toString() + " por colocar";
    }

    private void actualizarNumBarcos(){
        numBarcosFragata.setText(getNumBarcos(ETipoBarco.FRAGATA));
        numBarcosDestructor.setText(getNumBarcos(ETipoBarco.DESTRUCTOR));
        numBarcosSubmarino.setText(getNumBarcos(ETipoBarco.SUBMARINO));
        numBarcosPortaviones.setText(getNumBarcos(ETipoBarco.PORTAVIONES));
    }

    private String getNumEscudos(){
        return ListaJugadores.getInstance().getEntidad(0).obtenerNumEscudos().toString();
    }

    private void actualizarNumEscudos(){
        numEscudos.setText(getNumEscudos() + " escudos");
        escudo.setSelected(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        actualizarNumBarcos();
        actualizarNumEscudos();
    }
}
