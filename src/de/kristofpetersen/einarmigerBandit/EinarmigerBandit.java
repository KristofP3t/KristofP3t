package de.kristofpetersen.einarmigerBandit;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class EinarmigerBandit{
	private Button klickButton, aussteigenButton;
	private Label guthabenLabel;
	private int guthaben;
	private Label wuerfel1, wuerfel2, wuerfel3;
	private String[] moeglicheWuerfe = {"icons/eins.png","icons/zwei.png","icons/drei.png","icons/vier.png",
										"icons/fuenf.png","icons/sechs.png"};
	
	public EinarmigerBandit() {
		guthaben = 100;
	}
	
	public FlowPane initGUI(FlowPane flowPane) {
		feldZeichnen(flowPane);
		
		
		return flowPane;
	}

	class MeinListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if(event.getSource().equals(klickButton)) {
				rundeSpielen();
			}
			if(event.getSource().equals(aussteigenButton)) {
				ausSpielAussteigen();
			}
			
		}
		
	}
	//Zeichnet die GUI
	private void feldZeichnen(FlowPane flowPane) {
		
				
		wuerfel1 = new Label();
		wuerfel1.setGraphic(new ImageView("icons/eins.png"));
		flowPane.getChildren().add(wuerfel1);
		
		wuerfel2 = new Label();
		wuerfel2.setGraphic(new ImageView("icons/eins.png"));
		flowPane.getChildren().add(wuerfel2);
		
		wuerfel3 = new Label();
		wuerfel3.setGraphic(new ImageView("icons/eins.png"));
		flowPane.getChildren().add(wuerfel3);
		
		guthabenLabel = new Label("Dein Guthaben: " + guthaben + " EUR");
		flowPane.getChildren().add(guthabenLabel);
		flowPane.getChildren().add(new Label("                  "));
		MeinListener listener = new MeinListener();
		klickButton = new Button("Wetten");
		klickButton.setOnAction(listener);
		flowPane.getChildren().add(klickButton);
		flowPane.getChildren().add(new Label("                       "));
		aussteigenButton = new Button("Aussteigen");
		aussteigenButton.setOnAction(listener);
		flowPane.getChildren().add(aussteigenButton);
		
	}
	/*
	 * Spiel wird beendet, sobald der Spieler das Spiel beendet
	 */
	private void ausSpielAussteigen() {
		Alert meinAlert = new Alert(AlertType.CONFIRMATION, "Das Spiel wird beendet. Sie erhalten: " + guthaben + " EUR.");
		meinAlert.setHeaderText("Aus dem Spiel aussteigen?");
		meinAlert.showAndWait();
		if (meinAlert.getResult().equals(ButtonType.OK))
			Platform.exit();
	}
	/*
	 * Wenn der Spieler auf den Spielen-Button klickt, wird eine Runde gespielt.
	 * Fällt das Guthaben am Ende der Runde auf "0", so wird das Spiel beendet,
	 */
	private void rundeSpielen() {
		gameRoll();
		if (guthaben == 0) {
			Alert meinAlert = new Alert(AlertType.INFORMATION, "Sie haben kein Guthaben mehr. Das Spiel wird beendet.");
			meinAlert.setHeaderText("Verloren!");
			meinAlert.showAndWait();
			Platform.exit();
		}
	}
	//Wertet die  Ergebnisse aus, und aktualisiert die Punkteanzeige
	private void guthabenAktualisieren(int zahl1, int zahl2, int zahl3) {
		guthaben -=10;
		if (zahl1 == zahl2 && zahl2 == zahl3) {
			guthaben += 100;
		} else if (zahl1 == zahl2 || zahl1 == zahl3 || zahl2 == zahl3) {
			guthaben += 20;
		}
		guthabenLabel.setText("Dein Guthaben: " + guthaben + " EUR");
	}

	/*
	 * Ermittelt zufällige Würfelergebnisse
	 * und gibt die Werte an die guthabenAktualisieren-Methode weiter
	 */
	private void gameRoll() {
		int randomWuerfel1 = (int) (moeglicheWuerfe.length * Math.random());
		wuerfel1.setGraphic(new ImageView(moeglicheWuerfe[randomWuerfel1]));
		int randomWuerfel2 = (int) (moeglicheWuerfe.length * Math.random());
		wuerfel2.setGraphic(new ImageView(moeglicheWuerfe[randomWuerfel2]));
		int randomWuerfel3 = (int) (moeglicheWuerfe.length * Math.random());
		wuerfel3.setGraphic(new ImageView(moeglicheWuerfe[randomWuerfel3]));
		guthabenAktualisieren(randomWuerfel1, randomWuerfel2, randomWuerfel3);
	}

}
