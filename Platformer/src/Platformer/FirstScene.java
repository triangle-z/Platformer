package Platformer;

import java.nio.file.Paths;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.*;

public class FirstScene extends Application implements EventHandler<ActionEvent> {
	private  Media h =  new Media(getClass().getResource("images et sons/music1.mp3").toString());
	private MediaPlayer mediaplayer = new MediaPlayer(h); 
	private Button Bouton1 ;
	private Button Bouton2 ; 
	private Button Bouton3 ; 
	Stage MainStage;
	@Override
	public void start(Stage MainStage) throws Exception {
		this.MainStage = MainStage ; 
		VBox root = new VBox() ; // Premier élément de la scene qui va contenir les boutons.
		root.setAlignment(Pos.CENTER);// on aligne les éléments de root de sorte à ce qu''ils soient au centre 
		root.setSpacing(100);// on impose un espacement entre les nods (éléments) de Vbox de 100 pixels
		ImageView Bouton_un = new ImageView(new Image(getClass().getResource("images et sons/play_button.png").toString()));
		ImageView Bouton_deux = new ImageView(new Image(getClass().getResource("images et sons/tutorial_button.png").toString()));
		ImageView Bouton_trois = new ImageView(new Image(getClass().getResource("images et sons/credits_button.png").toString()));
		BackgroundImage BackGround = new BackgroundImage(new Image(getClass().getResource("images et sons/background_selection.png").toString()), null, null, null, null);
		BackgroundImage BackGround_bouton1 = new BackgroundImage(new Image(getClass().getResource("images et sons/play_button.png").toString()), null, null, null, null);
		BackgroundImage BackGround_bouton2 = new BackgroundImage(new Image(getClass().getResource("images et sons/tutorial_button.png").toString()), null, null, null, null);
		BackgroundImage BackGround_bouton3 = new BackgroundImage(new Image(getClass().getResource("images et sons/credits_button.png").toString()),BackgroundRepeat.NO_REPEAT , BackgroundRepeat.NO_REPEAT, null, null);
		// Pour les deux lignes au dessus on créé deux images plus le background.
		
		 Bouton1 = new Button("",Bouton_un);// on créé les boutons et on leur rajoute l'image associé
		 Bouton2 = new Button("",Bouton_deux);
		 Bouton3 = new Button("",Bouton_trois);
		 Bouton1.setOnAction(this);//on leur implémente un EventHandler ( l'équivalent d'un ActionListener)
		 Bouton2.setOnAction(this);
		 Bouton3.setOnAction(this);
	
		 	 
		 Bouton1.setTranslateY(200);// on place les boutons dans la Vbox
		 Bouton2.setTranslateY(80);
		 Bouton1.setScaleX(0.5);// on diminue la taille des boutons qui étaient un peu trop gros 
		 Bouton1.setScaleY(0.5);
		 Bouton2.setScaleX(0.5);
		 Bouton2.setScaleY(0.5);
		 Bouton3.setScaleX(0.5);
		 Bouton3.setScaleY(0.5);
		 Bouton1.setBackground(new Background(BackGround_bouton1)); // on met en place le même background que son image pour éviter que le bouton ne place a carré gris autour de son image 
		 Bouton2.setBackground(new Background(BackGround_bouton2));
		 Bouton3.setBackground(new Background(BackGround_bouton3));

		 
		 Bouton1.setOnMouseEntered(new EventHandler<MouseEvent>(){// augmentation de la taille des boutons lorsqu'on les survole.
			public void handle(MouseEvent e) {
				Bouton1.setScaleX(0.7);
				Bouton1.setScaleY(0.7);
			} 
		 });
		 Bouton1.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				Bouton1.setScaleX(0.5);
				Bouton1.setScaleY(0.5);
			} 
		 });
		 
		 Bouton2.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				Bouton2.setScaleX(0.7);
				Bouton2.setScaleY(0.7);
			} 
		 });
		 Bouton2.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				Bouton2.setScaleX(0.5);
				Bouton2.setScaleY(0.5);
			} 
		 });
		 
		 Bouton3.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				Bouton3.setScaleX(0.7);
				Bouton3.setScaleY(0.7);
			} 
		 });
		 Bouton3.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
				Bouton3.setScaleX(0.5);
				Bouton3.setScaleY(0.5);
			} 
		 });
		 
	        
		 mediaplayer.play();
		 
		 root.getChildren().addAll(Bouton1,Bouton2,Bouton3);// on rajoute les boutons dans la VBox	
		 root.setBackground(new Background(BackGround));// on implémente le Background
		Scene Intro = new Scene(root,1280,960);// on créé la Scene et on la dimensionne 
		MainStage.setScene(Intro);// on implémente la Scene dans le Stage 
		MainStage.show();// on montre le Stage
		
	}
	@Override
	public void handle(ActionEvent e) { // methode décrivant ce qui doit être fait lorsque nous cliquons sur les boutons 
		if(e.getSource() == Bouton1) {
			System.out.println("Start");
			mediaplayer.stop();
			//MainStage.hide();
			HistoryScene Suite = new HistoryScene();
			try {
				Suite.start(MainStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == Bouton2){
			System.out.println("Arcade");
		}	
	}

	
}
