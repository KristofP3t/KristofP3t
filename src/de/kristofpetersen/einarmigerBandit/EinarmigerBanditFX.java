package de.kristofpetersen.einarmigerBandit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class EinarmigerBanditFX extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage meineStage) throws Exception {
		FlowPane rootNode = new EinarmigerBandit().initGUI(new FlowPane());
		Scene meineScene = new Scene(rootNode, 240, 130);

		meineStage.setTitle("Einarmiger Bandit");
		meineStage.setScene(meineScene);
		meineStage.setResizable(false);
		meineStage.show();
		
	}

}
