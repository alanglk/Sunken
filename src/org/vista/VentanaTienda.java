package org.vista;

import org.controlador.ControladorVentanaMenu;
import org.controlador.ControladorVentanaPrincipal;
import org.controlador.ControladorVentanaTienda;

import javax.swing.*;
import java.awt.*;

public class VentanaTienda extends JFrame{
    private static VentanaTienda miVentanaTienda;

    private JPanelTienda panelTienda;
    private JPanel botones;

    private JButton comprar;
    private JButton salir;


    private VentanaTienda(){
        super("TIENDA");
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        setMinimumSize(new Dimension(350,200));
        setLayout(new GridLayout(2,1,0,0));
        panelTienda=new JPanelTienda();


        add(panelTienda, BorderLayout.SOUTH);

        comprar=getBoton("COMPRAR");
        salir=getBoton("SALIR TIENDA");

        botones=new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        botones.add(comprar,c);
        botones.add(salir,c);
        add(botones);
        addMouseListener(ControladorVentanaTienda.getInstance());
        setVisible(true);
    }

    public static VentanaTienda getInstance() {
        if(VentanaTienda.miVentanaTienda==null){
            VentanaTienda.miVentanaTienda=new VentanaTienda();
        }
        return VentanaTienda.miVentanaTienda;
    }


    private JButton getBoton(String text){
        JButton boton = new JButton(text);
        boton.addMouseListener(ControladorVentanaTienda.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }

}
