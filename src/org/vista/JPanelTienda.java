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
    private final ButtonGroup grupoBotonesArmamento;


    public JPanelTienda() {
        super();
        GestorDelJuego.getInstance().addObserver(this);

        setLayout(new GridLayout(2,3,0,0));

        grupoBotonesArmamento=new ButtonGroup();

        misilOneTap=getBotonRadio("Bomba Tap");
        add(misilOneTap);
        numMisilesOneTap=new JLabel();
        numMisilesOneTap.setText(String.valueOf(Tienda.getInstance().obtNumArmamento(EObjetoComprable.BOMBAONETAP)));
        add(numMisilesOneTap);
        dineroJugador=new JLabel();
        dineroJugador.setText("Dinero Jugador: " + String.valueOf(ListaJugadores.getInstance().getEntidad(0).obtenerDineroDisponible()));
        dineroJugador.setForeground(Color.green);
        add(dineroJugador);

        radar=getBotonRadio("Radar 3x3");
        add(radar);
        numRadares=new JLabel();
        numRadares.setText(String.valueOf(Tienda.getInstance().obtNumArmamento(EObjetoComprable.RADAR3x3)));
        add(numRadares);
        dineroEnemigo=new JLabel();
        dineroEnemigo.setText("Dinero Enemigo: " + String.valueOf(ListaJugadores.getInstance().getEntidad(1).obtenerDineroDisponible()));
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
             texto="Dinero Jugador: " + String.valueOf(form.dineroJugador);
        }
        else{
             texto="Dinero Enemigo: " + String.valueOf(form.dineroEnemigo);
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
