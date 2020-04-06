package graphique.conteneurs;

import java.io.File;
import java.nio.file.Paths;

import global.Global;
import graphique.objets.Bouton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import objetsMathematiques.Conversions;

public class Menu extends Scene implements EventHandler<ActionEvent>{
	
	Stage primaryStage ;
	private Media h ;
	private MediaPlayer mediaplayer ;
	private Bouton Bouton1 ;
	private Bouton Bouton2 ;
	private Bouton Bouton3 ;
	
	public Menu(VBox root, int width, int height, Stage stage){
		super(root, width, height) ;
		
		primaryStage = stage ;

		Image iB = new Image("Medias/image/menu/background_selection.png");
		BackgroundSize bs = new BackgroundSize(1280, 960, false, false, false, true) ;
		BackgroundImage BackGround = new BackgroundImage(iB, null, null, null, bs);
		root.setBackground(new Background(BackGround));// on impl�mente le Background

		//File f = new File("music1.mp3") ;
		//System.out.println(f.exists()) ;
		//h = new Media(new File("Medias/image/music1.mp3").toURI().toString()) ;
		//mediaplayer = new MediaPlayer(h); 
		//AudioClip ac = new AudioClip(new File("Medias/image/music1.mp3").toURI().toString()) ;
		//AudioClip ac = new AudioClip("music1.mp3") ;
		//ac.play();
		
		root.setAlignment(Pos.TOP_CENTER) ;// on aligne les �l�ments de root de sorte � ce qu''ils soient au centre 
		root.setSpacing(-10);// on impose un espacement entre les nods (�l�ments) de Vbox de 100 pixels
		 	 
		Bouton1 = new Bouton(0, this) ;
		Bouton2 = new Bouton(1, this) ;
		Bouton3 = new Bouton(2, this) ;
		
		//mediaplayer.play();
		 
		root.getChildren().addAll(Bouton1,Bouton2,Bouton3);// on rajoute les boutons dans la VBox	
		
		
	}
	
	public void lancePlanete(){
		primaryStage.setScene(Global.planete) ;
	}
	
	public void lanceCredits(){
		Global.credits.translateValue = Global.credits.translateValueMax ;
		primaryStage.setScene(Global.credits) ;
	}
	
	public void remetMenu(){
		primaryStage.setScene(Global.menu) ;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
