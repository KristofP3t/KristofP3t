package de.kristofpetersen.memoryGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MemoryFX extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage meineStage) throws Exception {
		FlowPane rootNode = new MemoryFeld().initGUI(new FlowPane());
		Scene meineScene = new Scene(rootNode, 500, 600);
		meineStage.setTitle("Memory");
		meineStage.setScene(meineScene);
		meineStage.setResizable(false);
		meineStage.show();
	}

}
