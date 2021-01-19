package de.kristofpetersen.javaAnDerILS.modul02;
/*
 * Ein komplexeres Beispiel für Variablen
 */
public class Java02d_03_04 {

	public static void main(String[] args) {
		// Vereinbarungen für Variablen
		int zahl1 = 1;
		int zahl2, zahl3, zahl4;
		
		zahl2 = 1 + 1;
		zahl3 = zahl2 * 5;
		
		System.out.println("zahl1 hat den Wert" + zahl1);
		System.out.println("zahl2 hat den Wert" + zahl2);
		System.out.println("zahl3 hat den Wert" + zahl3);
		
		//Ringtausch von Zahl1 und Zahl2
		zahl4 = zahl1;
		zahl1 = zahl2;
		zahl2 = zahl4;

		System.out.println("zahl1 hat den Wert" + zahl1);
		System.out.println("zahl2 hat den Wert" + zahl2);
	}

}
