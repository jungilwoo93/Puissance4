package fr.uha.ensisa.puissance4.jeu.algosIA;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.util.Constantes;

public class Minimax extends Algorithm {

	public Minimax(int levelIA, Grille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);

	}

	@Override
	public int choisirCoup() {

		Grille grille = grilleDepart;
		int profondeur = levelIA;
		int meilleurCoup = -1;
		double max_val = Constantes.SCORE_MIN_NON_DEFINI;
		for (int coup_actuel = 0; coup_actuel < Constantes.NB_COLONNES; coup_actuel++) {
			Grille g = grille.clone();
			g.ajouterCoup(coup_actuel, symboleMax);
			int val = min(g, profondeur);

			if (val > max_val) {
				max_val = val;
				meilleurCoup = coup_actuel;
			}
			grille = grilleDepart;
		}
		return meilleurCoup;
	}

	public int min(Grille grille, int profondeur) {
		if (profondeur == 0 || grille.getEtatPartie(symboleMax, tourDepart + profondeur) != Constantes.PARTIE_EN_COURS) {
			return (int) grille.evaluer(symboleMax);
		}
		double min_val = Constantes.SCORE_MIN_NON_DEFINI;
		for (int coup_actuel = 0; coup_actuel < Constantes.NB_COLONNES; coup_actuel++) {
			Grille g = grille.clone();
			g.ajouterCoup(coup_actuel, symboleMin);
			double val = max(g, profondeur - 1);

			if (val < min_val) {
				min_val = val;
			}
		}

		return (int) min_val;
	}

	public int max(Grille grille, int profondeur) {
		if (profondeur == 0 || grille.getEtatPartie(symboleMax, tourDepart + profondeur) != Constantes.PARTIE_EN_COURS) {
			return (int) grille.evaluer(symboleMax);
		}
		double max_val = Constantes.SCORE_MIN_NON_DEFINI;
		for (int coup_actuel = 0; coup_actuel < Constantes.NB_COLONNES; coup_actuel++) {
			Grille g = grille.clone();
			g.ajouterCoup(coup_actuel, symboleMax);
			double val = min(g, profondeur - 1);

			if (val > max_val) {
				max_val = val;
			}
		}

		return (int) max_val;
	}
}
