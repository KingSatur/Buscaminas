package interfaz;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import excepciones.NoEscogioNivelException;
import excepciones.NoIntrodujoNombreException;
import excepciones.YaExisteUnJugadorConEseNombreException;
import hilos.HiloMensajePerdio;
import hilos.HiloTiempo;
import modelo.Juego;
import modelo.Jugador;


/**
 * 
 * @author Asus
 *
 */
public class InterfazBuscaminas extends JFrame {

	/**
	 * Relacion con el mundo.
	 */
	private Juego buscaminas;
	/**
	 * Relacion con el panel que medira el tiempo.
	 */
	private PanelTiempo panelTiempo;
	/**
	 * Relacion con el panel que tendra la matriz del juego.
	 */
	private PanelMatriz panelMatriz;
	/**
	 * Relacion con el panel que tendra los puntajes del juego.
	 */
	private PanelInferior panelInferior;
	
	/**
	 * 
	 */
	public InterfazBuscaminas() {
		setTitle("Buscaminas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		panelTiempo = new PanelTiempo(this);
		panelMatriz = new PanelMatriz(this);
		panelInferior = new PanelInferior(this);
		add(panelTiempo, BorderLayout.NORTH);
		add(panelMatriz, BorderLayout.CENTER);
		add(panelInferior, BorderLayout.SOUTH);
		setSize(700,700);
	}

	/**
	 * 
	 * @return
	 */
	public PanelTiempo getPanelTiempo() {
		return panelTiempo;
	}

	/**
	 * 
	 * @param panelTiempo
	 */
	public void setPanelTiempo(PanelTiempo panelTiempo) {
		this.panelTiempo = panelTiempo;
	}

	/**
	 * 
	 * @return
	 */
	public PanelMatriz getPanelMatriz() {
		return panelMatriz;
	}

	/**
	 * 
	 * @param panelMatriz
	 */
	public void setPanelMatriz(PanelMatriz panelMatriz) {
		this.panelMatriz = panelMatriz;
	}

	/**
	 * 
	 * @return
	 */
	public PanelInferior getPanelInferior() {
		return panelInferior;
	}

	/**
	 * 
	 * @param panelInferior
	 */
	public void setPanelInferior(PanelInferior panelInferior) {
		this.panelInferior = panelInferior;
	}
	
	/**
	 * 
	 */
	public void pedirNombre(){
		
		String nombre = null;
		
		while(nombre == null || nombre.equals("")) {
			nombre = JOptionPane.showInputDialog("Introduce tu nombre");
		}
		
		asignarJugador(nombre);
		actualizarPanelTiempo();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darTamanhoFilasMatriz() {
		return buscaminas.getTamanhoFilas();
	}

	/**
	 * 
	 * @return
	 */
	public int darTamanhoColumnasMatriz() {
		return buscaminas.getTamanhoColumnas();
	}
	
	/**
	 * 
	 * @return
	 */
	public int darTamanhoCajas() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 50;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 25;
		}
		else {
			return 18;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int darEspacioXEntreCajas() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 78;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 40;
		}
		else {
			return 30;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darTamanhoLetra() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 20;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 15;
		}
		else {
			return 15;
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public int darCantidadSumarX() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return -5;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return -4;
		}
		else {
			return -4;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int darCantidadSumarY() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 5;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 5;
		}
		else {
			return 5;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darEspacioYEntreCajas() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 57;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 30;
		}
		else {
			return 23;
		}
		
	}
	
	/**
	 * 
	 * @throws NoEscogioNivelException
	 */
	public void inicializarJuego() throws NoEscogioNivelException{
		
		String[] niveles = {"Facil", "Intermedio" , "Avanzado"};
		String eleccion = (String)JOptionPane.showInputDialog(null, "Selecciona un jugador", "Nuevo Juego", JOptionPane.QUESTION_MESSAGE, null,
		niveles, null);
		
		if(eleccion != null) {
			if(eleccion.equals("Facil")) {
				buscaminas = new Juego(8,8);
				try {
					buscaminas.cargarJugadores();
				}
				catch(Exception e) {
					
				}
			}
			else if(eleccion.equals("Intermedio")) {
				buscaminas = new Juego(15, 15);
				try {
					buscaminas.cargarJugadores();
				}
				catch(Exception e) {
					
				}
			}
			else if(eleccion.equals("Avanzado")) {
				buscaminas = new Juego(20, 20);
				try {
					buscaminas.cargarJugadores();
				}
				catch(Exception e) {
					
				}
			}
		}
		else {
			throw new NoEscogioNivelException("No escogio nivel");
		}		
	}
	
	/**
	 * 
	 * @return
	 */
	public Juego getBuscaminas() {
		return buscaminas;
	}
	
