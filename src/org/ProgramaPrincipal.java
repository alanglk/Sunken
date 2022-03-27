package org;

import org.modelo.Jugador;
import org.modelo.barco.Barco;
import org.modelo.barco.ETipoBarco;
import org.modelo.barco.FactoriaBarcos;
import org.modelo.barco.Fragata;
import org.vista.VentanaPrincipal;

import java.awt.*;

public class ProgramaPrincipal {

    public static void main(String[] args){
        System.out.println("███████╗██╗   ██╗███╗   ██╗██╗  ██╗███████╗███╗   ██");
        System.out.println("██╔════╝██║   ██║████╗  ██║██║ ██╔╝██╔════╝████╗  ██");
        System.out.println("███████╗██║   ██║██╔██╗ ██║█████╔╝ █████╗  ██╔██╗ ██║");
        System.out.println("╚════██║██║   ██║██║╚██╗██║██╔═██╗ ██╔══╝  ██║╚██╗██║");
        System.out.println("███████║╚██████╔╝██║ ╚████║██║  ██╗███████╗██║ ╚████");
        System.out.println("╚══════╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        })
    }
}
