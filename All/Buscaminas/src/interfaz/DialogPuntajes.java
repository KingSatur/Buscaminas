package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Asus
 *
 */
public class DialogPuntajes extends JDialog{

	private InterfazBuscaminas interfaz;
	private JList listaJugadores;
	private DefaultListModel modeloLista;
	private JLabel labelTitulo;
	private PanelBotones panelBotones;
	private PanelIzquierdo panelIzquierdo;
	private PanelDerecho panelDerecho;
	
	public DialogPuntajes(InterfazBuscaminas v) {
		interfaz = v;
		setSize(600,600);
		setTitle("Puntajes");
		setLayout(new BorderLayout());
		modificarLabelPrincipal();
		panelBotones = new PanelBotones(this, v);
		panelIzquierdo = new PanelIzquierdo();
		panelDerecho = new PanelDerecho();
		modificarLista();
		cargarJugadoresYTiempos();
		add(panelIzquierdo, BorderLayout.WEST);
		add(panelDerecho, BorderLayout.EAST);
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	
	public void modificarLabelPrincipal() {  
		labelTitulo = new JLabel("                                         Jugadores");
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		labelTitulo.setBorder(borde);
		labelTitulo.setBackground(Color.DARK_GRAY);
		labelTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		labelTitulo.setForeground(Color.red);
		labelTitulo.setOpaque(true);
		add(labelTitulo, BorderLayout.NORTH);
	}
	
	public void modificarLista() {
		modeloLista = new DefaultListModel();
		listaJugadores = new JList();
		listaJugadores.setBackground(Color.DARK_GRAY);
		listaJugadores.setFont(new Font("Calibri", Font.BOLD, 25));
		listaJugadores.setForeground(Color.red);
		add(new JScrollPane(listaJugadores), BorderLayout.CENTER);
	}
	
	public void cargarJugadoresYTiempos() {
		
		ArrayList<String> listado = interfaz.obtenerAleatoriamente();
		
		for(int i = 0; i < listado.size() ; i ++) {
			modeloLista.addElement(listado.get(i));
		}
		
		listaJugadores.setModel(modeloLista);
	}
	
	public void modificarListado(ArrayList<String> listado) {
		
		modeloLista.removeAllElements();
		
		for(int i = 0; i < listado.size() ; i ++) {
			modeloLista.addElement(listado.get(i));
		}
		
		listaJugadores.setModel(modeloLista);			
	}
	
	
	
	
	
}
