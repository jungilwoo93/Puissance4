<<<<<<< HEAD
Puissance 4
===========
<center>
![logo](https://git.chabalier.com/snowert/puissance4/raw/b36b0231b49a235bb0613d2d60b04b2cd3c080da/Pan_Chabalier_Puissance4/src/fr/uha/ensisa/puissance4/ui/application/ressources/logo.png)
</center>

PAN Liuyan , CHABALIER Andy
-----------

<u>**Projet Intelligence Artificielle ENSISA 2A IR**</u>

Repository git du projet: [repo git](https://git.chabalier.com/snowert/puissance4)

<u>Objectifs</u>

- Implementer les algorithmes de jeu Min-Max et Alpha-Beta pour un Puissance 4
- Ecrire une fonction d'évaluation permettant de donner un score a une grille non terminale
- paralléliser les calculs pour accélerer le temps de reflexion des IA et les rendres plus efficaces
- Réaliser une interface graphique pour le jeu tout en proposant la possibilité de jouer en console

<u>Algorithmes</u>

Les IA disposent de deux algorithmes pour prévoir et jouer. La fonction d'évaluation des grilles est unique pour tout les algorithmes
Il est possible d'ajouter d'autres algorithmes sans toucher aux autres et au fonctionnement de l'application.

**Algorithme Min-Max**

		function MINIMAx-DECISION(state) returns an action
				inputs: state, current state in game

				return the a in ACTIONS(state) maximizing MIN-VALUE(RESULT(a, state))

		function MAX-VALUE(state) returns a utility value
				if TERMINAL-TEST(state) then return UTILITY(8tate)
				v <— —00
				for a, s in SUCCESSORS(state) do w— MAx(v. MIN-VALUE(3))
				return v

		function MIN-VALUE(state) returns a utility value
				if TERMINAL-TEST(state) then return UTILITY(state)
				v <— 00
				for a, s in SUCCESSORS(state) do w— MIN(v. MAX-VALUE(3))
				return v
				
				
**Algorithme Alpha-Beta**

		function ALPHA—BETA-DECISION(state) returns an action
				return the a in ACTIONS(state) maximizing MIN-VALUE(RESULT(a, state»

		function MAX-VALUE(state, alpha ,beta) returns a utility value
				inputs: state. current state in game

				alpha, the value of the best alternative for MAX along the path to state
				beta. the value of the best alternative for MIN along the path to state

				if TERMINAL-TEST(state) then return UTILITY(state)
				v i— -00
				for a, s in SUCCESSORS(state) do
					v<— MAX(v. MIN-VALUE(s,alpha,beta)
					if v >= beta then return 2)
					alpha *— Max(alpha,v))
				return v

		function MIN-VALUE(state, alpha, beta) returns a utility value
				same as MAX-VALUE but with roles of alpha,beta reversed



=======
Puissance 4
===========
<center>
![logo](https://git.chabalier.com/snowert/puissance4/raw/b36b0231b49a235bb0613d2d60b04b2cd3c080da/Pan_Chabalier_Puissance4/src/fr/uha/ensisa/puissance4/ui/application/ressources/logo.png)
</center>

PAN Liuyan , CHABALIER Andy
-----------

<u>**Projet Intelligence Artificielle ENSISA 2A IR**</u>

Repository git du projet: [repo git](https://git.chabalier.com/snowert/puissance4)

<u>Objectifs</u>

- Implementer les algorithmes de jeu Min-Max et Alpha-Beta pour un Puissance 4
- Ecrire une fonction d'évaluation permettant de donner un score a une grille non terminale
- paralléliser les calculs pour accélerer le temps de reflexion des IA et les rendres plus efficaces
- Réaliser une interface graphique pour le jeu tout en proposant la possibilité de jouer en console

<u>Algorithmes</u>

Les IA disposent de deux algorithmes pour prévoir et jouer. La fonction d'évaluation des grilles est unique pour tout les algorithmes
Il est possible d'ajouter d'autres algorithmes sans toucher aux autres et au fonctionnement de l'application.

**Algorithme Min-Max**

		function MINIMAx-DECISION(state) returns an action
				inputs: state, current state in game

				return the a in ACTIONS(state) maximizing MIN-VALUE(RESULT(a, state))

		function MAX-VALUE(state) returns a utility value
				if TERMINAL-TEST(state) then return UTILITY(8tate)
				v <— —00
				for a, s in SUCCESSORS(state) do w— MAx(v. MIN-VALUE(3))
				return v

		function MIN-VALUE(state) returns a utility value
				if TERMINAL-TEST(state) then return UTILITY(state)
				v <— 00
				for a, s in SUCCESSORS(state) do w— MIN(v. MAX-VALUE(3))
				return v
				
				
**Algorithme Alpha-Beta**

		function ALPHA—BETA-DECISION(state) returns an action
				return the a in ACTIONS(state) maximizing MIN-VALUE(RESULT(a, state»

		function MAX-VALUE(state, alpha ,beta) returns a utility value
				inputs: state. current state in game

				alpha, the value of the best alternative for MAX along the path to state
				beta. the value of the best alternative for MIN along the path to state

				if TERMINAL-TEST(state) then return UTILITY(state)
				v i— -00
				for a, s in SUCCESSORS(state) do
					v<— MAX(v. MIN-VALUE(s,alpha,beta)
					if v >= beta then return 2)
					alpha *— Max(alpha,v))
				return v

		function MIN-VALUE(state, alpha, beta) returns a utility value
				same as MAX-VALUE but with roles of alpha,beta reversed



>>>>>>> 7f201c70d4e1ddd7c46595fedc550df59f77e0e6
