package modelo;

public class Timer {

	/**
	 * 
	 */
	private int hora;

	/**
	 * 
	 */
	private int minuto;

	/**
	 * 
	 */
	private int segundo;

	/**
	 * 
	 */
	private String segundoDevolver;

	/**
	 * 
	 */
	private String minutoDevolver;

	/**
	 * 
	 */
	private String horaDevolver;
	
	
	/**
	 * 
	 */
	public Timer() {
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
		horaDevolver = "00";
		segundoDevolver = "00";
		minutoDevolver = "00";
	}

	/**
	 * @return the minuto
	 */
	public int getMinuto() {
		return minuto;
	}

	/**
	 * @param minuto the minuto to set
	 */
	public void setMinuto(int minuto) {

	}

	/**
	 * @return the segundo
	 */
	public int getSegundo() {
		return segundo;
	}

	/**
	 * @param segundo the segundo to set
	 */
	public void modificarSegundos() {
		
		if(segundo < 60) {
			if(segundo < 9) {
				segundo ++;
				segundoDevolver = "0" + segundo;
			}
			else{
				segundo ++;
				segundoDevolver = Integer.toString(segundo);
			}
		}
		else {
			segundo = 0;
			segundoDevolver = "00";
		}
		
	}

	/**
	 * 
	 * @return
	 */
	public String segundoDevolver() {
		return segundoDevolver;
	}
	
	/**
	 * 
	 */
	public void modificarMinutos() {
		
		if(segundo == 60) {
			if(minuto < 9) {
				minuto ++;
				minutoDevolver = "0" + minuto;
			}
			else {
				minuto ++;
				minutoDevolver = Integer.toString(minuto);
			}
		}
	
		
		
	}
	
	/**
	 * 
	 */
	public void modificarHoras() {
		
		if(minuto == 60) {
			minuto = 0;
			if(hora < 9) {
				hora ++;
				horaDevolver = "0" + hora;
			}
			else {
				hora ++;
				horaDevolver = Integer.toString(hora);
			}
		}
		
		
		
	}
	
	/**
	 * @return the hora
	 */
	public int getHora() {
		return hora;
	}
	

	/**
	 * @param hora the hora to set
	 */
	public void setHora(int hora) {
		
	}

	/**
	 * 
	 * @return
	 */
	public String minutoDevolver() {
		return minutoDevolver;
	}
	
	/**
	 * 
	 * @return
	 */
	public String horaDevolver() {
		return horaDevolver;
	}
	
	
	public int darPuntaje() {
		
		if(minuto == 1) {
			return 0;
		}
		else if(minuto == 2) {
			return 1;
		}
		else if(minuto == 3) {
			return 2;
		}
		else if(minuto == 4) {
			return 3;
		}
		else if(minuto == 5) {
			return 4;
		}
		else {
			return 5;
		}
		
		
	}
	
	
	
	
}
