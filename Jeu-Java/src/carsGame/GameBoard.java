package carsGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import cars.MenuChoixVoiture;
public class GameBoard extends JPanel implements MouseListener, MouseMotionListener{
	
	String Game[][] = new String[30][25];// Tableau contenant le circuit
	
	private static ArrayList<Mur> murs;
	private static ArrayList<Route> routes;
	private static ArrayList<Herbe> herbes;
	private static ArrayList<Eau> eaux;
	private static ArrayList<Sable> sables;
	private static ArrayList<Arrivee> arrivees;
	private static ArrayList<Flaque> flaques;
	private static ArrayList<Cratere> crateres;
	private static ArrayList<Squelette> squelettes;
	
	private int sourisX; 
	private int sourisY;
	private double alpha1;
	private double alpha2;
	private int nbClic = 0;
	private int repetition=0;
	private int x1, y1, x2, y2;// variable temporaires de la position des voitures
	private int mouvement = 0;// variable servant à la décomposition du mouvement
	private int collision = 0;
	private static ArrayList<Integer[]> coordPrecedentes1 = new ArrayList<Integer[]>();
	private static ArrayList<Integer[]> coordPrecedentes2 = new ArrayList<Integer[]>();
			
	 int numero = 1;
	 Mur mur;
	 Route route;
	 Herbe herbe;
	 Eau eau;
	 Sable sable;
	 Arrivee arrivee;
	 Flaque flaque;
	 Cratere cratere;
	 Squelette squelette;
	
	private SonAudio sonFond = null;
	private SonAudio sonCrash = null;
	private SonAudio sonArrivee = null;
	private SonAudio sonMoteur = null;
	private SonAudio sonCri = null;
	
	private boolean modePluie = false;
	private boolean modeApocalypse = false;
	
	private static Voiture voiture1 = new Voiture(67, 467, 0, 0);
	private static Voiture voiture2 = new Voiture(47, 467, 0, 0);
	private String nomVoiture = new String();
	
	FileReader fr;
	
	public GameBoard(){//Fonction de chargement des différents éléments du jeu 
		chargerCircuit();
		chargerMode();
		chargerSon();
		setFocusable(true);
		addMouseListener(this);
	}
	
	public void chargerSon(){
		sonFond = new SonAudio("Sons/fond.wav");// On indique le fichier son (en wav) à associer à "sonFond" 
		sonFond.initialiser();// On initialise le sonAudio
		sonFond.play();// On lance la lecture du fichier audio, cette comande permet de le lancer depuis n'importe quel endroit du code sans répéter les étapes d'initialisation
		sonCrash = new SonAudio("Sons/crash.wav");
		sonCrash.initialiser();
		sonArrivee = new SonAudio("Sons/arrivee.wav");
		sonArrivee.initialiser();
		sonMoteur = new SonAudio("Sons/moteur.wav");
		sonMoteur.initialiser();
		sonCri = new SonAudio("Sons/argh.wav");
		sonCri.initialiser();
	}
	
	public void chargerCircuit(){
		try{
			//fr = new FileReader("test");
			fr = new FileReader(cars.MenuChoixVoiture.configuration.getCircuit());// On lit le fichier indiquer dans le panel choixCircuit
		
			int x = 0, y = 0, i = 0;
			
			murs = new ArrayList<Mur>();// On créé les listes qui contiennent les différents bloc d'une même classe du terrain 
			routes = new ArrayList<Route>();
			herbes = new ArrayList<Herbe>();
			eaux = new ArrayList<Eau>();
			sables = new ArrayList<Sable>();
			arrivees = new ArrayList<Arrivee>();
			
			while((i = fr.read()) != -1){
				char strImg = (char) i;
				
				if(strImg == 'm'){// On rempli les listes pour chaque objet MUR trouvé dans le fichier lu
					Game[x][y] = "MUR";
					mur = new Mur(x*30, y*30);
					murs.add(mur);
				}
				else if(strImg == 'r'){
					Game[x][y] = "ROUTE";
					route = new Route(x*30, y*30);
					routes.add(route);
				}
				else if(strImg == 'h'){
					Game[x][y] = "HERBE";
					herbe = new Herbe(x*30, y*30);
					herbes.add(herbe);
				}
				else if(strImg == 'e'){
					Game[x][y] = "EAU";
					eau = new Eau(x*30, y*30);
					eaux.add(eau);
				}
				else if(strImg == 's'){
					Game[x][y] = "SABLE";
					sable = new Sable(x*30, y*30);
					sables.add(sable);
				}
				else if(strImg == 'a'){
					Game[x][y] = "ARRIVEE";
					arrivee = new Arrivee(x*30, y*30);
					arrivees.add(arrivee);
				}
				else if(strImg == ' '){
					Game[x][y] = null;
				}
				else if(strImg == '\r' || strImg == '\n'){
					x--;
				}
				if(x == 29){
					y++;
					x=0;
				}
				else{
					x++;
				}
			}
		}
		catch(Exception ex){
			System.out.println("Probleme detecte :"+ex);
		}
		repaint();
	}
	
