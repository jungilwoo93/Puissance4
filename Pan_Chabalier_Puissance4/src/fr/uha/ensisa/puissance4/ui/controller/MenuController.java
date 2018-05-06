package fr.uha.ensisa.puissance4.ui.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MenuController implements Initializable {

	@FXML
	private MenuItem menuClose;
	
	@FXML
	private MenuItem menuTicket;
	
	@FXML
	private MenuItem menuGit;
	
	@FXML
	private MenuItem menuWiki;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Add on menu item the handler to manage actions
		menuClose.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}

		});
		
		menuTicket.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("https://git.chabalier.com/snowert/puissance4/issues"));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		menuGit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("https://git.chabalier.com/snowert/puissance4"));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		
		menuWiki.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop()
								.browse(new URI("https://git.chabalier.com/snowert/puissance4/wiki"));
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
	}
}
