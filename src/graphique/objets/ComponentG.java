package graphique.objets;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import objetsJeux.Component;
import objetsJeux.Plateforme;
import objetsMathematiques.Conversions;
import objetsPhysiques.RigidBody;

public class ComponentG extends Parent{
	public int xMin ;
	public int xMax ;
	public int yMin ;
	public int yMax ;
	public int width ;
	public int height ;
	
	protected Rectangle forme ;
	protected Component component ;
	
	public ComponentG(Component component, Color couleur) {
		super();
		
		this.component = component ;
		majPosition() ;
	
		forme = new Rectangle(width, height, couleur) ;
		getChildren().add(forme) ;
		
		setTranslateX(xMin) ;
		setTranslateY(yMax);
	}
	
	public void majPosition(){

		double[] pointsMinMetric = {component.xMin , component.yMin} ;
		double[] pointsMaxMetric = {component.xMax , component.yMax} ;
		int[] pointsMinPixel = Conversions.metricToPixel(pointsMinMetric ) ;
		int[] pointsMaxPixel = Conversions.metricToPixel(pointsMaxMetric ) ;
		int[] taillesPixel = {pointsMaxPixel[0] - pointsMinPixel[0], pointsMinPixel[1] - pointsMaxPixel[1]} ; //attention a bien inverser pour y
		
		xMin 	= pointsMinPixel [0];
		yMin 	= pointsMinPixel [1];
		xMax 	= pointsMaxPixel [0];
		yMax 	= pointsMaxPixel [1];
		width 	= taillesPixel[0];
		height 	= taillesPixel[1];
	}
	
}
