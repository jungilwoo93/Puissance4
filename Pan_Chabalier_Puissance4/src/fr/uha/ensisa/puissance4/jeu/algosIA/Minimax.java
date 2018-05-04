package fr.uha.ensisa.puissance4.jeu.algosIA;

import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.util.Constantes;

public class Minimax extends Algorithm {

	public Minimax(int levelIA, Grille grilleDepart, Joueur joueurActuel, int tour) {
		super(levelIA, grilleDepart, joueurActuel, tour);

	}

	/**
	 * Retourne le meilleur coup d'après la difficulté de l'IA et l'état de la
	 * grille
	 * Utilise le calcul parallele pour raccourcir le temps de reflexion de l'IA
	 * Un pool de Threads gère la paralelisation
	 * @return Le meilleur coup
	 */
	@Override
	public int choisirCoup() {
		int corePoolSize = 0;
		int maxPoolSize = Integer.MAX_VALUE;
		long keepAliveTime = 5000;
		
		int tourSimule = 0;
		TreeMap<Double, Integer> result = new TreeMap<Double, Integer>(Double::compare);

		ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
				TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

		for (int i = 0; i < Constantes.NB_COLONNES; i++) {
			final int coup = i;

			threadPoolExecutor.submit(new Runnable() { //we submit new task to explore a branch of the decision tree

				@Override
				public void run() {
					int profondeur = levelIA;
					Grille virtualGrille = new Grille(grilleDepart.getGrille());
					virtualGrille.ajouterCoup(coup, symboleMax);
					double val = minValue(virtualGrille, profondeur - 1, tourSimule);

					result.put(val, coup);
				}
			});
		}

		try {
			threadPoolExecutor.shutdown(); //Lock the Pool, execute all previous submited task.
			threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); //Wait for task's execution
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return result.firstEntry().getValue(); //The first entry of the map is the best play
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
			Grille virtualGrille = new Grille(grille.getGrille());
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
			Grille virtualGrille = new Grille(grille.getGrille());
			virtualGrille.ajouterCoup(coup, symboleMax);
			double val =  minValue(virtualGrille,profondeur-1,tourSimule);
			if(val > maxVal) {
				maxVal= val;
			}
		}		
		return maxVal;
	}

}
