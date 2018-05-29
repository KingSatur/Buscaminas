package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MatrizBotones extends JPanel implements ActionListener{
	
	
	private JButton[][] matriz;
	private InterfazBuscaminas inter;
	
	public  MatrizBotones(InterfazBuscaminas v) {
		inter = v;
	}
	
	public void organizarTamanhos(int a, int b, String[][] matrizNombres) {
		
		setLayout(new GridLayout(a,b));
		matriz = new JButton[a][b];
		
		for(int i = 0; i < a ; i ++) {
			for(int j = 0; j < b ; j ++) {
				matriz[i][j] = new JButton();
				matriz[i][j].addActionListener(this);
				matriz[i][j].setBackground(Color.white);
				add(matriz[i][j]);
			}
		}
		
		nombrarBotones(matrizNombres);
		
		
	}
	
	private void nombrarBotones(String[][] matrizNombres) {
		
		for(int i = 0; i < matriz.length ; i ++) {
			for(int j = 0; j < matriz[0].length ; j ++) {
				matriz[i][j].setActionCommand(matrizNombres[i][j]);
			}
		}
		
	}
	
	private void pintarBoton(String a) {
		
		for(int i = 0; i < matriz.length ; i++) {
			for(int j = 0; j < matriz[0].length ; j ++) {
				if(matriz[i][j].getActionCommand().equals(a)) {
					matriz[i][j].setBackground(Color.BLUE);
				}
			}
		}
		
	}
	
	public void pintarCeldasConMina(boolean[][] minas) {
		
		for(int i = 0; i < matriz.length ; i ++) {
			for(int j = 0; j < matriz[0].length ; j ++) {
				if(minas[i][j] == true) {
					matriz[i][j].setBackground(Color.blue);
				}
			}
		}
		
	}
		
	public void pintarCeldasSinCuadrante(boolean[][] celdasSinCuadrantes) {
		
		for(int i = 0; i < celdasSinCuadrantes.length ; i ++) {
			for(int j = 0; j < celdasSinCuadrantes[0].length ; j ++) {
				if(celdasSinCuadrantes[i][j] == false) {
					matriz[i][j].setBackground(Color.BLUE);
				}
			}
		}	
				
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String a = e.getActionCommand();
		
//		pintarCeldasSinCuadrante(inter.obtenerMatrizPosiblesCuadrantes());
		pintarCeldasConMina(inter.obtenerMatrizBombas());
		
//		JOptionPane.showMessageDialog(this, inter.buscarMinaEnCelda(a));
		
		
		
	}
	



	
}
