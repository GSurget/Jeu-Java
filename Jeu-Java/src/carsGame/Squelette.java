package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Squelette {

	int x, y;
	Image squelette;
	
	public Squelette(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageSquelette = new ImageIcon("Images/Textures/lego_squelette.png");
		squelette = imageSquelette.getImage();
	}
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getImage(){
		return squelette;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
}
