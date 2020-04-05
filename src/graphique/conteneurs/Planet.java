package graphique.conteneurs;
import global.Global;
import graphique.objets.PlateformeG;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Planet extends Scene{
	
	public Planet(Group root, int width, int height){
		super(root, width, height, Color.GOLDENROD) ;
		
		for(int i = 0 ; i < Global.listePlateformesG.size() ; i++){
	        root.getChildren().add(Global.listePlateformesG.get(i));
		}
		root.getChildren().add(Global.joueurG) ;
		
	}
}
