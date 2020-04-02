package objetsJeux;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import global.Global;
import objetsMathematiques.Conversions;
import objetsMathematiques.Vecteur;
import objetsPhysiques.RigidBody ;

public abstract class Component {
	
	public RigidBody rb ;
	
	public double xMin;
	public double xMax;
	public double yMin;
	public double yMax;
	
	public double width ;
	public double height ;
	
	public Component(double aXMin, double aYMin, double aWidth, double aHeight) {
		xMin = aXMin ;
		yMin = aYMin ;
		width = aWidth ;
		height = aHeight ;
		xMax = xMin + width ;
		yMax = yMin + height ;
		
		Vecteur position = new Vecteur(xMin, yMin) ;
		Vecteur vitesse  = new Vecteur(0, 0) ;
		
		int nb = Global.persoChoisi ; // pour alléger la ligne suivante
		rb = new RigidBody(position, vitesse, Global.g, Global.masse[nb], Global.Cx[nb], Global.rho, Global.surface[nb],
				Global.longueurJambeRepos[nb], Global.longueurJambeContractee[nb], Global.raideur[nb]) ;
	}
	
	public void deplaceDroite(){
		rb.deplaceDroite() ;
	}
	
	public void deplaceGauche(){
		rb.deplaceGauche() ;
	}
	
	public void deplacement(){
		rb.deplacement() ;
		xMin = rb.position.getX() ;
		yMin = rb.position.getY() ;
	}
	
	public abstract void dessin(Graphics g) ;
	
	public void dessineComponent(Graphics g, Color c){
		g.setColor(c) ;
		
		double[] ptsMMin = {xMin, yMin} ;
		double[] ptsMMax = {xMin + width, yMin + height} ;
		
		int[] ptsPMin = Conversions.metricToPixel(ptsMMin) ;
		int[] ptsPMax = Conversions.metricToPixel(ptsMMax) ;
		
		int xMin = Math.min(ptsPMin[0], ptsPMax[0]) ;
		int width = Math.abs(ptsPMin[0] - ptsPMax[0]) ;
		int yMin = Math.min(ptsPMin[1], ptsPMax[1]) ;
		int height = Math.abs(ptsPMin[1] - ptsPMax[1]) ;
		g.fillRect(xMin, yMin, width, height);
		
	}
}
