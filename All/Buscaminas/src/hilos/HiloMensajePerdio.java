package hilos;

import interfaz.InterfazBuscaminas;
import modelo.Juego;

/**
 * 
 * @author JUAN DAVID CAMPILLO CEPEDA.
 *
 */
public class HiloMensajePerdio extends Thread {
	
	/**
	 * 
	 */
	private Juego buscaminas;
	/**
	 * 
	 */
	private InterfazBuscaminas interfaz;
	
	/**
	 * 
	 * @param buscaminas
	 * @param interfaz
	 */
	public HiloMensajePerdio(Juego buscaminas, InterfazBuscaminas interfaz) {
		this.buscaminas = buscaminas;
		this.interfaz = interfaz;
	}
	
	/**
	 * 
	 */
	public void run() {
		
		while(interfaz.obtenerIsJuegoPerdido()) {
			
			for(int i = interfaz.obtenerPosicionYMensaje(); i < 8; i ++) {
				try {
					buscaminas.setPosicionYMensajePerdio(i);
					interfaz.actualizarPanelTiempo();;
					this.sleep(100);
				}
				catch(Exception e) {
					
				}
			}
			
		}
	}
			
	
	
}
