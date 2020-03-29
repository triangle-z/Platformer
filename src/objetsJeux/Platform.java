package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;
import objetsMathematiques.Conversions ;
import objetsMathematiques.Matrice;

public class Platform extends Component{
	
	double choc ; //combien "d'énergie" va restituer la plateforme en cas de choc
	
	public Platform(double xMin, double yMin, double width, double height, double aChoc) {
		super(xMin, yMin, width, height) ;
		choc = aChoc ;
	}
	
	public void detecterCollision(Joueur joueur){
		boolean JoueurGaucheObjet = joueur.xMin+joueur.xMax <= xMin; 	//Le joueur se trouve à gauche de la plateforme
		boolean JoueurDroiteObjet = joueur.xMin >= xMin + xMax; 		//Le joueur se trouve à droite de la plateforme
		boolean JoueurHautObjet = joueur.yMin + joueur.yMax <= yMin; 	//Le joueur se trouve en haut de la plateforme
		boolean JoueurBasObjet = joueur.yMin >= yMin + yMax; 			//Le joueur se trouve en bas de la plateforme
		
		if(JoueurGaucheObjet || JoueurDroiteObjet || JoueurHautObjet || JoueurBasObjet){	// il n'y pas de collision
		} else {													// il y a collision
			trouverCoteCollision(joueur);
		}
		
	}
	
	public void trouverCoteCollision(Joueur joueur) {
		boolean[] tabDetectionCollision = new boolean[4] ;
		tabDetectionCollision[0] = joueur.yMin + joueur.yMax	== yMin			;	// le joueur touche le côté haut	de la plateforme
		tabDetectionCollision[1] = joueur.yMin					== yMin + yMax	;	// le joueur touche le côté bas		de la plateforme
		tabDetectionCollision[2] = joueur.xMin					== xMin + xMax	;	// le joueur touche le côté droit	de la plateforme
		tabDetectionCollision[3] = joueur.xMin + joueur.xMax	== xMin			;	// le joueur touche le côté gauche	de la plateforme
		joueur.gereCollision(tabDetectionCollision, choc) ;
	}

	public void dessin(Graphics g) {
		dessineComponent(g, Color.BLACK) ;
	}
}