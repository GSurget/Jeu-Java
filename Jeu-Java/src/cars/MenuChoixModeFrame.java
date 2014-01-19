package cars;

import javax.swing.JFrame;

public class MenuChoixModeFrame extends JFrame{

	MenuChoixMode menuChoixMode = new MenuChoixMode();
	
	public MenuChoixModeFrame(){
		this.setTitle("Choix Mode");
		this.setSize(600, 600);
		this.setResizable(false);
		this.setContentPane(menuChoixMode);
		this.setVisible(true);
	}
}
