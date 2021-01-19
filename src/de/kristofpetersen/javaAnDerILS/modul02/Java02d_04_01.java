package de.kristofpetersen.javaAnDerILS.modul02;
/*
 *Die ganzzähligen Typen von Java
 */
public class Java02d_04_01 {

	public static void main(String[] args) {
		//Ausgabe der Wertebereiche
		//benutzt werden die Klassen für die jeweiligen
		//Datentypen
		System.out.println("Typ\tGröße\tWertebereich");
		//bitte jeweils in einer Zeile eingeben
		System.out.println("byte\t " + Byte.SIZE + "\t" + Byte.MIN_VALUE + " bis " + Byte.MAX_VALUE);
		System.out.println("byte\t " + Short.SIZE + "\t" + Short.MIN_VALUE + " bis " + Short.MAX_VALUE);
		System.out.println("byte\t " + Integer.SIZE + "\t" + Integer.MIN_VALUE + " bis " + Integer.MAX_VALUE);
		System.out.println("byte\t " + Long.SIZE + "\t" + Long.MIN_VALUE + " bis " + Long.MAX_VALUE);

	}

}
