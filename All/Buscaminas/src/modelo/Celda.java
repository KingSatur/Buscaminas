package modelo;

public abstract class Celda{
	
	/**
	 * 
	 */
	private boolean presionada;
	/**
	 * Atributo que indica la celda tiene cuadrante.
	 */
	private String nombre;

	
	/**
	 * 
	 * @param nombre
	 */
	public Celda(String nombre) {
		this.nombre = nombre;	
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPresionada() {
		return presionada;
	}

	/**
	 * 
	 * @param presionada
	 */
	public void setPresionada(boolean presionada) {
		this.presionada = presionada;
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
	
	
	

		
	
		
		
		
		
	
	
	
}
