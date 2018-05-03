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
		double maxVal = Constantes.SCORE_MAX_NON_DEFINI;
		int meilleurCoup = Constantes.COUP_NON_DEFINI;
		int tourSimule = 0;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			int profondeur = this.levelIA;
			Grille virtualGrille = this.grilleDepart.clone();
			virtualGrille.ajouterCoup(coup, symboleMax);
			double val =  min(virtualGrille,profondeur-1,tourSimule);
			if(val > maxVal) {
				maxVal= val;
				meilleurCoup = coup;
			}
		}
		
		return meilleurCoup;
	}

	private double min(Grille grille, int profondeur,int tourSimule) {
		if (profondeur == 0
				|| grille.getEtatPartie(symboleMin, tourDepart + profondeur) != Constantes.PARTIE_EN_COURS) {
			return grille.evaluer(symboleMin);
		}
		tourSimule++;
		double minVal = Constantes.SCORE_MIN_NON_DEFINI;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			Grille virtualGrille = grille.clone();
			virtualGrille.ajouterCoup(coup, symboleMin);
			double val =  max(virtualGrille,profondeur-1,tourSimule);
			if(val < minVal) {
				minVal = val;
			}
		}		
		return minVal;
	}

	private double max(Grille grille, int profondeur,int tourSimule) {
		if (profondeur == 0
				|| grille.getEtatPartie(symboleMax, tourDepart + profondeur) != Constantes.PARTIE_EN_COURS) {
			return grille.evaluer(symboleMax);
		}
		tourSimule++;
		double maxVal = Constantes.SCORE_MAX_NON_DEFINI;
		for(int coup = 0;coup<Constantes.NB_COLONNES;coup++) {
			Grille virtualGrille = grille.clone();
			virtualGrille.ajouterCoup(coup, symboleMax);
			double val =  min(virtualGrille,profondeur-1,tourSimule);
			if(val > maxVal) {
				maxVal= val;
			}
		}		
		return maxVal;
	}

}
