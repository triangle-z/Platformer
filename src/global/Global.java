package global;
import java.util.LinkedList;

import objetsJeux.Joueur ;
import objetsJeux.Platform;

public class Global {
	
	public static double dt = 0.01 ;
	
	//donnees niveau
	public static double g = 9.81 ;
	public static double rho = 1.225 ;
	
	//donnees perso
	public static int persoChoisi ; //0 pour homme et 1 pour femme
			//public static String[] = {"Homme", "Femme"} ;
	public static double[] masse = {80, 65} ;
	public static double[] Cx = {1.2, 1.2} ;
	public static double[] surface = {(Math.pow(0.940, 2) + 2 * Math.pow(0.333, 2)) / (4 * Math.PI), (Math.pow(1.02, 2) + 2 * Math.pow(0.333, 2)) / (4 * Math.PI)} ;
	public static double[] longueurJambeRepos = {0.803, 0.721} ;
	public static double[] longueurJambeContractee = {Math.sqrt(2 * Math.pow(longueurJambeRepos[0] / 2, 2)), Math.sqrt(2 * Math.pow(longueurJambeRepos[1] / 2, 2))} ; //flechi à 90°
	public static double[] raideur = {3, 2.5} ;
	
	public static Joueur joueur = new Joueur(0, 0, 0.5, 1.7) ;
	
	public static LinkedList<Platform> listePlateformes = new LinkedList<Platform>() ;
	
}
