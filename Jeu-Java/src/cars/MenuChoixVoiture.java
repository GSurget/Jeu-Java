package cars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuChoixVoiture extends JPanel implements ActionListener {
		
	JButton modelePrecedent = new JButton("MODELE PRECEDENT");
	JButton modeleSuivant = new JButton("MODELE SUIVANT");
	JButton validerJoueur1 = new JButton("VALIDER JOUEUR 1");
	JButton validerJoueur2 = new JButton("VALIDER JOUEUR 2");
	int modele = 1;
	ImageIcon imageModele;
	
	public static Configuration configuration = new Configuration();
	
	public MenuChoixVoiture(){
		this.add(modelePrecedent);
		this.add(modeleSuivant);
		this.add(validerJoueur1);
		this.add(validerJoueur2);
		modelePrecedent.addActionListener(this);
		modeleSuivant.addActionListener(this);
		validerJoueur1.addActionListener(this);
		validerJoueur2.addActionListener(this);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, 600, 600);
		g2d.setBackground(Color.WHITE);
		switch(modele){
		case 1: 
			imageModele = new ImageIcon("Images/Voitures/lego_pilote.jpg");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		case 2:
			imageModele = new ImageIcon("Images/Voitures/batman.png");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		case 3:
			imageModele = new ImageIcon("Images/Voitures/bane.png");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		case 4:
			imageModele = new ImageIcon("Images/Voitures/Super_man.png");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		case 5:
			imageModele = new ImageIcon("Images/Voitures/soldat.png");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		case 6:
			imageModele = new ImageIcon("Images/Voitures/alien.jpg");
			g2d.drawImage(imageModele.getImage(), 50, 50,this);
		break;
		default:;
		}
	
		

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == modelePrecedent){
			if(modele >1){
				modele = modele-1;
			}
			System.out.println(modele);
			repaint();
		}
		else if(arg0.getSource() == modeleSuivant){
			if(modele<6){
				modele = modele+1;	
			}
			System.out.println(modele);
			repaint();

		}
		else if(arg0.getSource() == validerJoueur1){
			choixModele(modele, 1);
			
		}	
		else if(arg0.getSource() == validerJoueur2){
			choixModele(modele, 2);
			cars.MenuChoixCircuitFrame frame = new cars.MenuChoixCircuitFrame();
		}	
		
	}
	
	public void choixModele(int modele, int joueur){
		switch(modele){
		case 1:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/car1.png");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/car2.png");
			}	
		break;
		case 2:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/batmobile.png");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/batmobile.png");
			}	
		break;
		case 3:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/batmobile_kaki.png");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/batmobile_kaki.png");
			}	
		break;
		case 4:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/lego_superman.png");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/lego_superman.png");
			}	
		break;
		case 5:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/tank.jpg");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/tank.jpg");
			}	
		break;
		case 6:
			if(joueur == 1){
				configuration.setVoiture1("Images/Voitures/soucoupe.png");
			}else if(joueur == 2){
				configuration.setVoiture2("Images/Voitures/soucoupe.png");
			}	
		break;
		}
	}
}
