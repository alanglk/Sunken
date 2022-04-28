package org;

import org.modelo.GestorDelJuego;
import org.vista.VentanaMenu;
import org.vista.VentanaPrincipal;

import javax.swing.*;
import java.awt.*;

public class ProgramaPrincipal {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        System.out.println("███████╗██╗   ██╗███╗   ██╗██╗  ██╗███████╗███╗   ██╗");
        System.out.println("██╔════╝██║   ██║████╗  ██║██║ ██╔╝██╔════╝████╗  ██║");
        System.out.println("███████╗██║   ██║██╔██╗ ██║█████╔╝ █████╗  ██╔██╗ ██║");
        System.out.println("╚════██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝  ██║╚██╗██║");
        System.out.println("███████║╚██████╔╝██║ ╚████║██║  ██╗███████╗██║ ╚████║");
        System.out.println("╚══════╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝");

        GestorDelJuego.getInstance();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaMenu.getInstance();
            }
        });
    }


}
