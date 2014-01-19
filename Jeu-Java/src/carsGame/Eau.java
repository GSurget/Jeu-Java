package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Eau {

	int x, y;
	Image eau;
	double frottements = 2;
	
	public Eau(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageEau = new ImageIcon("Images/Textures/lego_bleu.png");
		eau = imageEau.getImage();
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
	
	public Image getImage() {
		return eau;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
}
