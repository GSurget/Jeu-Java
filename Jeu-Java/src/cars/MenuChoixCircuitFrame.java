package cars;

import javax.swing.JFrame;

public class MenuChoixCircuitFrame extends JFrame {
	
	MenuChoixCircuit menuChoixCircuit = new MenuChoixCircuit();
	
	public MenuChoixCircuitFrame(){
		this.setTitle("Choix Voiture");
		this.setSize(900, 900);
		this.setResizable(false);
		this.setContentPane(menuChoixCircuit);
		this.setVisible(true);
	}
}
