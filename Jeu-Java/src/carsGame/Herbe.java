package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Herbe {
	int x, y;
	Image herbe;
	double frottements = 1.2;
	
	public Herbe(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageHerbe = new ImageIcon("Images/Textures/lego_vert.png");
		herbe = imageHerbe.getImage();
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
		return herbe;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	
}
