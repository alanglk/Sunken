package org.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private PanelTablero tableroJugador;
    private PanelTablero tableroEnemigo;

    public VentanaPrincipal(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, 300, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new BorderLayout());

        // incluir los modulos de la vista
        tableroJugador = new PanelTablero();
        add(tableroJugador, BorderLayout.CENTER);

        tableroEnemigo = new PanelTablero();
        add(tableroEnemigo, BorderLayout.CENTER);


        setVisible(true);
    }
}
