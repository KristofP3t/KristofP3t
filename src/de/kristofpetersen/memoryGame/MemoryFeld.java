package de.kristofpetersen.memoryGame;

import java.util.Arrays;
import java.util.Collections;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class MemoryFeld {
	//Aufgabe1:
	//Sofern alle Karten aufgedeckt sind, wird das Spiel beendet. Dafür wird
	//geprüft, ob spielBeendet == true ist
	//Aufgabe3:
	//Sofern der Timerhandler aufgerufen wird, während der Spieler am Schummeln ist (amSchummeln == true)
	//wird wird die Methode schummelnDeaktivieren() aufgerufen.
	//Während der Spieler nicht am schummeln ist, funktioniert das Programm wie zuvor.
	class TimerHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {	
			if(spielBeendet)
				Platform.exit();
			if(!amSchummeln) 
				karteSchliessen();

			if (amSchummeln) 
				schummelnDeaktivieren();
		}
	}
	//Aufgabe 3:
	//Für die Funktion des Buttons habe ich einen ButtonHandler erstellt. 
	//Beim anklicken des schummelnButton wird die Methode schummelnAktivieren() aufgerufen
	//Schummeln funktioniert nur, wenn der Spieler (==0) am Zug ist und
	//noch keine Karte umgedreht wurde
	class ButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			if(spieler==0 && umgedrehteKarten == 0)
				schummelnAktivieren();	
		}
		
	}
	
	private int spielstaerke;
	private Timeline timer;
	private MemoryKarte[] karten;
	private String[] bilder = {"grafiken/apfel.jpg", "grafiken/birne.jpg", "grafiken/blume.jpg", "grafiken/blume2.jpg", "grafiken/ente.jpg",
			"grafiken/fisch.jpg", "grafiken/fuchs.jpg", "grafiken/igel.jpg", "grafiken/kaenguruh.jpg", "grafiken/katze.jpg", "grafiken/kuh.jpg",
			"grafiken/maus1.jpg", "grafiken/maus2.jpg", "grafiken/maus3.jpg", "grafiken/melone.jpg", "grafiken/pilz.jpg", "grafiken/ronny.jpg",
			"grafiken/schmetterling.jpg", "grafiken/sonne.jpg", "grafiken/wolke.jpg", "grafiken/maus4.jpg"};
	private int menschPunkte, computerPunkte;
	//Aufgabe1: gewinnerLabel als Instanzvariable eingebaut
	//Aufgabe2: esZiehtLabel als Instanzvariable eingebaut
	private Label menschPunkeLabel, computerPunkteLabel, esZiehtLabel, gewinnerLabel;
	private int umgedrehteKarten;
	private MemoryKarte[] paar;
	private int spieler;
	private int [][] gemerkteKarten;
	//Aufgabe1: Boolean spielBeendet eingebaut. wird true, sobald alle 21 Paare aufgedeckt wurden
	//Aufgabe3: schummelnButton als Instanzvariable eingebaut
	// boolean amSchummeln eingebaut - dies Steuert, ob wir gerade schummeln, oder das
	//Schummeln beendet ist
	private Button schummelnButton;
	private boolean amSchummeln = false, spielBeendet = false;
	
	public MemoryFeld() {
		karten = new MemoryKarte[42];
		paar = new MemoryKarte[2];
		gemerkteKarten = new int[2][21];
		menschPunkte = 0;
		computerPunkte = 0;
		umgedrehteKarten = 0;
		spieler = 0;
		spielstaerke = 10;
		
		for(int aussen = 0; aussen < 2; aussen++)
			for(int innen = 0; innen < 21; innen++)
				gemerkteKarten[aussen][innen] = -1;
	}
	
	public FlowPane initGUI(FlowPane feld) {
		kartenZeichnen(feld);
		menschPunkeLabel = new Label();
		computerPunkteLabel = new Label();
		//Aufgabe1: Ein leeres Label wird erzeugt. Hier erscheint zum Ende des Spiels 
		//die Anzeige des Gewinners
		gewinnerLabel = new Label("");
		//Aufgabe2: Label zur Anzeige des Spielers, der an der Reihe ist
		esZiehtLabel = new Label();
		//Aufgabe3: Button zum Schummeln wird erstellt. Es wird setOnAction() gesetzt
		schummelnButton = new Button("Schummeln");
		schummelnButton.setOnAction(new ButtonHandler());
		menschPunkeLabel.setText(Integer.toString(menschPunkte));
		computerPunkteLabel.setText(Integer.toString(computerPunkte));
		//Aufgabe2:
		//Das Label wird initalisiert mit dem Spieler, der beginnt. Dieser wird über die
		//Methode spielerAmZugWechselLabel() ermittelt.
		esZiehtLabel.setText(spielerAmZugWechselLabel());
		GridPane tempGrid = new GridPane();
		tempGrid.add(new Label("Mensch: "), 0, 0);
		tempGrid.add(menschPunkeLabel, 1, 0);
		tempGrid.add(new Label("Computer: "), 0, 1);
		tempGrid.add(computerPunkteLabel, 1, 1);
		//Aufgabe2:
		//Die Label zur Anzeige werden dem Gridpane hinzugefügt
		tempGrid.add(new Label("Es zieht: "), 0, 2);
		tempGrid.add(esZiehtLabel, 1, 2);
		//Aufgabe3:
		//Der Schummeln-Button wird dem Gridpane hinzugefügt
		tempGrid.add(schummelnButton, 0, 3);
		//Aufgabe1:
		//Das Label zur Anzeige des Gewinners wird dem GridPane hinzugefügt
		tempGrid.add(gewinnerLabel, 1, 3);
		feld.getChildren().add(tempGrid);
		return feld;
	}
	public void karteOeffnen(MemoryKarte karte) {
		int kartenID, kartenPos;
		paar[umgedrehteKarten] = karte;
		kartenID = karte.getBildID();
		kartenPos = karte.getBildPos();
		
		if((gemerkteKarten[0][kartenID] == -1))
			gemerkteKarten[0][kartenID] = kartenPos;
		else
			if(gemerkteKarten[0][kartenID] != kartenPos)
				gemerkteKarten[1][kartenID] = kartenPos;
		umgedrehteKarten++;
		
		if(umgedrehteKarten == 2) {
			paarPruefen(kartenID);
			timer = new Timeline(new KeyFrame(Duration.millis(2000), new TimerHandler()));
			timer.play();
		}
		//Aufgabe1: 
		//Sobald 21 Punkte erreicht sind, wird der Gewinner ermittelt und angezeigt
		if ((computerPunkte + menschPunkte) == 21) {
			gewinnerAnzeigen();
			
		}
	}
	//Aufgabe1:
	//Es wird der Spieler mit mehr Punkten ermittelt. Der Rückgabewert ist ein String,
	//der im Label angezeigt wird
	private void gewinnerAnzeigen() {
		String text =  "Der Gewinner ist: ";
		if(menschPunkte > computerPunkte)
			text += "Der Mensch";
		else 
			text += "Der Computer";
		gewinnerLabel.setText(text);
		spielBeendet = true;
		timer = new Timeline(new KeyFrame(Duration.millis(5000), new TimerHandler()));
		timer.play();
	}

	public boolean zugErlaubt() {
		boolean erlaubt = true;
		if (spieler == 1) 
			erlaubt = false;
		if(umgedrehteKarten == 2)
			erlaubt = false;
		return erlaubt;
	}
	
	private void kartenZeichnen(FlowPane feld) {
		int count = 0;
		for(int i = 0; i <= 41; i++) {
			karten[i] = new MemoryKarte(bilder[count], count, this);
			if((i + 1) % 2 == 0)
				count++;
		}
		Collections.shuffle(Arrays.asList(karten));
		for(int i = 0; i <= 41; i++) {
			feld.getChildren().add(karten[i]);
			karten[i].setBildPos(i);
		}
	}
	

	
	private void paarPruefen(int kartenId) {
		if(paar[0].getBildID() == paar[1].getBildID()) {
			paarGefunden();
			gemerkteKarten[0][kartenId] = -2;
			gemerkteKarten[1][kartenId] = -2;
		}
	}
	
	private void paarGefunden() {
		if (spieler == 0) {
			menschPunkte ++;
			menschPunkeLabel.setText(Integer.toString(menschPunkte));
		}else {
			computerPunkte++;
			computerPunkteLabel.setText(Integer.toString(computerPunkte));
		}
	}

	private void karteSchliessen() {
		boolean raus = false;
		if(paar[0].getBildID() == paar[1].getBildID())
			raus = true;
		paar[0].rueckseiteZeigen(raus);
		paar[1].rueckseiteZeigen(raus);
		umgedrehteKarten = 0;
		if(raus == false)
			spielerWechseln();
		else
			//Aufgabe1: Es wird nun nur der Computerzug ausgeführt, solange 21 Punkte noch
			//nicht erreicht sind.
			if(spieler == 1 && (menschPunkte + computerPunkte)< 21)
				computerZug();
	}
	//Aufgabe2:
	//Beim Spielerwechsel wird der Text des esZiehtLabel geändert. Die Anzeige wird über 
	//spielerAmZugWechselLabel() ermittelt.
	private void spielerWechseln() {
		if (spieler == 0) {
			spieler = 1;
			esZiehtLabel.setText(spielerAmZugWechselLabel());
			computerZug();
		} else {
			spieler = 0;
			esZiehtLabel.setText(spielerAmZugWechselLabel());
		}
	}
	
	private void computerZug() {
		int kartenZaehler = 0;
		int zufall = 0;
		
		boolean treffer = false;
		if ((int) (Math.random() * spielstaerke) == 0) {
			
			while ((kartenZaehler < 21) && (treffer ==false)) {
				if ((gemerkteKarten[0][kartenZaehler] >= 0) && (gemerkteKarten[1][kartenZaehler] >= 0)){
					treffer = true;
					karten[gemerkteKarten[0][kartenZaehler]].vorderseiteZeigen();
					karteOeffnen(karten[gemerkteKarten[0][kartenZaehler]]);
					karten[gemerkteKarten[1][kartenZaehler]].vorderseiteZeigen();
					karteOeffnen(karten[gemerkteKarten[1][kartenZaehler]]);
				}
				kartenZaehler++;
			}
		}
		if (!treffer) {
			do {
				zufall = (int) (Math.random() * karten.length);
			} while (karten[zufall].isNochImSpiel() == false);
			karten[zufall].vorderseiteZeigen();
			karteOeffnen(karten[zufall]);
			do {
				zufall = (int) (Math.random() * karten.length); 
			} while ((karten[zufall].isNochImSpiel() == false || karten[zufall].isUmgedreht() == true));
			karten[zufall].vorderseiteZeigen();
			karteOeffnen(karten[zufall]);
		}
	}
	
	//Aufgabe2:
	//Der Aktive Spieler wird ermittelt. Die Ausgabe wird entsprechend angepasst. DIe Rückgabe
	//der Methode ist ein String des Spielers, der an der Reihe ist
	public String spielerAmZugWechselLabel() {
		if(spieler == 0) {
			return "Der Mensch";
		}else {
			return "Der Computer";
		}
	}
	//Aufgabe 3:
	//Die Methode schummelnAktivieren() dreht alle Karten um, die noch im Spiel sind. 
	//Der Status amSchummeln wird true gesetzt. Anschließend wird über ein Timer nach 3 sekunden
	//Die Methode schummelnDeaktivieren() aufgerufen.
	public void schummelnAktivieren() {
		for (MemoryKarte karte : karten) {
			if(karte.isNochImSpiel())
				karte.vorderseiteZeigen();
		}
		amSchummeln = true;
		timer = new Timeline(new KeyFrame(Duration.millis(3000), new TimerHandler()));
		timer.play();
	}
	//Aufgabe3:
	//Bei der Methode schummelnDeaktivieren() prüfe ich für alle Karten, ob sie noch im Spiel sind
	//und zeige die Rückseite an
	//Zum Schluss wird der amSchummeln - status wieder auf false gesetzt
	public void schummelnDeaktivieren() {
		for (MemoryKarte karte : karten) {
			if(karte.isNochImSpiel())
				karte.rueckseiteZeigen(false);
		}
		amSchummeln = false;
	}

}
