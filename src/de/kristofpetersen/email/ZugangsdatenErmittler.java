package de.kristofpetersen.email;

import java.io.IOException;
import java.io.RandomAccessFile;
/*
 * Die Klasse enthält zwei private Instanzvariablen "benutzername" und "kennwort". Die Ermittlung der Daten
 * findet über die Klasse RandomAccessFile statt, welche die "nutzerdaten.bin" ausliest.
 * Über die getter kann auf die Werte zugegriffen werden.
 * Den Konstruktor habe ich protected gemacht, damit nur aus diesem Package auf das Passwort zugegriffen werden 
 * kann und nicht von außerhalb
 */
public class ZugangsdatenErmittler {
	
	private String benutzername;
	private String kennwort;
	
	protected ZugangsdatenErmittler() {

		try (RandomAccessFile dateiAccessFile = new RandomAccessFile("nutzerdaten.bin", "r")) {
				this.benutzername = dateiAccessFile.readUTF();
				this.kennwort = dateiAccessFile.readUTF();
		} catch (IOException e) {
			System.err.println("Beim Laden der Datei ist ein Problem aufgetreten");
		}
	}

	/**
	 * @return benutzername
	 */
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * @return kennwort
	 */
	public String getKennwort() {
		return kennwort;
	}
}
