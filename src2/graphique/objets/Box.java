package graphique.objets;

import java.util.LinkedList;

import global.Global;
import objetsJeux.Plateforme;
import objetsMathematiques.Vecteur;

public class Box {
	
	static final int nbBoxes = 4 ; //nb de boites en largeur par partie
	static final double largeurPlateforme = 0.5 ; // largeur d'un constituant d'une boite
	static final int nbPlateforme = 16 ; //nb de plateforme en largeur par boites
	static final double choc = 0.9 ;
	
	public static void generationBoxes(double x, double y){

		int premierePos = (int) (Math.random() * (nbBoxes + 1)) ;
		
		Global.spawnPoint = new Vecteur(x + premierePos * nbPlateforme * largeurPlateforme + 7 * largeurPlateforme,
				y + 3 * nbPlateforme * largeurPlateforme + 2 * largeurPlateforme) ;
		
		DG(x + premierePos * nbPlateforme * largeurPlateforme, y + 3 * nbPlateforme * largeurPlateforme) ;
		
	}
	
	static double[][] tabDG = {	{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
								{1.0, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 1.0},
								{1.0, 0.8, 0.8, 0.5, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.5, 0.8, 0.8, 1.0},
								{0.8, 0.8, 0.5, 0.3, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.3, 0.5, 0.8, 0.8},
								{0.8, 0.5, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.5, 0.8},
								{0.8, 0.5, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.5, 0.8},
								{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
								{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
								{0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.3, 0.5, 0.5, 0.3, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0},
								{0.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.5, 0.5, 0.5, 0.5, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0},
								{0.8, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.8},
								{0.8, 0.8, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.8, 0.8},
								{0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.5, 0.5, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8},
								{1.0, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 1.0},
								{1.0, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 1.0},
								{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0}	} ;
	
	public static void DG(double x, double y){		//crée une box d'ouvertures droite et gauche, de position min (x, y)
		LinkedList<Plateforme > box  = new LinkedList<Plateforme >() ;
		LinkedList<PlateformeG> boxG = new LinkedList<PlateformeG>() ;
		
		for(int i = 0 ; i < tabDG[0].length ; i++){
			for(int j = 0 ; j < tabDG.length ; j++){
				if(Math.random() < tabDG[i][j]){
					Plateforme p = new Plateforme(x + j * largeurPlateforme, y + i * largeurPlateforme,
							largeurPlateforme, largeurPlateforme, choc) ;
					PlateformeG pG = new PlateformeG(p) ;
					box.add(p) ;
					boxG.add(pG) ;
				}
			}
		}

		Global.listePlateformes .add(box ) ;
		Global.listePlateformesG.add(boxG) ;
	}
}
