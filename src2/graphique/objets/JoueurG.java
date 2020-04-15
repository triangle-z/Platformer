package graphique.objets;

import global.Global;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import objetsJeux.Component;
import objetsJeux.Joueur;
import objetsMathematiques.Conversions;

public class JoueurG extends ComponentG{
	
	long timePrecedent ;
	Joueur j ;
	public int cycle ; //0 --> stand, 1 --> run
	public int orientation ; //0 --> droite, 1 --> gauche //par défaut, les images sont orientees a droite
	int loop ;
	Image imageJoueur ;
	ImageView imageViewJoueur ;

	public JoueurG(Component component) {
		super(component, Color.WHITE);
		// TODO Auto-generated constructor stub
		
		j = (Joueur) component ;
		requestFocus();

		//System.out.println(xMin + " " + yMin + " " + width + " " + height) ;
		
		cycle = 0 ;
		orientation = 0 ;
		
		loop = 0 ;
		
		imageJoueur = new Image(Global.URLsStandUp[loop]) ;
		imageViewJoueur = new ImageView(imageJoueur) ;
		imageViewJoueur.setFitHeight(height) ;
		imageViewJoueur.setFitWidth(width) ;
		imageViewJoueur.setX(0) ;
		imageViewJoueur.setY(0) ;
		
		setTranslateX(Conversions.widthFenetrePixel/2);
		setTranslateY(-Conversions.heightFenetrePixel/2 - height);
		
		getChildren().add(imageViewJoueur) ;
		
		setOnKeyPressed (new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                if (ke.getCode() == KeyCode.RIGHT) {
					j.deplaceDroite();
				}
                
                if (ke.getCode() == KeyCode.LEFT) {
					j.deplaceGauche();
				}
                
                if (ke.getCode() == KeyCode.SPACE) {
					j.saute(); 
				}
            }
        });
		
		setOnKeyReleased (new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                if (ke.getCode() == KeyCode.RIGHT || ke.getCode() == KeyCode.LEFT) {
					j.arreteDeplace();
				}
            }
        });
		
        AnimationTimer timer = new MyTimer(this);
        timePrecedent = System.currentTimeMillis() ;
        timer.start();

	}
	
	public void deplace(){
		
		long timeActuel = System.currentTimeMillis() ;
		
		Global.dt = (double)(timeActuel - timePrecedent) / 1000 ;
		
		component.deplacement() ;
		/*majPosition() ; 					//inutile de mettre à jour la position et de redessiner
		   												//étant donné qu'il est toujours au centre
		
		setTranslateX(xMin);
		setTranslateY(yMax);*/
		
		timePrecedent = timeActuel ;
	}
	
	private class MyTimer extends AnimationTimer {
		
		JoueurG joueur ;
		
		public MyTimer(JoueurG j){
			super() ;
			joueur = j ;
		}
		
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			
			doHandle(now) ;
			requestFocus();
		}
		
		public void doHandle(long now){
			
			joueur.deplace() ;
			
			loop++ ;
			
			if(cycle == 0){
				if(loop > 0){
					loop = 0 ;
				}
				imageJoueur = new Image(Global.URLsStandUp[loop/3]) ;
			} else {
				if(cycle == 1){
					if(loop > 15){
						loop = 0 ;
					}
					imageJoueur = new Image(Global.URLsRunCycle[loop/3]) ;
				}
			}
			
			imageViewJoueur.setImage(imageJoueur);
			
			if(orientation == 1){
				imageViewJoueur.setScaleX(-1) ;
			} else {
				imageViewJoueur.setScaleX(1 ) ;
			}
			
			/*try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}

	}

}
