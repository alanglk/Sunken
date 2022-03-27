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
        System.out.println("â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•—â–ˆâ–ˆâ•—   â–ˆâ–ˆâ•—â–ˆâ–ˆâ–ˆâ•—   â–ˆâ–ˆâ•—â–ˆâ–ˆâ•—  â–ˆâ–ˆâ•—â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•—â–ˆâ–ˆâ–ˆâ•—   â–ˆâ–ˆ");
        System.out.println("â–ˆâ–ˆâ•”â•�â•�â•�â•�â•�â–ˆâ–ˆâ•‘   â–ˆâ–ˆâ•‘â–ˆâ–ˆâ–ˆâ–ˆâ•—  â–ˆâ–ˆâ•‘â–ˆâ–ˆâ•‘ â–ˆâ–ˆâ•”â•�â–ˆâ–ˆâ•”â•�â•�â•�â•�â•�â–ˆâ–ˆâ–ˆâ–ˆâ•—  â–ˆâ–ˆ");
        System.out.println("â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•—â–ˆâ–ˆâ•‘   â–ˆâ–ˆâ•‘â–ˆâ–ˆâ•”â–ˆâ–ˆâ•— â–ˆâ–ˆâ•‘â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•”â•� â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•—  â–ˆâ–ˆâ•”â–ˆâ–ˆâ•— â–ˆâ–ˆâ•‘");
        System.out.println("â•šâ•�â•�â•�â•�â–ˆâ–ˆâ•‘â–ˆâ–ˆâ•‘   â–ˆâ–ˆâ•‘â–ˆâ–ˆâ•‘â•šâ–ˆâ–ˆâ•—â–ˆâ–ˆâ•‘â–ˆâ–ˆâ•”â•�â–ˆâ–ˆâ•— â–ˆâ–ˆâ•”â•�â•�â•�  â–ˆâ–ˆâ•‘â•šâ–ˆâ–ˆâ•—â–ˆâ–ˆâ•‘");
        System.out.println("â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•‘â•šâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•”â•�â–ˆâ–ˆâ•‘ â•šâ–ˆâ–ˆâ–ˆâ–ˆâ•‘â–ˆâ–ˆâ•‘  â–ˆâ–ˆâ•—â–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ–ˆâ•—â–ˆâ–ˆâ•‘ â•šâ–ˆâ–ˆâ–ˆâ–ˆ");
        System.out.println("â•šâ•�â•�â•�â•�â•�â•�â•� â•šâ•�â•�â•�â•�â•�â•� â•šâ•�â•�  â•šâ•�â•�â•�â•�â•šâ•�â•�  â•šâ•�â•�â•šâ•�â•�â•�â•�â•�â•�â•�â•šâ•�â•�  â•šâ•�â•�â•�â•�");

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
