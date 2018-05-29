package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Asus
 *
 */
public class PanelTiempo extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private InterfazBuscaminas interfaz;
	
	/**
	 * 
	 * @param v
	 */
	public PanelTiempo(InterfazBuscaminas v) {
		interfaz = v;
		setLayout(null);
		setPreferredSize(new Dimension(0,100));
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder(borde);
		this.setBackground(Color.GRAY);
		this.addMouseListener(this);
	}
	
	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.fillRect(400, 35, 240, 60);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Calibri", Font.BOLD, 50));
		ImageIcon carita = new ImageIcon(interfaz.obtenerUrlImagen());
		if(interfaz.sePuedePintarNombre()) {
			g.drawString(interfaz.obtenerNombreJugador(), 120, 70);
		}
		g.setColor(Color.RED);
		g.drawImage(carita.getImage(), 225, -35, 240, 200, null);
		g.drawString(interfaz.obtenerHora(), 430, 80);
		g.drawString(" : ", 470, 75);
		g.drawString(interfaz.obtenerMinuto(), 500, 80);
		g.drawString(" : ", 540, 75);
		g.drawString(interfaz.obtenerSegundo(), 570, 80);
		if(interfaz.pintarMensajePerdio()) {
			g.setFont(new Font("Calibri", Font.BOLD, 26));
			g.setColor(Color.RED);
			g.drawString("¡ Perdiste !", interfaz.obtenerPosicionXMensaje(), interfaz.obtenerPosicionYMensaje());
		}
	}

	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(interfaz.obtenerIsJuegoPerdido()) {
			if(e.getX() >= 308 && e.getX() <= 381 && e.getY() >= 12 && e.getY() <= 90) {
				interfaz.preguntarGuardarPuntaje();
				try {
					interfaz.reiniciarPartida();
				}
				catch(Exception e1) {
					
				}
			}
		}
	
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
