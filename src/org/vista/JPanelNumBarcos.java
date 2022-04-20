package org.vista;

import org.modelo.ListaJugadores;
import org.modelo.barco.ETipoBarco;

import javax.swing.*;
import java.awt.*;

public class JPanelNumBarcos extends JPanel {
    private JPanel numFragata;
    private JPanel numPortaviones;
    private JPanel numSubmarino;
    private JPanel numDestructor;

    public JPanelNumBarcos(){
        setLayout(new GridLayout(5,1,0,0));

        numFragata=new JPanel();
        numPortaviones=new JPanel();
        numSubmarino=new JPanel();
        numDestructor=new JPanel();

        numFragata.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.FRAGATA).toString()));
        numPortaviones.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.PORTAVIONES).toString()));
        numSubmarino.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.SUBMARINO).toString()));
        numDestructor.add(getText(ListaJugadores.getInstance().getEntidad(0).obtenerNumBarcos(ETipoBarco.DESTRUCTOR).toString()));


        add(numFragata);
        add(numPortaviones);
        add(numSubmarino);
        add(numDestructor);

    }

    private JLabel getText(String text){
        JLabel texto = new JLabel(text);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        return texto;
    }

}
