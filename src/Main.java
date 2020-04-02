import global.Global;
import graphique.Fenetre;
import objetsJeux.Platform;
import objetsMathematiques.Conversions;
import objetsMathematiques.Matrice;

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
				Thread.sleep((long)(Global.dt*1000)) ; //temps physique entre chaque frame
				Global.joueur.deplacement() ;
				for(int i = 0 ; i < Global.listePlateformes.size() ; i++){
					Global.listePlateformes.get(i).gererCollision(Global.joueur) ;
				}
				Global.xMinFenetreMetric = Global.joueur.xMin - Global.widthFenetreMetric / 2 ; //centre le joueur dans
																									//la fenetre sur x
				Global.yMinFenetreMetric = Global.joueur.yMin - Global.heightFenetreMetric / 2 ; //centre le joueur dans
																									//la fenetre sur y
				fenetre.zoneJeu.repaint() ;
				System.out.println(Global.joueur.rb.vitesse) ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace() ;
			}
		}
	}
}
