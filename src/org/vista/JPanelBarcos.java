package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.FormularioModelo;
import org.modelo.GestorDelJuego;
import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;
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
    private JButton botonIniciarPartida;
    private JButton colocarAutomatico;

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
        JPanel panelBotonInicio = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        botonIniciarPartida=getBoton("Iniciar Partida");
        panelBotonInicio.add(botonIniciarPartida, c);
        colocarAutomatico=getBoton("Colocar Barcos");
        panelBotonInicio.add(colocarAutomatico,c);

        add(despegableOrientacion);
        add(panelBotonInicio);
    }


    // ------------------------- Componentes -------------------------------
    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesBarcos.add(boton);
        return boton;
    }

    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        return boton;
    }

    private String getNumBarcosPorColocar(ETipoBarco tipo, FormularioModelo form){
        return form.numBarcosNoColocados.get(tipo).toString() + " por colocar";
    }

    private String getNumBarcosPorHundir(ETipoBarco tipo, FormularioModelo form){
        return form.numBarcosNoHundidos.get(tipo).toString() + " por hundir";
    }

    private void actualizarNumBarcos(FormularioModelo form){
        if(form.colocandoBarcos) {
            numBarcosFragata.setText(getNumBarcosPorColocar(ETipoBarco.FRAGATA, form));
            numBarcosDestructor.setText(getNumBarcosPorColocar(ETipoBarco.DESTRUCTOR, form));
            numBarcosSubmarino.setText(getNumBarcosPorColocar(ETipoBarco.SUBMARINO, form));
            numBarcosPortaviones.setText(getNumBarcosPorColocar(ETipoBarco.PORTAVIONES, form));
        }else{
            numBarcosFragata.setText(getNumBarcosPorHundir(ETipoBarco.FRAGATA, form));
            numBarcosDestructor.setText(getNumBarcosPorHundir(ETipoBarco.DESTRUCTOR, form));
            numBarcosSubmarino.setText(getNumBarcosPorHundir(ETipoBarco.SUBMARINO, form));
            numBarcosPortaviones.setText(getNumBarcosPorHundir(ETipoBarco.PORTAVIONES, form));
        }
    }

    private String getNumEscudos(FormularioModelo form){
        return form.numEscudosJugador.toString();
    }

    private void actualizarNumEscudos(FormularioModelo form){
        numEscudos.setText(getNumEscudos(form) + " escudos");
        escudo.setSelected(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        FormularioModelo form = (FormularioModelo) arg;
        if(form != null) {
            actualizarNumBarcos(form);
            actualizarNumEscudos(form);
        }
    }
}
