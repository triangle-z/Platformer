package graphique;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objetsMathematiques.Conversions;

public class Fenetre extends JFrame {

	private ZoneJeu zoneJeu;

	public Fenetre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false) ;
		setVisible(true) ;
		int width = Math.abs(Conversions.widthFenetrePixel) ;
		int height = Math.abs(Conversions.heightFenetrePixel) ;
		setBounds(100, 100, width, height) ;
		zoneJeu = new ZoneJeu(width, height) ;
		setContentPane(zoneJeu) ;
	}

}
