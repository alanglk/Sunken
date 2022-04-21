package org.controlador;

import org.vista.VentanaMenu;
import org.vista.VentanaPrincipal;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaMenu implements MouseListener, ItemListener {
    private static ControladorVentanaMenu controlador;


    private ControladorVentanaMenu(){}

    public static ControladorVentanaMenu getInstance(){
        if(controlador == null) controlador = new ControladorVentanaMenu();
        return controlador;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        if(e.getSource() instanceof JButton){
            JButton boton = (JButton) e.getSource();
            if (boton.getText().equals("EMPEZAR PARTIDA")){
                VentanaPrincipal.getInstance();
                VentanaMenu.getInstance().setVisible(false);
            }
            else{
               System.exit(0);
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


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
