import global.Global;
import graphique.Fenetre;
import objetsJeux.Platform;
import objetsMathematiques.Conversions;

public class Main {
	public static void main(String[] args){
		Global.g = 9.81 ;
		Global.rho = 1.225 ;
		Global.persoChoisi = 0 ;
		
		double[] tab1 = {0, -1} ;
		//Conversions.metricToPixel(tab1) ;
		double[] tab2 = {10, 1} ;
		//System.out.println("Coordonnees pixels de la plateforme :") ;
		//System.out.println("xMin   = " + Conversions.metricToPixel(tab1)[0] + " " + Conversions.metricToPixel(tab1)[1]) ;
		//System.out.println("yMin   = " + Conversions.metricToPixel(tab2)[0] + " " + Conversions.metricToPixel(tab2)[1]) ;
		
		Platform plateforme = new Platform(0, -1, 10, 1, 0.9) ;
		Global.listePlateformes.add(plateforme) ;
		
		Fenetre fenetre = new Fenetre() ;
		
		while(true){
			try {
				Thread.sleep(1000) ;
				Global.joueur.deplacement() ;
				fenetre.zoneJeu.repaint() ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
