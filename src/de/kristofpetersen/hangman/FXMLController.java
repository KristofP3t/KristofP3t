package de.kristofpetersen.hangman;

import java.io.File;
import java.io.RandomAccessFile;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLController {
	@FXML private ComboBox<String> auswahl;
	@FXML private Label ausgabeText;
	@FXML private Label anzVersuche;
	@FXML private Label punktAusgabe;
	@FXML private Canvas zeichenflaeche;
	private String[] zeichen = new String[26];
	private StringBuilder anzeige;
	private String suchwort;
	private int restDurchlaeufe;
	private int fehler;
	private GraphicsContext gc;
	private Score spielpunkte;
	private Stage meineStage;
	
	public void setStage(Stage meineStage) {
		this.meineStage = meineStage;
		spielpunkte = new Score(meineStage);
	}
	
	@FXML protected void beendenKlick() {
		Platform.exit();
	}
	
	@FXML protected void auswahlNeu(ActionEvent event) {
		pruefen(auswahl.getSelectionModel().getSelectedItem());
		gewinnerOderNicht();
	}

	@FXML void initialize() {
		int tempIndex = 0;
		restDurchlaeufe = 9;
		anzVersuche.setText(Integer.toString(restDurchlaeufe));
		
		for (char temp = 'a'; temp <= 'z'; temp++) {
			zeichen[tempIndex] = Character.toString(temp);
			tempIndex ++;
		}
			
		auswahl.getItems().addAll(zeichen);
		neuesWort();
		
		gc = zeichenflaeche.getGraphicsContext2D();
		
	}
	/*
	 * Wenn die Datei existiert, wird diese über eine RandomAccessFile gelesen.
	 * Die Wörter werden in das String [] woerter hinzugefügt. Das Array hat die Größe, die
	 * im ersten Eintrag der Datei eingetragen ist.
	 * Wenn die Datei nicht geladen werden kann, wird das zuvor im Kursmaterial verwendete Array weiterhin verwendet.
	 * Unabhängig davon, welche Grundlage die Einträge des Array haben, wird ein zufälliges Wort daraus ermittelt.
	 * Dabei gibt es keine Besonderheiten
	 */
	private void neuesWort() {
		int zufall = 0;
		String [] woerter;
		try (RandomAccessFile randomAccessFile = new RandomAccessFile(new File("woerter.bin"), "r")) {
			int anzahlWoerter = randomAccessFile.readInt();
			woerter = new String[anzahlWoerter];
			for(int i = 0; i < anzahlWoerter; i++) {
				woerter[i] = randomAccessFile.readUTF();
			}
			
		} catch (Exception e) {
			System.err.println("Die Datei wurde nicht gefunden");
			woerter = new String[]{"Test", "Automobil", "Versuch", "Hund", "Katze", "Ziege", "Maus", "Elefant", "Isopropylalkohol", "Schwimmbad"};
			
		}
		zufall = (int) (Math.random() * woerter.length);
		
		suchwort = new String(woerter[zufall]);
		anzeige = new StringBuilder(suchwort);
		
		for (int zeichen = 0; zeichen < suchwort.length(); zeichen ++) {
			anzeige.setCharAt(zeichen, '*');
		}
		ausgabeText.setText(anzeige.toString());
	}
	
	private void pruefen(String auswahlZeichen) {
		char zeichen;
		int treffer = 0;
		zeichen = auswahlZeichen.charAt(0);
		treffer = suchwort.toLowerCase().indexOf(zeichen);
		
		if (treffer < 0) {
			restDurchlaeufe--;
			anzVersuche.setText(Integer.toString(restDurchlaeufe));
			erhoeheFehler();
			punktAusgabe.setText(Integer.toString(spielpunkte.veraenderePunkte(-1)));
		}
		else {
			while (treffer >= 0) {
				anzeige.setCharAt(treffer, suchwort.charAt(treffer));
				treffer ++;
				treffer = suchwort.toLowerCase().indexOf(zeichen, treffer);
				punktAusgabe.setText(Integer.toString(spielpunkte.veraenderePunkte(5)));
			}
			ausgabeText.setText(anzeige.toString());
		}
		
	}
	
	private void gewinnerOderNicht() {
		boolean ende = false;
		
		gc.setLineWidth(1);
		if(restDurchlaeufe == 0) {
			gc.strokeText("Das gesuchte Wort war: " + suchwort, 20, 100);
			ende = true;
		}
		if (anzeige.toString().equals(suchwort)) {
			spielpunkte.veraenderePunkte(restDurchlaeufe * 10);
			gc.strokeText("Hurra, Sie haben gewonnen!", 20, 100);
			ende = true;
		}
		if (ende) {
			if (spielpunkte.neuerEintrag()) {
				spielpunkte.listeZeigen();
			}
			Platform.exit();
		}
	}
	
	private void erhoeheFehler() {
		fehler +=1;
		gc.setLineWidth(4);

		switch (fehler) {
		case 1: {
			gc.strokeLine(10, 10, 10, 200);
			break;
		}
		case 2: {
			gc.strokeLine(10, 10, 100, 10);
			break;
		}
		case 3: {
			gc.strokeLine(40, 10, 10, 40);
			break;
		}
		case 4: {
			gc.strokeLine(100, 10, 100, 50);
			break;
		}
		case 5: {
			gc.strokeLine(70, 50, 130, 50);
			break;
		}
		case 6: {
			gc.strokeLine(130, 50, 130, 110);
			break;
		}
		case 7: {
			gc.strokeLine(130, 110, 70, 110);
			break;
		}
		case 8: {
			gc.strokeLine(70, 110, 70, 50);
			break;
		}
		case 9: {
			gc.strokeLine(0, 200, 20, 200);
			break;
		}
	
		
	}


}
}
