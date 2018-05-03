package fr.uha.ensisa.puissance4.data;

import fr.uha.ensisa.puissance4.util.Constantes;
import fr.uha.ensisa.puissance4.util.Constantes.Case;

public class Grille {

	private Case[][] grille;

	public Grille() {
		grille = new Case[Constantes.NB_COLONNES][Constantes.NB_LIGNES];
		for (int i = 0; i < Constantes.NB_COLONNES; i++)
			for (int j = 0; j < Constantes.NB_LIGNES; j++) {
				grille[i][j] = Case.V;
			}
	}

	/**
	 * Constructeur qui créé une copie de la grille donné en argument
	 * 
	 * @param original
	 */
	private Grille(Grille original) {
		this.grille = original.grille;
	}

	/**
	 * Clone la grille
	 */
	public Grille clone() {
		Grille copy = new Grille(this);
		return copy;
	}

	/**
	 * Renvoie le contenu de la case aux coordonnées données en argument
	 * 
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public Case getCase(int ligne, int colonne) {
		return grille[colonne][ligne];
	}
	
	public void setCase(int ligne, int colonne,Case value) {
		this.grille[colonne][ligne] = value;
	}

	/**
	 * Indique s'il y a encore de la place dans la colonne indiquée
	 * 
	 * @param colonne
	 * @return
	 */
	public boolean isCoupPossible(int colonne) {
		if (colonne >= 0 && colonne < Constantes.NB_COLONNES) {
			return grille[colonne][Constantes.NB_LIGNES - 1] == Case.V;
		} else {
			return false;
		}
	}

	/**
	 * Ajoute le symbole indiqué dans la colonne indiquée ce qui permet de jouer ce
	 * coup
	 * 
	 * @param colonne
	 * @param symbole
	 */
	public void ajouterCoup(int colonne, Case symbole) {
		for (int j = 0; j < Constantes.NB_LIGNES; j++) {
			if (grille[colonne][j] == Case.V) {
				grille[colonne][j] = symbole;
				break;
			}
		}

	}
	
	/**
	 * retire le dernier symbole indiquée de la colonne indiquée
	 * @param colonne
	 * @param symbole
	 */
	public void annulerCoup(int colonne, Case symbole) {
		for (int j = Constantes.NB_LIGNES-1; 0 <= j; j--) {
			if (grille[colonne][j] == symbole) {
				grille[colonne][j] = Case.V;
				break;
			}
		}
	}

	/**
	 * permet de simuler un coup, sans affichage. 
	 * /!\ WARNING /!\ doit être annulé par la suite avec @see {@link Grille#annulerCoup(int, Case)}
	 * @param coup_actuel
	 * @param symboleMax
	 */
	public void simulerCoup(int coup_actuel, Case symboleMax) {
		// TODO Auto-generated method stub
		ajouterCoup(coup_actuel,symboleMax);
	}
	
	/**
	 * Renvoie l'état de la partie
	 * 
	 * @param symboleJoueurCourant
	 * @param tour
	 * @return
	 */
	public int getEtatPartie(Case symboleJoueurCourant, int tour) {
		int victoire;
		if (symboleJoueurCourant == Constantes.SYMBOLE_J1) {
			victoire = Constantes.VICTOIRE_JOUEUR_1;
		} else {
			victoire = Constantes.VICTOIRE_JOUEUR_2;
		}
		int nbAlignes = 0;
		// Vérification alignement horizontaux
		for (int i = 0; i < Constantes.NB_LIGNES; i++) {
			for (int j = 0; j < Constantes.NB_COLONNES; j++) {
				if (grille[j][i] == symboleJoueurCourant)
					nbAlignes++;
				else
					nbAlignes = 0;
				if (nbAlignes == 4) {
					return victoire;
				}
			}
			nbAlignes = 0;
		}
		// Vérification alignement verticaux
		for (int j = 0; j < Constantes.NB_COLONNES; j++) {
			for (int i = 0; i < Constantes.NB_LIGNES; i++) {
				if (grille[j][i] == symboleJoueurCourant)
					nbAlignes++;
				else
					nbAlignes = 0;
				if (nbAlignes == 4) {
					return victoire;
				}
			}
			nbAlignes = 0;
		}
		// Vérification alignement diagonaux (bas-droite vers haut-gauche)
		for (int i = 0; i < Constantes.NB_LIGNES - 3; i++)
			for (int j = 0; j < Constantes.NB_COLONNES - 3; j++) {
				for (int x = 0; i + x < Constantes.NB_LIGNES && j + x < Constantes.NB_COLONNES; x++) {
					if (grille[j + x][i + x] == symboleJoueurCourant)
						nbAlignes++;
					else
						nbAlignes = 0;
					if (nbAlignes == 4) {
						return victoire;
					}
				}
				nbAlignes = 0;
			}

		// Vérification alignement diagonaux (bas-gauche vers haut-droit)
		for (int i = 0; i < Constantes.NB_LIGNES - 3; i++)
			for (int j = Constantes.NB_COLONNES - 1; j >= 3; j--) {
				for (int x = 0; i + x < Constantes.NB_LIGNES && j - x >= 0; x++) {
					if (grille[j - x][i + x] == symboleJoueurCourant)
						nbAlignes++;
					else
						nbAlignes = 0;
					if (nbAlignes == 4) {
						return victoire;
					}
				}
				nbAlignes = 0;
			}

		if (tour == Constantes.NB_TOUR_MAX) {
			return Constantes.MATCH_NUL;
		}

		return Constantes.PARTIE_EN_COURS;
	}

