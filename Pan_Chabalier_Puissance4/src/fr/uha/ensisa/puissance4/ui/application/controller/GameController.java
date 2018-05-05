package fr.uha.ensisa.puissance4.ui.application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.uha.ensisa.puissance4.data.Grille;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.data.Partie;
import fr.uha.ensisa.puissance4.ui.GUI;
import fr.uha.ensisa.puissance4.util.Constantes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GameController extends Thread implements Initializable {

	private String colorEmpty = "WHITE";
	private String colorPlayer1 = "YELLOW";
	private String colorPlayer2 = "RED";

	private Joueur player1;
	private Joueur player2;
	private Joueur currentPlayer;
	private BorderPane root;
	private ChoiceController choiceController;

	ArrayList<Button> buttons = new ArrayList<Button>();
	private String display;

	HashMap<String, Circle> cases = new HashMap<String, Circle>();

	private int coup = 0;

	@FXML
	private AnchorPane gamePane;

	@FXML
	private TextArea displayArea;

	@FXML
	private Button endButton;

	@FXML
	private Button buttonCol1;

	@FXML
	private Button buttonCol2;

	@FXML
	private Button buttonCol3;

	@FXML
	private Button buttonCol4;

	@FXML
	private Button buttonCol5;

	@FXML
	private Button buttonCol6;

	@FXML
	private Button buttonCol7;

	@FXML
	private Circle case00;

	@FXML
	private Circle case01;

	@FXML
	private Circle case02;

	@FXML
	private Circle case03;

	@FXML
	private Circle case04;

	@FXML
	private Circle case05;

	@FXML
	private Circle case10;

	@FXML
	private Circle case11;

	@FXML
	private Circle case12;

	@FXML
	private Circle case13;

	@FXML
	private Circle case14;

	@FXML
	private Circle case15;

	@FXML
	private Circle case20;

	@FXML
	private Circle case21;

	@FXML
	private Circle case22;

	@FXML
	private Circle case23;

	@FXML
	private Circle case24;

	@FXML
	private Circle case25;

	@FXML
	private Circle case30;

	@FXML
	private Circle case31;

	@FXML
	private Circle case32;

	@FXML
	private Circle case33;

	@FXML
	private Circle case34;

	@FXML
	private Circle case35;

	@FXML
	private Circle case40;

	@FXML
	private Circle case41;

	@FXML
	private Circle case42;

	@FXML
	private Circle case43;

	@FXML
	private Circle case44;

	@FXML
	private Circle case45;

	@FXML
	private Circle case50;

	@FXML
	private Circle case51;

	@FXML
	private Circle case52;

	@FXML
	private Circle case53;

	@FXML
	private Circle case54;

	@FXML
	private Circle case55;

	@FXML
	private Circle case60;

	@FXML
	private Circle case61;

	@FXML
	private Circle case62;

	@FXML
	private Circle case63;

	@FXML
	private Circle case64;

	@FXML
	private Circle case65;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonCol1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol1.getText()) - 1;
			}
		});
		buttonCol2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol2.getText()) - 1;
			}
		});
		buttonCol3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol3.getText()) - 1;
			}
		});
		buttonCol4.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol4.getText()) - 1;
			}
		});
		buttonCol5.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol5.getText()) - 1;
			}
		});
		buttonCol6.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol6.getText()) - 1;
			}
		});
		buttonCol7.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				coup = Integer.parseInt(buttonCol7.getText()) - 1;
			}
		});
		buttons.add(buttonCol1);
		buttons.add(buttonCol2);
		buttons.add(buttonCol3);
		buttons.add(buttonCol4);
		buttons.add(buttonCol5);
		buttons.add(buttonCol6);
		buttons.add(buttonCol7);
		this.displayArea.setEditable(false);
		this.displayArea.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				displayArea.setScrollTop(Double.MAX_VALUE); // this will scroll to the bottom
				// use Double.MIN_VALUE to scroll to the top
			}
		});

		this.cases.put("case00", case00);
		this.cases.put("case01", case01);
		this.cases.put("case02", case02);
		this.cases.put("case03", case03);
		this.cases.put("case04", case04);
		this.cases.put("case05", case05);
		this.cases.put("case10", case10);
		this.cases.put("case11", case11);
		this.cases.put("case12", case12);
		this.cases.put("case13", case13);
		this.cases.put("case14", case14);
		this.cases.put("case15", case15);
		this.cases.put("case20", case20);
		this.cases.put("case21", case21);
		this.cases.put("case22", case22);
		this.cases.put("case23", case23);
		this.cases.put("case24", case24);
		this.cases.put("case25", case25);
		this.cases.put("case30", case30);
		this.cases.put("case31", case31);
		this.cases.put("case32", case32);
		this.cases.put("case33", case33);
		this.cases.put("case34", case34);
		this.cases.put("case34", case34);
		this.cases.put("case40", case40);
		this.cases.put("case41", case41);
		this.cases.put("case42", case42);
		this.cases.put("case43", case43);
		this.cases.put("case44", case44);
		this.cases.put("case45", case45);
		this.cases.put("case50", case50);
		this.cases.put("case51", case51);
		this.cases.put("case52", case52);
		this.cases.put("case53", case53);
		this.cases.put("case54", case54);
		this.cases.put("case55", case55);
		this.cases.put("case60", case60);
		this.cases.put("case61", case61);
		this.cases.put("case62", case62);
		this.cases.put("case63", case63);
		this.cases.put("case64", case64);
		this.cases.put("case65", case65);
	}

	public void start() {
		setButtonColorToCurrent();

		display = "************* Début de partie ************" + System.lineSeparator();
		display += "Joueur 1 : " + this.player1.getNom() + " (" + this.player1.getTypeNom() + ")"
				+ System.lineSeparator();
		display += "Joueur 2 : " + this.player2.getNom() + " (" + this.player2.getTypeNom() + ")"
				+ System.lineSeparator();
		this.displayArea.setText(display);
		this.displayArea.appendText("");

		Partie partie = new Partie(this.player1, this.player2);

		tour(partie);

		display += afficherFinPartie(partie);
		this.displayArea.setText(display);
		this.displayArea.appendText("");

		this.endButton.setVisible(true);

	}

	public void backToMenu() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GUI.class.getResource("application/view/ChoiceView.fxml"));

		this.gamePane.setVisible(false);
		this.choiceController.getChoicePane().setVisible(true);
	}

	private void tour(Partie partie) {
		if (!partie.isPartieFinie()) {
			display += "************* Tour " + partie.getTour() + " ************" + System.lineSeparator();
			display += "C'est à " + partie.getJoueurCourant().getNom() + " de jouer !" + System.lineSeparator();
			this.displayArea.setText(display);
			this.displayArea.appendText("");
			afficheGrille(partie.getGrille());

			long tempsReflexion = System.currentTimeMillis();

			if (partie.getJoueurCourant().getType() == 1) { // si le joueur est humain
				TextInputDialog inDialog = new TextInputDialog("1");
				inDialog.setTitle("choix du coup");
				inDialog.setHeaderText("Choissisez votre coup");
				inDialog.setContentText("Coup :");
				Optional<String> textIn = inDialog.showAndWait();
				if (textIn.isPresent()) {
					coup = Integer.parseInt(textIn.get()) - 1;
				}

			} else {
				this.coup = partie.getJoueurCourant().joue(partie.getGrille(), partie.getTour());
			}

			tempsReflexion = System.currentTimeMillis() - tempsReflexion;

			display += partie.getJoueurCourant().getNom() + " a choisi de mettre un jeton dans la colonne "
					+ (this.coup + 1) + " après " + timeToString(tempsReflexion) + " de réflexion"
					+ System.lineSeparator();
			this.displayArea.setText(display);
			this.displayArea.appendText("");
			this.displayArea.appendText("");

			if (!partie.jouerCoup(coup, tempsReflexion)) {
				display += "COUP INVALIDE : Recommencez !" + System.lineSeparator();
				this.displayArea.setText(display);
				this.displayArea.appendText("");
			}
			this.coup = 0;
			tour(partie);
		}
	}

	private void afficheGrille(Grille grille) {
		for (int i = Constantes.NB_LIGNES - 1; i >= 0; i--) {
			for (int j = 0; j < Constantes.NB_COLONNES; j++) {
				if (grille.getCase(i, j) == Constantes.SYMBOLE_J1) {
					cases.get("case" + j + i).setFill(Paint.valueOf(this.colorPlayer1));
				}
				if (grille.getCase(i, j) == Constantes.SYMBOLE_J2) {
					cases.get("case" + j + i).setFill(Paint.valueOf(this.colorPlayer2));
				}
				if (grille.getCase(i, j) == Constantes.SYMBOLE_V) {
					// cases.get("case"+j+i).setFill(Paint.valueOf(this.colorEmpty)); // in case of
					// non white background
				}
			}
		}
	}

	private String afficherFinPartie(Partie partie) {
		String msg;
		switch (partie.getEtatPartie()) {
		case Constantes.VICTOIRE_JOUEUR_1:
			msg = "VICTOIRE " + partie.getJoueur1().getNom() + System.lineSeparator();
			break;
		case Constantes.VICTOIRE_JOUEUR_2:
			msg = "VICTOIRE " + partie.getJoueur2().getNom() + System.lineSeparator();
			break;
		default:
			msg = "MATCH NUL" + System.lineSeparator();
			break;
		}
		msg += "************ " + msg + " en " + (partie.getTour() - 1) + " tours ***************"
				+ System.lineSeparator();
		afficheGrille(partie.getGrille());
		msg += partie.getJoueur1().getNom() + " : " + timeToString(partie.getTempsReflexionJ1()) + "s"
				+ System.lineSeparator();
		msg += partie.getJoueur2().getNom() + " : " + timeToString(partie.getTempsReflexionJ2()) + "ms"
				+ System.lineSeparator();
		return msg += "******************************************************************" + System.lineSeparator();
	}

	public void setButtonColorToCurrent() {
		if (this.currentPlayer.getOrder() == 1) {
			for (Button b : this.buttons) {
				b.setStyle("-fx-background-color:" + this.colorPlayer1.toLowerCase() + ";");
			}
		} else {
			for (Button b : this.buttons) {
				b.setStyle("-fx-background-color:" + this.colorPlayer2.toLowerCase() + ";");
			}
		}
	}

	/**
	 * @return the colorEmpty
	 */
	public String getColorEmpty() {
		return colorEmpty;
	}

	/**
	 * @param colorEmpty
	 *            the colorEmpty to set
	 */
	public void setColorEmpty(String colorEmpty) {
		this.colorEmpty = colorEmpty;
	}

	/**
	 * @return the colorPayer1
	 */
	public String getColorPayer1() {
		return colorPlayer1;
	}

	/**
	 * @param colorPayer1
	 *            the colorPayer1 to set
	 */
	public void setColorPayer1(String colorPayer1) {
		this.colorPlayer1 = colorPayer1;
	}

	/**
	 * @return the colorPlayer2
	 */
	public String getColorPlayer2() {
		return colorPlayer2;
	}

	/**
	 * @param colorPlayer2
	 *            the colorPlayer2 to set
	 */
	public void setColorPlayer2(String colorPlayer2) {
		this.colorPlayer2 = colorPlayer2;
	}

	/**
	 * @return the player1
	 */
	public Joueur getPlayer1() {
		return player1;
	}

	/**
	 * @param player1
	 *            the player1 to set
	 */
	public void setPlayer1(Joueur player1) {
		this.player1 = player1;
	}

	/**
	 * @return the player2
	 */
	public Joueur getPlayer2() {
		return player2;
	}

	/**
	 * @param player2
	 *            the player2 to set
	 */
	public void setPlayer2(Joueur player2) {
		this.player2 = player2;
	}

	/**
	 * @return the currentPlayer
	 */
	public Joueur getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer
	 *            the currentPlayer to set
	 */
	public void setCurrentPlayer(Joueur currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the gamePane
	 */
	public AnchorPane getGamePane() {
		return gamePane;
	}

	/**
	 * @param gamePane
	 *            the gamePane to set
	 */
	public void setGamePane(AnchorPane gamePane) {
		this.gamePane = gamePane;
	}

	/**
	 * @return the root
	 */
	public BorderPane getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(BorderPane root) {
		this.root = root;
	}

	private String timeToString(long t) {
		String s = "";
		if (t > 3600000) {
			long h = t / 3600000;
			s += h + "h ";
			t -= h * 3600000;
		}
		if (t > 60000) {
			long m = t / 60000;
			s += m + "m ";
			t -= m * 60000;
		}
		if (t > 1000) {
			long sec = t / 1000;
			s += sec + "s ";
			t -= sec * 1000;
		}
		if (t > 0) {
			s += t + "ms";
		}
		return s;
	}

	public ChoiceController getChoiceController() {
		return choiceController;
	}

	public void setChoiceController(ChoiceController choiceController) {
		this.choiceController = choiceController;
	}

}
