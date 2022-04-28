package org.vista;

import org.controlador.ControladorVentanaMenu;
import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame {
    private static VentanaMenu miMenu;

    private JPanel panelMenu;
    private JPanelImagen panelFondo;

    private JButton empezar;
    private JButton salir;

    private VentanaMenu(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(700,700));

        panelMenu=new JPanel();
        panelFondo=new JPanelImagen("EMPEZAR PARTIDA");

        panelMenu.setLayout(new GridLayout(3, 0, 0, 0));
        add(panelMenu, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        add(panelFondo,BorderLayout.CENTER);

        empezar=getBoton("EMPEZAR PARTIDA");
        salir=getBoton("SALIR");
        panelMenu.add(empezar,BorderLayout.CENTER);
        panelMenu.add(salir,BorderLayout.CENTER);
        addMouseListener(ControladorVentanaMenu.getInstance());

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
