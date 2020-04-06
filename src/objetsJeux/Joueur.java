package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;

import global.Global;
import objetsMathematiques.Conversions;
import objetsMathematiques.Matrice;
import objetsMathematiques.Vecteur;

public class Joueur extends Component{
	
	public Joueur(double xMin, double yMin, double width, double height) {
		super(xMin, yMin, width, height);
	}
	
	public Joueur(Vecteur posMin, double width, double height) {
		super(posMin, width, height);
	}

	public void forcePlacement(double x, double y) {

		rb.forcePlacement(x, y);
		
		xMin = rb.position.getX() ;
		yMin = rb.position.getY() ;
		
	}
	
	public void deplacement(){
		super.deplacement() ;
		
		Global.camera.setCentre(xMin, yMin);
		
	}

	public void dessin(Graphics g) {
		dessineComponent(g, Color.WHITE) ;
	}
}