package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author JUAN DAVID CAMPILLO CEPEDA
 *
 */
public class PanelMatrizAuxiliar extends JPanel implements MouseMotionListener, MouseListener {
	
	/**
	 * Relacion con la interfaz principal.
	 */
	private InterfazBuscaminas inter;
	/**
	 * Atributo que contiene la ubicacion horizontal.
	 */
	private int posX;
	/**
	 * Atributo que contiene la ubicacion vertical.
	 */
	private int posY;
	
	/**
	 * Constructor de la clase PanelMatrizAuxiliar.
	 * @param v Interfaz principal
	 */
	public PanelMatrizAuxiliar(InterfazBuscaminas v) {
		inter = v;
		posX = 0;
		posY = 0;
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	/**
	 * Metodo encargar de pintar todos los graficos del juego.
	 */
	public void paintComponent(Graphics g) {
		
		boolean[][] celdasPresionadas = inter.obtenerMatrizCeldasPresiondas();
		boolean[][] minas = inter.obtenerMatrizBombas();
		boolean[][] celdasBanderaPresionadas = inter.obtenerMatrizCeldasBanderaPresionadas();
		boolean[][] matrizDeBanderas = inter.obtenerMatrizConBanderas();

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for(int i = 0; i < inter.darTamanhoFilasMatriz() ; i ++) {
			for(int j = 0; j < inter.darTamanhoColumnasMatriz(); j ++) {
				g.setColor(Color.GRAY);
//				if(minas[i][j] == true) {
//					g.setColor(Color.red);
//				}
				if(posX >= 10 + j*inter.darEspacioXEntreCajas() && posX < 10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas() && posY >= 10 + i*inter.darEspacioYEntreCajas() && posY < 10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas()) {
					if(inter.obtenerIsJuegoPerdido()) {
						if(minas[i][j]) {
							g.setColor(Color.YELLOW);
						}
						else {
							g.setColor(Color.GRAY);
						}
					}
					else {
						g.setColor(Color.white);
					}
				}
				g.fill3DRect(10 + j*inter.darEspacioXEntreCajas(), 10 + i*inter.darEspacioYEntreCajas(), inter.darTamanhoCajas(), inter.darTamanhoCajas(), true);
				if(celdasPresionadas[i][j]) {
					if(inter.buscarMinaEnCelda(i,j) == true) {
						g.setColor(Color.YELLOW);
						g.fill3DRect(10 + j*inter.darEspacioXEntreCajas(), 10 + i*inter.darEspacioYEntreCajas(), inter.darTamanhoCajas(), inter.darTamanhoCajas(), true);
						ImageIcon bomba = new ImageIcon("imagenes/Bombinta.png");
						g.drawImage(bomba.getImage() ,10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen() , 10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen(), inter.darTamanhoXImagen(), inter.darTamanhoYImagen(), null);
					}
					else {
						g.setColor(Color.WHITE);
						g.setFont(new Font("Calibri", Font.BOLD, inter.darTamanhoLetra()));
						g.fillRect(10 + j*inter.darEspacioXEntreCajas(), 10 + i*inter.darEspacioYEntreCajas(), inter.darTamanhoCajas(), inter.darTamanhoCajas());
						g.setColor(obtenerColorAPintar(inter.obtenerCanditadBombas(i, j)));
						g.drawString(Integer.toString(inter.obtenerCanditadBombas(i, j)), 10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darCantidadSumarX() ,  10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darCantidadSumarY());
					}	
				}
				else if(celdasBanderaPresionadas[i][j]) {
					if(matrizDeBanderas[i][j]) {
						ImageIcon bandera = new ImageIcon("imagenes/Bandera2.png");
						g.drawImage(bandera.getImage() ,10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen() , 10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen(), inter.darTamanhoXImagen(), inter.darTamanhoYImagen(), null);
					}
					else {
						g.setColor(Color.GRAY);
					}
				}
				else if(inter.obtenerIsJuegoPerdido()) {
					if(minas[i][j]) {
						g.setColor(Color.YELLOW);
						g.fill3DRect(10 + j*inter.darEspacioXEntreCajas(), 10 + i*inter.darEspacioYEntreCajas(), inter.darTamanhoCajas(), inter.darTamanhoCajas(), true);
						ImageIcon bomba = new ImageIcon("imagenes/Bombinta.png");
						g.drawImage(bomba.getImage() ,10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen() , 10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas() / 2 + inter.darEspacioYImagen(), inter.darTamanhoXImagen(), inter.darTamanhoYImagen(), null);
					}
					else {
						g.setColor(Color.GRAY);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de devolver un color de acuerdo al numero de bombas adyacentes.
	 * @param cantidadBombas, cantidad >= 0.
	 * @return Color.
	 */
	public Color obtenerColorAPintar(int cantidadBombas) {
		
		if(cantidadBombas == 0){
			return Color.BLACK;
		}
		else if(cantidadBombas == 1){
			return Color.BLUE;
		}
		else if(cantidadBombas == 2){
			return Color.RED;
		}
		else if(cantidadBombas == 3){
			return Color.GREEN;
		}
		else if(cantidadBombas == 4){
			return Color.ORANGE;
		}
		else if(cantidadBombas == 5){
			return Color.MAGENTA;
		}
		else if(cantidadBombas == 6){
			return Color.GREEN;
		}
		else if(cantidadBombas == 7){
			return Color.PINK;
		}
		else {
			return Color.YELLOW;
		}
		
		
		
	}
	
	/**
	 * Metodo por medio de el cual se obtiene la relacion con la interfaz principal.
	 * @return
	 */
	public InterfazBuscaminas getInter() {
		return inter;
	}

	
	/**
	 * 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo que se encarga de devolver el atributo posX.
	 * @return posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Metodo que se encarga de darle un valor al atributo posX.
	 * @param posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Metodo que se encarga de devolver el valor del atributo posY.
	 * @return
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Metodo encargado de darle un valor al atributo posY.
	 * @param posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		posX = e.getX();
		posY = e.getY();
		this.repaint();
	}

	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getButton() == 1){
			if(obtenerCajaX() != -1 && obtenerCajaY() != -1) {
				inter.obtenerMatrizCeldasPresiondas()[obtenerCajaY()][obtenerCajaX()] = true;
				if(inter.obtenerIsIniciado()) {
					if(pisoMina(obtenerCajaY(), obtenerCajaX())){
						inter.modificarPerdioJuego(true);
						inter.cambiarCarita();
						inter.iniciarHiloMensaje();
					}
				}
				else {
					if(pisoMina(obtenerCajaY(), obtenerCajaX())){
						inter.modificarPerdioJuego(true);
						inter.cambiarCarita();
						inter.iniciarHiloMensaje();
					}
					else {
						inter.cambiarAJuegoIniciado();
						inter.iniciarHiloTiempo();
					}
				}	
			}
		}
		else {
			if(inter.obtenerIsIniciado()) {
				if(obtenerCajaX() != -1 && obtenerCajaY() != -1) {
					inter.obtenerMatrizCeldasBanderaPresionadas()[obtenerCajaY()][obtenerCajaX()] = true;
					if(inter.obtenerMatrizConBanderas()[obtenerCajaY()][obtenerCajaX()]) {
						inter.quitarBandera(obtenerCajaY(), obtenerCajaX());
					}
					else {
						inter.colocarBandera(obtenerCajaY(), obtenerCajaX());
					}
				}
			}
		}
	}

	
	/**
	 * Metodo para definir si hay una mina o no en esa posicion.
	 * @param fila Ubicacion y de la matriz.
	 * @param columna Ubicacion x de la matriz.
	 * @return true si al presionar en la fila y columna hay una mina, false si no la hay.
	 */
	public boolean pisoMina(int fila, int columna) {
		
		boolean[][] minas = inter.obtenerMatrizBombas();
		boolean	pisoMina = false;
		
		if(minas[fila][columna] == true) {
			pisoMina = true;
		}
		else {
			pisoMina = false;
		}
		
		return pisoMina;
	
	}
	
	/**
	 * Metodo para obtener la fila en la que se encuentra ubicado el click respecto a la matriz.
	 * @return
	 */
	public int obtenerCajaY() {
	
		for(int i = 0; i < inter.darTamanhoFilasMatriz() ; i ++) {
			for(int j = 0; j < inter.darTamanhoColumnasMatriz() ; j ++) { 
				if(posY >= 10 + i*inter.darEspacioYEntreCajas() && posY < 10 + i*inter.darEspacioYEntreCajas() + inter.darTamanhoCajas()) {
					return i;
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @return
	 */
	public int obtenerCajaX() {
		
		for(int i = 0; i < inter.darTamanhoFilasMatriz() ; i ++) {
			for(int j = 0; j < inter.darTamanhoColumnasMatriz() ; j ++) {
				if(posX >= 10 + j*inter.darEspacioXEntreCajas() && posX < 10 + j*inter.darEspacioXEntreCajas() + inter.darTamanhoCajas()) {
					return j;
				}
			}
		}
		
		return -1;
		
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
