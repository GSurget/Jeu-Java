package cars;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MenuPrincipalFrame extends JFrame {

	
	MenuPrincipal menuPrincipal = new MenuPrincipal();
	
	public MenuPrincipalFrame(){
		this.setTitle("Clicker Racer");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(menuPrincipal);
		this.setVisible(true);
	}
}
