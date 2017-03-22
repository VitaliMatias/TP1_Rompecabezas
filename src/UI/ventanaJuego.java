package UI;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Clases.Matriz;

public class ventanaJuego {

	private JFrame frame;
	JTextField[][] controlCuadriculas;
	Matriz tablero;
	static int tamañoTablero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tamañoTablero = 4 ;
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
		renderizarCuadriculas();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int tamañoTablero) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		controlCuadriculas = new JTextField[tamañoTablero][tamañoTablero];
		
		for (int i = 0; i < tamañoTablero ; i++){
			for (int t = 0 ; t < tamañoTablero ; t++){
				JTextField textField = new JTextField();
				textField.setBounds(10 + (30*i), 10 + (30*t), 30, 30);
				frame.getContentPane().add(textField);
				textField.setColumns(2);
				textField.setEditable(false);
				controlCuadriculas[i][t] = textField;
			}
		}
		
		
	}
	
	private void renderizarCuadriculas(){
		for (int i = 0; i < tamañoTablero ; i++){
			for (int t = 0 ; t < tamañoTablero ; t++){
				if (tablero.valorEnPosicion(i, t) != 0)
					controlCuadriculas[i][t].setText(String.valueOf(tablero.valorEnPosicion(i, t)));
			}
		}
	}
}
