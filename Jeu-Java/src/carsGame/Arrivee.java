package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Arrivee {
	int x, y;
	Image arrivee;
	
	public Arrivee(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageArrivee = new ImageIcon("Images/Textures/lego_rouge.png");
		arrivee = imageArrivee.getImage();
	}
	
	public Rectangle getBounds(){
		Rectangle Boite = new Rectangle(x,y,30,30);
		return Boite;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage() {
		return arrivee;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
}
