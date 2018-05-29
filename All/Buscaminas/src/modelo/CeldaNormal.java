package modelo;

/**
 * 
 * @author Asus
 *
 */
public class CeldaNormal extends Celda{

	/**
	 * 
	 */
	private boolean tieneBomba;
	
	/**
	 * 
	 * @param nombre
	 */
	public CeldaNormal(String nombre) {
		super(nombre);
	}

	/**
	 * @return the tieneBomba
	 */
	public boolean isTieneBomba() {
		return tieneBomba;
	}

	/**
	 * @param tieneBomba the tieneBomba to set
	 */
	public void setTieneBomba(boolean tieneBomba) {
		this.tieneBomba = tieneBomba;
	}
	
	
	
	
}
