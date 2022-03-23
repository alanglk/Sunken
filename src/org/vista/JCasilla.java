package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.EEstadoCasilla;
import org.modelo.GestorDelJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class JCasilla extends JButton implements Observer {

    private boolean mouseEntered = false;
    private final int pos;
    private  boolean casillaEnemigo;

    private Color color = Color.CYAN;

    public JCasilla(int pPos, boolean pCasillaEnemigo){
        super();
        pos = pPos;
        casillaEnemigo = pCasillaEnemigo;
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setBackground(Color.CYAN);
        setOpaque(true);

        addMouseListener(ControladorVentanaPrincipal.getInstance());

        GestorDelJuego.getInstance().addObserver(this);

        update(null, null);
    }

    public void setMouseEntered(boolean pMouseEntered){
        mouseEntered = pMouseEntered;
        updateColor();
    }

    private void updateColor(){
        if(mouseEntered) setBackground(Color.GRAY);
        else setBackground(color);
    }
    public int getPos(){
        return pos;
    }

    public boolean esEnemigo(){ return casillaEnemigo; }

    @Override
    public void update(Observable o, Object arg) {
        EEstadoCasilla estado;


        if(casillaEnemigo) estado = GestorDelJuego.getInstance().getEstadoCasillaEnemigo();
        else estado = GestorDelJuego.getInstance().getEstadoCasillaJugador();

        if(estado.equals(EEstadoCasilla.AGUA))
            color = Color.BLUE;

        if(estado.equals(EEstadoCasilla.OCULTO))
            color = Color.LIGHT_GRAY;

        if(estado.equals(EEstadoCasilla.BARCO))
            color = Color.BLACK;

        updateColor();
    }
}
