package interfaz;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDerecho extends JPanel {                  

	public PanelDerecho() {
		setPreferredSize(new Dimension(40,0));
		TitledBorder borde = BorderFactory.createTitledBorder("");
		borde.setTitleColor(Color.BLACK);
		setBorder(borde); 	
		this.setBackground(Color.DARK_GRAY);
	}
	
	
}
