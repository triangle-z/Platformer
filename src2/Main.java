import global.Global;
import graphique.conteneurs.Credits;
import graphique.conteneurs.Menu;
import graphique.conteneurs.Planet;
import graphique.objets.Box;
import graphique.objets.Camera;
import graphique.objets.JoueurG;
import graphique.objets.PlateformeG;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import objetsJeux.Joueur;
import objetsJeux.Plateforme;
import objetsMathematiques.Conversions;
import objetsMathematiques.Vecteur;

public class Main extends Application {

    public static void main(String[] args) {
    	Global.g = 9.81 ;
		Global.rho = 1.225 ;
		Global.persoChoisi = 1 ;
		
		double[] tab1 = {0, -1} ;
		//Conversions.metricToPixel(tab1) ;
		double[] tab2 = {10, 1} ;
 		
 		Global.URLsStandUp = new String[Global.nbStandUp] ;
 		for(int i = 1 ; i <= Global.nbStandUp ; i++){
 			Global.URLsStandUp[i - 1] = Global.URLStandUp + i + ".png" ;
 		}
 		
 		Global.URLsRunCycle = new String[Global.nbRunCycle] ;
 		for(int i = 1 ; i <= Global.nbRunCycle ; i++){
 			Global.URLsRunCycle[i - 1] = Global.URLRunCycle + i + ".png" ;
 		}
 		
 		Global.URLsJumpCycle = new String[Global.nbJumpCycle] ;
 		for(int i = 1 ; i <= Global.nbJumpCycle ; i++){
 			Global.URLsJumpCycle[i - 1] = Global.URLJumpCycle + i + ".jpg" ;
 		}
		
		Global.camera = new Camera(0, 0, Global.widthFenetreMetric, Global.heightFenetreMetric) ;
 		
 		Box.generationBoxes(7, 0) ;
		
		Global.joueur  = new Joueur(Global.spawnPoint, 0.5, 1.7) ;
		Global.joueurG = new JoueurG(Global.joueur) ;
		
		/*Plateforme  plateforme1  = new Plateforme(0, -2, 7, 2, 0.9) ;
		PlateformeG plateforme1G = new PlateformeG(plateforme1) ;
		Global.listePlateformes .add(plateforme1 ) ;
		Global.listePlateformesG.add(plateforme1G) ;
		
		Plateforme  plateforme2  = new Plateforme(-2, -2, 2, 2, 0.9) ;
		PlateformeG plateforme2G = new PlateformeG(plateforme2) ;
		Global.listePlateformes .add(plateforme2 ) ;
		Global.listePlateformesG.add(plateforme2G) ;
		
		Plateforme  plateforme3  = new Plateforme(-2, 0, 1, 2, 0.9) ;
		PlateformeG plateforme3G = new PlateformeG(plateforme3) ;
		Global.listePlateformes .add(plateforme3 ) ;
		Global.listePlateformesG.add(plateforme3G) ;
		
		Plateforme  plateforme4  = new Plateforme(-6, 2, 4, 2, 0.9) ;
		PlateformeG plateforme4G = new PlateformeG(plateforme4) ;
		Global.listePlateformes .add(plateforme4 ) ;
		Global.listePlateformesG.add(plateforme4G) ;
		
		Plateforme  plateforme5  = new Plateforme(-7, 4, 1, 4, 0.9) ;
		PlateformeG plateforme5G = new PlateformeG(plateforme5) ;
		Global.listePlateformes .add(plateforme5 ) ;
		Global.listePlateformesG.add(plateforme5G) ;
		
		Plateforme  plateforme6  = new Plateforme(7, 0, 1, 18, 0.9) ;
		PlateformeG plateforme6G = new PlateformeG(plateforme6) ;
		Global.listePlateformes .add(plateforme6 ) ;
		Global.listePlateformesG.add(plateforme6G) ;*/
		
		Group root1 = new Group() ;
 		Global.credits = new Credits(root1, Conversions.widthFenetrePixel, - Conversions.heightFenetrePixel) ;
 		
		Group root2 = new Group() ;
 		Global.planete = new Planet(root2, Conversions.widthFenetrePixel, - Conversions.heightFenetrePixel) ;
		
        Application.launch(Main.class, args);
    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

        primaryStage.setTitle("Little Gravity");
        
		VBox root = new VBox() ; // Premier �l�ment de la scene qui va contenir les boutons.
        Global.menu = new Menu(root, Conversions.widthFenetrePixel, - Conversions.heightFenetrePixel, primaryStage) ;
        
        primaryStage.setScene(Global.menu);
        primaryStage.show();
	}
	
}
