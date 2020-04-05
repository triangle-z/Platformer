package graphique.objets;

import global.Global;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import objetsJeux.Component;
import objetsJeux.Joueur;

public class JoueurG extends ComponentG{
	
	long timePrecedent ;
	Joueur j ;

	public JoueurG(Component component) {
		super(component, Color.WHITE);
		// TODO Auto-generated constructor stub
		
		/*Translate rotation = new Translate(0, width);
		forme.getTransforms().add(rotation);
		
		int i = 0 ;
		Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(rotation.xProperty(), 10)),
            new KeyFrame(new Duration(1000), new KeyValue(rotation.xProperty(), 20))
        );
        timeline.setAutoReverse(false);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
		
		j = (Joueur) component ;
		requestFocus();
		
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
			
			/*try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}

	}

}
