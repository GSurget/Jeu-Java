package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sable {
	int x, y;
	Image sable;
	double frottements = 1.5;
	
	public Sable(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageGrass = new ImageIcon("Images/Textures/lego_jaune.png");
		sable = imageGrass.getImage();
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
	
	public double getFrottements(){
		return frottements;
	}
	
	public Image getImage(){
		return sable;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
}
