package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import excepciones.NoExisteJugadorConEseNombreException;
import excepciones.NoExisteJugadorConPuntajeException;
import excepciones.YaExisteUnJugadorConEseNombreException;

/**
 * Clase que representa el juego buscaminas
 */
public class Juego {
	
	/**
	 * 
	 */
	public static final String CARITA_FELIZ = "imagenes/CaritaFeliz.png";
	
	/**
	 * 
	 */
	public static final String CARITA_TRISTE = "imagenes/CaritaTriste.png";
	
	/**
	 * Relacion de matriz con la clase celda.
	 */
	private CeldaNormal[][] buscaminas;
	
	/**
	 * 
	 */
	private CeldaConBandera[][] banderas;
	
	/**
	 * 
	 */
	private boolean inicioJuego;
	
	/**
	 * 
	 */
	private boolean[][] matrizCeldasBanderaPresionada;
	
	/**
	 * Atributo que indica el numero de filas de la matriz.
	 */
	private int tamanhoFilas;
	
	/**
	 * Atributo que indica el numero de columnas de la matriz.
	 */
	private int tamanhoColumnas;
	
	/**
	 * Relacion con la clase datos, contendra todos los jugadores.
	 */
	private Datos datosGenerales;
	
	/**
	 * 
	 */
	private boolean[][] matrizCeldasPresionadas;
	
		
	
	/**
	 * 
	 */
	private String urlImagen;
	
	/**
	 * 
	 */
	private boolean perdioJuego;
	
	/**
	 * 
	 */
	private Timer tiempo;
	
	/**
	 * 
	 */
	private int posicionXMensajePerdio;
	
	/**
	 * 
	 */
	private int posicionYMensajePerdio;
	
	/**
	 * 
	 */
	private ArrayList<Jugador> baseModificaciones;
	
	/**
	 * 
	 * @param tamanhoFilas
	 * @param tamanhoColumnas
	 */
	public Juego(int tamanhoFilas, int tamanhoColumnas) {
		this.tamanhoFilas = tamanhoFilas;
		this.tamanhoColumnas = tamanhoColumnas;
		buscaminas = new CeldaNormal[tamanhoFilas][tamanhoColumnas];
		banderas = new CeldaConBandera[tamanhoFilas][tamanhoColumnas];
		matrizCeldasPresionadas = new boolean[tamanhoFilas][tamanhoColumnas];
		matrizCeldasBanderaPresionada = new boolean[tamanhoFilas][tamanhoColumnas];
		inicioJuego = false;
		tiempo = new Timer();
		datosGenerales = new Datos(this);
		baseModificaciones = new ArrayList<Jugador>();
		crearYNombrarMatrizCeldas();
		regarMinasAleatorias();
		colocarImagenPorDefecto();
		posicionXMensajePerdio = 465;
		posicionYMensajePerdio = 0;
	}