	/**
	 * 
	 * @param buscaminas
	 */
	public void setBuscaminas(Juego buscaminas) {
		this.buscaminas = buscaminas;
	}

	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public boolean buscarMinaEnCelda(int fila, int columna) {
		
		return buscaminas.buscarSiCeldaTieneMina(fila, columna);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerMatrizBombas(){
		
		return buscaminas.getMatrizBombas();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerMatrizCeldasPresiondas(){
		return buscaminas.getMatrizCeldasPresionadas();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerMatrizCeldasBanderaPresionadas(){
		return buscaminas.getMatrizCeldasBanderaPresionada();
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public int obtenerCanditadBombas(int fila, int columna) {
		
		return buscaminas.darCantidadBombasAdyacentes(fila, columna);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darEspacioXImagen() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return -24;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return -13;
		}
		else {
			return 35;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darEspacioYImagen() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return -25;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return -10;
		}
		else {
			return -8;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darTamanhoXImagen() {
		
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 50;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 20;
		}
		else {
			return 17;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int darTamanhoYImagen() {
		
		if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 64) {
			return 50;
		}
		else if(darTamanhoFilasMatriz() * darTamanhoColumnasMatriz() == 225) {
			return 20;
		}
		else {
			return 17;
		}
		
	}
	
	/**
	 * 
	 */
	public void actualizarPanelTiempo() {
		
		panelTiempo.repaint();
		
	}
	
	/**
	 * 
	 */
	public void cambiarAJuegoIniciado() {
		buscaminas.setInicioJuego(true);
	}
	
	/**
	 * 
	 * @return
	 */
	public String obtenerSegundo() {
		return buscaminas.getTiempo().segundoDevolver();
	}
	
	/**
	 * 
	 */
	public void iniciarHiloTiempo() {
		
		HiloTiempo hiloTime = new HiloTiempo(this, buscaminas);
		hiloTime.start();	
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean obtenerIsIniciado() {
		return buscaminas.isInicioJuego();
	}
	
	/**
	 * 
	 * @return
	 */
	public String obtenerMinuto() {
		return buscaminas.getTiempo().minutoDevolver();
	}
	
	/**
	 * 
	 * @return
	 */
	public String obtenerHora() {
		return buscaminas.getTiempo().horaDevolver();
	}
	
	/**
	 * 
	 * @return
	 */
	public String obtenerUrlImagen() {
		return buscaminas.getUrlImagen();
	}
	
	/**
	 * 
	 * @param x
	 */
	public void modificarPerdioJuego(boolean x) {
		buscaminas.setPerdioJuego(true);
	}

	/**
	 * 
	 */
	public void cambiarCarita() {
		buscaminas.cambiarImagen();
		actualizarPanelTiempo();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean obtenerIsJuegoPerdido() {
		return buscaminas.isPerdioJuego();
	}
	
	/**
	 * 
	 */
	public void reiniciarPartida() throws NoEscogioNivelException, FileNotFoundException, IOException, ClassNotFoundException, YaExisteUnJugadorConEseNombreException {
		try {
			this.inicializarJuego();
			this.pedirNombre();
		}
		catch(NoEscogioNivelException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void asignarJugador(String nombre) {
		try {
			buscaminas.asignarJugador(nombre);
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			pedirNombre();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean sePuedePintarNombre() {
		
		if(buscaminas.getJugadorActual() != null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * 
	 */
	public void imprimirNombreJugador() {
		System.out.println(buscaminas.getJugadorActual());
	}

	/**
	 * 
	 * @return
	 */
	public String obtenerNombreJugador() {
		return buscaminas.getJugadorActual().getNombre();
	}
	
	/**
	 * 
	 */
	public void preguntarGuardarPuntaje() {
		
		int a = JOptionPane.showConfirmDialog(this, "Desea guardar el puntaje");
		
		if(a == 0) {
			try {
				buscaminas.guardarJugadorYPartida();
				buscaminas.guardarArchivoPlanoPuntajes();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			buscaminas.quitarJugadorPrimero();
			JOptionPane.showMessageDialog(this, "No se guardara el puntaje", "Datos", JOptionPane.WARNING_MESSAGE);
			
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean pintarMensajePerdio() {
		if(obtenerIsJuegoPerdido()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int obtenerPosicionXMensaje() {
		return buscaminas.getPosicionXMensajePerdio();
	}
	
	/**
	 * 
	 * @return
	 */
	public int obtenerPosicionYMensaje() {
		return buscaminas.getPosicionYMensajePerdio();
	}
	
	/**
	 * 
	 */
	public void iniciarHiloMensaje() {
		
		HiloMensajePerdio mensaje = new HiloMensajePerdio(buscaminas, this);
		mensaje.start();
		
		
	}
	
	/**
	 * 
	 */
	public void actualizarPanelAuxiliar() {
		panelMatriz.actualizarPanelAuxiliar();
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 */
	public void colocarBandera(int fila, int columna) {
		buscaminas.colocarBandera(fila, columna);
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 */
	public void quitarBandera(int fila, int columna) {
		buscaminas.quitarBandera(fila, columna);
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public boolean verificarBandera(int fila, int columna) {
		return buscaminas.verificiarBandera(fila, columna);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerMatrizConBanderas(){
		return buscaminas.obtenerMatrizBanderas();
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> obtenerAleatoriamente(){
		return buscaminas.convertirString(buscaminas.convertirJugadores());
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> obtenerListadoPorNombre(){
		return buscaminas.convertirString(buscaminas.ordenarPorNombre(buscaminas.getBaseModificaciones()));
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> obtenerListadoPorPuntaje(){
		return buscaminas.convertirString(buscaminas.ordenarPorPuntaje(buscaminas.getBaseModificaciones()));
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void busquedaConNombre(String nombre) {
		try {
			JOptionPane.showMessageDialog(this, buscaminas.obtenerJugadorPorPosicion(buscaminas.obtenerJugadorPorPosicion(buscaminas.busquedaBinariaJugadorNombre(nombre)).getTiempo()));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param puntaje
	 */
	public void busquedaConPuntaje(int puntaje) {
		try {
			JOptionPane.showMessageDialog(this,buscaminas.obtenerJugadorPorPosicion(buscaminas.busquedaBinariaJugadorPuntaje(puntaje)).getTiempo());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		InterfazBuscaminas v = new InterfazBuscaminas();
		try {
			v.inicializarJuego();
			v.pedirNombre();
			v.setVisible(true);
		}
		catch(NoEscogioNivelException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Niveles", JOptionPane.ERROR_MESSAGE);
		}
	}		
}
