package ObjetAffichageSwing;

import ObjetGenerationProcedurale.Map;

public class Main {

	public static void main(String[] args) {
		Map map = new Map(10,3,3);
		Fenetre fenetre = new Fenetre(map.listOPlateforme);
	}

}
