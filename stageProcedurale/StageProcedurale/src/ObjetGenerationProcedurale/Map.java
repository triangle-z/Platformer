package ObjetGenerationProcedurale;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
public class Map {

	private int nbPlateforme;
	private int nbTypePlateforme;
	private int nbPositionPossible;
	private ArrayList<ArrayList<Image>> listAllImagePlateforme;		 // Creation d'une liste contenant des listes d'images pour les plateformes
	private int[][] tabInfoPlateforme;
	private ArrayList<ArrayList<Integer>> listDimensionAllPlateforme;
	private LectureFichierTexte[] tabTypePlateforme;
	private TableauAleatoire objetPatternTypePlateforme;	// tableau avec des valeurs aléatoires permettant de séléctionner un type de plateforme 
	private TableauAleatoire objetPatternPositionPlateforme;	// tableau avec des valeurs aléatoires permettant de séléctionner une position position possible (x,y) pour la plateforme 

	public ArrayList<ArrayList<ArrayList<Plateforme>>> listOPlateforme;	// Creation d'une liste qui contient des listes qui contient des listes de blocks

	private CoordonneePoint[][][] tabPositionPossible; 												// Tableau contenant 3 tableaux : chaque tableau possède des objets de type CoordonneePoint (x,y). Permet de donner à chaque platform une position aléatoire
	
	public Map(int aNbPlateforme,int aNbTypePlateforme,int aNbPositionPossible) {
		nbPlateforme = aNbPlateforme;
		nbTypePlateforme = aNbTypePlateforme;
		nbPositionPossible = aNbPositionPossible;
		
		objetPatternTypePlateforme = new TableauAleatoire(nbPlateforme, nbTypePlateforme, 0);
		objetPatternPositionPlateforme = new TableauAleatoire(nbPlateforme, nbPositionPossible, 0);

		listAllImagePlateforme = new ArrayList<ArrayList<Image>>();
		tabInfoPlateforme = new int[nbTypePlateforme][2];
		listDimensionAllPlateforme = new ArrayList<ArrayList<Integer>>();
		tabTypePlateforme = new LectureFichierTexte[nbTypePlateforme];
		listOPlateforme = new ArrayList<ArrayList<ArrayList<Plateforme>>>();
		
		creationListImage();	
		creationObjetPlateforme();
		
		calculPositionPlatform();   												// Tableau contenant 3 tableaux : chaque tableau possède des objets de type CoordonneePoint (x,y). Permet de donner à chaque platform une position aléatoire
		placementPlateformeAleatoirement();	// Donne à chaque block formant les plateformes une position. Chaque plateforme est placee aléatoirement.
	}
	
	
	/* Métode permettant de creer une liste d'image pour chaque type de plateforme. Renvoie une liste contenant toutes les listes d'images correspondant à chaque type de plateforme */
	public void creationListImage(){
		int largeurPlateforme;
		int hauteurPlateforme;
		
		for(int i = 0;i<nbTypePlateforme ;i++)
		{		
			switch(i) {
				case 0:
					tabInfoPlateforme[i][0] = 3;
					tabInfoPlateforme[i][1] = 1;
					largeurPlateforme = 128;
					hauteurPlateforme = 93;
					affecterDimensionPlateforme(i,largeurPlateforme,hauteurPlateforme);	
				break;
				case 1:
					tabInfoPlateforme[i][0] = 3;
					tabInfoPlateforme[i][1] = 3;
					largeurPlateforme = 128;
					hauteurPlateforme = 128;
					affecterDimensionPlateforme(i,largeurPlateforme,hauteurPlateforme);
				break;
				case 2:
					tabInfoPlateforme[i][0] = 1;
					tabInfoPlateforme[i][1] = 1;
					largeurPlateforme = 101;
					hauteurPlateforme = 99;
					affecterDimensionPlateforme(i,largeurPlateforme,hauteurPlateforme);
				break;
				default:
					System.out.println("Erreur creation listPlateforme");
			}
			tabTypePlateforme[i] = new LectureFichierTexte(i,tabInfoPlateforme[i][0],tabInfoPlateforme[i][1]);
			importerImages(i);
		}
	}
	
