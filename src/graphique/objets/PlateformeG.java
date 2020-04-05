package graphique.objets;

import global.Global;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objetsJeux.Component;
import objetsJeux.Plateforme;

public class PlateformeG extends ComponentG {

	public PlateformeG(Component component) {
		super(component, Color.BLACK);
		// TODO Auto-generated constructor stub
		AnimationTimer timer = new MyTimer(this);
        timer.start();
	}
	
	public void replace(){
		
		majPosition() ; 
	
		forme = new Rectangle(width, height, Color.BLACK) ;
		getChildren().add(forme) ;
		
		setTranslateX(xMin) ;
		setTranslateY(yMax) ;
		
	}
	
	private class MyTimer extends AnimationTimer {
		
		PlateformeG plateforme ;
		
		public MyTimer(PlateformeG p){
			super() ;
			plateforme = p ;
		}
		
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			
			doHandle(now) ;
			
		}
		
		public void doHandle(long now){
			
			plateforme.replace() ;
			
			Plateforme t = (Plateforme) plateforme.component ;
			
			t.gererCollision(Global.joueur) ;
			
			/*try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}

	}

}
