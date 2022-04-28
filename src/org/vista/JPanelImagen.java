package org.vista;

import org.modelo.GaleriaIconos;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JPanelImagen extends JPanel {
    private Image imagen;
    private String tipoVentana;
    public JPanelImagen(String pTipoVentana){
        tipoVentana = pTipoVentana;
    }
    @Override
    public void paint(Graphics g){
        Dimension dimension=getSize();
        imagen = GaleriaIconos.getInstance().obtenerImagen(tipoVentana);
        g.drawImage(imagen,0,0,getWidth(),getHeight(),null);
        this.setSize(getWidth(),getHeight());
        setOpaque(false);

       super.paintComponent(g);
    }
}
