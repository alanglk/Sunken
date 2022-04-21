package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.EEstadoCasilla;
import org.modelo.GestorDelJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
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

        //setBackground(Color.CYAN);
        //Para poner imagen en las casillas
        //this.setIcon(new ImageIcon(Objects.requireNonNull(JCasilla.class.getResource("/org/imagenes/sea.png"))));
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

        if(casillaEnemigo) estado = GestorDelJuego.getInstance().getEstadoCasillaEnemigo(pos);
        else estado = GestorDelJuego.getInstance().getEstadoCasillaJugador(pos);

        if(estado == null)
            color = Color.MAGENTA;

        else if(estado.equals(EEstadoCasilla.AGUA))
            color = Color.BLUE;

        else if(estado.equals(EEstadoCasilla.AGUADISPARO))
            color = Color.CYAN;

        else if(estado.equals(EEstadoCasilla.OCULTO))
            color = Color.LIGHT_GRAY;

        else if(estado.equals(EEstadoCasilla.BARCO))
            color = Color.BLACK;

        else if(estado.equals(EEstadoCasilla.HUNDIDO))
            color = Color.RED;

        else if(estado.equals(EEstadoCasilla.BARCOHUNDIDO))
            color = Color.orange;

        else if(estado.equals(EEstadoCasilla.ESCUDO))
            color = Color.MAGENTA;

        else if(estado.equals(EEstadoCasilla.POSRADAR))
            color = Color.GREEN;

        updateColor();
    }
}
