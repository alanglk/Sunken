package org.vista;



import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {

    private final int size = 10;

    public PanelTablero() {
        super();
        setLayout(new BorderLayout());

        // creamos los ejes
        anadirEjeX();
        anadirEjeY();

        // creamos el tablero
        crearTablero();
    }

    private void anadirEjeX(){
        for(int i = 0; i < size; i++){
            add(new JLabel(String.valueOf(i)), BorderLayout.NORTH);
        }
    }

    private void anadirEjeY(){
        char car = 'A';
        for(int i = 0; i < size; i++){
            add(new JLabel(String.valueOf(car)), BorderLayout.WEST);
            car++;
        }
    }

    private void crearTablero(){

    }
}
