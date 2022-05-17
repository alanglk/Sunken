package org.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaManual extends JFrame {

	public VentanaManual(){
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 700));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1186, 845);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea txtrmanualrnconLosBotones = new JTextArea();
		txtrmanualrnconLosBotones.setEditable(false);
		txtrmanualrnconLosBotones.setText("*MANUAL*\r\n-----------\r\nBARCOS\r\n-----------\r\n-->Con los botones que se encuentran a la derecha de nuestro tablero, seleccionamos entre los 5 tipos de barco que queremos colocar. \r\nEstos botones tienen un n\u00FAmero a su derecha que nos indica los barcos de ese tipo que queden por colocar. \r\n-->Adem\u00E1s, podemos elegir de qu\u00E9 manera orientar nuestro barco en el tablero, eligiendo la orientaci\u00F3n \u201CNorte\u201D,\u201DEste\u201D,\u201DOeste\u201D o \u201CSur\u201D.\r\n-->En el caso de que no queramos colocar manualmente los barcos, existe la opci\u00F3n de que se coloquen autom\u00E1ticamente con el bot\u00F3n \u201CColocar Barcos\u201D.\r\n-->Una vez colocados, podemos poner un escudo a 3 de nuestros barcos para que est\u00E9n m\u00E1s protegidos.\r\n\r\nCuando tengamos la configuraci\u00F3n del tablero finalizada, podemos iniciar la partida pulsando el bot\u00F3n \u201CIniciar Partida\u201D.\r\n\r\n-->Una vez comenzada la partida, el jugador tendr\u00E1 el primer turno en el que puede decidir si quiere disparar o colocar un radar.\r\n\r\nBOMBA\r\n---------\r\n-->Si queremos utilizar una bomba, la seleccionamos en el panel y pinchamos en una casilla del tablero enemigo. \r\n\tAl disparar, la casilla se convertir\u00E1 en una de color azul si es agua o roja si se ha tocado un barco. \r\n\tSi hundimos un barco, este se pondr\u00E1 de color amarillo y ser\u00E1 rodeado por agua autom\u00E1ticamente.\r\n-->Adem\u00E1s, disponemos de otro tipo de bomba llamada \u201COneTap\u201D, la cual rompe el escudo de un barco en caso de tenerlo, y si no lo tuviera hunde el barco directamente.\r\n\r\nRADAR\r\n--------\r\n-->El uso del radar ,en cambio, se situar\u00E1 en una casilla aleatoria. \r\n-->Al pulsar el bot\u00F3n \u201CColocar radar\u201D, este se pondr\u00E1 en una casilla al azar del tablero enemigo y se pasar\u00E1 un turno. \r\n-->Cuando se usa un radar, se muestra con un radio de 1 casilla si hay agua o un barco en la casilla que hayamos seleccionado.\r\n-->El n\u00FAmero de radares disponibles se mostrar\u00E1 a la derecha del bot\u00F3n \u201CRecolocar Radar\u201D.\r\n\r\nTIENDA\r\n--------- \r\n-->Aparte de estas dos opciones, existe un bot\u00F3n \u201CTienda\u201D en la que podremos comprar bombas o radares con el dinero inicial de la partida (el cual no puede aumentar en ning\u00FAn momento). \r\n\r\n-->Una vez hayan sido hundidos todos los barcos de un tablero la partida finalizar\u00E1.\r\n-->Finalmente, se abrir\u00E1 una pesta\u00F1a nueva dici\u00E9ndote si has ganado o has perdido.\r\n\r\nBuena suerte!! :-)\r\n");
		txtrmanualrnconLosBotones.setBounds(0, 0, 1186, 846);
		panel.add(txtrmanualrnconLosBotones);
		
		setVisible(true);
    }
}