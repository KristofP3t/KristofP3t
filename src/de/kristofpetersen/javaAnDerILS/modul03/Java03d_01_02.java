package de.kristofpetersen.javaAnDerILS.modul03;
/*
 * Logisches NICHT
 */
public class Java03d_01_02 {

	public static void main(String[] args) {
		int zahl1 = 1;
		int zahl2 = 0;
		
		//ein direkter Vergleich von zwei Zahlen
		System.out.println("! (5 < 10) \t" + !(5<10));
		// ein Vergleich einer Zahl mit einer Variablen
		System.out.println("! (5 > " + zahl1 + " )\t" + !(5>zahl1));
		//Vergleich von zwe Variablen
		System.out.println("! (" + zahl1 + " == " + zahl2 + ")\t" + !(zahl1 == zahl2));
		System.out.println("! (" + zahl1 + " != " + zahl2 + ")\t" + !(zahl1 != zahl2));
	}

}
