package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotones extends JPanel implements ActionListener{

	public static final String BOTON_1 = "ORDENAR POR PUNTAJE";
	public static final String BOTON_2 = "ORDENAR POR NOMBRE";
	public static final String BOTON_3 = "BUSCAR POR NOMBRE";
	public static final String BOTON_4 = "BUSCAR POR PUNTAJE";
	private DialogPuntajes emergente;
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private InterfazBuscaminas inter;
	
	public PanelBotones(DialogPuntajes emergente, InterfazBuscaminas inter) {
		this.inter = inter;
		this.emergente = emergente;
		setLayout(new GridLayout(1, 4));
		inicializacionDeBotones();
		
	}

	public void inicializacionDeBotones() {
		
		boton1= new JButton("Ordenar por puntaje");
		boton1.setActionCommand(BOTON_1);
		boton1.setBackground(Color.DARK_GRAY);
		boton1.setFont(new Font("Calibri", Font.BOLD, 12));
		boton1.setForeground(Color.YELLOW);
		boton1.addActionListener(this);
		boton2 = new JButton("Ordenar por nombre");
		boton2.setActionCommand(BOTON_2);
		boton2.setBackground(Color.DARK_GRAY);
		boton2.setFont(new Font("Calibri", Font.BOLD, 12));
		boton2.setForeground(Color.YELLOW);
		boton2.addActionListener(this);
		boton3 = new JButton("Buscar por nombre");
		boton3.setActionCommand(BOTON_3);
		boton3.setBackground(Color.DARK_GRAY);
		boton3.setFont(new Font("Calibri", Font.BOLD, 12));
		boton3.setForeground(Color.YELLOW);
		boton3.addActionListener(this);
		boton4= new JButton("Buscar por puntaje");
		boton4.setActionCommand(BOTON_4);
		boton4.setBackground(Color.DARK_GRAY);
		boton4.setFont(new Font("Calibri", Font.BOLD, 12));
		boton4.setForeground(Color.YELLOW);
		boton4.addActionListener(this);
		add(boton1);
		add(boton4);
		add(boton2);
		add(boton3);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		String a = e.getActionCommand();
		
		if(a.equals(BOTON_1)) {
			emergente.modificarListado(inter.obtenerListadoPorPuntaje());
		}
		else if(a.equals(BOTON_2)) {
			emergente.modificarListado(inter.obtenerListadoPorNombre());
		}
		else if(a.equals(BOTON_3)) {
			String name = JOptionPane.showInputDialog("Introduce el nombre");
			if(name == null) {
				JOptionPane.showMessageDialog(inter, "No introdujo nada");
			}
			else {
				inter.busquedaConNombre(name);
			}
		}
		else if(a.equals(BOTON_4)) {
			String puntaje = JOptionPane.showInputDialog("Introduce el puntaje");
			if(puntaje == null) {
				JOptionPane.showMessageDialog(inter, "No introdujo nada");
			}
			else {
				int pun = Integer.parseInt(puntaje);
				inter.busquedaConPuntaje(pun);
			}
		}
		
		
	}
	
	
}
