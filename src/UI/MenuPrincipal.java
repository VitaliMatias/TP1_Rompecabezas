package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MenuPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void ejecutar() {
		frame.setVisible(true);}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaJuego ventanaJuego = new VentanaJuego();
				ventanaJuego.ejecutar();
			}
		});
		btnNuevoJuego.setBounds(164, 53, 128, 23);
		frame.getContentPane().add(btnNuevoJuego);
		
		JButton btnClasificaciones = new JButton("Clasificaciones");
		btnClasificaciones.setBounds(164, 87, 128, 23);
		frame.getContentPane().add(btnClasificaciones);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnSalir.setBounds(164, 121, 128, 23);
		frame.getContentPane().add(btnSalir);
	}
}
