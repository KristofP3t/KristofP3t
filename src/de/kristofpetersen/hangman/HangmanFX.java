package de.kristofpetersen.hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HangmanFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage meineStage) throws Exception {
		FXMLLoader meinLoader = new FXMLLoader(getClass().getResource("sb_hangman.fxml"));
		Parent root = meinLoader.load();
		FXMLController meinController = meinLoader.getController();
		meinController.setStage(meineStage);
		
		Scene meineScene = new Scene(root, 350, 450);
		meineStage.setTitle("Hangman");
		meineStage.setScene(meineScene);
		
		meineStage.show();
		
	}

}
