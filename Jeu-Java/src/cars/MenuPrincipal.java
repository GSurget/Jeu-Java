package cars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel implements ActionListener {

	JButton modeJeu = new JButton("MODE JEU");
	JButton modeEditeur = new JButton("MODE EDITEUR");
	JButton credits = new JButton("CREDITS");
	JButton b = new JButton("b");

	public MenuPrincipal(){
		credits.setSize(50, 50);
		this.add(modeJeu);
		this.add(modeEditeur);
		this.add(credits);
		repaint();
		modeJeu.addActionListener(this);
		modeEditeur.addActionListener(this);
		credits.addActionListener(this);
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setBackground(Color.BLACK);
		ImageIcon imageDrapeau = new ImageIcon("Images/logo.jpg");
		g2d.drawImage(imageDrapeau.getImage(), 50, 50,this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == modeJeu){
			cars.MenuChoixVoitureFrame frame = new cars.MenuChoixVoitureFrame();
		}
		else if(arg0.getSource() == modeEditeur){
			carsEditorCircuit.EditorFrame frame = new carsEditorCircuit.EditorFrame();
		}
		else if(arg0.getSource() == credits){
			System.out.println("Projet programmation Java\n\nRéalisation : Guillaume Surget et Hervé Do van\n----------------------------------------------\nDernière mise à jour: 14/01/2014");
		}		
	}
}
