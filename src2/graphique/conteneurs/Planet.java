package graphique.conteneurs;
import java.util.LinkedList;

import global.Global;
import graphique.objets.PlateformeG;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import objetsJeux.Plateforme;

public class Planet extends Scene{
	
	public Group root ;
	
	public Planet(Group r, int width, int height){
		super(r, width, height, Color.GOLDENROD) ;
		
		root = r ;
		
		for(int i = 0 ; i < Global.listePlateformesG.size() ; i++){
			LinkedList<PlateformeG> listeTemp = Global.listePlateformesG.get(i) ;
			for(int j = 0 ; j < listeTemp.size() ; j++){
		        root.getChildren().add(listeTemp.get(j));
			}
		}

		root.getChildren().add(Global.joueurG) ;
		
	}
}
