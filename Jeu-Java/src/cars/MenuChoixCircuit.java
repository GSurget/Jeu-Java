package cars;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuChoixCircuit extends JPanel implements ActionListener {
		
	JButton circuitPrecedent = new JButton("CIRCUIT PRECEDENT");
	JButton circuitSuivant = new JButton("CIRCUIT SUIVANT");
	JButton valider = new JButton("VALIDER CIRCUIT");
	
	
	int circuit = 1;
	ImageIcon imageCircuit;

	
	public MenuChoixCircuit(){
		this.add(circuitPrecedent);
		this.add(circuitSuivant);
		this.add(valider);
		circuitPrecedent.addActionListener(this);
		circuitSuivant.addActionListener(this);
		valider.addActionListener(this);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 900, 900);
		g2d.setBackground(Color.WHITE);
		Font circuitFont = new Font("SansSerif", Font.BOLD, 30);
		g2d.setColor(Color.BLACK);
		g2d.setFont(circuitFont);
		
		switch(circuit){
		case 1: 
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Lac.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit du Lac", 10, 70);
		break;
		case 2:
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Iles.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit des Iles", 10, 70);
		break;
		case 3: 
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Lab.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit Labyrinthe", 10, 70);
		break;
		case 4:
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Flipper.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit Flipper", 10, 70);
		break;
		case 5: 
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Hard.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit difficile", 10, 70);
		break;
		case 6:
			imageCircuit = new ImageIcon("Images/Circuits/Circuit_Batman.png");
			g2d.drawImage(imageCircuit.getImage(), 50, 100,this);
			g2d.drawString("Circuit Batman", 10, 70);
		break;
		default:;
		}
	
		

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == circuitPrecedent){
			if(circuit > 1){
				circuit = circuit-1;
			}
			System.out.println(circuit);
			repaint();
		}
		else if(arg0.getSource() == circuitSuivant){
			if(circuit < 6){
				circuit = circuit+1;
			}
			System.out.println(circuit);
			repaint();	

		}
		else if(arg0.getSource() == valider){
			choixCircuit(circuit);
			cars.MenuChoixModeFrame menuChoixModeFrame = new cars.MenuChoixModeFrame();
			
		}	
	}
	
	public void choixCircuit(int circuit){
		switch(circuit){
		case 1:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Lac.circuit");
		break;
		case 2:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Iles.circuit");
		break;
		case 3:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Lab.circuit");
		break;
		case 4:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Flipper.circuit");
		break;
		case 5:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Hard.circuit");
		break;
		case 6:
			cars.MenuChoixVoiture.configuration.setCircuit("Circuits/Circuit_Batman.circuit");
		break;
		default:;
		}
	}
}