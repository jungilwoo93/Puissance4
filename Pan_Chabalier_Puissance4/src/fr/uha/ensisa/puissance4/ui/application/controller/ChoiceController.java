package fr.uha.ensisa.puissance4.ui.application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChoiceController implements Initializable {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startButton.setDisable(true);
	}
	
	
	public void tooglePlayer1TypeChoice() {
		if(player1ChoiceIA.isFocused()) {
			player1ChoiceHuman.setSelected(false);
		}
		if(player1ChoiceHuman.isFocused()) {
			player1ChoiceIA.setSelected(false);
		}
	}
	
	public void tooglePlayer2TypeChoice() {
		if(player2ChoiceIA.isFocused()) {
			player2ChoiceHuman.setSelected(false);
		}
		if(player2ChoiceHuman.isFocused()) {
			player2ChoiceIA.setSelected(false);
		}
	}
	
	//Getters and setters

	/**
	 * @return the player1HumanName
	 */
	public TextField getPlayer1HumanName() {
		return player1HumanName;
	}

	/**
	 * @param player1HumanName the player1HumanName to set
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
	 * @param player1ChoiceHuman the player1ChoiceHuman to set
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
	 * @param player1ChoiceIA the player1ChoiceIA to set
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
	 * @param player1AlgoIA the player1AlgoIA to set
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
	 * @param player1iaLvl the player1IALvl to set
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
	 * @param player2HumanName the player2HumanName to set
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
	 * @param player2ChoiceHuman the player2ChoiceHuman to set
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
	 * @param player2ChoiceIA the player2ChoiceIA to set
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
	 * @param player2AlgoIA the player2AlgoIA to set
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
	 * @param player2iaLvl the player2IALvl to set
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
	 * @param startButton the startButton to set
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
	 * @param warningStartLabel the warningStartLabel to set
	 */
	public void setWarningStartLabel(Text warningStartLabel) {
		this.warningStartLabel = warningStartLabel;
	}
	
	
}
