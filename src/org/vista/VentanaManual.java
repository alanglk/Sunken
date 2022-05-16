package org.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaManual extends JFrame {
	private static VentanaManual miVentanaManual;

	private VentanaManual(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(1000,400));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 986, 363);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtrmanualrnconLosBotones = new JTextArea();
		txtrmanualrnconLosBotones.setEditable(false);
		txtrmanualrnconLosBotones.setText("MANUAL\r\n-----------\r\nCon los botones que encontramos a la derecha de nuestro tablero seleccionamos el barco a colocar. \r\nEstos botones tienen un numero a su derecha que nos informa de los barcos de ese tipo que quedan por colocar. Antes de comenzar la partida podemos poner un escudo a 3 de \r\nnuestros barcos para que esten protegidos. \r\nUna vez esten todos colocados al gusto, para comenzar la partida, se pulsara el boton iniciar partida.\r\nUna vez comenzada la partida, el jugador tendra el primer turno en el que puede decidir si disparar o colocar el radar. \r\nAparte de estas dos opciones existe un boton  \"Tienda\" en la que podremos comprar bombas o radares (con el dinero inicial de la partida el cual no puede aumentar en ningun momento). \r\nSi queremos utilizar una bomba con esta seleccionada pinchamos en una casilla del tablero enemigo que se pondra azul si es agua o roja si se ha tocado un barco. \r\nSi en vez de tocar el barco se hunde, este se pondra de color amarillo y ser rodeado por agua automaticamente.\r\nEl uso del radar en  cambio es aleatorio. Al pulsar el boton colocar radar este se pondra en una casilla aleatoria del enemigo y se acaba el turno. \r\nEn el siguiente turno podremos recolocar el radar para ponerlo en otra posicion aleatoria  (acaba turno tambien), usar radar (que muestra en un radio de 1 casilla si hay agua o barco), \r\no usar bombas.\r\nEn el caso de la bomba existen 2 tipos la normal la cual destruye 1 casilla, y la bomba \"OneTap\" la cual rompe el escudo de un barco en caso de tenerlo, y si no lo tuviera \r\nhunde el barco directamente.\r\nUna vez hayan sido hundidos todos los barcos de un tablero la partida finalizara. En este juego, se abrira una pestanna nueva advirtiendote de si has ganado o has perdido.\r\n \r\nBuena suerte!!");
		txtrmanualrnconLosBotones.setBounds(0, 0, 986, 363);
		panel.add(txtrmanualrnconLosBotones);
		
		setVisible(true);
    }

	public static VentanaManual getInstance() {
		if(VentanaManual.miVentanaManual==null){
			VentanaManual.miVentanaManual=new VentanaManual();
        }
        return VentanaManual.miVentanaManual;
	}
}