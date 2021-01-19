package de.kristofpetersen.javaAnDerILS.modul03;
/*
 * Der Schleifenzähler wird mit 1 initialisiert.
 * In der Schleife wird der Zähler verdoppelt und um 1 inkrementiert.
 * Die Schleife hat 100 Durchläufe. 
 */
public class DoppelteWerte {
	public static void main(String[] args) {
		int zahler = 1;
		while (zahler <= 100) {
			System.out.println(zahler * 2);
			zahler++;
		}

	}

}
