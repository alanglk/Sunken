package org.vista;

import org.controlador.ControladorVentanaPrincipal;
import org.modelo.FormularioModelo;
import org.modelo.GestorDelJuego;
import org.modelo.ListaJugadores;
import org.modelo.misil.ETipoMisil;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PanelOpciones extends JPanel implements Observer {
    private JRadioButton botonBomba;
    private JLabel numBombas;

    private JRadioButton reparar;

    private JRadioButton botonBombaOneTap;
    private JLabel numBombasOneTap;

    private JButton botonUsarRadar;
    private JButton botonRecolocarRadar;
    private JButton botonTienda;
    private JLabel numUsosRadar;


    private final ButtonGroup grupoBotonesMisiles=new ButtonGroup();

    public PanelOpciones(){
        super();
        GestorDelJuego.getInstance().addObserver(this);

        setLayout(new GridLayout(4, 1, 0, 0));

        JPanel panelMisiles = new JPanel(new GridLayout(2, 2, 0, 0));

        botonBomba=getBotonRadio("Bomba");
        numBombas = new JLabel();
        panelMisiles.add(botonBomba);
        panelMisiles.add(numBombas);

        botonBombaOneTap=getBotonRadio("BombaTap");
        numBombasOneTap = new JLabel();
        panelMisiles.add(botonBombaOneTap);
        panelMisiles.add(numBombasOneTap);

        add(panelMisiles);

        reparar=getBotonRadio(("Reparar barco"));
        add(reparar);

        JPanel panelRadar = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        botonUsarRadar=getBoton(("Usar Radar"));
        panelRadar.add(botonUsarRadar, c);

        botonRecolocarRadar=getBoton(("Recolocar Radar"));
        panelRadar.add(botonRecolocarRadar, c);

        add(panelRadar);

        numUsosRadar = new JLabel();
        add(numUsosRadar);

        botonTienda=getBoton("TIENDA");
        add(botonTienda);
    }

    private JRadioButton getBotonRadio(String text) {
        JRadioButton boton = new JRadioButton(text);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());

        //boton.setIcon(loadImage());

        grupoBotonesMisiles.add(boton);
        return boton;
    }

    private JButton getBoton(String text){
        JButton boton=new JButton(text);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.addMouseListener(ControladorVentanaPrincipal.getInstance());
        return boton;
    }

    // Num bombas
    private String getNumMisilesDisponibles(ETipoMisil tipo, FormularioModelo form){
        return form.numMisilesJugador.get(tipo).toString();
    }

    private void actualizarNumMisiles(FormularioModelo form){
        numBombas.setText(getNumMisilesDisponibles(ETipoMisil.BOMBA, form));
        numBombasOneTap.setText(getNumMisilesDisponibles(ETipoMisil.BOMBAONETAP, form));
    }

    // Num radar
    private String getNumUsosRadar(FormularioModelo form){
        String strUsos = "";
        Integer num = form.numUsosRadarJugador;

        if(num < 0)
            strUsos = "Recoloca el radar para ver su número de usos.";
        else
            strUsos = num.toString() + " usos del radar";

        return strUsos;
    }

    private void actualizarNumRadar(FormularioModelo form){
        numUsosRadar.setText(getNumUsosRadar(form));
    }

    @Override
    public void update(Observable o, Object arg) {
        FormularioModelo form = (FormularioModelo) arg;
        if(form != null) {
            actualizarNumMisiles(form);
            actualizarNumRadar(form);
        }
    }

}
