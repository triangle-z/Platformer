package global;
import java.util.LinkedList;

import graphique.objets.Camera;
import graphique.objets.JoueurG;
import graphique.objets.PlateformeG;
import objetsJeux.Joueur ;
import objetsJeux.Plateforme;
import objetsMathematiques.Vecteur;

public class Global {
	
	public static double dt = 0.01 ; //seconde
	
	//donnees niveau
	public static double g = 9.81 ;		//constante d'acceleration de la gravite 
	public static double rho = 1.225 ;	//masse volumique du fluide dans lequel le personnage est
	
	//donnees perso
	public static int persoChoisi = 0 ; //0 pour homme et 1 pour femme
			//public static String[] = {"Homme", "Femme"} ;
	public static double[] masse = {80, 65} ;
	public static double[] Cx = {1.2, 1.2} ;
	public static double[] surface = {(Math.pow(0.940, 2) + 2 * Math.pow(0.333, 2)) / (4 * Math.PI), (Math.pow(1.02, 2) + 2 * Math.pow(0.333, 2)) / (4 * Math.PI)} ;
	public static double[] longueurJambeRepos = {0.803, 0.721} ;
	public static double[] longueurJambeContractee = {Math.sqrt(2 * Math.pow(longueurJambeRepos[0] / 2, 2)), Math.sqrt(2 * Math.pow(longueurJambeRepos[1] / 2, 2))} ; //flechi à 90°
	public static double[] raideur = {66150, 57500} ;
	
	public static Camera camera ;
	
	public static Vecteur spawnPoint ;
	
	public static Joueur  joueur  ;
	public static JoueurG joueurG ;
	
	public static LinkedList<Plateforme > listePlateformes  = new LinkedList<Plateforme >() ;
	public static LinkedList<PlateformeG> listePlateformesG = new LinkedList<PlateformeG>() ;
	
	public static double xMinFenetreMetric = -10 ;
	public static double yMinFenetreMetric = -10 ;
	public static double widthFenetreMetric = 20 ;
	public static double heightFenetreMetric = 20 ;
	
	public static int nbRepetitionsDichotomie = 100 ; //lors d'une collision, le placement dichotomique sera repete tant de fois
	
	public static Plateforme derniereCollisionBas    = new Plateforme(0, 0, 0, 0, 0) ;
	public static Plateforme derniereCollisionDroit = new Plateforme(0, 0, 0, 0, 0) ;
	public static Plateforme derniereCollisionGauche = new Plateforme(0, 0, 0, 0, 0) ;
}
