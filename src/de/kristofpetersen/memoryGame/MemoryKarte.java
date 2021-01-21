package de.kristofpetersen.memoryGame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
public class MemoryKarte extends Button{
	private int bildID;
	private ImageView bildVorne, bildHinten;
	private int bildPos;
	private boolean umgedreht;
	private boolean nochImSpiel;
	private MemoryFeld spielfeld;
	
	class KartenListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			if ((!nochImSpiel) || (spielfeld.zugErlaubt()==false)) {
				return;
			}
			if (!umgedreht) {
				setGraphic(bildVorne);
				umgedreht = true;
				spielfeld.karteOeffnen(MemoryKarte.this);
			}
		}
	}
	
	public MemoryKarte(String vorne, int bildID, MemoryFeld spielfeld) {
		bildVorne = new ImageView(vorne);
		bildHinten = new ImageView("grafiken/back.jpg");
		setGraphic(bildHinten);
		this.bildID = bildID;
		umgedreht = false;
		nochImSpiel = true;
		this.spielfeld = spielfeld;
		setOnAction(new KartenListener());
	}
	
	public void rueckseiteZeigen(boolean rausnehmen) {
		if (rausnehmen) {
			setGraphic(new ImageView("grafiken/aufgedeckt.jpg"));
			nochImSpiel = false;
		}
		else {
			setGraphic(bildHinten);
			umgedreht =false;
		}
	}
	
	public int getBildID() {
		return bildID;
	}
	
	public int getBildPos() {
		return bildPos;
	}
	
	public void setBildPos(int bildPos) {
		this.bildPos = bildPos;
	}
	
	public boolean isUmgedreht() {
		return umgedreht;
	}
	
	public boolean isNochImSpiel() {
		return nochImSpiel;
	}
	
	public void vorderseiteZeigen() {
		setGraphic(bildVorne);
		umgedreht = true;
	}
}
