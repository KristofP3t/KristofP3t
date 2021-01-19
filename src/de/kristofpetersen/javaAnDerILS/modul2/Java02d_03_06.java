package de.kristofpetersen.javaAnDerILS.modul2;
/*
 * Symbolische Konstanten
 */
public class Java02d_03_06 {

	public static void main(String[] args) {
		// Vereinbarung der Konstanten
		final int KONSTANTE = 10;
		//Vereinbarung der Variablen
		int zahl2 = 15;
		int ausgabe;
		
		System.out.println("KONSTANTE hat den Wert " + KONSTANTE);
		
		ausgabe = zahl2 + KONSTANTE;
		
		System.out.println("ausgabe hat den Wert " + ausgabe);
		
		//DAS GEHT NICHT:
		//"KONSTANTE = 5;"
		
	}

}