	public void affecterDimensionPlateforme(int i,int largeurPlateforme,int hauteurPlateforme) {
		
		ArrayList<Integer> listDimensionPlateforme =  new ArrayList<Integer>();
		listDimensionAllPlateforme.add(i,listDimensionPlateforme);
		for(int n =0;n<tabInfoPlateforme[i][0]*tabInfoPlateforme[i][1];n++)
		{
			listDimensionAllPlateforme.get(i).add(2*n,largeurPlateforme);
			listDimensionAllPlateforme.get(i).add(2*n+1,hauteurPlateforme);
		}
	}
	
	public void importerImages(int i) {
		String cheminImages;
		ArrayList<Image> listImagePlateforme = new ArrayList<Image>();
		for(int L = 0;L<tabInfoPlateforme[i][0] ;L++)
		{
			for(int H = 0;H<tabInfoPlateforme[i][1];H++) {
				cheminImages = "Images/"+tabTypePlateforme[i].tabPlateforme[H][L]+".png";
				if(tabTypePlateforme[i].tabPlateforme[H][L] != '.') {
					listImagePlateforme.add(L*tabInfoPlateforme[i][1]+H,Toolkit.getDefaultToolkit().getImage(cheminImages));
				}
				else {
					listImagePlateforme.add(L*tabInfoPlateforme[i][1]+H,null);

				}
			}
		}
		listAllImagePlateforme.add(listImagePlateforme);
	}
	
	
	
	
		/* Méthode qui remplis une liste avec des blocks pour former des plateformes de types aléatoires.
	 	La premiere liste contient plusieurs listes representant chaque plateforme
	 	Chaque liste représentant une plateforme, contient plusieurs listes représentant des colonnes de blocks formant la plateforme
	 	Ces listes représentant les colonnes de blocks contiennent des blocks*/
	public void creationObjetPlateforme() {
		int typePlateforme;
		int nbColonne;
		int nbLigne;
		int largeur;
		int hauteur;
		Image img;
		
		for(int i = 0;i<nbPlateforme;i++) {
			typePlateforme = objetPatternTypePlateforme.tabPattern[i];
			nbColonne = tabInfoPlateforme[objetPatternTypePlateforme.tabPattern[i]][0];
			nbLigne = tabInfoPlateforme[objetPatternTypePlateforme.tabPattern[i]][1];
			ArrayList<ArrayList<Plateforme>> listOBlockLigne = new ArrayList<ArrayList<Plateforme>>();																		
			listOPlateforme.add(listOBlockLigne);
			
			for(int j = 0; j < nbColonne;j++) {
				ArrayList<Plateforme> listOBlockColonne = new ArrayList<Plateforme>();
				listOPlateforme.get(i).add(listOBlockColonne);
				for(int m = 0; m< nbLigne; m++) {
					largeur = listDimensionAllPlateforme.get(typePlateforme).get(2*(m + j*nbLigne));
					hauteur = listDimensionAllPlateforme.get(typePlateforme).get(2*(m + j*nbLigne)+1);
					img = listAllImagePlateforme.get(typePlateforme).get(m+j*nbLigne);
					listOPlateforme.get(i).get(j).add(m,new Plateforme(0,0,largeur,hauteur,img));
				}
			}
		}
		
	}
	/* Creation d'un tableau contenant 3 tableaux.
	 *  Le 1er tableau possède les positions les plus proches ou l'on peut placer la plateforme
	 *  Le 2eme tableau possède les positions un peu plus éloigné ou l'on peut placer la plateforme
	 *  Le 3eme tableau possède les positions les plus loins ou l'on peut placer la plateforme*/
	public void calculPositionPlatform() {
		CoordonneePoint[][] tabPositionPetitSautPersonnage = new CoordonneePoint[nbPositionPossible][1];
		CoordonneePoint[][] tabPositionSautMoyenPersonnage = new CoordonneePoint[nbPositionPossible][1];
		CoordonneePoint[][] tabPositionGrandSautPersonnage = new CoordonneePoint[nbPositionPossible][1];
		
		tabPositionPossible = new CoordonneePoint[3][nbPositionPossible][1];


		for(int i = 0;i<nbPositionPossible;i++) {
			tabPositionPetitSautPersonnage[i][0] = new CoordonneePoint(100*(1+i), 100*(1+i));	 // ecart entre 2 platform : x en plus = +100 ,+200,+300,+400,+500
			tabPositionSautMoyenPersonnage[i][0] = new CoordonneePoint(100*(1+i), 100*(1+i));
			tabPositionGrandSautPersonnage[i][0] = new CoordonneePoint(100*(1+i), 100*(1+i));//  						 y valeur de y = +100 , +200 ,+300 ,+400 ,+500
		}

		
		for(int i = 0;i<nbPositionPossible;i++) {			// Calcul des positions possibles pour les plateformes en fonction du saut du personnage
			tabPositionPossible[0][i][0] = new CoordonneePoint(tabPositionPetitSautPersonnage[i][0].x,tabPositionPetitSautPersonnage[i][0].y);
			tabPositionPossible[1][i][0] = new CoordonneePoint(tabPositionSautMoyenPersonnage[i][0].x,tabPositionSautMoyenPersonnage[i][0].y);
			tabPositionPossible[2][i][0] = new CoordonneePoint(tabPositionGrandSautPersonnage[i][0].x,tabPositionGrandSautPersonnage[i][0].y);

		}
	}
	
	
	/* Méthode pour placer les plateformes*/
	public void placementPlateformeAleatoirement() {
		listOPlateforme.get(0).get(0).get(0).xMin = 0;			// Placer le 1er Blocks de la carte. Block de départ
		listOPlateforme.get(0).get(0).get(0).yMin = 400;
		for(int i = 0;i<listOPlateforme.size();i++) {
			if(i != 0) {										// Place le 1er Block de chaque plateforme hormis de la première plateforme
				if(listOPlateforme.get(i-1).size() <= 2) {		// Si la plateforme d'avant est petite (2 blocks de largeurs), on place la plateforme suivante aléatoirement mais proche   
					listOPlateforme.get(i).get(0).get(0).xMin =  tabPositionPossible[0][objetPatternPositionPlateforme.tabPattern[i]][0].x + listOPlateforme.get(i-1).get(0).get(0).xMin  + listOPlateforme.get(i-1).get(0).get(0).xMax * listOPlateforme.get(i-1).size();
					listOPlateforme.get(i).get(0).get(0).yMin = tabPositionPossible[0][objetPatternPositionPlateforme.tabPattern[i]][0].y;
				}
				else if(listOPlateforme.get(i-1).size() <= 4) {	// Si la plateforme d'avant est de taille moyenne ( <= 4 blocks de largeurs), on place la plateforme suivante aléatoirement mais plus eloignee 
					listOPlateforme.get(i).get(0).get(0).xMin =  tabPositionPossible[1][objetPatternPositionPlateforme.tabPattern[i]][0].x + listOPlateforme.get(i-1).get(0).get(0).xMin  + listOPlateforme.get(i-1).get(0).get(0).xMax * listOPlateforme.get(i-1).size();
					listOPlateforme.get(i).get(0).get(0).yMin = tabPositionPossible[1][objetPatternPositionPlateforme.tabPattern[i]][0].y;
				}
				else if(listOPlateforme.get(i-1).size() <= 8 ) { 	// Si la plateforme d'avant est grande ( <= 8 blocks de largeurs), on place la plateforme suivante aléatoirement mais loin
					listOPlateforme.get(i).get(0).get(0).xMin =  tabPositionPossible[2][objetPatternPositionPlateforme.tabPattern[i]][0].x + listOPlateforme.get(i-1).get(0).get(0).xMin  + listOPlateforme.get(i-1).get(0).get(0).xMax * listOPlateforme.get(i-1).size();
					listOPlateforme.get(i).get(0).get(0).yMin = tabPositionPossible[2][objetPatternPositionPlateforme.tabPattern[i]][0].y;
				}
			}
			for(int j = 0;j<listOPlateforme.get(i).size();j++) {
				if(j != 0) {  // Place le premier block de chaque colonne qui compose la plateforme hormis la premiere colonne
					listOPlateforme.get(i).get(j).get(0).xMin = listOPlateforme.get(i).get(j-1).get(0).xMin  + listOPlateforme.get(i).get(j-1).get(0).xMax;
					listOPlateforme.get(i).get(j).get(0).yMin = listOPlateforme.get(i).get(j-1).get(0).yMin;
				}
				for(int m = 1;m < listOPlateforme.get(i).get(j).size();m++) { // Place les blocks de chaque ligne qui composent la plateforme hormis la premiere ligne 
					listOPlateforme.get(i).get(j).get(m).xMin = listOPlateforme.get(i).get(j).get(m-1).xMin;
					listOPlateforme.get(i).get(j).get(m).yMin = listOPlateforme.get(i).get(j).get(m-1).yMin+ listOPlateforme.get(i).get(j).get(m-1).yMax;
				}
			}
		}
			
	}
}
