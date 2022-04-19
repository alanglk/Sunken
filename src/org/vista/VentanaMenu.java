package org.vista;

import org.controlador.ControladorVentanaMenu;
import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JFrame {
    private JPanel panelMenu;
    private static VentanaMenu miMenu;
    private ImagenFondo fondo;
    private JButton empezar;
    private JButton salir;

    private VentanaMenu(){
        super("SUNKEN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, 500, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new BorderLayout());

        //Incluir botones
        panelMenu=new JPanel();
        fondo= new ImagenFondo();

        panelMenu.setLayout(new GridLayout(2, 1, 0, 0));
        add(panelMenu, BorderLayout.SOUTH);


        empezar=getBoton("EMPEZAR PARTIDA");
        salir=getBoton("SALIR");

        setContentPane(fondo);
        add(empezar);
        add(salir);
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
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        boton.setHorizontalAlignment(SwingConstants.CENTER);

        return boton;
    }


    class ImagenFondo extends JPanel{
        private Image imagen;

        public void paint(Graphics g){
            imagen =new ImageIcon(getClass().getResource("/org/imagenes/juego.png")).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);

            setOpaque(false);

            super.paint(g);
        }
    }
}
