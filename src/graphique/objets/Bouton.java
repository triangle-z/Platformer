package graphique.objets;

import global.Global;
import graphique.conteneurs.Menu;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;

public class Bouton extends Parent {
	
	int nbBouton ; //0 pour lancer planete, 
	double scale ;
	int translate = 260 ;
	Menu menu ;
	
	public Bouton(int nb, Menu scene){
		super() ;
		
		nbBouton = nb ;
		
		menu = scene ;
		
		if(nbBouton == 0){
			translate += 25 ;
			scale = 0.5 ;
		} else {
			scale = 0.7 ;
		}
		
		Image imageBouton = new Image(Global.URLsMenu[nbBouton]) ;
		ImageView imageViewBouton = new ImageView(imageBouton);
		
		getChildren().add(imageViewBouton) ;
		
		//setTranslateY(nbBouton * 10);
		
		setScaleX(scale) ;// on diminue la taille des boutons qui �taient un peu trop gros 
		setScaleY(scale) ;
		
		setTranslateY(translate) ;
		
		setOnMouseEntered(new EventHandler<MouseEvent>(){// augmentation de la taille des boutons lorsqu'on les survole.
			public void handle(MouseEvent e) {
				setScaleX(scale * 1.4);
				setScaleY(scale * 1.4);
			} 
		 });
		 setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				setScaleX(scale);
				setScaleY(scale);
			} 
		 });
		 
		 setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				if(nb == 0){
					menu.lancePlanete();
				} else {
					if(nb == 1){
						
					} else {
						if(nb == 2){
							menu.lanceCredits() ;
						}
					}
				}
			} 
		 }) ;
		
	}

}
