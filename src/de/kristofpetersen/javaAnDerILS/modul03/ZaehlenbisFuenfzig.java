package de.kristofpetersen.javaAnDerILS.modul03;

public class ZaehlenbisFuenfzig {
/*
 * Das Programm zählt bis Fünfzig und setzt hinter jeder Zahl ein Komma.
 * Hinter der letzten Zahl soll kein Komma stehen, daher verwende ich in der If-Abfrage "echt kleiner" anstatt "kleiner Gleich".  
 */
	public static void main(String[] args) {
		for (int i = 1; i < 50; i++) {
			System.out.print(i + ",");
		}
		System.out.println(50);

	}

}
