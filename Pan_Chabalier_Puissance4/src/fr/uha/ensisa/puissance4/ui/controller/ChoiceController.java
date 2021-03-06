package fr.uha.ensisa.puissance4.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.uha.ensisa.puissance4.data.Humain;
import fr.uha.ensisa.puissance4.data.IA;
import fr.uha.ensisa.puissance4.data.Joueur;
import fr.uha.ensisa.puissance4.ui.GUI;
import fr.uha.ensisa.puissance4.util.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ChoiceController implements Initializable {

	private Joueur player1;
	private Joueur player2;
	private boolean gameReady = false;

	@FXML
	private AnchorPane choicePane;

	@FXML
	private TextField player1HumanName;

	@FXML
	private CheckBox player1ChoiceHuman;

	@FXML
	private CheckBox player1ChoiceIA;

	@FXML
	private Slider player1AlgoIA;

	@FXML
	private TextField player1IALvl;

	@FXML
	private TextField player2HumanName;

	@FXML
	private CheckBox player2ChoiceHuman;

	@FXML
	private CheckBox player2ChoiceIA;

	@FXML
	private Slider player2AlgoIA;

	@FXML
	private TextField player2IALvl;

	@FXML
	private Button startButton;

	@FXML
	private Text warningStartLabel;
	
	private BorderPane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startButton.setDisable(true); //Disable to not allow user to start game without choosing players
	}

	/**
	 * User player choices are allowed with checkbox. this method block multiple choice of player type. 
	 * User can check IA and Human at the same time
	 * Used only by player 1 checkboxes
	 * @see #tooglePlayer2TypeChoice()
	 */
	public void tooglePlayer1TypeChoice() { 
		if (player1ChoiceIA.isFocused()) {
			player1ChoiceHuman.setSelected(false);
		}
		if (player1ChoiceHuman.isFocused()) {
			player1ChoiceIA.setSelected(false);
		}
		checkAllPlayerChoosed();
	}

	/**
	 * User player choices are allowed with checkbox. this method block multiple choice of player type. 
	 * User can check IA and Human at the same time
	 * Used only by player 2 checkboxes
	 * @see #tooglePlayer1TypeChoice()
	 */
	public void tooglePlayer2TypeChoice() {
		if (player2ChoiceIA.isFocused()) {
			player2ChoiceHuman.setSelected(false);
		}
		if (player2ChoiceHuman.isFocused()) {
			player2ChoiceIA.setSelected(false);
		}
		checkAllPlayerChoosed();
	}

	/**
	 * Check if all player type is choosed, and if true, enable the start button
	 * Invoked by players type checkboxes
	 * @return true if one checkbox is check for each player
	 */
	public boolean checkAllPlayerChoosed() {
		if ((player1ChoiceIA.isSelected() || player1ChoiceHuman.isSelected())
				&& (player2ChoiceIA.isSelected() || player2ChoiceHuman.isSelected())) {
			startButton.setDisable(false);
			warningStartLabel.setVisible(false);
		} else {
			startButton.setDisable(true);
			warningStartLabel.setVisible(true);
		}
		return false;
	}

	/**
	 * Prepare the game by creating players and launching the game view with theses players
	 * If IA values are not changed, by default, algorithm is Min-Max, difficulty 1
	 * Invoked by @see #startButton
	 */
	public void startGame() {
		if (player1ChoiceIA.isSelected()) { 
			String nom = Constantes.IA_NAMES[(int) Math.floor(Math.random() * Constantes.IA_NAMES.length)];
			int algoIA1 = (int) this.player1AlgoIA.getValue();
			try { //is the field player1IALvl is empty, catching the exception and give to IA difficulty lvl 1
				this.player1 = new IA(nom, 1, algoIA1, Integer.parseInt(this.player1IALvl.getText()));
			} catch (NumberFormatException nbE) {
				this.player1 = new IA(nom, 1, algoIA1, 1);
			}
		} else {
			String nom;
			if (player1HumanName.getText().length() == 0) { //if player name is not provided, by default will be player 1
				nom = "Player 1";
			} else {
				nom = player1HumanName.getText();
			}
			this.player1 = new Humain(nom, 1);
		}
		if (player2ChoiceIA.isSelected()) {
			String nom = Constantes.IA_NAMES[(int) Math.floor(Math.random() * Constantes.IA_NAMES.length)];
			int algoIA2 = (int) this.player2AlgoIA.getValue();
			try {
				this.player2 = new IA(nom, 2, algoIA2, Integer.parseInt(this.player2IALvl.getText()));
			} catch (NumberFormatException nbE) {
				this.player2 = new IA(nom, 2, algoIA2, 1);
			}
		} else {
			String nom;
			if (player2HumanName.getText().length() == 0) {
				nom = "Player 2";
			} else {
				nom = player2HumanName.getText();
			}
			this.player2 = new Humain(nom, 2);
		}

		this.gameReady = true;
		launchGame(player1, player2);
	}

	/**
	 * launch the game view and give to it the two players
	 * @param j1 player 1
	 * @param j2 player 2
	 */
	public void launchGame(Joueur j1, Joueur j2) {
		try {
			FXMLLoader loader = new FXMLLoader();
			//Load the view which manage the game
			loader.setLocation(GUI.class.getResource("view/GameView.fxml"));
			AnchorPane gameView = (AnchorPane) loader.load();

			
			GameController gameController = loader.getController();
			//After getting the controler of the game view, we set player as created in choice view
			gameController.setPlayer1(j1);
			gameController.setPlayer2(j2);
			gameController.setCurrentPlayer(j1);
			
			//we remove choice panel to replace it by the game panel
			choicePane.setVisible(false);
			root.getChildren().remove(choicePane);
			root.setCenter(gameView);
			
			gameController.setChoiceController(this);
			gameController.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters and setters

	/**
	 * @return the player1HumanName
	 */
	public TextField getPlayer1HumanName() {
		return player1HumanName;
	}

	/**
	 * @param player1HumanName
	 *            the player1HumanName to set
	 */
	public void setPlayer1HumanName(TextField player1HumanName) {
		this.player1HumanName = player1HumanName;
	}

	/**
	 * @return the player1ChoiceHuman
	 */
	public CheckBox getPlayer1ChoiceHuman() {
		return player1ChoiceHuman;
	}

	/**
	 * @param player1ChoiceHuman
	 *            the player1ChoiceHuman to set
	 */
	public void setPlayer1ChoiceHuman(CheckBox player1ChoiceHuman) {
		this.player1ChoiceHuman = player1ChoiceHuman;
	}

	/**
	 * @return the player1ChoiceIA
	 */
	public CheckBox getPlayer1ChoiceIA() {
		return player1ChoiceIA;
	}

	/**
	 * @param player1ChoiceIA
	 *            the player1ChoiceIA to set
	 */
	public void setPlayer1ChoiceIA(CheckBox player1ChoiceIA) {
		this.player1ChoiceIA = player1ChoiceIA;
	}

	/**
	 * @return the player1AlgoIA
	 */
	public Slider getPlayer1AlgoIA() {
		return player1AlgoIA;
	}

	/**
	 * @param player1AlgoIA
	 *            the player1AlgoIA to set
	 */
	public void setPlayer1AlgoIA(Slider player1AlgoIA) {
		this.player1AlgoIA = player1AlgoIA;
	}

	/**
	 * @return the player1IALvl
	 */
	public TextField getPlayer1IALvl() {
		return player1IALvl;
	}

	/**
	 * @param player1iaLvl
	 *            the player1IALvl to set
	 */
	public void setPlayer1IALvl(TextField player1iaLvl) {
		player1IALvl = player1iaLvl;
	}

	/**
	 * @return the player2HumanName
	 */
	public TextField getPlayer2HumanName() {
		return player2HumanName;
	}

	/**
	 * @param player2HumanName
	 *            the player2HumanName to set
	 */
	public void setPlayer2HumanName(TextField player2HumanName) {
		this.player2HumanName = player2HumanName;
	}

	/**
	 * @return the player2ChoiceHuman
	 */
	public CheckBox getPlayer2ChoiceHuman() {
		return player2ChoiceHuman;
	}

	/**
	 * @param player2ChoiceHuman
	 *            the player2ChoiceHuman to set
	 */
	public void setPlayer2ChoiceHuman(CheckBox player2ChoiceHuman) {
		this.player2ChoiceHuman = player2ChoiceHuman;
	}

	/**
	 * @return the player2ChoiceIA
	 */
	public CheckBox getPlayer2ChoiceIA() {
		return player2ChoiceIA;
	}

	/**
	 * @param player2ChoiceIA
	 *            the player2ChoiceIA to set
	 */
	public void setPlayer2ChoiceIA(CheckBox player2ChoiceIA) {
		this.player2ChoiceIA = player2ChoiceIA;
	}

	/**
	 * @return the player2AlgoIA
	 */
	public Slider getPlayer2AlgoIA() {
		return player2AlgoIA;
	}

	/**
	 * @param player2AlgoIA
	 *            the player2AlgoIA to set
	 */
	public void setPlayer2AlgoIA(Slider player2AlgoIA) {
		this.player2AlgoIA = player2AlgoIA;
	}

	/**
	 * @return the player2IALvl
	 */
	public TextField getPlayer2IALvl() {
		return player2IALvl;
	}

	/**
	 * @param player2iaLvl
	 *            the player2IALvl to set
	 */
	public void setPlayer2IALvl(TextField player2iaLvl) {
		player2IALvl = player2iaLvl;
	}

	/**
	 * @return the startButton
	 */
	public Button getStartButton() {
		return startButton;
	}

	/**
	 * @param startButton
	 *            the startButton to set
	 */
	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}

	/**
	 * @return the warningStartLabel
	 */
	public Text getWarningStartLabel() {
		return warningStartLabel;
	}

	/**
	 * @param warningStartLabel
	 *            the warningStartLabel to set
	 */
	public void setWarningStartLabel(Text warningStartLabel) {
		this.warningStartLabel = warningStartLabel;
	}

	/**
	 * @return the choicePane
	 */
	public AnchorPane getChoicePane() {
		return choicePane;
	}

	/**
	 * @param choicePane
	 *            the choicePane to set
	 */
	public void setChoicePane(AnchorPane choicePane) {
		this.choicePane = choicePane;
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

	public boolean isGameReady() {
		return gameReady;
	}

	public void setGameReady(boolean gameReady) {
		this.gameReady = gameReady;
	}

	public void setRoot(BorderPane rootLayout) {
		this.root = rootLayout;
	}
}
