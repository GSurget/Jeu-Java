package carsEditorCircuit;

import javax.swing.*;

public class EditorFrame extends JFrame {
	
	// Constructeur //
	public EditorFrame(){
		this.setTitle("Circuit Editor !"); // Titre de notre fenêtre
		//this.setLocation(null);// Position de notre fenêtre, null=>centrée dans l'écran
		this.setSize(905,780); // Taille de notre fenetre, 30 images*25 images de 30 pixels => 900 et 750 on ajoute à cela les bodures supérieure infèrieure et latérales
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Ferme la fenetre et arrete le programme en cliquant sur la croix
		this.add(new EditorBoard());// Ajoute à la fenetre EditorBoard comme content pane
		this.setResizable(false);//Redimensionnement de la fenetre desactivé
		this.setVisible(true);//rend la fenetre et son contenu visible
	}

}
