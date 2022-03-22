package org.controlador;

import org.modelo.GestorDelJuego;
import org.modelo.barco.EOrientaconBarco;
import org.modelo.barco.ETipoBarco;
import org.modelo.misil.ETipoMisil;
import org.vista.JCasilla;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorVentanaPrincipal implements MouseListener {

    private static ControladorVentanaPrincipal controlador;
    private ETipoBarco barcoSel = null;
    private EOrientaconBarco orientacionSel = null;

    private ETipoMisil misilSel = null;

    private ControladorVentanaPrincipal(){}

    public static ControladorVentanaPrincipal getInstance(){
        if(controlador == null) controlador = new ControladorVentanaPrincipal();
        return controlador;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {

        // Casilla del tablero. Llamar al modelo.
        if(e.getSource() instanceof JCasilla){
            JCasilla casilla = (JCasilla) e.getSource();

            int pos = casilla.getPos();
            FormularioControlador datos = new FormularioControlador(pos, barcoSel, orientacionSel, misilSel);

            GestorDelJuego.getInstance().notificarCasillaPresionada(datos);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        // Casilla del tablero
        if(e.getSource() instanceof JCasilla){
            JCasilla casilla = (JCasilla) e.getSource();
            casilla.setMouseEntered(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Casilla del tablero
        if(e.getSource() instanceof JCasilla){
            JCasilla casilla = (JCasilla) e.getSource();
            casilla.setMouseEntered(false);
        }
    }
}