	public void chargerMode(){
		switch(cars.MenuChoixVoiture.configuration.getMode()){
		case 2:/// mode course pluie
			chargerModePluie();
			modePluie = true;
		break;
		case 4:///mode course nuit pluie
			chargerModePluie();
			modePluie = true;
		break;
		case 5:///Mode Post-apocalypse
			chargerModeApocalypse();
			modeApocalypse =true;
		break;
		}
	}
	
	public void chargerModePluie(){
		flaques = new ArrayList<Flaque>();
		for(int i=0; i<30; i++){
			for(int j=3; j<25; j++){
				if((Math.random()<0.05)&&((i<24)||(j<19))){
					flaque = new Flaque(i*30, j*30);
					flaques.add(flaque);
				}
			}
		}
	}
	
	public void chargerModeApocalypse(){
		/////Placement aléatoire des squelettes/////
		crateres = new ArrayList<Cratere>();
		for(int i=0; i<30; i++){
			for(int j=3; j<25; j++){
				if((Math.random()<0.02)&&((i<24)||(j<19))){
					cratere = new Cratere(i*30, j*30);
					crateres.add(cratere);
				}
			}
		}
		
		squelettes = new ArrayList<Squelette>();
		for(int i=0; i<30; i++){
			for(int j=3; j<25; j++){
				if((Math.random()<0.04)&&((i<24)||(j<19))){
					squelette = new Squelette(i*30, j*30);
					squelettes.add(squelette);
				}
			}
		}
	}
	
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;// On utilise Graphics2D pour pouvoir dessiner des images, sachant que Graphics ne le permet pas
		
