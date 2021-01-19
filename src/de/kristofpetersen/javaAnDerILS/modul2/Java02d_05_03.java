package de.kristofpetersen.javaAnDerILS.modul2;
/*
 * Typecasting
 */
public class Java02d_05_03 {

	public static void main(String[] args) {
		//Vereinbarung der Variablen
		int intVariable1, intVariable2;
		double doubleVariable1, doubleVariable2, doubleVariable3;
		
		//Wertzuweisung
		intVariable1 = 10;
		doubleVariable1 = 10;
		intVariable2 = 3;
		doubleVariable2 = 3;
		
		//beide double werden zum int, das Ergebnis wird falsch
		//bitte jeweils in einer Zeile eingeben
		doubleVariable3 = (int)doubleVariable1 / (int) doubleVariable2;
		System.out.println("(int) double / (int) double = " + doubleVariable3);
		
		//Ein int wird zum double, das Ergebnis stimmt
		doubleVariable3 = (double) intVariable1 / intVariable2;
		System.out.println("(double) int / int = " + doubleVariable3);
		
		//noch einmal zur Kontrolle ohne Casting
		//das Ergebnis wird falsch
		doubleVariable3 = intVariable1 / intVariable2;
		System.out.println("int / int = " + doubleVariable3);
		
		//der gesamte Ausdruck wird gecastet
		doubleVariable3 = (double)(intVariable1 / intVariable2);
		System.out.println("(double) (int / int) = " + doubleVariable3);

	}

}
