package org.vista;

import org.controlador.ControladorVentanaPrincipal;

import javax.swing.*;

public class JDespOrien extends JComboBox<String> {

    private final String[] opciones={"Norte","Sur","Este","Oeste"};

    public JDespOrien(){
        super();
        for (String item: opciones) {
            addItem(item);
        }
        addItemListener(ControladorVentanaPrincipal.getInstance());

        System.out.println("Consturctora opciones: " + this.getSelectedItem());
    }
}
