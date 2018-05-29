package modelo;

import java.util.ArrayList;

import excepciones.YaExisteUnJugadorConEseNombreException;

public class Datos {
	
	/**
	 * Relacion con el jugador que esta jugando actualmente.
	 */
	private Jugador primerJugador;
	/**
	 * Atributo que indica la cantidad de jugadores que han jugado.
	 */
	private int cantidadJugadores;
	
	/**
	 * 
	 */
	private Juego juego;
	
	/**
	 * 
	 */
	public Datos(Juego juego) {
		this.juego = juego;
	}

	/**
	 * 
	 * @return
	 */
	public Jugador getPrimerJugador() {
		return primerJugador;
	}

	/**
	 * 
	 * @param jugdorActual
	 * @throws YaExisteUnJugadorConEseNombreException 
	 */
	public void setPrimerJugador(Jugador jugador) throws YaExisteUnJugadorConEseNombreException {
		
		Jugador actual = primerJugador;
		
		if(jugador != null) {
			if(actual == null) {
				primerJugador = jugador;
			}
			else {
				if(actual.getJugadorSiguiente() == null) {
					jugador.setJugadorSiguiente(primerJugador);
					primerJugador = jugador;
				}
				else {
					if(buscarJugadorPorNombre(jugador) == null) {
						jugador.setJugadorSiguiente(primerJugador);
						primerJugador = jugador;
					}
					else {
						throw new YaExisteUnJugadorConEseNombreException("Ya esta " + jugador.getNombre());
					}
				}
			}
		}
		else {
			primerJugador = null;
		}
		
		
	}

	/**
	 * 
	 * @param jugador
	 * @return
	 */
	public Jugador buscarJugadorPorNombre(Jugador jugador) {
		
		Jugador actual = primerJugador;
		
		while(actual != null && (!(actual.getNombre().equals(jugador.getNombre())))){
			actual = actual.getJugadorSiguiente();
		}
		
		return actual;
		
	}
	
	public void quitarPrimero() {
		primerJugador = primerJugador.getJugadorSiguiente();
		primerJugador = null;
	}
	
	public void modificarPuntajeJugador() {
		primerJugador.setTiempo(juego.getTiempo().darPuntaje());
	}
	
	
	
	
}