		for(int i=0; i<murs.size(); i++){// On dessine les différent éléments contenu dans les listes
			mur = (Mur) murs.get(i);
			g2d.drawImage(mur.getImage(), mur.getX(), mur.getY(), null);
		}
		for(int i=0; i<routes.size(); i++){
			route = (Route) routes.get(i);
			g2d.drawImage(route.getImage(), route.getX(), route.getY(), null);
		}
		for(int i=0; i<herbes.size(); i++){
			herbe = (Herbe) herbes.get(i);
			g2d.drawImage(herbe.getImage(), herbe.getX(), herbe.getY(), null);
		}
		for(int i=0; i<eaux.size(); i++){
			eau = (Eau) eaux.get(i);
			g2d.drawImage(eau.getImage(), eau.getX(), eau.getY(), null);
		}
		for(int i=0; i<sables.size(); i++){
			sable = (Sable) sables.get(i);
			g2d.drawImage(sable.getImage(), sable.getX(), sable.getY(), null);
		}
		for(int i=0; i<arrivees.size(); i++){
			arrivee = (Arrivee) arrivees.get(i);
			g2d.drawImage(arrivee.getImage(), arrivee.getX(), arrivee.getY(), null);
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////Evolution du circuit en fonction du mode ////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////
		switch(cars.MenuChoixVoiture.configuration.getMode()){
		case 1:/// Mode course
			/////Placement des bordures sur la piste/////
			dessinBordures(g2d);
		break;
		case 2:/// mode course pluie
			dessinBordures(g2d);
			for(int i=0; i<flaques.size(); i++){// Placement aléatoire des flaques d'eau
				flaque = (Flaque) flaques.get(i);
				g2d.drawImage(flaque.getImage(), flaque.getX(), flaque.getY(), null);
			}
		break;
		case 3:/// mode course nuit
			dessinBordures(g2d);
			nuit(g2d);
		break;
		case 4:///mode course nuit pluie
			dessinBordures(g2d);
			nuit(g2d);
			for(int i=0; i<flaques.size(); i++){
				flaque = (Flaque) flaques.get(i);
				g2d.drawImage(flaque.getImage(), flaque.getX(), flaque.getY(), null);
			}
		break;
		case 5:///Mode Post-apocalypse
			for(int i=0; i<squelettes.size(); i++){
				squelette = (Squelette) squelettes.get(i);
				g2d.drawImage(squelette.getImage(), squelette.getX(), squelette.getY(), null);
			}			
			/////Placement aléatoire des crateres depuis l'ArrayList associée/////
			for(int i=0; i<crateres.size(); i++){
				cratere = (Cratere) crateres.get(i);
				g2d.drawImage(cratere.getImage(), cratere.getX(), cratere.getY(), null);
			}
			nuit(g2d);
		break;
		}
	
		g.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);
		
		/////Dessin des boutons de commande/////
		g2d.drawOval(720, 570, 30, 30);
		g2d.drawOval(780, 570, 30, 30);
		g2d.drawOval(840, 570, 30, 30);
		
		g2d.drawOval(720, 630, 30, 30);
		g2d.drawOval(780, 630, 30, 30);
		g2d.drawOval(840, 630, 30, 30);
		
		g2d.drawOval(720, 690, 30, 30);
		g2d.drawOval(780, 690, 30, 30);
		g2d.drawOval(840, 690, 30, 30);
		/////////////////////////////////////////
		///Dessin des trajectoires précédentes///
		g.setColor(Color.RED);
		
		for(int i=0; i<coordPrecedentes1.size()-1; i++){
			g2d.drawLine(coordPrecedentes1.get(i)[0]+10, coordPrecedentes1.get(i)[1]+15, coordPrecedentes1.get(i+1)[0]+10, coordPrecedentes1.get(i+1)[1]+15);
			//g2d.drawOval(coordPrecedentes1.get(i)[0]+10, coordPrecedentes1.get(i)[1]+15, 10, 10);
		}
		g.setColor(Color.BLUE);
		for(int i=0; i<coordPrecedentes2.size()-1; i++){
			g2d.drawLine(coordPrecedentes2.get(i)[0]+10, coordPrecedentes2.get(i)[1]+15, coordPrecedentes2.get(i+1)[0]+10, coordPrecedentes2.get(i+1)[1]+15);
		}

		
		nomVoiture = cars.MenuChoixVoiture.configuration.getVoiture1();
		System.out.println(nomVoiture);
		voiture1.setImage(nomVoiture);
		g2d.rotate(alpha1, voiture1.getX()+10, voiture1.getY()+15);	
		g2d.drawImage(voiture1.getImage(), voiture1.getX(), voiture1.getY(),this);//this pour afficher le gif
		if((cars.MenuChoixVoiture.configuration.getMode()==3)||(cars.MenuChoixVoiture.configuration.getMode()==4)){
			phare(g2d, voiture1);
		}
		g2d.rotate(-alpha1, voiture1.getX()+10, voiture1.getY()+15);	
		
