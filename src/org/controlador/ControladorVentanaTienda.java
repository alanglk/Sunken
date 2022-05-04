package org.controlador;

import org.modelo.EObjetoComprable;
import org.modelo.GestorDelJuego;
import org.modelo.ListaJugadores;
import org.modelo.Tienda;
import org.vista.VentanaTienda;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaTienda implements MouseListener, ItemListener {
    private static ControladorVentanaTienda controlador;

    private EObjetoComprable armamentoSel=null;

    private ControladorVentanaTienda(){}

    public static ControladorVentanaTienda getInstance(){
        if(controlador == null) controlador = new ControladorVentanaTienda();
        return controlador;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof JRadioButton){
            JRadioButton boton = (JRadioButton) e.getSource();
            if(boton.getText().equals("Bomba Tap")){
                armamentoSel=EObjetoComprable.BOMBAONETAP;
            }
            if(boton.getText().equals("Radar 3x3")){
                armamentoSel=EObjetoComprable.RADAR3x3;
            }
        }
        if(e.getSource() instanceof JButton){
            JButton boton= (JButton) e.getSource();
            if(boton.getText().equals("COMPRAR")){
                //TODO HACER ESTO
                GestorDelJuego.getInstance().comprar(armamentoSel, ListaJugadores.getInstance().getEntidad(0).obtenerDineroDisponible());
            }
            if(boton.getText().equals("SALIR TIENDA")) {
                VentanaTienda.getInstance().setVisible(false);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
