package objetsMathematiques;

public class Matrice {
	// L'objet Matrice est défini par 3 attributs

	public int n; // Le nombre de lignes n
	public int p; // Le nombre de colonnes p
	public double[][] tab; // Un tableau [n][p] contenant des doubles

	public Matrice(int L, int C) { // Le premier constructeur crée une matrice à
									// partir des paramètres lignes et colonnes
		this.n = L;
		this.p = C;
		this.tab = new double[this.n][this.p]; // On crée un tableau avec les
												// bonnes dimensions, par défaut
												// constitué de zéros
	}

	public Matrice(double[][] tableau) { // Le deuxième constructeur crée une
											// matrice directement avec un
											// tableau en entrée
		this.n = tableau.length;
		this.p = tableau[0].length;
		this.tab = new double[n][p];

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.p; j++) {
				this.tab[i][j] = tableau[i][j];
			}
		}
	}

	public void afficherLigne(int l) { // Permet d'afficher la ligne de rang l
		for (int i = 0; i < this.p; i++) {
			System.out.print(chiffreSignificatif(this.tab[l][i]) + "|");
		}
		System.out.println();
	}

	public void afficherColonne(int c) { // Affiche la colonne de rang c
		for (int i = 0; i < this.n; i++) {
			System.out.println(this.tab[i][c]);
		}
	}

	public void affiche() {
		for (int i = 0; i < this.n; i++) {
			this.afficherLigne(i); // On affiche toutes les lignes de la matrice
		}
		System.out.println();
	}

	public void additionnerLignes(int l1, int l2, double coeff) {
		for (int i = 0; i < this.p; i++) {
			this.tab[l1][i] += coeff * this.tab[l2][i]; // Cette méthode permet
														// d'ajouter à une ligne
														// l1 une combinaison
		} // linéaire de la ligne l2 (qui sera multipliée par coeff)
			// Cela est très utile pour échelonner une matrice
	}

	public void additionnerColognes(int c1, int c2, double coeff) { // Même
																	// méthode
																	// que
																	// précédemment,
																	// mais pour
																	// des
																	// colonnes
		for (int i = 0; i < this.n; i++) {
			this.tab[i][c1] += coeff * this.tab[i][c2];
		}
	}

	public Matrice transpose() {

		Matrice M = new Matrice(this.p, this.n); // On inverse les colonnes et
													// les lignes

		for (int i = 0; i < this.n; i++) { // Pour tout nombre contenu dans la
											// case [i][j] de la matrice de
											// départ,
			for (int j = 0; j < this.p; j++) { // on l'affectera à la case
												// [j][i] de la matrice
												// d'arrivée, ce qui donne la
												// transposée
				M.tab[j][i] = this.tab[i][j];
			}
		}

		return (M);
	}

	public void permutation(int c) { // Permet, pour une matrice donnée,
										// d'échanger la position de 2 colonnes
		double x;
		int c2 = c;
		while (c2 < this.p - 1 && this.tab[c][c2] == 0) { // Si le coefficient
															// au niveau de la
															// ligne c vaut 0,
															// on ne peut pas
															// réaliser un pivot
															// de Gauss
			c2++; // Du coup, on incrémente c2 jusqu'à trouver un coefficient
					// différent de 0
		} // puis on permute les 2 colonnes

		for (int i = c; i < this.n; i++) { // Attention, on remplace la colonne1
											// par -colonne2, car permuter des
											// colonnes
			x = -this.tab[i][c]; // change le signe du déterminant
			this.tab[i][c] = this.tab[i][c2];
			this.tab[i][c2] = x;
		}
	}

	public Matrice trigonalisation() { // Permet de transformer une matrice en
										// une matrice triangulaire inférieure,
										// par la méthode du
		Matrice M = new Matrice(this.tab); // pivot de Gauss
		double coeff = 0;
		for (int i = 1; i < M.n; i++) {
			if (M.tab[i - 1][i - 1] == 0) { // Si le coefficient au niveau de la
											// diagonale vaut 0, on permute avec
											// une autre colonne
				M.permutation(i - 1);
			}
			for (int j = i; j < M.n; j++) { // Si après l'étape de permutation,
											// on a toujours un 0 au niveau de
											// la diagonale,
				if (M.tab[i - 1][i - 1] == 0) {// cela veut dire une des
												// colonnes n'est constituée que
												// de zéros
					coeff = 0; // On met donc coeff=0, la matrice trigonale aura
								// un 0 au niveau de sa diagonale

				} else { // Pour toutes les lignes en dessous du rang[i-1], on
							// soustrait la ligne de rang[i]
					coeff = M.tab[j][i - 1] / M.tab[i - 1][i - 1];// multipliée
																	// par le
																	// bon
																	// coefficient
																	// afin de
																	// n'avoir
																	// que des
																	// zéros
																	// sous la
																	// diagonale
				}
				for (int k = i - 1; k < n; k++) {
					M.tab[j][k] -= M.tab[i - 1][k] * coeff;
				}
			}
		}
		return (M);
	}

	public boolean ligneNulle(int N) { // renvoie true si une des lignes n'est
										// constitué que de zéros
		boolean b = true;
		for (int i = 0; i < this.p; i++) {
			if (this.tab[N][i] != 0) {
				b = false;
				break;
			}
		}
		return (b);
	}

	public int rang() {
		int rang = this.p; // Par défaut, le rang de la matrice est égal au
							// nombre de colonnes
		Matrice M = this.trigonalisation(); // On échelonne la matrice, puis
											// pour chaque ligne nulle, on
											// enlève 1 au rang
		for (int i = 0; i < this.n; i++) {
			if (M.ligneNulle(i)) {
				rang = rang - 1;
			}
		}
		return (rang);
	}

	public double determinant() { // Renvoie le déterminant de la matrice
		double det = 1;
		Matrice M = this.trigonalisation(); // En effectuant des combinaisons
											// linéaires sur une matrice, on ne
											// change pas son détermiant
		for (int i = 0; i < M.n; i++) { // Donc le déterminant de la matrice
										// triangulaire associée a la même
										// valeur
			det *= M.tab[i][i]; // Pour une matrice triangulaire, le déterminant
								// correspond au produit des termes diagonaux
		}
		return (chiffreSignificatif(det)); // Cette opération permet d'effectuer
											// beaucoup moins d'opération que la
											// méthodes des cofacteurs, qui
											// demandent
		// de prendre en compte n! possibilités pour une matrice de dimension n
	} // Ici, nous sommes de l'ordre de n³ opérations

	public boolean isCarre() { // Renvoie true si la matrice est carré, ce qui
								// est bon à savoir, notamment pour
		if (this.n == this.p) { // des calculs d'inverses
			return (true);
		} else {
			return (false);
		}
	}

	public boolean isInversible() {
		if (!this.isCarre()) { // Si la matrice n'est pas carré, on sait
								// d'emblée que la matrice n'est pas inversible
			return (false); // Pas besoin de calculer le déterminant
		}
		if (this.determinant() != 0) { // Si le déterminant est différent de 0,
										// alors la matrice est inversible
			return (true);
		} else {
			return (false);
		}
	}

	public Matrice additionner(Matrice M) { // Permet d'additionner 2 matrices
		Matrice Res = new Matrice(this.n, this.p);
		if (M.p == this.p && M.n == this.n) { // on vérifie que l'addition peut
												// se faire
			for (int i = 0; i < this.n; i++) {
				for (int j = 0; j < this.p; j++) { // on parcourt donc pour les
													// lignes d'abord
					Res.tab[i][j] = this.tab[i][j] + M.tab[i][j]; // chaque case
																	// prend la
																	// somme des
																	// mêmes
																	// cases des
																	// deux
																	// matrices
				}
			}
			return Res;
		} else {
			System.out
					.println("votre matrice M doit comporter le meme nombre de lignes et colonnes que cette matrice:");
			return this;
		}
	}

	public Matrice multiplier(Matrice M) { // Effectue le produit this*M, et non
											// pas M*this
		if (this.p == M.n) {
			//System.out.println("MULTIPLICATION")
			Matrice Res = new Matrice(this.n, M.p);
			for (int i = 0; i < Res.n; i++) {
				for (int j = 0; j < Res.p; j++) { // on parcourt chaque case de
													// Res calculer par ligne
					for (int k = 0; k < this.p; k++) { // pour différencier les
														// multiplications, k
														// prend les cases à
														// aller chercher dans
														// les matrices à
														// multiplier
						Res.tab[i][j] = Res.tab[i][j] + this.tab[i][k] * M.tab[k][j]; // on
																						// somme
																						// les
																						// multipliactions
																						// à
																						// faire
																						// pour
																						// chaque
																						// cases
					}
				}
			}
			return Res;
		} else {
			System.out.println(
					"Votre matrice en paramètre doit avoir en nombre de lignes le nombre de colonne de cette matrice");
			return this;
		}
	}

	public Matrice multiplier(double k) { // Effectue le produit this*M, et non pas M*this
		Matrice Res = new Matrice(n, p);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) { 
				Res.tab[i][j] = tab[i][j] * k ;
			}
		}
		return Res;
	}

	public Matrice puissance(int pow) { // Renvoie la matrice à la puissance pow
		Matrice M = new Matrice(this.tab);
		for (int i = 0; i < pow - 1; i++) {
			M = this.multiplier(M); // On mutiplie M par elle-même pow-1 fois
		}
		return (M);
	}

	public Matrice cofacteur(int I, int J) { // Renvoie la matrice de départ à
												// laquelle on a enlevé la ligne
												// I et la colonne J

		Matrice M = new Matrice(this.n - 1, this.p - 1); // On enlève une
															// dimension à la
															// matrice de départ

		int Ligne = 0; // Ligne et colonne sont des indices de la matrice que
						// l'on fera varier manuellement,
		int Colonne = 1; // et non pas avec une boucle for

		for (int i = 0; i < this.n; i++) { // On parcourt toutes les lignes de
											// la matrice initiale
			Colonne = 0;
			if (i != I) { // Si l'on est pas à la ligne I, alors on parcourt la
							// colonne
				for (int j = 0; j < this.p; j++) {
					if (j != J) { // Si l'on est pas à la colonne J, alors on
									// remplit les cases de la matrice
						M.tab[Ligne][Colonne] = this.tab[i][j];
						Colonne++;
					} // On incrémente Colonne de 1 seulement si on est pas à la
						// colonne J
				}
				Ligne++; // De même, on ne fait varier Ligne que si on est pas à
							// la ligne I
			}

		}
		return (M);
	}

	public Matrice comatrice() { // Renvoie la comatrice de la matrice de départ
		Matrice M = new Matrice(this.n, this.p);
		for (int i = 0; i < this.n; i++) { // On remplit chaque case de la
											// matrice avec le déterminant du
											// cofacteur
			for (int j = 0; j < this.p; j++) { // associé à cette case, le tout
												// à la puissance -1(i+j)
				M.tab[i][j] = Math.pow(-1, i + j) * this.cofacteur(i, j).determinant();
			}
		}
		return (M);
	}

	public Matrice inverse() { // Renvoie l'inverse de la matrice
		Matrice M = new Matrice(this.tab);
		if (this.isInversible()) { // On vérifie si la matrice est belle et bien
									// inversible
			M = M.comatrice();
			M = M.transpose(); // On obtient d'abord la transposé de la
								// comatrice
			for (int i = 0; i < M.n; i++) {
				for (int j = 0; j < M.p; j++) { // Puis, on divise chaque case
												// par le déterminant de la
												// matrice de départ
					M.tab[i][j] = M.tab[i][j] / this.determinant(); // Cela nous
																	// donne la
																	// matrice
																	// inverse
				}
			}
		} else {
			System.out.println("La matrice n'est pas inversible");
		}
		return (M);

	}

	/*
	 * Cette méthode renvoie un double à 4 chiffres significatifs En effet, pour
	 * des calculs sur des grosses matrices, ou alors avec des nombres à
	 * virgule, les calculs d'inverse donne parfois des résultats peu lisibles.
	 * On a donc voulu simplifier tout en arrondissant à 4 chiffres après la
	 * virgule
	 */
	public static double chiffreSignificatif(double n) {

		if (n < 10000) {
			String s = String.valueOf(n); // renvoie le nombre mais sous forme
											// de caractère
			int nAvantVirg = s.indexOf('.'); // indexOf renvoie le nombre de
												// caractère qu'il y a avant le
												// caractère mentionné en
												// paramètre
			if (s.length() > 4 && nAvantVirg == 4) { // Si 1000<n<9999 on
														// affiche la partie
														// entière du nombre
														// (4C.S.)
				n = Double.parseDouble(s.substring(0, 4));
			} else if (s.length() > 4 && nAvantVirg < 4) { // si n<1000 on
															// affiche au total
															// avec 4 C.S. aussi
				n = Double.parseDouble(s.substring(0, 5));
			}
			return n; // on renvoie la chaine de caractère contenant 4 CS

		} else {
			return n;
		}
	}

	public Matrice transformationAffineInterne(Matrice a, Matrice b) { //
		Matrice res = a.multiplier(this.additionner(b));
		return res;
	}

	public Matrice transformationAffineExterne(Matrice a, Matrice b) {
		Matrice res = a.multiplier(this);
		return res.additionner(b);
	}
	
	public String toString(){
		String res = "" ;
		for(int i = 0 ; i < tab.length ; i++){
			for(int j = 0 ; j < tab[0].length ; j++){
				res += tab[i][j] + "  " ;
			}
			res += "\n" ;
		}
		return res ;
	}

}
