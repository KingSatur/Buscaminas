package modelo;

import java.io.Serializable;

public class Jugador implements Serializable{

	/**
	 * Atributo que indica la cantidad de jugadores que han jugado.
	 */
	private String nombre;
	
	/**
	 * Atributo que indica la cantidad de jugadores que han jugado.
	 */
	private int tiempo;
	
	/**
	 * 
	 */
	private Jugador jugadorSiguiente;
	
	/**
	 * 
	 * @param nombre
	 */
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.tiempo = 0;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * 
	 * @param tiempo
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the jugadorSiguiente
	 */
	public Jugador getJugadorSiguiente() {
		return jugadorSiguiente;
	}

	/**
	 * @param jugadorSiguiente the jugadorSiguiente to set
	 */
	public void setJugadorSiguiente(Jugador jugadorSiguiente) {
		this.jugadorSiguiente = jugadorSiguiente;
	}

	
	
	
	
	
	
	
}
