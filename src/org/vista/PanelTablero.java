package org.vista;



import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {

    private final int size = 10;
    private JPanel panelCasillas;
    private final boolean panelEnemigo;

    public PanelTablero(boolean pPanelEnemigo) {
        super();
        panelEnemigo = pPanelEnemigo;

        setLayout(new BorderLayout());

        crearPanelCasillas();
        add(panelCasillas, BorderLayout.CENTER);
    }

    private void crearPanelCasillas(){
        panelCasillas = new JPanel();
        panelCasillas.setLayout(new GridLayout(size + 1, size + 1, 0, 0));

        // Espacio en blanco superior que separa los ejes
        panelCasillas.add(new JLabel(""));

        // Coordenadas del eje X
        for(int i = 0; i < size; i++){
            JLabel coord = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            coord.setOpaque(true);

            if(i % 2 == 0)
                coord.setBackground(Color.GRAY);
            else
                coord.setBackground(Color.LIGHT_GRAY);

            panelCasillas.add(coord);
        }

        char car = 'A';
        for(int j = 0; j < size; j++){
            // Coordenada del eje Y
            panelCasillas.add(new JLabel(String.valueOf(car), SwingConstants.CENTER));

            // Fila de casillas
            for(int i = 0; i < size; i++)
                panelCasillas.add(new JCasilla(10*j+i, panelEnemigo));

            car++;
        }
    }
}
