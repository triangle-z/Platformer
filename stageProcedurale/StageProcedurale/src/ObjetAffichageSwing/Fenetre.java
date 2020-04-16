package ObjetAffichageSwing;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ObjetGenerationProcedurale.Plateforme;

public class Fenetre extends JFrame{
	public JPanel mapJeu;
	public ArrayList<ArrayList<ArrayList<Plateforme>>> listOPlateforme;
	
	public Fenetre(ArrayList<ArrayList<ArrayList<Plateforme>>> listOPlateforme){
		
		// Initialisation de la fenetre
		this.setTitle("Projet Info");
		this.setSize(1920, 920);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//Creation tableau d'objets
		this.listOPlateforme = listOPlateforme;
		mapJeu = new Affichage(listOPlateforme);
		this.add(mapJeu);

		while(true) {

		}
	}


}