package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;

import global.Global;
import objetsMathematiques.Conversions ;
import objetsMathematiques.Matrice;

public class Platform extends Component{
	
	double choc ; //combien "d'énergie" va restituer la plateforme en cas de choc
	
	public Platform(double xMin, double yMin, double width, double height, double aChoc) {
		super(xMin, yMin, width, height) ;
		choc = aChoc ;
	}
	
	public boolean detecterCollision(Joueur joueur){
		boolean JoueurGaucheObjet = joueur.xMin + joueur.width <= xMin ; 	//Le joueur se trouve à gauche de la plateforme
		boolean JoueurDroiteObjet = joueur.xMin >= xMin + width; 			//Le joueur se trouve à droite de la plateforme
		boolean JoueurHautObjet = joueur.yMin + joueur.height <= yMin ; 	//Le joueur se trouve en bas de la plateforme
		boolean JoueurBasObjet = joueur.yMin >= yMin + height ; 			//Le joueur se trouve en haut de la plateforme
		
		boolean pasDeCollision = JoueurGaucheObjet || JoueurDroiteObjet || JoueurHautObjet || JoueurBasObjet ;	// il n'y pas de collision
		
		return !pasDeCollision ;
	}
	
	public void gererCollision(Joueur joueur){
		if(detecterCollision(joueur)){ //il y a collision
			placementDichotomique(joueur) ;
		}
	}
	
	public void placementDichotomique(Joueur joueur) {
		double xGauche = joueur.rb.position.getXTemp() ;
		double yGauche = joueur.rb.position.getYTemp() ;
		double xDroit = joueur.rb.position.getX() ;
		double yDroit = joueur.rb.position.getY() ;
		int i = 0 ;
		
		while (i < Global.nbRepetitionsDichotomie){
			double xMilieu = (xGauche + xDroit) / 2 ;
			double yMilieu = (yGauche + yDroit) / 2 ;
			joueur.forcePlacement(xMilieu, yMilieu) ;
			if(detecterCollision(joueur)){
				xDroit = xMilieu ;
				yDroit = yMilieu ;
			} else {
				xGauche = xMilieu ;
				yGauche = yMilieu ;
			}
			i++ ;
		}
	}

	public void dessin(Graphics g) {
		dessineComponent(g, Color.BLACK) ;
	}
}