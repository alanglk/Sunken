package org.vista;

import org.modelo.GaleriaIconos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

public class JPanelImagen extends JPanel {
    private ImageIcon imagen;
    private String tipoVentana;
    public JPanelImagen(String pTipoVentana){
        super();
        tipoVentana = pTipoVentana;

        JLabel display = new JLabel();
        JLabel message = null;
        ImageIcon imageIcon = GaleriaIconos.getInstance().obtenerImagen(tipoVentana);

        if(!Objects.equals(tipoVentana, "EMPEZAR PARTIDA")) {
            message = new JLabel(tipoVentana);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(350, 230, Image.SCALE_DEFAULT));
        }

        display.setIcon(imageIcon);

        add(display);
        if(message != null)
            add(message);

    }
}
