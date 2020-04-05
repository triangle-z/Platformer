package graphique.objets;

import global.Global;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;

public class Camera extends Parent {
	
	//tout sera dans le systeme metrique
	public double xMin ;
	public double xCentre ;
	public double width  ;
	public double xMax ;
	public double yMin ;
	public double yMax ;
	public double yCentre ;
	public double height ;
	
	public Camera(double xCentre, double yCentre, double width, double height){
		super() ;
		
		this.xCentre = xCentre ;
		this.yCentre = yCentre ;
		this.width = width ;
		this.height = height ;
		
		majBorders() ;
		
        /*AnimationTimer timer = new MyTimer(this);
        timer.start();*/
		
	}
	
	private void majBorders(){
		xMin = xCentre - width  / 2 ;
		xMax = xCentre + width  / 2 ;
		yMin = yCentre - height / 2 ;
		yMax = yCentre + height / 2 ;
	}
	
	public void setCentre(double x, double y){
		xCentre = x ;
		yCentre = y ;
		
		majBorders() ;
	}
	
	private class MyTimer extends AnimationTimer {
		
		Camera camera ;
		
		public MyTimer(Camera c){
			super() ;
			camera = c ;
		}
		
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			
			doHandle(now) ;
			
		}
		
		public void doHandle(long now){
			
			camera.setCentre(Global.joueur.xMin, Global.joueur.yMin) ;
			camera.majBorders() ;
			
		}

	}

}
