package de.kristofpetersen.einkommensrechner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class EinkommensrechnerFX extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage meineStage) throws Exception {
		FlowPane rootnote = new RechnerGUI().initGUI(new FlowPane());
		Scene meineScene = new Scene(rootnote, 800, 1000);
		meineStage.setTitle("Einkommensrechner");
		meineStage.setScene(meineScene);
		meineStage.show();
	}

}
