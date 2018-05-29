package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author JUAN DAVID CAMPILLO CEPEDA
 *
 */
public class PanelInferior extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private InterfazBuscaminas interfaz;
	
	private int posX;
	private int posY;
	
	/**
	 * 
	 * @param v
	 */
	public PanelInferior(InterfazBuscaminas v) {
		interfaz = v;
		setPreferredSize(new Dimension(0,100));
		setLayout(new BorderLayout());
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder(borde);
		posX = 0;
		posY = 0;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);		
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.GRAY);
		if(posX >= 200 && posX <= 500 && posY >= 20 && posY <= 70) {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.fillRect(200, 20, 300, 50);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Calibri", Font.BOLD, 20));
		g.drawString("Puntajes" , 310, 50);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(obtenerBoton() == 1) {
			DialogPuntajes m = new DialogPuntajes(interfaz);
			m.setVisible(true);
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
		posX = e.getX();
		posY = e.getY();
		this.repaint();
	}
	
	public int obtenerBoton() {
		
		if(posX >= 200 && posX <= 500 && posY >= 20 && posY <= 70) {
			return 1;
		}
		else {
			return -1;
		}
		
	}
	

	
	
	
	
}
