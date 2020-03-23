package Platformer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HistoryScene extends Application implements EventHandler<KeyEvent> {
	BackgroundImage ImageFond = new BackgroundImage(new Image(getClass().getResource("images et sons/background_selection.png").toString()), null, null, null, null);
	AnimationTimer jeu;
	Circle cercle = new Circle(10);
	private int marche = 5 ; 

	@Override
	public void start(Stage MainStage) throws Exception {	
		Pane root = new Pane();
		root.setBackground(new Background(ImageFond));
		
		
		
		
		
		
		
		
		
		
		
		root.getChildren().addAll(cercle);
		Scene History = new Scene(root,500,500);
		History.setOnKeyPressed(this);
		MainStage.setScene(History);
		MainStage.show();
		
		
		 jeu = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		};
		//jeu.start();
		
		
		
	}

	@Override
	public void handle(KeyEvent e) {

		if(e.getText() == "d") {
			marche++;
			System.out.println(marche);
			
		}
		
		
	}


}
