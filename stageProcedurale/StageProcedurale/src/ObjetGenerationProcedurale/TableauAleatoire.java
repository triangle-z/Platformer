package ObjetGenerationProcedurale;

import java.util.Random;

public class TableauAleatoire {
	
	private int tailleTableau;
	private int valeurMax;
	private int valeurMin;
	public int[] tabPattern;
	
	private Random rand;
	public TableauAleatoire(int aTailleTableau,int aValeurMax, int aValeurMin) {
		tailleTableau = aTailleTableau;
		valeurMax = aValeurMax;
		valeurMin = aValeurMin;
		creationPattern();
	}
	
	public void creationPattern() {
		rand = new Random();
		tabPattern = new int[tailleTableau];
		for(int i = 0;i<tabPattern.length;i++) {
			tabPattern[i] = rand.nextInt(valeurMax - valeurMin ) + valeurMin;
		}
	}

}