	/**
	 * Give a score to the "grille" according to a player
	 * 
	 * @param symboleJoueurCourant
	 *            Symbol representing current player
	 * @return value of all alignment no locked and possible
	 * @see Grille#scoreAlignementHorizontaux(Case)
	 * @see Grille#scoreAlignementVerticaux(Case)
	 * @see Grille#scoreAlignementDiagonalBdHg(Case)
	 * @see Grille#scoreAlignementDiagonalBgHd(Case)
	 */
	public double evaluer(Case symboleJoueurCourant) {	
		
		Case symboleJoueurEnnemy;
		if(symboleJoueurCourant==Constantes.SYMBOLE_J1) {
			symboleJoueurEnnemy = Constantes.SYMBOLE_J2;
		}else {
			symboleJoueurEnnemy = Constantes.SYMBOLE_J1;
		}

		int c1 = scoreAlignementHorizontaux(symboleJoueurCourant);
		int c2 = scoreAlignementVerticaux(symboleJoueurCourant);
		int c3 = scoreAlignementDiagonalBgHd(symboleJoueurCourant);
		int c4 = scoreAlignementDiagonalBdHg(symboleJoueurCourant);
		
		int c12 = scoreAlignementHorizontaux(symboleJoueurEnnemy);
		int c22 = scoreAlignementVerticaux(symboleJoueurEnnemy);
		int c32 = scoreAlignementDiagonalBgHd(symboleJoueurEnnemy);
		int c42 = scoreAlignementDiagonalBdHg(symboleJoueurEnnemy);
		
		return (c1 + c2 + c3 + c4) - (c12 + c22 + c32 + c42);
	}

	/**
	 * calculate the player score with taking all horizontal coin aligned. alignment
	 * of 1 = 10 points, 2 = 100 points, 3 = 1000 points and 4 = 10000 points
	 * Alignment locked (can't be ended by fault of bounds or enemy coin) are not
	 * counted
	 * 
	 * @param symboleJoueurCourant
	 *            Symbol representing current player
	 * @return the score of the player
	 */
	private int scoreAlignementHorizontaux(Case symboleJoueurCourant) {
		int nbAlignGood = 0;
		int nbAlignes = 0;
		// Vérification horizontal alignement
		for (int i = 0; i < Constantes.NB_LIGNES; i++) {
			for (int j = 0; j < Constantes.NB_COLONNES; j++) {
				if (grille[j][i] == symboleJoueurCourant)
					nbAlignes++;
				else {
					try { // on essaye de voir si c'est un alignement libre. CAD si c'est un alignement de
							// 4, 3, 2 ou 1 et si ce n'est pas bloqué
						if (nbAlignes == 4) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 3 && grille[j][i + 1] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 2 && grille[j][i + 1] == Constantes.SYMBOLE_V
								&& grille[j][i + 2] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 1 && grille[j][i + 1] == Constantes.SYMBOLE_V
								&& grille[j][i + 2] == Constantes.SYMBOLE_V
								&& grille[j][i + 3] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						}

						nbAlignes = 0;
					} catch (IndexOutOfBoundsException e) {
					}
				}

			}
			nbAlignes = 0;
		}

