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

public class ventanaJuego {

	private JFrame frame;
	private JTextField[][] controlCuadriculas;
	private Matriz tablero;
	private static int tamañoTablero;
	private int movimientos;
	private JTextPane tpPuntaje;
	

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
		renderizar();
		movimientos = 0;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int tamañoTablero) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnArriba = new JButton("New button");
		btnArriba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablero.moverIzquierda();
					movimientos++;
					renderizar();					
				} catch (Exception e) {
				}
			}
		});
		btnArriba.setBounds(314, 203, 45, 23);
		frame.getContentPane().add(btnArriba);
		
		JButton btnIzquierda = new JButton("New button");
		btnIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverArriba();
					movimientos++;
					renderizar();					
				} catch (Exception e2) {
				}
			}
		});
		btnIzquierda.setBounds(263, 227, 45, 23);
		frame.getContentPane().add(btnIzquierda);
		
		JButton btnAbajo = new JButton("New button");
		btnAbajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverDerecha();
					movimientos++;
					renderizar();					
				} catch (Exception e2) {
				}
			}
		});
		btnAbajo.setBounds(314, 227, 45, 23);
		frame.getContentPane().add(btnAbajo);
		
		JButton btnDerecha = new JButton("New button");
		btnDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablero.moverAbajo();
					renderizar();
					movimientos++;
				} catch (Exception e2) {
					
				}
			}
		});
		btnDerecha.setBounds(369, 227, 45, 23);
		frame.getContentPane().add(btnDerecha);
		
		tpPuntaje = new JTextPane();
		tpPuntaje.setEditable(false);
		tpPuntaje.setBounds(10, 227, 231, 20);
		frame.getContentPane().add(tpPuntaje);

		controlCuadriculas = new JTextField[tamañoTablero][tamañoTablero];

		for (int i = 0; i < tamañoTablero; i++) {
			for (int t = 0; t < tamañoTablero; t++) {
				JTextField textField = new JTextField();
				textField.setBounds(10 + (30 * i), 10 + (30 * t), 30, 30);
				frame.getContentPane().add(textField);
				textField.setColumns(2);
				textField.setEditable(true);
				controlCuadriculas[i][t] = textField;
			}
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
