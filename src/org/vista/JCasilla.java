package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.EEstadoCasilla;
import org.modelo.FormularioModelo;
import org.modelo.GestorDelJuego;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class JCasilla extends JButton implements Observer {

    private boolean mouseEntered = false;
    private final int pos;
    private  boolean casillaEnemigo;

    private Timer timer = null;
    private Color color = Color.CYAN;
    private Color prevcolor = Color.CYAN;

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
    }

    public void setMouseEntered(boolean pMouseEntered){
        mouseEntered = pMouseEntered;

        if(mouseEntered)
            setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        else
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

    }

    private void updateColor(){
        setBackground(color);
    }
    public int getPos(){
        return pos;
    }

    public boolean esEnemigo(){ return casillaEnemigo; }

    @Override
    public void update(Observable o, Object arg) {
        FormularioModelo form = (FormularioModelo) arg;

        if(form != null) {
            EEstadoCasilla estado;

            if (casillaEnemigo) estado = form.casillasEnemigo.get(pos);
            else estado = form.casillasJugador.get(pos);

            if (estado == null){
                prevcolor = color;
                color = Color.pink;
            }else if (estado.equals(EEstadoCasilla.AGUA)) {
                prevcolor = color;
                color = Color.BLUE;
            } else if (estado.equals(EEstadoCasilla.AGUADISPARO)) {
                prevcolor = color;
                color = Color.CYAN;
            } else if (estado.equals(EEstadoCasilla.OCULTO)) {
                prevcolor = color;
                color = Color.LIGHT_GRAY;
            } else if (estado.equals(EEstadoCasilla.BARCO)) {
                prevcolor = color;
                color = Color.BLACK;
            } else if (estado.equals(EEstadoCasilla.HUNDIDO)) {
                prevcolor = color;
                color = Color.RED;
            } else if (estado.equals(EEstadoCasilla.BARCOHUNDIDO)) {
                prevcolor = color;
                color = Color.orange;
            } else if (estado.equals(EEstadoCasilla.ESCUDO)) {
                prevcolor = color;
                color = Color.MAGENTA;
            }else if (estado.equals(EEstadoCasilla.POSRADAR)) {
                prevcolor = color;
                color = Color.GREEN;
            }

           updateColor();
        }else{
            System.err.println("Notificacion y null");
        }
    }
}
