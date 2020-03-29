package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;

import objetsMathematiques.Conversions;
import objetsMathematiques.Matrice;

public class Joueur extends Component{
	
	public Joueur(double xMin, double yMin, double width, double height) {
		super(xMin, yMin, width, height);
	}

	public void gereCollision(boolean[] tabDetectionCollision, double choc) {

		rb.gereCollision(tabDetectionCollision, choc) ;
		
	}

	public void dessin(Graphics g) {
		dessineComponent(g, Color.WHITE) ;
	}
}