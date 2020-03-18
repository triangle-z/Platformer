package objetsJeux;

import java.awt.Color;
import java.awt.Graphics;

import objetsMathematiques.Conversions;
import objetsMathematiques.Matrice;

public class Joueur extends Component{
	
	public Joueur(double xMin, double yMin, double xMax, double yMax) {
		super(xMin, yMin, xMax, yMax);
	}

	public void gereCollision(boolean[] tabDetectionCollision, double choc) {

		rb.gereCollision(tabDetectionCollision, choc) ;
		
	}

	public void dessin(Graphics g) {
		g.setColor(Color.WHITE) ;
		
		double[] ptsM = {xMin, yMin} ;
		double[] tailleM = {width, height} ;
		
		int[] ptsP = Conversions.metricToPixel(ptsM) ;
		int[] tailleP = Conversions.metricToPixel(tailleM) ;
		g.fillRect(ptsP[0], ptsP[1], tailleP[0], tailleP[1]);
	}
}