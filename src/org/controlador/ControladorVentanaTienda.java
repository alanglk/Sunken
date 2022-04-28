package org.controlador;

import org.modelo.EEstadoCasilla;
import org.modelo.misil.ETipoMisil;
import org.modelo.tienda.ETipoArmamento;
import org.vista.VentanaTienda;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaTienda implements MouseListener, ItemListener {
    private static ControladorVentanaTienda controlador;

    private ETipoArmamento armamentoSel=null;

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
            if(boton.getText().equals(ETipoArmamento.BOMBATAP.getTipo())){
                armamentoSel=ETipoArmamento.BOMBATAP;
            }
            if(boton.getText().equals(ETipoArmamento.RADAR3X3.getTipo())){
                armamentoSel=ETipoArmamento.RADAR3X3;
            }
        }
        if(e.getSource() instanceof JButton){
            JButton boton= (JButton) e.getSource();
            if(boton.getText().equals("COMPRAR")){
                //TODO HACER ESTO

            }
            if(boton.getText().equals("SALIR TIENDA")){
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
