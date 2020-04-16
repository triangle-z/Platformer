package ObjetGenerationProcedurale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierTexte {
	private int nbLigne;
	private int nbColonne;
	public char tabPlateforme [][];
	private String cheminFichier;
	
	public LectureFichierTexte(int i,int aNbColonne, int aNbLigne) {
		nbLigne = aNbLigne;
		nbColonne = aNbColonne;
		tabPlateforme = new char[aNbLigne][aNbColonne];
		cheminFichier = "fichierTexte/plateforme"+i+".txt";
		InitialisationTableauPlateforme();
		
	}
	
	public void InitialisationTableauPlateforme(){ // Cette méthode va remplir le tableau tabPlateforme en ouvrant et lisant un fichier texte qui contient les blocks consituants la plateforme
		Scanner sc = null;
		int L = 0;
		int H = 0;
        try {
            try {
                sc = new Scanner(new File(cheminFichier));
                while (sc.hasNextLine()) {
                    for (char c : sc.next().toCharArray()) { // On lit chaque caractère du fichier texte
                    	if(H < nbLigne && L < nbColonne){
                    		tabPlateforme[H][L] = c; // on remplit notre tableau
                    	}
                    	L++;
                    	if(L == nbColonne){
                    		L = 0;
                    		H++;
                    	}
                    }
                }
            } finally {
                if (sc != null)
                    sc.close();
            }
 
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
