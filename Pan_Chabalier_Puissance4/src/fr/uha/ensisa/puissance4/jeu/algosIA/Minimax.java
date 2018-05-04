package fr.uha.ensisa.puissance4.jeu.algosIA;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.util.Constantes;

public class Minimax extends Algorithm {

	public Minimax(int levelIA, Grille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);

	}

	/**
	 * Retourne le meilleur coup d'après la difficulté de l'IA et l'état de la grille
	 * @return Le meilleur coup
	 */
	@Override
	public int choisirCoup() {
		double maxVal = Constantes.SCORE_MAX_NON_DEFINI;
		int meilleurCoup = Constantes.COUP_NON_DEFINI;
		int tourSimule = 0;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			int profondeur = this.levelIA;
			Grille virtualGrille = new Grille(grilleDepart.getGrille());
			virtualGrille.ajouterCoup(coup, symboleMax);
			double val =  minValue(virtualGrille,profondeur-1,tourSimule);
			if(val > maxVal) {
				maxVal= val;
				meilleurCoup = coup;
			}
		}
		return meilleurCoup;
	}
	
	/**
	 * permet de retourner la valeur minimale des noeuds inferieurs. On essaye ici de minimiser le jeu de l'adversaire
	 * @param grille grille simulée. Reflète la grille de depart et les reflexions
	 * @param profondeur profondeur actuelle du noeud
	 * @param tourSimule tour "virtuel" correspond au tour du noeud simulé. 
	 * @return la valeur minimale des noeuds enfants
	 */
	private double minValue(Grille grille, int profondeur,int tourSimule) {
		if (profondeur == 0
				|| grille.getEtatPartie(symboleMin, tourDepart + profondeur + tourSimule) != Constantes.PARTIE_EN_COURS) {
			return grille.evaluer(symboleMin);
		}
		tourSimule++;
		double minVal = Constantes.SCORE_MIN_NON_DEFINI;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			Grille virtualGrille = grille.clone();
			virtualGrille.ajouterCoup(coup, symboleMin);
			double val =  maxValue(virtualGrille,profondeur-1,tourSimule);
			if(val < minVal) {
				minVal = val;
			}
		}		
		return minVal;
	}

	/**
	 * permet de retourner la valeur maximale des noeuds inferieurs. On essaye ici de maximiser le jeu du joueur
	 * @param grille grille simulée. Reflète la grille de depart et les reflexions
	 * @param profondeur profondeur actuelle du noeud
	 * @param tourSimule tour "virtuel" correspond au tour du noeud simulé. 
	 * @return la valeur maximale des noeuds enfants
	 */
	private double maxValue(Grille grille, int profondeur,int tourSimule) {
		if (profondeur == 0
				|| grille.getEtatPartie(symboleMax, tourDepart + profondeur + tourSimule) != Constantes.PARTIE_EN_COURS) {
			return grille.evaluer(symboleMax);
		}
		tourSimule++;
		double maxVal = Constantes.SCORE_MAX_NON_DEFINI;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			Grille virtualGrille = grille.clone();
			virtualGrille.ajouterCoup(coup, symboleMax);
			double val =  minValue(virtualGrille,profondeur-1,tourSimule);
			if(val > maxVal) {
				maxVal= val;
			}
		}		
		return maxVal;
	}

}
