package hilos;

import interfaz.InterfazBuscaminas;
import modelo.Juego;

/**
 * 
 * @author Asus
 *
 */
public class HiloTiempo extends Thread {


	/**
	 * 
	 */
	private InterfazBuscaminas inter;

	/**
	 * 
	 */
	private Juego juego;
	
	/**
	 * 
	 * @param inter
	 * @param juego
	 */
	public HiloTiempo(InterfazBuscaminas inter, Juego juego) {
		this.inter = inter;
		this.juego = juego;
	}
	
	/**
	 * 
	 */
	public void run() {
		
		while(juego.isInicioJuego() == true && juego.isPerdioJuego() == false) {
			try {
				this.sleep(1000);
				juego.getTiempo().modificarSegundos();
				juego.getTiempo().modificarMinutos();
				juego.getTiempo().modificarHoras();
				inter.actualizarPanelTiempo();
			}
			catch(Exception e) {
				
			}	
		}
			
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
