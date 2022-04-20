package org.vista;

import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

public class JPanelNumBarcos extends JPanel implements Observer {
    private JPanel panelNumFragata;
    private JPanel panelNumPortaviones;
    private JPanel panelNumSubmarino;
    private JPanel panelNumDestructor;


    public JPanelNumBarcos(){
        setLayout(new GridLayout(5,1,0,0));

        panelNumFragata=new JPanel();
        panelNumPortaviones=new JPanel();
        panelNumSubmarino=new JPanel();
        panelNumDestructor=new JPanel();

        panelNumFragata.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString()));
        panelNumPortaviones.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES).toString()));
        panelNumSubmarino.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO).toString()));
        panelNumDestructor.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR).toString()));


        add(panelNumFragata);
        add(panelNumPortaviones);
        add(panelNumSubmarino);
        add(panelNumDestructor);

    }

    private JLabel getText(String text){
        JLabel texto = new JLabel(text);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        return texto;
    }

    @Override
    public void update(Observable o, Object arg) {
        panelNumFragata=new JPanel();
        panelNumPortaviones=new JPanel();
        panelNumSubmarino=new JPanel();
        panelNumDestructor=new JPanel();

        panelNumFragata.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString()));
        panelNumPortaviones.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES).toString()));
        panelNumSubmarino.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO).toString()));
        panelNumDestructor.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR).toString()));

        add(panelNumFragata);
        add(panelNumPortaviones);
        add(panelNumSubmarino);
        add(panelNumDestructor);


    }

}
