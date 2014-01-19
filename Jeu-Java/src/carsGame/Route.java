package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Route {
	int x,y;
	Image route;
	double frottements = 1.0;
	
	public Route(int startX, int startY){
		x = startX;
		y = startY;	
		
		ImageIcon imageRoute = new ImageIcon("Images/Textures/asphalt.jpg");
		route = imageRoute.getImage();
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
		return route;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	

}
