package UI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Clases.Matriz;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;

public class VentanaJuego {

	private JFrame frame;
	private JTextField[][] controlCuadriculas;
	private Matriz tablero;
	private int tamañoTablero;
	private int movimientos;
	private long tmpInicial;
	private static int cantMezclar;
	private JTextPane tpPuntaje;
	private JTextPane tpMensaje;
	private JButton btnNormal;
	private JButton btnDificil;
	private Panel panel;
	private JButton btnRepetir;

	/**
	 * Create the application.
	 */
	public VentanaJuego(int posX, int posY) {
		cantMezclar = 1;
		initialize(posX, posY);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int posX, int posY) {
		tmpInicial = System.currentTimeMillis();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(184, 134, 11));
		frame.setBounds(posX, posY, 440, 270);
		frame.setTitle("Rompecabezas");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// TextPanel donde se muestra el puntaje
		tpPuntaje = new JTextPane();
		tpPuntaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				switch (key.getKeyCode()) {
				case KeyEvent.VK_UP:
					try {
						tablero.moverDerecha();
						movimientos++;
						controlarVictoria();
						renderizar();
					} catch (Exception e) {
					}
					break;
				case KeyEvent.VK_DOWN:
					try {
						tablero.moverIzquierda();
						movimientos++;
						controlarVictoria();
						renderizar();
					} catch (Exception e) {
					}
					break;
				case KeyEvent.VK_RIGHT:
					try {
						tablero.moverArriba();
						movimientos++;
						controlarVictoria();
						renderizar();
					} catch (Exception e2) {
					}
					break;
				case KeyEvent.VK_LEFT:
					try {
						tablero.moverAbajo();
						movimientos++;
						controlarVictoria();
						renderizar();
					} catch (Exception e2) {
					}
					break;
				default:
					break;
				}
			}
		});
		tpPuntaje.setFont(new Font("Tahoma", Font.BOLD, 13));
		tpPuntaje.setBackground(new Color(184, 134, 11));
		tpPuntaje.setEditable(false);
		tpPuntaje.setVisible(false);
		tpPuntaje.setBounds(frame.getWidth() - 170, 21, 150, 20);
		frame.getContentPane().add(tpPuntaje);
		tpPuntaje.setFocusable(true);
		tpPuntaje.requestFocus();

		// TextPanel donde se muestra el mensaje que gano
		tpMensaje = new JTextPane();
		tpMensaje.setText("Usar Flechas\r\n\u00BAArriba\r\n\u00BAAbajo\r\n\u00BAIzquierda\r\n\u00BADerecha");
		tpMensaje.setBackground(new Color(184, 134, 11));
		tpMensaje.setFont(new Font("Tahoma", Font.BOLD, 17));
		tpMensaje.setBounds(260, 52, 134, 116);
		tpMensaje.setEditable(false);
		tpMensaje.setVisible(false);
		frame.getContentPane().add(tpMensaje);
		
		panel = new Panel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(189, 183, 107));
		panel.setBounds(121, 31, 151, 157);
		frame.getContentPane().add(panel);
		
		JButton btnFacil = new JButton("Facil");
		btnFacil.setBounds(10, 5, 131, 23);
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tamañoTablero = 3;
				frame.setTitle("Rompecabezas - FACIL");
				crearCuadricula(tamañoTablero);
			}
		});
		panel.setLayout(null);
		panel.add(btnFacil);
		
		btnNormal = new JButton("Normal");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamañoTablero = 4;
				frame.setTitle("Rompecabezas - NORMAL");
				crearCuadricula(tamañoTablero);
			}
		});
		btnNormal.setBounds(10, 39, 131, 23);
		panel.add(btnNormal);
		
		btnDificil = new JButton("Dificil");
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamañoTablero = 5;
				frame.setTitle("Rompecabezas - DIFICIL");
				crearCuadricula(tamañoTablero);
			}
		});
		btnDificil.setBounds(10, 71, 131, 23);
		panel.add(btnDificil);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnSalir.setBounds(10, 123, 131, 23);
		panel.add(btnSalir);

		
		btnRepetir = new JButton("Repetir");
		btnRepetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaJuego nuevaVentana = new VentanaJuego(frame.getX(),frame.getY());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));				
			}
		});
		btnRepetir.setBounds(250, 168, 89, 23);
		frame.getContentPane().add(btnRepetir);	
		btnRepetir.setVisible(false);
		
	}
	
	private void crearCuadricula(int tamañoTablero){
		movimientos = 0;
		panel.setEnabled(false);
		panel.setVisible(false);
		tpPuntaje.setVisible(true);
		tpMensaje.setEnabled(true);
		tpMensaje.setVisible(true);
		tablero = new Matriz(tamañoTablero);
		tablero.mezclarMatriz(cantMezclar);
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
		renderizar();
	}

	private void controlarVictoria() {
		if (tablero.estaResuelto()) {
			tpPuntaje.setVisible(false);
			tpMensaje.setText("GANASTE!!! \n Puntaje: " + movimientos);
			btnRepetir.setVisible(true);
		}
	}

	private void renderizar() {		
		tpPuntaje.requestFocus();
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
		controlarVictoria();
	}

}
