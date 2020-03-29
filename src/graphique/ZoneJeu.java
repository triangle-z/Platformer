package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import global.Global;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZoneJeu extends JPanel {

	public ZoneJeu(int width, int height) {
		setBounds(0, 0, width, height);
		setLayout(null);
		setBackground(Color.GRAY) ;
		
		////////////////////////*** gere fleches clavier ***\\\\\\\\\\\\\\\\\\\\\\\\

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					Global.joueur.deplaceDroite() ;
				} else {
					if(e.getKeyCode() == KeyEvent.VK_LEFT){
						Global.joueur.deplaceGauche() ;
					}
				}
			}
		});
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		for(int i = 0 ; i < Global.listePlateformes.size() ; i++){
			Global.listePlateformes.get(i).dessin(g) ;
		}
		
		Global.joueur.dessin(g) ;
		
		grabFocus();
	}

}
