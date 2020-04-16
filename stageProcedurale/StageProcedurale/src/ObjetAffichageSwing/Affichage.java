package ObjetAffichageSwing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import ObjetGenerationProcedurale.Plateforme;

public class Affichage  extends JPanel {
	public ArrayList<ArrayList<ArrayList<Plateforme>>> listOPlateforme;
	
	public Affichage(ArrayList<ArrayList<ArrayList<Plateforme>>> listOPlateforme){
		this.listOPlateforme = listOPlateforme;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		for(int i = 0; i<listOPlateforme.size();i++) {
			for(int j = 0; j<listOPlateforme.get(i).size();j++) {
				for(int m = 0; m < listOPlateforme.get(i).get(j).size();m++) {
					g.drawImage(listOPlateforme.get(i).get(j).get(m).img,listOPlateforme.get(i).get(j).get(m).xMin,listOPlateforme.get(i).get(j).get(m).yMin, this);
				}
			}
		}
  }  

}
