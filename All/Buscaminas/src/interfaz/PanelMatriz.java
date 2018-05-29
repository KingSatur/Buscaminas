package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelMatriz extends JPanel{

	private InterfazBuscaminas interfaz;
	private PanelDerecho panelDerecho;
	private PanelIzquierdo panelIzquierdo;
	private PanelMatrizAuxiliar panelAux;
	
	
	public PanelMatriz(InterfazBuscaminas v) {
		interfaz = v;
		panelDerecho = new PanelDerecho();
		panelIzquierdo = new PanelIzquierdo();
		panelAux = new PanelMatrizAuxiliar(interfaz);
		setLayout(new BorderLayout());
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder(borde);
		this.add(panelAux, BorderLayout.CENTER);
		this.add(panelDerecho, BorderLayout.WEST);
		this.add(panelIzquierdo, BorderLayout.EAST);
	}

	public InterfazBuscaminas getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(InterfazBuscaminas interfaz) {
		this.interfaz = interfaz;
	}

	public PanelDerecho getPanelDerecho() {
		return panelDerecho;
	}

	public void setPanelDerecho(PanelDerecho panelDerecho) {
		this.panelDerecho = panelDerecho;
	}

	public PanelIzquierdo getPanelIzquierdo() {
		return panelIzquierdo;
	}

	public void setPanelIzquierdo(PanelIzquierdo panelIzquierdo) {
		this.panelIzquierdo = panelIzquierdo;
	}

	public void actualizarPanelAuxiliar() {
		panelAux.repaint();
	}
	
	
	
}
