package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Global;

public class ZoneJeu extends JPanel {

	public ZoneJeu(int width, int height) {
		setBounds(0, 0, width, height);
		setLayout(null);
		setBackground(Color.GRAY) ;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		Global.joueur.dessin(g) ;
		
		for(int i = 0 ; i < Global.listePlateformes.size() ; i++){
			Global.listePlateformes.get(i).dessin(g) ;
		}
	}

}
