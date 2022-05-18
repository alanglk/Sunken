package org.vista;

import org.controlador.ControladorVentanaMenu;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    private static VentanaMenu miMenu;

    private JPanel panelMenu;
    private JPanelImagen panelFondo;

    private JButton empezar;
    private JButton salir;
    private JButton manual;

    private VentanaMenu(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(700,700));

        panelMenu=new JPanel();
        panelFondo=new JPanelImagen("EMPEZAR PARTIDA");

        panelMenu.setLayout(new GridLayout(3, 0, 0, 0));
        getContentPane().add(panelMenu, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        getContentPane().add(panelFondo,BorderLayout.CENTER);

        empezar=getBoton("EMPEZAR PARTIDA");
        manual = getBoton("MANUAL");
        salir=getBoton("SALIR");
        salir.setBackground(new Color(255, 150, 150));

        panelMenu.add(empezar,BorderLayout.CENTER);
        panelMenu.add(manual, BorderLayout.CENTER);
        panelMenu.add(salir,BorderLayout.CENTER);

        setVisible(true);
    }

    public static VentanaMenu getInstance(){
        if(VentanaMenu.miMenu==null){
            VentanaMenu.miMenu=new VentanaMenu();
        }
        return VentanaMenu.miMenu;
    }

    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaMenu.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }
}
