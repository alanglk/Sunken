package org.vista;

import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class JCasilla extends JButton{

    private final int width = 1;
    private final int height = 1;
    private boolean mouseEntered = false;

    private final boolean casillaEnemigo;
    private final int pos;

    public JCasilla(int pPos, boolean pCasillaEnemigo){
        super();
        pos = pPos;
        casillaEnemigo = pCasillaEnemigo;

        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setBackground(Color.CYAN);
        setOpaque(true);

        addMouseListener(ControladorVentanaPrincipal.getInstance());
    }

    public void setMouseEntered(boolean pMouseEntered){
        mouseEntered = pMouseEntered;
        updateMouseHover();
    }
    private void updateMouseHover(){
        if(mouseEntered) setBackground(Color.GRAY);
        else setBackground(Color.CYAN);
    }

    public int getPos(){
        return pos;
    }

    public boolean esEnemigo(){ return casillaEnemigo; }
}