	/**
	 * 
	 */
	private void crearYNombrarMatrizCeldas() {
		
		for(int i = 0; i < buscaminas.length ; i ++) {
			for(int j = 0; j < buscaminas[0].length ; j ++) {
				buscaminas[i][j] = new CeldaNormal((i + ", " + j));
				banderas[i][j] = new CeldaConBandera((i + ", " + j));
			}
		}
		
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Celda[][] getBuscaminas() {
		return buscaminas;
	}

	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	private ArrayList<CeldaNormal> darBombasAdyacentes(int fila, int columna) {
		
		ArrayList<CeldaNormal> celdasAdyacentes = new ArrayList<CeldaNormal>();
		
		if(fila == 0 || columna == 0 || fila == buscaminas.length - 1 || columna == buscaminas[0].length - 1) {
			if(fila == 0 && columna == 0) {
				celdasAdyacentes.add(buscaminas[fila + 1][columna]);
				celdasAdyacentes.add(buscaminas[fila][columna + 1]);
				celdasAdyacentes.add(buscaminas[fila + 1][columna + 1]);
			}
			else if(fila == 0 && columna == buscaminas[0].length - 1) {
				celdasAdyacentes.add(buscaminas[fila][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila + 1][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila + 1][columna]);
			}
			else if(fila == buscaminas.length - 1 && columna == 0) {
				celdasAdyacentes.add( buscaminas[fila - 1][columna]);
				celdasAdyacentes.add( buscaminas[fila - 1][columna + 1]);
				celdasAdyacentes.add(buscaminas[fila][columna + 1]);
			}
			else if((fila == buscaminas.length - 1) && (columna == buscaminas[0].length - 1)) {
				celdasAdyacentes.add(buscaminas[fila][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila - 1][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila - 1][columna]);
			}
			else if(fila == 0) {
				celdasAdyacentes.add(buscaminas[fila][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila][columna + 1]);
				celdasAdyacentes.add(buscaminas[fila + 1][columna - 1]);
				celdasAdyacentes.add( buscaminas[fila + 1][columna]);
				celdasAdyacentes.add(buscaminas[fila + 1][columna + 1]);
			}
			else if(fila == buscaminas.length - 1) {
				celdasAdyacentes.add( buscaminas[fila][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila][columna + 1]);
				celdasAdyacentes.add(buscaminas[fila - 1][columna - 1]);
				celdasAdyacentes.add(buscaminas[fila - 1][columna]);
				celdasAdyacentes.add( buscaminas[fila - 1][columna + 1]);
			}
			else if(columna == 0) {
				celdasAdyacentes.add( buscaminas[fila - 1][columna]);
				celdasAdyacentes.add( buscaminas[fila - 1][columna + 1]);
				celdasAdyacentes.add( buscaminas[fila][columna + 1]);
				celdasAdyacentes.add( buscaminas[fila + 1][columna]);
				celdasAdyacentes.add( buscaminas[fila + 1][columna + 1]);
			}
			else if(columna == buscaminas[0].length - 1) {
				celdasAdyacentes.add( buscaminas[fila][columna - 1]);
				celdasAdyacentes.add( buscaminas[fila - 1][columna - 1]);
				celdasAdyacentes.add( buscaminas[fila - 1][columna]);
				celdasAdyacentes.add( buscaminas[fila + 1][columna - 1]);
				celdasAdyacentes.add( buscaminas[fila + 1][columna]);
			}
		}
		else {
			celdasAdyacentes.add( buscaminas[fila - 1][columna - 1]);
			celdasAdyacentes.add( buscaminas[fila][columna - 1]);
			celdasAdyacentes.add( buscaminas[fila + 1][columna - 1]);
			celdasAdyacentes.add( buscaminas[fila - 1][columna]);
			celdasAdyacentes.add( buscaminas[fila + 1][columna]);
			celdasAdyacentes.add( buscaminas[fila - 1][columna + 1]);
			celdasAdyacentes.add( buscaminas[fila][columna + 1]);
			celdasAdyacentes.add( buscaminas[fila + 1][columna + 1]);
		}
		
		return celdasAdyacentes;
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public int darCantidadBombasAdyacentes(int fila, int columna) {
		
		int cantidadBombas = 0;
		
		ArrayList<CeldaNormal> celdasVecinas = darBombasAdyacentes(fila, columna);
		
		for(int i = 0; i < celdasVecinas.size() ; i ++) {
			if(celdasVecinas.get(i).isTieneBomba() == true) {
				cantidadBombas++;
			}
		}
		
		return cantidadBombas;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String[][] matrizDeNombres(){
		
		String[][] matrizNombres = new String[tamanhoFilas][tamanhoColumnas];
		
		for(int i = 0; i < tamanhoFilas; i ++) {
			for(int j = 0; j < tamanhoColumnas; j ++) {
				matrizNombres[i][j] = buscaminas[i][j].getNombre();
			}
		}
		
		return matrizNombres;
		
	}
	
	/**
	 * 
	 * @param buscaminas
	 */
	public void setBuscaminas(CeldaNormal[][] buscaminas) {
		this.buscaminas = buscaminas;
	}

	/**
	 * 
	 * @return
	 */
	public int getTamanhoFilas() {
		return tamanhoFilas;
	}

	/**
	 * 
	 * @param tamanhoFilas
	 */
	public void setTamanhoFilas(int tamanhoFilas) {
		this.tamanhoFilas = tamanhoFilas;
	}

	/**
	 * 
	 * @return
	 */
	public int getTamanhoColumnas() {
		return tamanhoColumnas;
	}

	/**
	 * 
	 * @param tamanhoColumnas
	 */
	public void setTamanhoColumnas(int tamanhoColumnas) {
		this.tamanhoColumnas = tamanhoColumnas;
	}

	/**
	 * 
	 * @return
	 */
	public Datos getDatosGenerales() {
		return datosGenerales;
	}

	/**
	 * 
	 * @param datosGenerales
	 */
	public void setDatosGenerales(Datos datosGenerales) {
		this.datosGenerales = datosGenerales;
	}
	
	/**
	 * 
	 */
	private void regarMinasAleatorias() {
		
		Random filas = new Random();
		Random columnas = new Random();
		
		for(int i = 0; i < cantidadDeMinasAColocar() ; i ++) {
			buscaminas[filas.nextInt(tamanhoFilas)][columnas.nextInt(tamanhoColumnas)].setTieneBomba(true);
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	private int cantidadDeMinasAColocar() {
		
		if(tamanhoFilas * tamanhoColumnas == 64) {
			return 15;
		}
		else if(tamanhoFilas * tamanhoColumnas == 225) {
			return 50;
		}
		else {
			return 100;
		}
		
			
		
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public boolean buscarSiCeldaTieneMina(int fila, int columna) {
	
		return buscaminas[fila][columna].isTieneBomba();
		
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public int encontrarFilaCelda(String a) {
		
		int fila = 0;
		boolean seEncontro = false;
		
		for(int i = 0; i < buscaminas.length && !seEncontro; i ++) {
			for(int j = 0; j < buscaminas[0].length && !seEncontro; j ++) {
				if(buscaminas[i][j].getNombre().equals(a)) {
					fila = i;
					seEncontro = true;
				}
			}
		}
		
		return fila;	
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public int encontrarColumnaCelda(String a) {
	
		int columna = 0;
		boolean seEncontro = false;
	
		for(int i = 0; i < buscaminas.length && !seEncontro; i ++) {
			for(int j = 0; j < buscaminas[0].length && !seEncontro; j ++) {
				if(buscaminas[i][j].getNombre().equals(a)) {
					columna = j;
					seEncontro = true;
				}
			}
		}
	
		return columna;	
		
	}

	/**
	 * 
	 * @return
	 */
	public boolean[][] getMatrizBombas(){
		
		boolean matrizDeBoolean[][] = new boolean[buscaminas.length][buscaminas[0].length];
		
		for(int i = 0; i < matrizDeBoolean.length ; i ++) {
			for(int j = 0; j < matrizDeBoolean[0].length ; j ++) {
				matrizDeBoolean[i][j] = buscaminas[i][j].isTieneBomba();
			}
		}
		
		return matrizDeBoolean;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] getMatrizCeldasPresionadas() {
		return matrizCeldasPresionadas;
	}

	/**
	 * 
	 * @param matrizCeldasPresionadas
	 */
	public void setMatrizCeldasPresionadas(boolean[][] matrizCeldasPresionadas) {
		this.matrizCeldasPresionadas = matrizCeldasPresionadas;
	}


	/**
	 * @return the inicioJuego
	 */
	public boolean isInicioJuego() {
		return inicioJuego;
	}


	/**
	 * @param inicioJuego the inicioJuego to set
	 */
	public void setInicioJuego(boolean inicioJuego) {
		this.inicioJuego = inicioJuego;
	}


	/**
	 * @return the tiempo
	 */
	public Timer getTiempo() {
		return tiempo;
	}


	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(Timer tiempo) {
		this.tiempo = tiempo;
	}
	
	/**
	 * 
	 */
	public void cambiarImagen() {
		
		if(perdioJuego == true) {
			urlImagen = CARITA_TRISTE;
		}
		else {
			urlImagen = CARITA_FELIZ;
		}
	
	}


	/**
	 * @return the urlImagen
	 */
	public String getUrlImagen() {
		return urlImagen;
	}


	/**
	 * @param urlImagen the urlImagen to set
	 */
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	/**
	 * 
	 */
	public void colocarImagenPorDefecto() {
		this.urlImagen = CARITA_FELIZ;
	}
	
	/**
	 * @return the perdioJuego
	 */
	public boolean isPerdioJuego() {
		return perdioJuego;
	}


	/**
	 * @param perdioJuego the perdioJuego to set
	 */
	public void setPerdioJuego(boolean perdioJuego) {
		this.perdioJuego = perdioJuego;
	}

	/**
	 * 
	 * @param nombre
	 * @throws YaExisteUnJugadorConEseNombreException 
	 */
	public void asignarJugador(String nombre) throws YaExisteUnJugadorConEseNombreException {
		
		Jugador j = new Jugador(nombre);
		try {
			datosGenerales.setPrimerJugador(j);
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public Jugador getJugadorActual() {
		return datosGenerales.getPrimerJugador();
	}


	/**
	 * @return the posicionXMensajePerdio
	 */
	public int getPosicionXMensajePerdio() {
		return posicionXMensajePerdio;
	}


	/**
	 * @param posicionXMensajePerdio the posicionXMensajePerdio to set
	 */
	public void setPosicionXMensajePerdio(int posicionXMensajePerdio) {
		this.posicionXMensajePerdio = posicionXMensajePerdio;
	}


	/**
	 * @return the posicionYMensajePerdio
	 */
	public int getPosicionYMensajePerdio() {
		return posicionYMensajePerdio;
	}


	/**
	 * @param posicionYMensajePerdio the posicionYMensajePerdio to set
	 */
	public void setPosicionYMensajePerdio(int posicionYMensajePerdio) {
		this.posicionYMensajePerdio += posicionYMensajePerdio;
	}

	/**
	 * @return the banderas
	 */
	public CeldaConBandera[][] getBanderas() {
		return banderas;
	}

	/**
	 * @param banderas the banderas to set
	 */
	public void setBanderas(CeldaConBandera[][] banderas) {
		this.banderas = banderas;
	}

	/**
	 * @return the matrizCeldasBanderaPresionada
	 */
	public boolean[][] getMatrizCeldasBanderaPresionada() {
		return matrizCeldasBanderaPresionada;
	}

	/**
	 * @param matrizCeldasBanderaPresionada the matrizCeldasBanderaPresionada to set
	 */
	public void setMatrizCeldasBanderaPresionada(boolean[][] matrizCeldasBanderaPresionada) {
		this.matrizCeldasBanderaPresionada = matrizCeldasBanderaPresionada;
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 */
	public void colocarBandera(int fila, int columna) {
		banderas[fila][columna].setTieneBandera(true);
	}
	
	/**
	 * 
	 * @param fila
	 * @param columna
	 */
	public void quitarBandera(int fila, int columna) {
		banderas[fila][columna].setTieneBandera(false);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[][] obtenerMatrizBanderas(){
		
		boolean[][] matrizBanderas = new boolean[tamanhoFilas][tamanhoColumnas];
		
		for(int i = 0; i < matrizBanderas.length ; i ++) {
			for(int j = 0; j < matrizBanderas[0].length ; j ++) {
				matrizBanderas[i][j] = banderas[i][j].isTieneBandera();
			}
		}
		
		return matrizBanderas;
	}	

	/**
	 * 
	 * @param fila
	 * @param columna
	 * @return
	 */
	public boolean verificiarBandera(int fila, int columna) {
		
		if(banderas[fila][columna].isPresionada()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	public void guardarArchivoPlanoPuntajes() {
		
		try {
			File archivo = new File("datos/Puntajes.txt");
			FileWriter escr;
			BufferedWriter buf;
			if(archivo.exists() == true) {
				escr = new FileWriter(archivo);
				buf = new BufferedWriter(escr);
				boolean salirse = false;
				while(getJugadorActual() != null && !salirse) {
					if(getJugadorActual().getJugadorSiguiente() == null) {
						buf.write(getJugadorActual().getNombre() + " - " + getJugadorActual().getTiempo());
						buf.newLine();
						salirse = true;
					}
					else {
						Jugador actual = getJugadorActual();
						while(actual.getJugadorSiguiente() != null) {
							buf.write(actual.getNombre() + " - " + actual.getTiempo());
							buf.newLine();
							actual = actual.getJugadorSiguiente();
						}
					}
					salirse = true;
				}
			}
			else {
				escr = new FileWriter(archivo);
				buf = new BufferedWriter(escr);
				boolean salirse = false;
				while(getJugadorActual() != null && !salirse) {
					if(getJugadorActual().getJugadorSiguiente() == null) {
						buf.write(getJugadorActual().getNombre() + " - " + getJugadorActual().getTiempo());
						buf.newLine();
						salirse = true;
					}
					else {
						Jugador actual = getJugadorActual();
						while(actual.getJugadorSiguiente() != null) {
							buf.write(actual.getNombre() + " - " + actual.getTiempo());
							buf.newLine();
							actual = actual.getJugadorSiguiente();
						}
					}
					salirse = true;
				}
			}
			buf.close();
			escr.close();
		}
		catch(Exception e) {
			
		}
		
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Jugador> convertirJugadores() {
		
		try {
			File m = new File("datos/Puntajes.txt");
			if(m.exists() == true) {
				FileReader reader = new FileReader(m);
				BufferedReader bufReader = new BufferedReader(reader);
				String linea;
				int  i = 0;
				while((linea = bufReader.readLine()) != null) {
						String[] caracteres = linea.split(" - ");
						baseModificaciones.add(new Jugador(caracteres[0]));
						baseModificaciones.get(0).setTiempo(Integer.parseInt(caracteres[1]));
						i++;
				}
			}
			else {
				FileReader reader = new FileReader(m);
				BufferedReader bufReader = new BufferedReader(reader);
				String linea;
				int  i = 0;
				while((linea = bufReader.readLine()) != null) {
						String[] caracteres = linea.split(" - ");
						baseModificaciones.add(new Jugador(caracteres[0]));
						baseModificaciones.get(0).setTiempo(Integer.parseInt(caracteres[1]));
						i++;
				}
			}
		}
		catch(Exception e) {
			
		}
		
		return baseModificaciones;			
	}
	
	/**
	 * 
	 * @param jugadores
	 * @return
	 */
	public ArrayList<String> convertirString(ArrayList<Jugador> jugadores){
		
		ArrayList<String> listado = new ArrayList<String>();
		
		for(int i = 0; i < jugadores.size(); i ++) {
			listado.add("Nombre : " + jugadores.get(i).getNombre() + ", " + "Puntaje: " + jugadores.get(i).getTiempo() + "\n" + "\n" );							 	
		}
	
		return listado;
		
	}
	
	/**
	 * 
	 * @param jugadores
	 * @return
	 */
	public ArrayList<Jugador> ordenarPorPuntaje(ArrayList<Jugador> jugadores) {
		
		for(int i = 0; i < jugadores.size() ; i ++) {
			for(int j = 0; j < jugadores.size() - 1- i; j ++) {
				if(jugadores.get(j).getTiempo() > jugadores.get(j + 1).getTiempo()) {
					Jugador temp = jugadores.get(j + 1);
					jugadores.add(j + 1, jugadores.get(j));
					jugadores.add(j, temp);
				}
			}
		}
		
		return jugadores;
	}
	
	/**
	 * 
	 * @param jugadores
	 * @return
	 */
	public ArrayList<Jugador> ordenarPorNombre(ArrayList<Jugador> jugadores){
		
		ArrayList<Jugador> listaDevolver = new ArrayList<Jugador>();
		Jugador[] lista = new Jugador[jugadores.size()];
		jugadores.toArray(lista);
		
		for(int i = 0; i < lista.length - 1 ; i ++) {
			int min = i;
			for(int j = i + 1; j < lista.length ; j ++) {
				if(lista[j].getNombre().compareTo(lista[min].getNombre()) < 0) {
					min = j;
				}
			}
			Jugador temp = lista[min];
			lista[min] = lista[i];
			lista[i] = temp;
		}
		
		for(int i = 0; i < lista.length ; i ++) {
			listaDevolver.add(lista[i]);
		}
		
		return listaDevolver;
		
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void guardarJugadorYPartida()throws FileNotFoundException, IOException {
		
		ObjectOutputStream salida;
		salida = new ObjectOutputStream(new FileOutputStream("datos\\serializable.dat"));
		salida.writeObject(getJugadorActual());
		salida.close();
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws YaExisteUnJugadorConEseNombreException
	 */
	public void cargarJugadores() throws FileNotFoundException, IOException, ClassNotFoundException, YaExisteUnJugadorConEseNombreException{
		
		ObjectInputStream entrada;
		
		entrada = new ObjectInputStream(new FileInputStream("datos\\serializable.dat"));
		datosGenerales.setPrimerJugador((Jugador)entrada.readObject());
		entrada.close();	
		
	}
	
	/**
	 * 
	 */
	public void quitarJugadorPrimero() {
		datosGenerales.quitarPrimero();
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Jugador> getBaseModificaciones() {
		return baseModificaciones;
	}

	/**
	 * 
	 * @param baseModificaciones
	 */
	public void setBaseModificaciones(ArrayList<Jugador> baseModificaciones) {
		this.baseModificaciones = baseModificaciones;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 * @throws NoExisteJugadorConEseNombreException
	 */
	public int busquedaBinariaJugadorNombre(String nombre)throws NoExisteJugadorConEseNombreException {
		
		ArrayList<Jugador> modificar = ordenarPorNombre(baseModificaciones);
		Jugador[] jugadores = new Jugador[modificar.size()];
		modificar.toArray(jugadores);
		int limiteInferior = 0;
		int limiteSuperior = jugadores.length - 1;
		
		while(limiteInferior <= limiteSuperior) {
			
			int mitad = limiteInferior + ((limiteSuperior - limiteInferior) / 2);
			
				if(jugadores[mitad].getNombre().equals(nombre)) {
					return mitad;
				}
				if(jugadores[mitad].getNombre().compareToIgnoreCase(nombre) < 0) {
					limiteInferior = mitad + 1; 
				}
				else {
					limiteSuperior = mitad - 1;
				}
			}
		throw new NoExisteJugadorConEseNombreException("No se encuentra un jugador relacionado con ese nombre");
		
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 */
	public Jugador obtenerJugadorPorPosicion(int i) {
		return baseModificaciones.get(i);
	}
	
	/**
	 * 
	 * @param puntaje
	 * @return
	 * @throws NoExisteJugadorConPuntajeException
	 */
	public int busquedaBinariaJugadorPuntaje(int puntaje)throws NoExisteJugadorConPuntajeException {
		
		ArrayList<Jugador> listado = ordenarPorNombre(baseModificaciones);
		Jugador[] juga = new Jugador[listado.size()];
		listado.toArray(juga);
		int limiteInferior = 0;
		int limiteSuperior = juga.length - 1;
		
		while(limiteInferior <= limiteSuperior) {
			
			int mitad = limiteInferior + ((limiteSuperior - limiteInferior) / 2);
			
				if(juga[mitad].getTiempo() == puntaje) {
					return mitad;
				}
				if(juga[mitad].getTiempo() < puntaje ) {
					limiteInferior = mitad + 1; 
				}
				else {
					limiteSuperior = mitad - 1;
				}
			}
		throw new NoExisteJugadorConPuntajeException("No se encuentra un jugador relacionado con ese nombre");
	}
	
	
	
	
}
