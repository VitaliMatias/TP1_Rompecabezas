package UI;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases.Matriz;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class ventanaJuego {

	private JFrame frame;
	private JTextField[][] controlCuadriculas;
	private Matriz tablero;
	private static int tamañoTablero;
	private int movimientos;
	private JTextPane tpPuntaje;
	private JTextPane tpMensaje;
	private JButton btnArriba, btnAbajo, btnIzquierda, btnDerecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tamañoTablero = 4;
					ventanaJuego window = new ventanaJuego();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ventanaJuego() {
		initialize(tamañoTablero);
		tablero = new Matriz(tamañoTablero);
		tablero.mezclarMatriz(1);
		controlarVictoria();
		renderizar();
		movimientos = 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int tamañoTablero) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnArriba = new JButton("\u21E7");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablero.moverIzquierda();
					controlarVictoria();
					movimientos++;
					renderizar();
				} catch (Exception e) {
				}
			}
		});
		btnArriba.setBounds(314, 189, 45, 30);
		frame.getContentPane().add(btnArriba);

		btnIzquierda = new JButton("\u21E6");
		btnIzquierda.addActionListener(new ActionListener() {
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
		btnIzquierda.setBounds(263, 220, 50, 30);
		frame.getContentPane().add(btnIzquierda);

		btnAbajo = new JButton("\u21E9");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverDerecha();
					movimientos++;
					controlarVictoria();
					renderizar();
				} catch (Exception e2) {
				}
			}
		});
		btnAbajo.setBounds(314, 220, 45, 30);
		frame.getContentPane().add(btnAbajo);

		btnDerecha = new JButton("\u21E8");
		btnDerecha.addActionListener(new ActionListener() {
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
		btnDerecha.setBounds(361, 220, 50, 30);
		frame.getContentPane().add(btnDerecha);

		tpPuntaje = new JTextPane();
		tpPuntaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		tpPuntaje.setBackground(Color.LIGHT_GRAY);
		tpPuntaje.setEditable(false);
		tpPuntaje.setBounds(10, 227, 231, 20);
		frame.getContentPane().add(tpPuntaje);

		tpMensaje = new JTextPane();
		tpMensaje.setBackground(Color.LIGHT_GRAY);
		tpMensaje.setFont(new Font("Tahoma", Font.BOLD, 17));
		tpMensaje.setBounds(263, 117, 148, 61);
		tpMensaje.setEditable(false);
		tpMensaje.setEnabled(false);
		frame.getContentPane().add(tpMensaje);

		controlCuadriculas = new JTextField[tamañoTablero][tamañoTablero];

		for (int i = 0; i < tamañoTablero; i++) {
			for (int t = 0; t < tamañoTablero; t++) {
				JTextField textField = new JTextField();
				textField.setBounds(10 + (30 * i), 10 + (30 * t), 30, 30);
				frame.getContentPane().add(textField);
				textField.setColumns(2);
				textField.setEditable(false);
				controlCuadriculas[i][t] = textField;
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
				if (tablero.valorEnPosicion(i, t) != 0)
					controlCuadriculas[t][i].setText(String.valueOf(tablero.valorEnPosicion(i, t)));
				else
					controlCuadriculas[t][i].setText("");
			}
		}
		tpPuntaje.setText("Movimientos = " + movimientos);
	}
}
