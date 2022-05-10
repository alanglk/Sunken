package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.controlador.ControladorVentanaTienda;
import org.modelo.*;
import org.modelo.barco.ETipoBarco;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class JPanelTienda extends JPanel implements Observer {
    private JRadioButton misilOneTap;
    private JRadioButton radar;
    private JLabel numMisilesOneTap;
    private JLabel numRadares;
    private JLabel dineroJugador;
    private JLabel dineroEnemigo;
    private JLabel precioBombaOneTap;
    private JLabel precioRadar3x3;
    private JLabel armamento;
    private JLabel almacen;
    private JLabel precio;
    private JLabel dinero;
    private final ButtonGroup grupoBotonesArmamento;


    public JPanelTienda() {
        super();
        GestorDelJuego.getInstance().addObserver(this);

        setLayout(new GridLayout(3,4,0,0));

        grupoBotonesArmamento=new ButtonGroup();

        armamento=new JLabel();
        armamento.setText("ARMAMENTO");
        armamento.setForeground(Color.BLUE);
        add(armamento);
        almacen=new JLabel();
        almacen.setText("ALMACEN");
        almacen.setForeground(Color.BLUE);
        add(almacen);
        precio=new JLabel();
        precio.setText("PRECIO");
        precio.setForeground(Color.BLUE);
        add(precio);
        dinero=new JLabel();
        dinero.setText("DINERO");
        dinero.setForeground(Color.BLUE);
        add(dinero);
        misilOneTap=getBotonRadio("Bomba Tap");
        add(misilOneTap);
        numMisilesOneTap=new JLabel();
        numMisilesOneTap.setText(String.valueOf(Tienda.getInstance().obtNumArmamento(EObjetoComprable.BOMBAONETAP)));
        add(numMisilesOneTap);
        precioBombaOneTap=new JLabel();
        precioBombaOneTap.setText(String.valueOf(Tienda.getInstance().getPrecio(EObjetoComprable.BOMBAONETAP)));
        add(precioBombaOneTap);
        dineroJugador=new JLabel();
        dineroJugador.setText("Jugador: " + String.valueOf(ListaJugadores.getInstance().getEntidad(0).obtenerDineroDisponible()));
        dineroJugador.setForeground(Color.green);
        add(dineroJugador);

        radar=getBotonRadio("Radar 3x3");
        add(radar);
        numRadares=new JLabel();
        numRadares.setText(String.valueOf(Tienda.getInstance().obtNumArmamento(EObjetoComprable.RADAR3x3)));
        add(numRadares);
        precioRadar3x3=new JLabel();
        precioRadar3x3.setText(String.valueOf(Tienda.getInstance().getPrecio(EObjetoComprable.RADAR3x3)));
        add(precioRadar3x3);
        dineroEnemigo=new JLabel();
        dineroEnemigo.setText("Enemigo: " + String.valueOf(ListaJugadores.getInstance().getEntidad(1).obtenerDineroDisponible()));
        dineroEnemigo.setForeground(Color.red);
        add(dineroEnemigo);

        setVisible(true);

    }

    private JRadioButton getBotonRadio(String text){
        JRadioButton boton = new JRadioButton(text);
        boton.addMouseListener(ControladorVentanaTienda.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        grupoBotonesArmamento.add(boton);
        return boton;
    }

    private String getDineroRestante(String turno, FormularioModelo form){
        String texto=null;
        if(turno.equals("Jugador")){
             texto="Jugador: " + String.valueOf(form.dineroJugador);
        }
        else{
             texto="Enemigo: " + String.valueOf(form.dineroEnemigo);
        }
        return texto;
    }

    private void actualizarDinero(FormularioModelo form){
        dineroJugador.setText(getDineroRestante("Jugador",form));
        dineroEnemigo.setText(getDineroRestante("Enemigo",form));
    }

    private int getNumArmamento(EObjetoComprable armamento, FormularioModelo form){
        int numArmamento=0;
        if(armamento.equals(EObjetoComprable.BOMBAONETAP)){
            numArmamento= form.numBombaOneTap;
        }
        if(armamento.equals(EObjetoComprable.RADAR3x3)){
            numArmamento= form.numRadar3x3;
        }
        return numArmamento;
    }
    private void actualizarAlmacenDisponible(FormularioModelo form){
        numMisilesOneTap.setText(String.valueOf(getNumArmamento(EObjetoComprable.BOMBAONETAP,form)));
        numRadares.setText(String.valueOf(getNumArmamento(EObjetoComprable.RADAR3x3,form)));
    }

    @Override
    public void update(Observable o, Object arg) {
        FormularioModelo form = (FormularioModelo) arg;
        if(form != null) {
            actualizarDinero(form);
            actualizarAlmacenDisponible(form);
        }
    }
}
