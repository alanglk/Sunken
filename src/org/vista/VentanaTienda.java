package org.vista;

import org.controlador.ControladorVentanaMenu;
import org.controlador.ControladorVentanaPrincipal;
import org.controlador.ControladorVentanaTienda;

import javax.swing.*;
import java.awt.*;

public class VentanaTienda extends JFrame{
    private static VentanaTienda miVentanaTienda;

    private JPanel panelTienda;
    private JPanel panelArmamento;

    private JButton comprar;
    private JButton salir;
    private JRadioButton misilOneTap;
    private JRadioButton radar;
    private JLabel numMisilesOneTap;
    private JLabel numRadares;
    private final ButtonGroup grupoBotonesArmamento;


    private VentanaTienda(){
        super("TIENDA");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setMinimumSize(new Dimension(300,200));

        panelTienda=new JPanel();
        panelArmamento=new JPanel();
        grupoBotonesArmamento=new ButtonGroup();

        panelTienda.setLayout(new GridLayout(2, 2, 0, 0));
        panelArmamento.setLayout(new GridLayout(2, 2, 0, 0));

        add(panelTienda, BorderLayout.SOUTH);
        add(panelArmamento, BorderLayout.CENTER);

        misilOneTap=getBotonRadio("Bomba Tap");
        radar=getBotonRadio("Radar 3x3");

        panelArmamento.add(misilOneTap,BorderLayout.CENTER);
        numMisilesOneTap=new JLabel();
        numMisilesOneTap.setText("5 restantes");
        panelArmamento.add(numMisilesOneTap,BorderLayout.CENTER);

        panelArmamento.add(radar,BorderLayout.CENTER);
        numRadares=new JLabel();
        numRadares.setText("5 restantes");
        panelArmamento.add(numRadares,BorderLayout.CENTER);

        comprar=getBoton("COMPRAR");
        salir=getBoton("SALIR TIENDA");
        panelTienda.add(comprar,BorderLayout.CENTER);
        panelTienda.add(salir,BorderLayout.CENTER);
        addMouseListener(ControladorVentanaTienda.getInstance());
        setVisible(true);
    }

    public static VentanaTienda getInstance() {
        if(VentanaTienda.miVentanaTienda==null){
            VentanaTienda.miVentanaTienda=new VentanaTienda();
        }
        return VentanaTienda.miVentanaTienda;
    }


    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaMenu.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }

    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesArmamento.add(boton);
        return boton;
    }
}
