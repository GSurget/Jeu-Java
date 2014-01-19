package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mur {

	int x, y;
	Image mur;
	
	public Mur(int startX, int startY){
		x = startX;
		y = startY;
		
		ImageIcon imageMur = new ImageIcon("Images/Textures/lego_blanc.png");
		mur = imageMur.getImage();
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
	
	public Image getImage(){
		return mur;
	}
	
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	

}
