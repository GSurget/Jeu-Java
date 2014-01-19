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

public class MenuChoixMode extends JPanel implements ActionListener {
	
	JButton modePrecedent = new JButton("MODE PRECEDENT");
	JButton modeSuivant = new JButton("MODE SUIVANT");
	JButton valider = new JButton("VALIDER");
	int mode = 0;
	ImageIcon apocalypse;
	
	public MenuChoixMode(){
		this.add(modePrecedent);
		this.add(modeSuivant);
		this.add(valider);
		modePrecedent.addActionListener(this);
		modeSuivant.addActionListener(this);
		valider.addActionListener(this);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 600, 600);
		apocalypse = new ImageIcon("Images/lego_zombie.jpg");
		g2d.setBackground(Color.WHITE);
		Font modeFont = new Font("SansSerif", Font.BOLD, 30);
		Font modeFont1 = new Font("SansSerif", Font.BOLD, 20);
		g2d.setColor(Color.BLACK);
		g2d.setFont(modeFont);
		switch(mode){
		case 0:
			g2d.drawString("Mode découverte", 100, 200);
			g2d.setFont(modeFont1);
			g2d.drawString("Juste pour le fun !", 100, 250);
		break;
		case 1: 
			g2d.setFont(modeFont);
			g2d.drawString("Mode course", 100, 200);
			g2d.setFont(modeFont1);
			g2d.drawString("Courez à deux !", 100, 250);
		break;
		case 2:
			g2d.setFont(modeFont);
			g2d.drawString("Mode course avec pluie", 100, 200);
			g2d.setFont(modeFont1);
			g2d.drawString("Attention ça glisse !", 100, 250);
		break;
		case 3:
			g2d.setFont(modeFont);
			g2d.drawString("Mode course de nuit", 100, 200);
			g2d.setFont(modeFont1);
			g2d.drawString("Il fait sombre...", 100, 250);
		break;
		case 4:
			g2d.setFont(modeFont);
			g2d.drawString("Mode course de nuit avec pluie", 100, 200);
			g2d.setFont(modeFont1);
			g2d.drawString("Il fait sombre et attention ça glisse !", 100, 250);
		break;
		case 5:
			Font circuitnewFont = new Font("Viner Hand ITC", Font.BOLD, 30);
			g2d.setFont(circuitnewFont);
			g2d.drawString("Mode Post-Apocalypse",100, 200);
			
			g2d.drawImage(apocalypse.getImage(),100,230,this);
		break;
		default:;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == modePrecedent){
			if(mode > 0){
				mode = mode-1;
			}
			System.out.println(mode);
			repaint();
		}
		else if(arg0.getSource() == modeSuivant){
			if(mode < 5){
				mode = mode+1;				
			}
			System.out.println(mode);
			repaint();
		}
		else if(arg0.getSource() == valider){
			choixMode(mode);
			carsGame.GameFrame frame = new carsGame.GameFrame();
		}	
	}
	
	public void choixMode(int mode){
		switch(mode){
		case 0:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		case 1:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		case 2:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		case 3:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		case 4:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		case 5:
			cars.MenuChoixVoiture.configuration.setMode(mode);
		break;
		}	
	}
}
