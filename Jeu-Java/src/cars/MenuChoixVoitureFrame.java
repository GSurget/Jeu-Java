package cars;

import javax.swing.JFrame;

public class MenuChoixVoitureFrame extends JFrame {
	
	MenuChoixVoiture menuChoixVoiture = new MenuChoixVoiture();
	
	public MenuChoixVoitureFrame(){
		this.setTitle("Choix Voiture");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setContentPane(menuChoixVoiture);
		this.setVisible(true);
	}
}