		nomVoiture = cars.MenuChoixVoiture.configuration.getVoiture2();
		voiture2.setImage(nomVoiture);
		g2d.rotate(alpha2, voiture2.getX()+10, voiture2.getY()+15);	
		g2d.drawImage(voiture2.getImage(), voiture2.getX(), voiture2.getY(),this);
		if((cars.MenuChoixVoiture.configuration.getMode()==3)||(cars.MenuChoixVoiture.configuration.getMode()==4)){
			phare(g2d, voiture2);
		}
		g2d.rotate(-alpha2, voiture2.getX()+10, voiture2.getY()+15);
		switch(collision){
		case 1:///collision avec un mur//////
			ImageIcon explosion = new ImageIcon("Images/explosion.gif");
			g2d.drawImage(explosion.getImage(), voiture1.getX()-60, voiture1.getY()-60, this);
			collision =0;
		break;
		case 2:	
			ImageIcon explosion2 = new ImageIcon("Images/explosion.gif");
			g2d.drawImage(explosion2.getImage(), voiture2.getX()-60, voiture2.getY()-60, this);
			collision = 0;
		break;
		default:
			System.out.println("Pas de collision");
		}
	}
	
	public void dessinBordures(Graphics2D g2d){
		for(int i=0; i<29; i++){///On parcourt le circuit et on dessine unen bordure (verticale ou horizontale) si une tuile de route est accolé à une tuil de texture différente(ce qui nous permet de délimiter le circuit)
			for(int j=0; j<24; j++){
				String tuile1 = Game[i][j];
				String tuile2 = Game[i+1][j];
				if(((tuile1 == "ROUTE")&&(tuile2 != "ROUTE"))||((tuile1 != "ROUTE")&&(tuile2 == "ROUTE"))){
					ImageIcon bordure = new ImageIcon("Images/Textures/bordureV.jpg");
					g2d.drawImage(bordure.getImage(), (i+1)*30, j*30, null);
				}
				tuile1 = Game[i][j];
				tuile2 = Game[i][j+1];
				if(((tuile1 == "ROUTE")&&(tuile2 != "ROUTE"))||((tuile1 != "ROUTE")&&(tuile2 == "ROUTE"))){
					ImageIcon bordure = new ImageIcon("Images/Textures/bordureH.jpg");
					g2d.drawImage(bordure.getImage(), i*30, (j+1)*30, null);
				} 
			}
		}
	}
	
	public void nuit(Graphics2D g2d){// Application d'une image faisant office de filtre assombrissant 
		ImageIcon nuit=new ImageIcon("Images/Textures/nuit.png");
		g2d.drawImage(nuit.getImage(), 0, 0, null);
	}
	
	public void phare(Graphics2D g2d, Voiture v){// Affichage d'un image pour les phares
		ImageIcon nuit=new ImageIcon("Images/Voitures/phare.png");
		g2d.drawImage(nuit.getImage(), v.getX()-2, v.getY()-14, null);
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
	
		mouvement =0;
		sourisX = arg0.getX();
		sourisY = arg0.getY();
		System.out.println("x ="+sourisX);
		System.out.println("y ="+sourisY);
		nbClic += 1;
		if(nbClic == 1){
			prepaMouvement(voiture1);

			alpha1 = (Math.PI/2)+Math.atan2(voiture1.getVitesseY(),voiture1.getVitesseX());
		}
		if(nbClic == 2){
			prepaMouvement(voiture2);
			alpha2 = (Math.PI/2)+Math.atan2(voiture2.getVitesseY(),voiture2.getVitesseX());
			//x1 = voiture1.getX();
			//y1 = voiture1.getY();
			//x2 = voiture2.getX();
			//y2 = voiture2.getY();
			System.out.println("VitesseX x1 :"+x1);
			System.out.println("VitesseY y1 :"+y1);
			System.out.println("VitesseX x2 :"+x2);
			System.out.println("VitesseY y2 :"+y2);
			mouvement();
			System.out.println("VitesseX voiture1 :"+voiture1.getVitesseX());
			System.out.println("VitesseY voiture1 :"+voiture1.getVitesseY());
			System.out.println("VitesseX voiture2 :"+voiture2.getVitesseX());
			System.out.println("VitesseY voiture2 :"+voiture2.getVitesseY());
			nbClic = 0;
			
		}		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
	public void prepaMouvement(Voiture v){//Méthode vérifiant si un clic a été effetué dans un des cercles de commande et modifiant la vitesse en conséquence
		
			if((Math.pow((sourisX-735),2)+Math.pow((sourisY-585),2))<=225){
				v.setVitesseX(v.getVitesseX()-30);
				v.setVitesseY(v.getVitesseY()-30);
				System.out.println("On y est !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-795),2)+Math.pow((sourisY-585),2))<=225){
				v.setVitesseX(v.getVitesseX()-0);
				v.setVitesseY(v.getVitesseY()-30);
				System.out.println("On y est2 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-855),2)+Math.pow((sourisY-585),2))<=225){
				v.setVitesseX(v.getVitesseX()+30);
				v.setVitesseY(v.getVitesseY()-30);
				System.out.println("On y est3 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			////////////////////////////////////////////////////////
			if((Math.pow((sourisX-735),2)+Math.pow((sourisY-645),2))<=225){
				v.setVitesseX(v.getVitesseX()-30);
				v.setVitesseY(v.getVitesseY()-0);
				System.out.println("On y est4 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-795),2)+Math.pow((sourisY-645),2))<=225){
				v.setVitesseX(v.getVitesseX()+0);
				v.setVitesseY(v.getVitesseY()+0);
				System.out.println("On y est5 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-855),2)+Math.pow((sourisY-645),2))<=225){
				v.setVitesseX(v.getVitesseX()+30);
				v.setVitesseY(v.getVitesseY()+0);
				System.out.println("On y est6 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			////////////////////////////////////////////////////////
			if((Math.pow((sourisX-735),2)+Math.pow((sourisY-705),2))<=225){
				v.setVitesseX(v.getVitesseX()-30);
				v.setVitesseY(v.getVitesseY()+30);
				System.out.println("On y est7 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-795),2)+Math.pow((sourisY-705),2))<=225){
				v.setVitesseX(v.getVitesseX()+0);
				v.setVitesseY(v.getVitesseY()+30);
				System.out.println("On y est8 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			if((Math.pow((sourisX-855),2)+Math.pow((sourisY-705),2))<=225){
				v.setVitesseX(v.getVitesseX()+30);
				v.setVitesseY(v.getVitesseY()+30);
				System.out.println("On y est9 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
	}
	public void mouvement(){//Animation du mouvement de la voiture en créant un nouveau thread
	    SwingWorker sw = new SwingWorker(){
	    	protected Object doInBackground() throws Exception {
	    		int progres=0;
		    	boolean collision = false;
		    	while(mouvement != 20){
		    		try {
		    			//On change la propriété d'état
		    			setProgress(progres);
		    			progres++;
		    			Thread.sleep(100);
		    		} catch (InterruptedException e) {
		    			e.printStackTrace();
		    		}
		        }
		        return null;
		    }
		         
	    	public void done(){
	    		if(SwingUtilities.isEventDispatchThread())
	    			System.out.println("Dans l'EDT ! ");
		    }         
	    };
		//On écoute le changement de valeur pour la propriété
		sw.addPropertyChangeListener(new PropertyChangeListener(){
		//Méthode de l'interface
		public void propertyChange(PropertyChangeEvent event) {
			//On vérifie tout de même le nom de la propriété
		    if("progress".equals(event.getPropertyName())){
		    	if(SwingUtilities.isEventDispatchThread())
		          	//On récupère sa nouvelle valeur
		          	System.out.println(event.getNewValue());
		    		
		          	Integer[]coord1 = {voiture1.getX(), voiture1.getY()};
		    		coordPrecedentes1.add(coord1);
		          	Integer[]coord2 = {voiture2.getX(), voiture2.getY()};
		          	coordPrecedentes2.add(coord2);
		          	
		          	System.out.println("Vitesse X = "+voiture1.getVitesseX());
		          	System.out.println("Vitesse Y = "+voiture1.getVitesseY());	
		        	System.out.println("Position X = "+voiture1.getX());
		          	System.out.println("Position Y = "+voiture1.getY());	
		          	voiture1.setX(voiture1.getX() + voiture1.getVitesseX()/20);
					voiture1.setY(voiture1.getY() + voiture1.getVitesseY()/20);
		          	voiture2.setX(voiture2.getX() + voiture2.getVitesseX()/20);
					voiture2.setY(voiture2.getY() + voiture2.getVitesseY()/20);
					checkCollision();
					repaint();
					
					mouvement = mouvement+1;
		          	
		        }            
		      }         
		    });
		    //On lance le SwingWorker
		    sw.execute();
		}
		  

		public void checkCollision(){//vérification des collisions entre la voiture et l'environnement
			
			Rectangle voiture1Contour;//création d'un rectangle autour de la voiture et correspondant aux dimension renseigner dans sa classe
			voiture1Contour = voiture1.getBounds();
			Rectangle voiture2Contour;
			voiture2Contour = voiture2.getBounds();
			
			for(int i=0; i<murs.size(); i++){
				mur = murs.get(i);
				Rectangle murContour = mur.getBounds();//création d'un rectangle autour du mur
				
				if(voiture1Contour.intersects(murContour)){//si les deux rectangle se touchent
					voiture1.setVitesseX(voiture1.getVitesseX()/10);
					voiture1.setVitesseY(voiture1.getVitesseY()/10);
					collision=1;
					sonCrash.play();
				}
				if(voiture2Contour.intersects(murContour)){
					voiture2.setVitesseX(voiture2.getVitesseX()/10);
					voiture2.setVitesseY(voiture2.getVitesseY()/10);
					collision=2;
					sonCrash.play();
				}
			}
			
			for(int i=0; i<routes.size(); i++){
				route = routes.get(i);
				Rectangle routeContour = route.getBounds();
				
				if(voiture1Contour.intersects(routeContour)){
					voiture1.setVitesseX((int) (voiture1.getVitesseX()/route.getFrottements()));
					voiture1.setVitesseY((int) (voiture1.getVitesseY()/route.getFrottements()));
					sonMoteur.play();
				}
				if(voiture2Contour.intersects(routeContour)){
					voiture2.setVitesseX((int) (voiture2.getVitesseX()/route.getFrottements()));
					voiture2.setVitesseY((int) (voiture2.getVitesseY()/route.getFrottements()));
				}
			}

			for(int i=0; i<herbes.size(); i++){
				herbe = herbes.get(i);
				Rectangle herbeContour = herbe.getBounds();
				
				if(voiture1Contour.intersects(herbeContour)){
					voiture1.setVitesseX((int) (voiture1.getVitesseX()/herbe.getFrottements()));
					voiture1.setVitesseY((int) (voiture1.getVitesseY()/herbe.getFrottements()));
				}
				if(voiture2Contour.intersects(herbeContour)){
					voiture2.setVitesseX((int) (voiture2.getVitesseX()/herbe.getFrottements()));
					voiture2.setVitesseY((int) (voiture2.getVitesseY()/herbe.getFrottements()));
				}
				
			}
			
			for(int i=0; i<eaux.size(); i++){
				eau = eaux.get(i);
				Rectangle eauContour = eau.getBounds();
				
				if(voiture1Contour.intersects(eauContour)){
					voiture1.setVitesseX((int) (voiture1.getVitesseX()/eau.getFrottements()));
					voiture1.setVitesseY((int) (voiture1.getVitesseY()/eau.getFrottements()));	
				}
				if(voiture2Contour.intersects(eauContour)){
					voiture2.setVitesseX((int) (voiture2.getVitesseX()/eau.getFrottements()));
					voiture2.setVitesseY((int) (voiture2.getVitesseY()/eau.getFrottements()));	
				}
			}
			
			for(int i=0; i<sables.size(); i++){
				sable = sables.get(i);
				Rectangle murContour = sable.getBounds();
				
				if(voiture1Contour.intersects(murContour)){
					voiture1.setVitesseX((int) (voiture1.getVitesseX()/sable.getFrottements()));
					voiture1.setVitesseY((int) (voiture1.getVitesseY()/sable.getFrottements()));	
				}
				if(voiture2Contour.intersects(murContour)){
					voiture2.setVitesseX((int) (voiture2.getVitesseX()/sable.getFrottements()));
					voiture2.setVitesseY((int) (voiture2.getVitesseY()/sable.getFrottements()));	
				}
			}
			
			for(int i=0; i<arrivees.size(); i++){
				arrivee = arrivees.get(i);
				Rectangle arriveeContour = arrivee.getBounds();
				
				if(voiture1Contour.intersects(arriveeContour)){
					System.out.println("Le joureur 1 a gagné");
					sonArrivee.play();
					
					JOptionPane j = new JOptionPane();//Ouverture d'une fenêtre de choix à la fin du jeu
					String lesTextes[]={ "Recommencer", "Quitter le Jeu"};
						
					int retour = j.showOptionDialog(this,"Le Joueur 1 a gagné !\nQue voulez-vous faire ?","Fin de partie", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, lesTextes, lesTextes[0]);
					if(retour!=JOptionPane.CLOSED_OPTION){
						fin(retour);
					}
				}
				if(voiture2Contour.intersects(arriveeContour)){
					System.out.println("Le joureur 2 a gagné");
					sonArrivee.play();
					
					JOptionPane j = new JOptionPane();
					String lesTextes[]={ "Recommencer", "Quitter le Jeu"};
						
					int retour = j.showOptionDialog(this,"Le Joueur 2 a gagné !\nQue voulez-vous faire ?","Fin de partie", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, lesTextes, lesTextes[0]);
					if(retour!=JOptionPane.CLOSED_OPTION){
						fin(retour);
					}					
				}
			}
			if(modePluie == true){//Vérification que nous sommes dans le mode pluie pour ne pas faire de tests inutiles
				for(int i=0; i<flaques.size(); i++){
					flaque = flaques.get(i);
					Rectangle flaqueContour = flaque.getBounds();
					
					if(voiture1Contour.intersects(flaqueContour)){
						voiture1.setVitesseX((int) (voiture1.getVitesseX()/flaque.getFrottements())-5);
						voiture1.setVitesseY((int) (voiture1.getVitesseY()/flaque.getFrottements()));	
					}
					if(voiture2Contour.intersects(flaqueContour)){
						voiture2.setVitesseX((int) (voiture2.getVitesseX()/flaque.getFrottements())-5);
						voiture2.setVitesseY((int) (voiture2.getVitesseY()/flaque.getFrottements()));
					}
				}
			}
			
			if(modeApocalypse == true){
				for(int i=0; i<crateres.size(); i++){
					cratere = crateres.get(i);
					Rectangle cratereContour = cratere.getBounds();
					
					if(voiture1Contour.intersects(cratereContour)){
						sonCri.play();
						JOptionPane j = new JOptionPane();
						String lesTextes[]={ "Recommencer", "Quitter le Jeu"};
							
						int retour = j.showOptionDialog(this,"Le Joueur 2 a gagné !\nQue voulez-vous faire ?","Fin de partie", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, lesTextes, lesTextes[0]);
						if(retour!=JOptionPane.CLOSED_OPTION){
							fin(retour);
						}
					}
					if(voiture2Contour.intersects(cratereContour)){
						sonCri.play();
						JOptionPane j = new JOptionPane();
						String lesTextes[]={ "Recommencer", "Quitter le Jeu"};
							
						int retour = j.showOptionDialog(this,"Le Joueur 1 a gagné !\nQue voulez-vous faire ?","Fin de partie", JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, lesTextes, lesTextes[0]);
						if(retour!=JOptionPane.CLOSED_OPTION){
							fin(retour);
						}
					}
				}
				
			}
		}

		public void fin(int retour){
			switch(retour){
			case 0:
				coordPrecedentes1.clear();
				coordPrecedentes2.clear();
				voiture1.setX(67);
				voiture1.setY(467);
				voiture2.setX(47);
				voiture2.setY(467);
				repaint();
				//cars.MenuPrincipalFrame f = new cars.MenuPrincipalFrame();
			
			break;
			case 1:
				 System.exit(0);
				
			 }
		}
}

