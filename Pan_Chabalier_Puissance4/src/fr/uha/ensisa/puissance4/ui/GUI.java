package fr.uha.ensisa.puissance4.ui;

import java.io.IOException;

import fr.uha.ensisa.puissance4.ui.controller.ChoiceController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ChoiceController choiceController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Puissance 4");
        this.primaryStage.getIcons().add(new Image(GUI.class.getResourceAsStream("ressources/icon.png")));

        initRootLayout();

        showChoiceOverview();
        
        
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("view/RootView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the choice overview inside the root layout.
     */
    public void showChoiceOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GUI.class.getResource("view/ChoiceView.fxml"));
            AnchorPane choiceView = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(choiceView);
            
            //get the choice view controller
            this.choiceController = loader.getController();
            this.choiceController.setRoot(rootLayout);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
	public static void main(String[] args) {
		launch(args);
	}

}
