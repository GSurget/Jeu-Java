package carsEditorCircuit;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EditorBoard extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	//MouseListener : lecture des �v�nements de la souris (clic)
	//MouseMotionListener : lecture du mouvement de la souris
	//MouseWheelListener : lectured de la molette de la souris
	//KeyListener : lecture du clavier
	
	String editeur[][] = new String[30][25]; // Tableau g�rant notre panel de 20*20 images
	String imageSelect[] = {"MUR", "ROUTE", "HERBE", "EAU", "SABLE", "ARRIVEE" }; //Tableau contenant les images 
	String imageCourante = "MUR"; // Image selectionn�e
	int sourisX, sourisY; //variable pour la position de la souris
	int index = 0; //index de l'image courante
	Image mur;//wall est du type image 
	Image route;
	Image herbe;
	Image eau;
	Image sable;
	Image arrivee;
	FileWriter fw;
	FileReader fr;
	
	// Constructeur //
	public EditorBoard(){
		ImageIcon imageMur = new ImageIcon("Images/Textures/lego_blanc.png"); //importation de l'image "grass" dans la variable imageGrass
		mur = imageMur.getImage();//copie de l'image contenue dans imageGrass vers la variable Grass
		
		ImageIcon imageRoute = new ImageIcon("Images/Textures/asphalt.jpg");
		route = imageRoute.getImage();
		
		ImageIcon imageHerbe = new ImageIcon("Images/Textures/lego_vert.png");
		herbe = imageHerbe.getImage();
		
		ImageIcon imageEau = new ImageIcon("Images/Textures/lego_bleu.png");
		eau = imageEau.getImage();
		
		ImageIcon imageSable = new ImageIcon("Images/Textures/lego_jaune.png");
		sable = imageSable.getImage();
		
		ImageIcon imageArrivee = new ImageIcon("Images/Textures/lego_rouge.png");
		arrivee = imageArrivee.getImage();
		
		setFocusable(true);
		
		addMouseListener(this);//instance en tant que param�tre 
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
	
	}

	public void paint (Graphics g){ //m�thode de dessin sur le panel
		
		super.paint(g); //appelle la m�thode paint de la classe parente de EditorBoad : JPanel
		Graphics2D g2d = (Graphics2D) g; //on caste g en Graphics2D qui offre plus de possibilit�s
		
		for(int i=0; i<30; i++){
			for(int j=0; j<25; j++){//deplacement dans le tableau de notre panel
				
				if(editeur[i][j] == "MUR"){
					g2d.drawImage(mur, i*30, j*30, null);//si l'editeur contient l'image MUR alors on dessine mur � l'emplacement i*30 et j*30 car l'image fait 30*30pixels
				}
				if(editeur[i][j] == "ROUTE"){
					g2d.drawImage(route, i*30, j*30, null);
				}
				if(editeur[i][j] == "HERBE"){
					g2d.drawImage(herbe, i*30, j*30, null);
				}
				if(editeur[i][j] == "EAU"){
					g2d.drawImage(eau, i*30, j*30, null);
				}
				if(editeur[i][j] == "SABLE"){
					g2d.drawImage(sable, i*30, j*30, null);
				}
				if(editeur[i][j] == "ARRIVEE"){
					g2d.drawImage(arrivee, i*30, j*30, null);
				}
			}
		}
		
		if(imageCourante == "MUR"){
			g2d.drawImage(mur, sourisX, sourisY, null);//dessine l'image courante � la position de la souris
		}
		else if(imageCourante == "ROUTE"){
			g2d.drawImage(route, sourisX, sourisY, null);
		}
		else if(imageCourante == "HERBE"){
			g2d.drawImage(herbe, sourisX, sourisY, null);
		}
		else if(imageCourante == "EAU"){
			g2d.drawImage(eau, sourisX, sourisY, null);
		}
		else if(imageCourante == "SABLE"){
			g2d.drawImage(sable, sourisX, sourisY, null);
		}
		else if(imageCourante == "ARRIVEE"){
			g2d.drawImage(arrivee, sourisX, sourisY, null);
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {//evenement touche relach�e
		char key = arg0.getKeyChar();//key vaut le cract�re du clavier relach�
		
		if(key == 's'){
			try{
				fw = new FileWriter (JOptionPane.showInputDialog(null, "Entrez le chemin de sauvegarde :", "EDITEUR DE CIRCUITS", JOptionPane.QUESTION_MESSAGE));//Ouvre une fenetre de dialogue pour entrez le chemin d'enregistrement du fichier
				for (int j=0; j<25; j++ ){
					for (int i=0; i<30; i++){// on parcourt le tableau editeur
						if(editeur[i][j] == "MUR"){
							fw.write('m');//on �crit dans le fichier un "m" si il y a un "wall" dans l'editeur
						}
						else if(editeur[i][j] == "ROUTE"){
							fw.write('r');
						}
						else if(editeur[i][j] == "HERBE"){
							fw.write('h');
						}
						else if(editeur[i][j] == "EAU"){
							fw.write('e');
						}
						else if(editeur[i][j] == "SABLE"){
							fw.write('s');
						}
						else if(editeur[i][j] == "ARRIVEE"){
							fw.write('a');
						}
						else if(editeur[i][j] == null){
							fw.write(' '); //on �crit un espace si il n'y a rien dans l'�diteur
						}
					}
					fw.write("\r\n"); //on �crit "\r\n" � la fin de chaque ligne
				}
				fw.close(); //on ferme la m�thode d'�criture
			}
			catch (Exception ex){}
		}
		else if(key == 'l'){
			try{
				fr = new FileReader (JOptionPane.showInputDialog(null, "Entrez le chemin de lecture :", "EDITEUR DE CIRCUITS", JOptionPane.QUESTION_MESSAGE));
				int i=0;
				int x=0, y=0;
				
				while((i = fr.read()) != -1){//on continue � lire tant qu'on a pas atteint -1, signifiant la fin de la lecture
					char strImg = (char) i; //on caste i en chaine de caract�re pour pouvoir lire le fichier
					
					if(strImg == 'm'){ 
						editeur[x][y] = "MUR";//si strImg vaut 'm' alors on met un "MUR" dans l'�diteur
					}
					else if(strImg == 'r'){
						editeur[x][y] = "ROUTE";
					}
					else if(strImg == 'h'){
						editeur[x][y] = "HERBE";
					}
					else if(strImg == 'e'){
						editeur[x][y] = "EAU";
					}
					else if(strImg == 's'){
						editeur[x][y] = "SABLE";
					}
					else if(strImg == 'a'){
						editeur[x][y] = "ARRIVEE";
					}
					else if(strImg == ' '){
						editeur[x][y] = null;
					}
					else if(strImg == '\n' || strImg == '\r'){//si on arrive � la fin de la ligne on d�cr�mente x afin de revenir � x=29
						x--;//
					}
					
					if(x == 29){
						y++;//si x=29 on avance d'une ligne et on remet x � 0
						x =0;
					}
					else{
						x++;//on incr�mente x
					}
				}
			}
			catch(Exception ex){}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {//mouvement sur la molette
		int rotation = arg0.getWheelRotation();//rotation indique le sens de rotation de la molette (+ ou -)
		
		if(rotation < 0){
			if(index > 0){//rotation n�gative => on d�cr�mente l'index correspondant aux images et il reste bloqu� sur l'image si inf�rieur
				index--;
			}
		}
		else if(rotation > 0){
			if(index < 5){//rotation positive => on incr�mente l'index qui reste bloqu� sur l'image si sup�rieur  
				index++;  
			}
		}
		
		imageCourante = imageSelect[index];//on met dans l'image courante l'image de num�ro index dans le tableau imageSelect
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		sourisX = arg0.getX()-15;//la variable mX prend la coordonn�e x de la position de la souris � laquelle on soustrait 15 pour que l'image soit centr�e 
		sourisY = arg0.getY()-15;
		
		repaint(); //appelle la m�thode paint
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) { //evenement bouton relach�
		int x = arg0.getX()/30; // x corrspond � l'index du tableau qui g�re notre circuit donc on divise la coordonn�e de la souris par 30 et on garde la partie r�elle (x est un int) pour avoir une valeur comprise entre 1 et 20
		int y = arg0.getY()/30;

		if(arg0.getButton() == MouseEvent.BUTTON1){// si le bouton 1 (clic gauche) est relach�
			editeur[x][y] = imageCourante;//on place l'image courante dans le tableau editeur
		}
		else if(arg0.getButton() == MouseEvent.BUTTON3){// si le bouton 1 (clic droit) est relach�
			editeur[x][y] = null;//on ne place rien dans le tableau editeur
		}
		
		repaint();
	}
}
