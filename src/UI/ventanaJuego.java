package UI;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Clases.Matriz;

import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.Color;

public class VentanaJuego {

	private JFrame frame;
	private JTextField[][] controlCuadriculas;
	private Matriz tablero;
	private static int tamañoTablero;
	private int movimientos;
	private static int cantMezclar;
	private JTextPane tpPuntaje;
	private JTextPane tpMensaje;
	private JButton btnArriba, btnAbajo, btnIzquierda, btnDerecha;

	/**
	 * Launch the application.
	 */
	public void ejecutar() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public VentanaJuego() {
		cantMezclar = 1000;
		tamañoTablero = 4;
		initialize(tamañoTablero);
		tablero = new Matriz(tamañoTablero);
		tablero.mezclarMatriz(cantMezclar);
		controlarVictoria();
		renderizar();
		movimientos = 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int tamañoTablero) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(184, 134, 11));
		frame.setBounds(200, 200, (40*tamañoTablero) + 220, (40*tamañoTablero) + 70);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		// BOTONES--------------------------------------------------------------------BOTONES
		// Boton Arriba
		btnArriba = new JButton("\u21E7");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablero.moverDerecha();
					movimientos++;
					controlarVictoria();
					renderizar();
				} catch (Exception e) {
				}
			}
		});
		btnArriba.setBounds(frame.getWidth() - 125, frame.getHeight() - 110, 50, 30);
		frame.getContentPane().add(btnArriba);

		// Boton Izquierda
		btnIzquierda = new JButton("\u21E6");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverAbajo();
					movimientos++;
					controlarVictoria();
					renderizar();
				} catch (Exception e2) {
				}
			}
		});
		btnIzquierda.setBounds(frame.getWidth() - 175, frame.getHeight() - 80, 50, 30);
		frame.getContentPane().add(btnIzquierda);

		// Boton Abajo
		btnAbajo = new JButton("\u21E9");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverIzquierda();
					movimientos++;
					controlarVictoria();
					renderizar();
				} catch (Exception e2) {
				}
			}
		});
		btnAbajo.setBounds(frame.getWidth() - 125, frame.getHeight() - 80, 50, 30);
		frame.getContentPane().add(btnAbajo);

		// Boton Derecha
		btnDerecha = new JButton("\u21E8");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverArriba();
					movimientos++;
					controlarVictoria();
					renderizar();
				} catch (Exception e2) {
				}
			}
		});
		btnDerecha.setBounds(frame.getWidth() - 75, frame.getHeight() - 80, 50, 30);
		frame.getContentPane().add(btnDerecha);
		// BOTONES--------------------------------------------------------------------BOTONES

		// TextPanel donde se muestra el puntaje
		tpPuntaje = new JTextPane();
		tpPuntaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		tpPuntaje.setBackground(new Color(184, 134, 11));
		tpPuntaje.setEditable(false);
		tpPuntaje.setBounds(frame.getWidth() - 170, 21, 150, 20);
		frame.getContentPane().add(tpPuntaje);

		// TextPanel donde se muestra el mensaje que gano
		tpMensaje = new JTextPane();
		tpMensaje.setBackground(new Color(184, 134, 11));
		tpMensaje.setFont(new Font("Tahoma", Font.BOLD, 17));
		tpMensaje.setBounds(frame.getWidth() - 150, 52, 150, 61);
		tpMensaje.setEditable(false);
		tpMensaje.setEnabled(false);
		frame.getContentPane().add(tpMensaje);

		// Matriz para mantener un control de las cuadriculas y las inicio
		controlCuadriculas = new JTextField[tamañoTablero][tamañoTablero];
		for (int i = 0; i < tamañoTablero; i++) {
			for (int t = 0; t < tamañoTablero; t++) {
				JTextField textField = new JTextField();
				textField.setBackground(new Color(222, 184, 135));
				textField.setFont(new Font("Square721 BT", Font.BOLD, 22));
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				textField.setBounds(20 + (40 * i), 20 + (40 * t), 40, 40);
				frame.getContentPane().add(textField);
				textField.setEditable(false);
				controlCuadriculas[t][i] = textField;
			}
		}
	}

	private void controlarVictoria() {
		if (tablero.estaResuelto()) {
			tpPuntaje.setVisible(false);
			tpMensaje.setEnabled(true);
			tpMensaje.setText("GANASTE!!! \n Puntaje: " + movimientos);
			btnAbajo.setVisible(false);
			btnArriba.setVisible(false);
			btnIzquierda.setVisible(false);
			btnDerecha.setVisible(false);
		}
	}

	private void renderizar() {
		for (int i = 0; i < tamañoTablero; i++) {
			for (int t = 0; t < tamañoTablero; t++) {
				if (tablero.valorEnPosicion(i, t) != 0) {
					if (controlCuadriculas[i][t].isVisible() == false)
						controlCuadriculas[i][t].setVisible(true);
					controlCuadriculas[i][t].setText(String.valueOf(tablero
							.valorEnPosicion(i, t)));
				} else {
					controlCuadriculas[i][t].setText("");
					controlCuadriculas[i][t].setVisible(false);
				}

			}
		}
		tpPuntaje.setText("Movimientos = " + movimientos);
	}
}
