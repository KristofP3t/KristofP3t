package de.kristofpetersen.mediaplayer;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FXMLController {
	//für die Bühne
	private Stage meineStage;
	//für den Player
	private MediaPlayer mediaplayer;
	
	//für die MediaView
	@FXML private MediaView mediaview;
	//für die ImageView mit dem Symbol
	@FXML private ImageView symbol;
	//für das Listenfeld
	@FXML private ListView<String> liste;
	
	//die Methode setzt die Bühne auf den übergebenen Wert
	public void setMeineStage(Stage meineStage) {
		this.meineStage = meineStage;
	}
	
	//die Methode zum Laden
	@FXML protected void ladenKlick(ActionEvent event) {
		//eine Instanz der Klasse FileChooser erzeugen
		FileChooser oeffnenDialog = new FileChooser();
		//den Titel für den Dialog setzen
		oeffnenDialog.setTitle("Datei öffnen");
		//den Filter setzen
		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("Audiodateien", "*.mp3"));
		oeffnenDialog.getExtensionFilters().add(new ExtensionFilter("Videodateien", "*.mp4"));
		//den Startordner auf den Benutzerordner setzen
		oeffnenDialog.setInitialDirectory(new File(System.getProperty("user.home")));
		
		//den öffnendialog anzeigen und das Ergebnis beschaffen
		File datei = oeffnenDialog.showOpenDialog(meineStage);
		//wurde eine Datei ausgewählt?
		if(datei != null)
			//dann über eine eigene Methode laden
			dateiLaden(datei);
	}

	//die Methode zum Stoppen
	@FXML protected void stoppKlick(ActionEvent event) {
		//gibt es überhaupt einen Mediaplayer?
		if(mediaplayer != null)
			//dann anhalten
			mediaplayer.stop();
	}
	
	//die Methode für die Pause
	@FXML protected void pauseKlick(ActionEvent event) {
		//gibt es überhaupt einen Mediaplayer?
		if(mediaplayer != null)
			//dann unterbrechen
			mediaplayer.pause();
	}
	
	//die Methode für die Wiedergabe
	@FXML protected void playKlick(ActionEvent event) {
		//gibt es überhaupt einen Mediaplayer
		if (mediaplayer != null)
			//dann wiedergeben
			mediaplayer.play();
	}
	
	//die Methode für das Ein-und Ausschalten der Lautstärke
	@FXML protected void lautsprecherKlick(ActionEvent event) {
		String dateiname;
		//gibt es überhaupt einen Mediaplayer?
		if(mediaplayer != null) {
			//ist die lautstärke 0?
			if (mediaplayer.getVolume() == 0) {
				//dann auf 100 setzen
				mediaplayer.setVolume(100);
				//und das "normale" Icon setzen
				dateiname = "icons/mute.gif";
			}
			else {
				//sonst auf 0 setzen
				mediaplayer.setVolume(0);
				//und das durchgestrichene Symbol setzen
				dateiname = "icons/mute_off.gif";
			}
			//das Bild erzeugen und anzeigen
			File bilddatei = new File(dateiname);
			Image bild = new Image(bilddatei.toURI().toString());
			symbol.setImage(bild);
		}
	}
	
	//die Methode zum beenden
	@FXML protected void beendenKlick(ActionEvent event) {
		Platform.exit();
	}
	
	//die Methode lädt eine Datei
	private void dateiLaden(File datei) {
		//läuft schon eine Wiedergabe?
		if (mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING) {
			//dann anhalten
			mediaplayer.stop();
		}
		//das Medium, den Mediaplayer und die MediaView erzeugen
		try {
			Media medium = new Media(datei.toURI().toString());
			mediaplayer = new MediaPlayer(medium);
			mediaview.setMediaPlayer(mediaplayer);
			//die Wiedergabe starten
			mediaplayer.play();
			
			//den Pfad in das Listenfeld eintragen
			liste.getItems().add(datei.toString());
			//und die Titelleiste anpassen
			meineStage.setTitle("JavaFX Multimedia-Player " + datei.toString());
		} catch (Exception e) {
			Alert meinDialog = new Alert(AlertType.INFORMATION, "Beim Laden hat es ein Problem gegeben.\n" + e.getMessage());
			meinDialog.setHeaderText("Bitte beachten");
			meinDialog.initOwner(meineStage);
			//den Dialog anzeigen
			meinDialog.showAndWait();
		}
		
	}

}
