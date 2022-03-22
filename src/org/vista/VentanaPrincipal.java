package org.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel panelControlesBarcos;
    private JPanel panelControladoresMisiles;
    private JPanel panelTableros;
    private JPanel panelControladoresPrincipal;
    private PanelTablero tableroJugador;
    private PanelTablero tableroEnemigo;
    private JRadioButton botonFragata;
    private JRadioButton botonPortaviones;
    private JRadioButton botonSubmarino;
    private JRadioButton botonDestuctor;
    private JRadioButton botonBomba;
    private JComboBox<String> despegableOrientacion;
    private final ButtonGroup grupoBotonesBarcos =new ButtonGroup();
    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public VentanaPrincipal(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, 500, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new BorderLayout());

        // incluir los modulos de la vista
        panelTableros = new JPanel();
        panelControladoresPrincipal=new JPanel();
        panelControlesBarcos =new JPanel();
        panelControladoresMisiles=new JPanel();
        panelControlesBarcos.setLayout(new GridLayout(1,5,0,0));
        panelTableros.setLayout(new GridLayout(2, 1, 0, 10));
        panelControladoresMisiles.setLayout(new GridLayout(1,4,0,0));
        panelControladoresPrincipal.setLayout(new GridLayout(2,4,0,0));

        add(panelControladoresPrincipal,BorderLayout.EAST);
        add(panelTableros, BorderLayout.CENTER);
        panelControladoresPrincipal.add(panelControlesBarcos,BorderLayout.CENTER);
        panelControladoresPrincipal.add(panelControladoresMisiles,BorderLayout.CENTER);

        tableroJugador = new PanelTablero();
        panelTableros.add(tableroJugador);

        tableroEnemigo = new PanelTablero();
        panelTableros.add(tableroEnemigo);

        panelControlesBarcos.add(getBotonDestuctor());
        panelControlesBarcos.add(getBotonFragata());
        panelControlesBarcos.add(getBotonPortaviones());
        panelControlesBarcos.add(getBotonSubmarino());
        panelControlesBarcos.add(getDespegableOrientacion());
        panelControladoresMisiles.add(getBotonBomba());


        setVisible(true);
    }
// ------------------------- BARCOS -------------------------------
    private JRadioButton getBotonFragata(){
        if(botonFragata==null){
            botonFragata=new JRadioButton("Fragata");
            grupoBotonesBarcos.add(botonFragata);
            botonFragata.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonFragata;
    }

    private JRadioButton getBotonPortaviones(){
        if(botonPortaviones==null){
            botonPortaviones=new JRadioButton("Portaviones");
            grupoBotonesBarcos.add(botonPortaviones);
            botonPortaviones.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonPortaviones;
    }

    private JRadioButton getBotonSubmarino(){
        if(botonSubmarino==null){
            botonSubmarino=new JRadioButton("Submarino");
            grupoBotonesBarcos.add(botonSubmarino);
            botonSubmarino.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonSubmarino;
    }

    private JRadioButton getBotonDestuctor(){
        if(botonDestuctor==null){
            botonDestuctor=new JRadioButton("Destructor");
            grupoBotonesBarcos.add(botonDestuctor);
            botonDestuctor.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonDestuctor;
    }

    private JComboBox<String> getDespegableOrientacion(){
        String[] opciones={"Norte","Sur","Este","Oeste"};
        if(despegableOrientacion==null){
            despegableOrientacion=new JComboBox<>(opciones);
        }
        return despegableOrientacion;
    }
// ------------------------- MISILES -------------------------------
    private JRadioButton getBotonBomba() {
        if (botonBomba == null) {
            botonBomba = new JRadioButton("Bomba");
            grupoBotonesMisiles.add(botonBomba);
            botonBomba.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return botonBomba;
    }


}
