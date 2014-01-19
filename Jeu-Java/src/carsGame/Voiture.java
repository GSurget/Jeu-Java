package carsGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {
	int x=0,y=0;
	int vitesseX=0, vitesseY=0;
	//String car = "CAR";
	Image voiture;
	int hauteurImage;
	int largeurImage;
	
	public Voiture(int startX, int startY, int startVitesseX, int startVitesseY){
		x = startX;
		y = startY;
		vitesseX = startVitesseX;
		vitesseY = startVitesseY;
		
		ImageIcon imageCar = new ImageIcon("Images/car.png");
		voiture = imageCar.getImage();
		hauteurImage = voiture.getHeight(null);
		System.out.println("hauteur = "+hauteurImage);
		largeurImage = voiture.getWidth(null);
		System.out.println("largeur = "+largeurImage);
	}
	
	public Rectangle getBounds(){
		Rectangle Boite = new Rectangle(x,y,20,30);
		return Boite;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getVitesseX(){
		return vitesseX;
	}
	
	public int getVitesseY(){
		return vitesseY;
	}
	
	public Image getImage(){
		return voiture;
	}
	
	public void setImage(String nom){
		ImageIcon newImageCar = new ImageIcon(nom);
		voiture = newImageCar.getImage();
	}
	public void setX(int newX){
		this.x = newX;
	}
	
	public void setY(int newY){
		this.y = newY;
	}
	
	public void setVitesseX(int newVitesseX){
		this.vitesseX = newVitesseX;
	}
	public void setVitesseY(int newVitesseY){
		this.vitesseY = newVitesseY;
	}
		
}
