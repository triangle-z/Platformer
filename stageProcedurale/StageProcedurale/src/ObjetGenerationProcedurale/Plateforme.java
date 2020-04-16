package ObjetGenerationProcedurale;

import java.awt.Image;

public class Plateforme {
	public int xMin;
	public int xMax;
	public int yMin;
	public int yMax;
	public Image img;

	public Plateforme(int xMin, int yMin, int xMax, int yMax, Image img) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.img = img;	
	}
}
