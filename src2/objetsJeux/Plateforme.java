package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;

import global.Global;
import objetsMathematiques.Conversions ;
import objetsMathematiques.Matrice;

public class Plateforme extends Component{
	
	double choc ; //combien "d'énergie" va restituer la plateforme en cas de choc
	
	public Plateforme(double xMin, double yMin, double width, double height, double aChoc) {
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
		double xMilieu = (xGauche + xDroit) / 2 ;
		double yMilieu = (yGauche + yDroit) / 2 ;
		
		while (i < Global.nbRepetitionsDichotomie || (detecterCollision(joueur) && i < Global.nbRepetitionsDichotomie * 100 )){
			joueur.forcePlacement(xMilieu, yMilieu) ;
			if(detecterCollision(joueur)){
				xDroit = xMilieu ;
				yDroit = yMilieu ;
			} else {
				xGauche = xMilieu ;
				yGauche = yMilieu ;
			}
			xMilieu = (xGauche + xDroit) / 2 ;
			yMilieu = (yGauche + yDroit) / 2 ;
			i++ ;
		}
		while(detecterCollision(joueur)){
			joueur.forcePlacement(joueur.xMin, joueur.yMin - 0.1) ;
		}
		
		joueur.forcePlacement(xMilieu, yMilieu) ;
		
		//System.out.println("fini") ;
		
		boolean[] coteCollision = trouverCoteCollision(joueur) ;
		
		if(coteCollision[0] || coteCollision[1]){
			joueur.rb.vitesse.modifierComposants(0, joueur.rb.vitesse.getY()) ;
			if(coteCollision[0]){
				joueur.rb.contactDroit = true ;
				Global.derniereCollisionDroit = this ; //cote gauche de la plateforme = droite du joueur
			} else {
				joueur.rb.contactGauche = true ;
				Global.derniereCollisionGauche = this ;
			}
		} 
		if(coteCollision[2] || coteCollision[3]){
			joueur.rb.vitesse.modifierComposants(joueur.rb.vitesse.getX(), 0) ;	
			if(coteCollision[3]){
				joueur.rb.contactBas = true ;
				Global.derniereCollisionBas = this ;
			}
		}
	}
	
	public boolean[] trouverCoteCollision(Joueur joueur) {
		boolean[] tabDetectionCollision = new boolean[4] ;
		tabDetectionCollision[0] = joueur.xMin + joueur.width 	<= xMin				;	// le joueur touche le côté gauche	de la plateforme
		tabDetectionCollision[1] = joueur.xMin 					>= xMin + width		;	// le joueur touche le côté droit	de la plateforme
		tabDetectionCollision[2] = joueur.yMin + joueur.height 	<= yMin 			;	// le joueur touche le côté bas		de la plateforme
		tabDetectionCollision[3] = joueur.yMin 					>= yMin + height	;	// le joueur touche le côté haut	de la plateforme
		
		/*if(tabDetectionCollision[0]){
			System.out.println("gauche") ;
		}
		if(tabDetectionCollision[1]){
			System.out.println("droite") ;
		}
		if(tabDetectionCollision[2]){
			System.out.println("bas") ;
		}
		if(tabDetectionCollision[3]){
			System.out.println("haut") ;
		}*/
		return tabDetectionCollision ;
	}

	public void dessin(Graphics g) {
		dessineComponent(g, Color.BLACK) ;
	}
}