package UI;

import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



public class VentanaClasificacion {
	
	public enum Dificultades {
		FACIL, NORMAL, DIFICIL;
	}

	private JFrame frame;
	private JTable table;


	public VentanaClasificacion() {
		initialize();
		frame.setVisible(true);
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnFacil = new JButton("Facil");
		frame.getContentPane().add(btnFacil);
		
		JButton btnNormal = new JButton("Normal");
		frame.getContentPane().add(btnNormal);
		
		JButton btnDificil = new JButton("Dificil");
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnDificil);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setWheelScrollingEnabled(false);
		scrollPane.setBounds(10, 100, 400, 200);
		frame.getContentPane().add(scrollPane);
		
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Nombre");
		model.addColumn("Movimientos");
		
		model.addRow(new String[] {"JUGADOR","PUNTAJE"});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
		model.addRow(new String[] {"",""});
	
		
		table.setModel(model);
		scrollPane.add(table);
		
	}
	
	public void rellenarTabla(Dificultades dificultad){
		try {
			FileOutputStream file = new FileOutputStream("FIles/Clasificaiones.xlm");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (dificultad) {
		case FACIL:
			break;
		case NORMAL:
			break;
		case DIFICIL:
			break;
			

		default:
			break;
		}
	}

}