		return nbAlignGood;
	}

	/**
	 * calculate the player score with taking all vertical coin aligned. alignment
	 * of 1 = 10 points, 2 = 100 points, 3 = 1000 points and 4 = 10000 points
	 * Alignment locked (can't be ended by fault of bounds or enemy coin) are not
	 * counted
	 * 
	 * @param symboleJoueurCourant
	 *            Symbol representing current player
	 * @return the score of the player
	 */
	private int scoreAlignementVerticaux(Case symboleJoueurCourant) {
		int nbAlignGood = 0;
		int nbAlignes = 0;
		// Vérification alignement vertical
		for (int j = 0; j < Constantes.NB_COLONNES; j++) {
			for (int i = 0; i < Constantes.NB_LIGNES; i++) {
				if (grille[j][i] == symboleJoueurCourant)
					nbAlignes++;
				else {
					try { // on essaye de voir si c'est un alignement libre. CAD si c'est un alignement de
							// 4, 3, 2 ou 1 et si ce n'est pas bloqué
						if (nbAlignes == 4) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 3 && grille[j + 1][i] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 2 && grille[j + 1][i] == Constantes.SYMBOLE_V
								&& grille[j + 2][i] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						} else if (nbAlignes == 1 && grille[j + 1][i] == Constantes.SYMBOLE_V
								&& grille[j + 2][i] == Constantes.SYMBOLE_V
								&& grille[j + 3][i] == Constantes.SYMBOLE_V) {
							nbAlignGood += Math.pow(10, nbAlignes);
						}

						nbAlignes = 0;
					} catch (IndexOutOfBoundsException e) {
					}
				}

			}
			nbAlignes = 0;
		}

		return nbAlignGood;
	}

	/**
	 * calculate the player score with taking all diagonal coin aligned (lower-right
	 * to upper-left). alignment of 1 = 10 points, 2 = 100 points, 3 = 1000 points
	 * and 4 = 10000 points Alignment locked (can't be ended by fault of bounds or
	 * enemy coin) are not counted
	 * 
	 * @param symboleJoueurCourant
	 *            Symbol representing current player
	 * @return the score of the player
	 */
	private int scoreAlignementDiagonalBdHg(Case symboleJoueurCourant) {
		int nbAlignGood = 0;
		int nbAlignes = 0;
		// Vérification alignement diagonaux (bas-droite vers haut-gauche)
		for (int i = 0; i < Constantes.NB_LIGNES - 3; i++)
			for (int j = 0; j < Constantes.NB_COLONNES - 3; j++) {
				for (int x = 0; i + x < Constantes.NB_LIGNES && j + x < Constantes.NB_COLONNES; x++) {
					if (grille[j + x][i + x] == symboleJoueurCourant)
						nbAlignes++;
					else {
						try { // on essaye de voir si c'est un alignement libre. CAD si c'est un alignement de
								// 4, 3, 2 ou 1 et si ce n'est pas bloqué
							if (nbAlignes == 4) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 3 && grille[j + 1][i + 1] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 2 && grille[j + 1][i + 1] == Constantes.SYMBOLE_V
									&& grille[j + 2][i + 2] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 1 && grille[j + 1][i + 1] == Constantes.SYMBOLE_V
									&& grille[j + 2][i + 2] == Constantes.SYMBOLE_V
									&& grille[j + 3][i + 3] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							}

							nbAlignes = 0;
						} catch (IndexOutOfBoundsException e) {
						}
					}

				}
				nbAlignes = 0;
			}

		return nbAlignGood;
	}

	/**
	 * calculate the player score with taking all diagonal coin aligned (lower-left
	 * to upper-right). alignment of 1 = 10 points, 2 = 100 points, 3 = 1000 points
	 * and 4 = 10000 points Alignment locked (can't be ended by fault of bounds or
	 * enemy coin) are not counted
	 * 
	 * @param symboleJoueurCourant
	 *            Symbol representing current player
	 * @return the score of the player
	 */
	private int scoreAlignementDiagonalBgHd(Case symboleJoueurCourant) {
		int nbAlignGood = 0;
		int nbAlignes = 0;
		// Vérification alignement diagonaux (bas-gauche vers haut-droit)
		for (int i = 0; i < Constantes.NB_LIGNES - 3; i++)
			for (int j = Constantes.NB_COLONNES - 1; j >= 3; j--) {
				for (int x = 0; i + x < Constantes.NB_LIGNES && j - x >= 0; x++) {
					if (grille[j - x][i + x] == symboleJoueurCourant)
						nbAlignes++;
					else {
						try { // on essaye de voir si c'est un alignement libre. CAD si c'est un alignement de
								// 4, 3, 2 ou 1 et si ce n'est pas bloqué
							if (nbAlignes == 4) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 3 && grille[j - 1][i + 1] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 2 && grille[j - 1][i + 1] == Constantes.SYMBOLE_V
									&& grille[j - 2][i + 2] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							} else if (nbAlignes == 1 && grille[j - 1][i + 1] == Constantes.SYMBOLE_V
									&& grille[j - 2][i + 2] == Constantes.SYMBOLE_V
									&& grille[j - 3][i + 3] == Constantes.SYMBOLE_V) {
								nbAlignGood += Math.pow(10, nbAlignes);
							}

							nbAlignes = 0;
						} catch (IndexOutOfBoundsException e) {
						}
					}

				}
				nbAlignes = 0;
			}

		return nbAlignGood;
	}



}
