package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Cratere {
	int x, y;
	Image cratere;
	double frottements = 1.0;
	
	public Cratere(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageCratere = new ImageIcon("Images/Textures/lego_cratere.png");
		cratere = imageCratere.getImage();
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
		return cratere;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
}
