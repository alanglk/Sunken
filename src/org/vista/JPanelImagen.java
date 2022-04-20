package org.vista;

import javax.swing.*;
import java.awt.*;

public class JPanelImagen extends JPanel {
    private Image fondo;

    public JPanelImagen(){

    }

    public void paint(Graphics g){
        Dimension dimension=getSize();
        fondo =new ImageIcon(getClass().getResource("/org/imagenes/juego.jpg")).getImage();
        g.drawImage(fondo,0,0,getWidth(),getHeight(),null);

        setOpaque(false);

        super.paintChildren(g);
    }
}
