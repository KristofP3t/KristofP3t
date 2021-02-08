package de.kristofpetersen.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Score extends Stage{
	private Stage owner;
	private GridPane rootNode;
	private Listenelement[] bestenListe;
	private int anzahl;
	
	private int punkte;
	private String dateiName;
	
	class Listenelement implements Comparable<Listenelement>{
		private int listePunkte;
		private String listeName;
		
		public Listenelement(int listePunkte, String listeName) {
			setzeEintrag(listePunkte, listeName);
		}

		@Override
		public int compareTo(Listenelement tempEintrag) {
			if(this.listePunkte < tempEintrag.listePunkte)
				return 1;
			if (this.listePunkte > tempEintrag.listePunkte)
				return -1;
			else 
				return 0;
		}
		
		private void setzeEintrag(int listePunkte, String listeName) {
			this.listeName = listeName;
			this.listePunkte = listePunkte;
		}
		
		public int getListePunkte() {
			return listePunkte;
		}
		
		public String getListeName() {
			return listeName;
		}
		
	}
	
	public Score(Stage fenster) {
		super();
		dateiName = "score.dat";
		anzahl = 10;
		bestenListe = new Listenelement[anzahl];
		for(int index = 0; index < anzahl; index++)
			bestenListe[index] = new Listenelement(0, "Nobody");
		owner = fenster;
		File dateiTest = new File(dateiName);
		if(dateiTest.exists())
			datenlesen();
		initOwner(fenster);
		rootNode = new GridPane();
		Scene meineScene = new Scene(rootNode, 300, 300);
		setScene(meineScene);
		initModality(Modality.WINDOW_MODAL);
		
		loeschePunkte();
	}

	public int veraenderePunkte(int punkte) {
		this.punkte = this.punkte + punkte;
		return this.punkte;
	}
	
	private void loeschePunkte() {
		punkte = 0;
	}
	
	public boolean neuerEintrag() {
		String tempName;
		if (punkte > bestenListe[anzahl - 1].getListePunkte()) {
			TextInputDialog meinDialog = new TextInputDialog();
			meinDialog.setTitle("Gewonnen");
			meinDialog.setHeaderText("Herzlichen Glückwunsch");
			meinDialog.setContentText("Bitte geben Sie Ihren Namen ein: ");
			meinDialog.initOwner(owner);
			Optional<String> dialogEingabe = meinDialog.showAndWait();
			if (dialogEingabe.isPresent()) {
				tempName = dialogEingabe.get();
			}
			else {
				tempName = "Max Mustermann";
			}
			bestenListe[anzahl-1].setzeEintrag(punkte, tempName);
			Arrays.sort(bestenListe);
			datenSchreiben();
			return true;
		}
		else {
			return false;
		}
	}

	public void listeZeigen() {
		setTitle("Bestenliste");
		rootNode.add(new Label("Punkte"), 0, 0);
		rootNode.add(new Label("Name"), 1, 0);
		for (int i = 0; i < anzahl; i++) {
			rootNode.add(new Label(Integer.toString(bestenListe[i].getListePunkte())), 0, i + 1);
			rootNode.add(new Label(bestenListe[i].getListeName()), 1, i+1);
		}
		showAndWait();
	}
	
	private void datenSchreiben() {
		try (RandomAccessFile datei = new RandomAccessFile(dateiName, "rw")){
			for (int i = 0; i < anzahl; i++) {
				datei.writeInt(bestenListe[i].getListePunkte());
				datei.writeUTF(bestenListe[i].getListeName());
			}
		} 
		catch (IOException e) {
			System.out.println("Beim Schreiben der Bestenliste ist ein Problem aufgetreten");		
		}
	}
	
	public void datenlesen() {
		String tempName;
		int tempPunkte;
		try(RandomAccessFile datei = new RandomAccessFile(dateiName, "r")){
			for (int i = 0; i < anzahl; i++) {
				tempPunkte = datei.readInt();
				tempName = datei.readUTF();
				bestenListe[i].setzeEintrag(tempPunkte, tempName);
			}
		} 
		catch (IOException e) {
			System.out.println("Beim Laden der Bestenliste ist ein Problem aufgetreten");
		}
	}
}


