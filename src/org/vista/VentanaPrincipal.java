package org.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JPanel panelTableros;
    private PanelTablero tableroJugador;
    private PanelTablero tableroEnemigo;

    public VentanaPrincipal(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, 500, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new BorderLayout());

        // incluir los modulos de la vista
        panelTableros = new JPanel();
        panelTableros.setLayout(new GridLayout(2, 1, 0, 10));
        add(panelTableros, BorderLayout.CENTER);

        tableroJugador = new PanelTablero();
        panelTableros.add(tableroJugador);

        tableroEnemigo = new PanelTablero();
        panelTableros.add(tableroEnemigo);

        setVisible(true);
    }
}
