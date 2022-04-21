package org.vista;

import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PanelOpciones extends JPanel {
    private JRadioButton botonBomba;
    private JRadioButton botonBombaOneTap;
    private JButton botonUsarRadar;
    private JButton botonRecolocarRadar;

    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public PanelOpciones(){
        super();
        setLayout(new GridLayout(2, 1, 0, 0));

        JPanel panelMisiles = new JPanel(new GridLayout(1, 2, 0, 0));

        botonBomba=getBotonRadio("Bomba");
        panelMisiles.add(botonBomba);

        botonBombaOneTap=getBotonRadio("Bomba One Tap");
        panelMisiles.add(botonBombaOneTap);

        add(panelMisiles, BorderLayout.CENTER);

        JPanel panelRadar = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        botonUsarRadar=getBotonRadar(("Usar Radar"));
        panelRadar.add(botonUsarRadar, c);

        botonRecolocarRadar=getBotonRadar(("Recolocar Radar"));
        panelRadar.add(botonRecolocarRadar, c);

        add(panelRadar, BorderLayout.CENTER);
    }

    private JRadioButton getBotonRadio(String text) {
        JRadioButton boton = new JRadioButton(text);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());

        //boton.setIcon(loadImage());

        grupoBotonesMisiles.add(boton);
        return boton;
    }

    private JButton getBotonRadar(String text){
        JButton boton=new JButton(text);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        return boton;
    }

    /*private ImageIcon loadImage(){
        URL url = getClass().getResource("images/submarino.png");
        ImageIcon imageIcon = null; // load the image to a imageIcon
        try {
            assert url != null;
            imageIcon = new ImageIcon(ImageIO.read(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert imageIcon != null;
        System.out.println(imageIcon.getIconHeight());
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }*/
}
