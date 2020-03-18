package objetsJeux;
import java.awt.Graphics;

import global.Global;
import objetsMathematiques.Vecteur;
import objetsPhysiques.RigidBody ;

public abstract class Component {
	
	protected RigidBody rb ;
	
	public double xMin;
	public double xMax;
	public double yMin;
	public double yMax;
	
	public double width ;
	public double height ;
	
	public Component(double aXMin, double aYMin, double aXMax, double aYMax) {
		xMin = aXMin ;
		yMin = aYMin ;
		xMax = aXMax ;
		yMax = aYMax ;
		width = xMax - xMin ;
		height = yMax - yMin ;
		
		Vecteur position = new Vecteur(xMin, yMin) ;
		Vecteur vitesse  = new Vecteur(1, 0) ;
		
		int nb = Global.persoChoisi ; // pour alléger la ligne suivante
		rb = new RigidBody(position, vitesse, Global.g, Global.masse[nb], Global.Cx[nb], Global.rho, Global.surface[nb],
				Global.longueurJambeRepos[nb], Global.longueurJambeContractee[nb], Global.raideur[nb]) ;
	}
	
	public void deplacement(){
		rb.deplacement() ;
	}
	
	public abstract void dessin(Graphics g) ;
}
