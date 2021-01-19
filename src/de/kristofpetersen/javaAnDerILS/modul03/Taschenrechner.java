package de.kristofpetersen.javaAnDerILS.modul03;

import javax.swing.JOptionPane;

public class Taschenrechner {
	//Je Rechenart habe ich eine Methode erstellt, mit den eingabewerten als Parameter
	//Für die ersten Rechenarten genügt der Rückgabewert int, da wir int-Werte an die Methoden übergeben.
	static int addition(int x, int y) {
		return x+y;
	}
	static int subtraktion(int x, int y) {
		return x-y;
	}
	static int multiplikation ( int x, int y) {
		return x*y;
	}
	//Rückgabewert dieser Methode ist double, da diese Methode sonst auf ganze Zahlen abrunden würde. 
	//Da wir zwei int übergeben, habe ich vor dem Return noch ein double 1.0 multipliziert.
	static double division (int x, int y) {
		return 1.0*x/y;
	}
/*
 * Es werden vom Nutzer 3 Zahlen eingegeben. Die ersten dienen für die Berechnungen. Die dritte wählt die Rechenart aus.
 * Ich habe mich für "switch" entschieden, da wir 4 festgelegte Rechenoperationen haben. 
 * Als Argumente werden je Rechenoperationen zahl1 und zahl2 übergeben.
 * Mit dem Try-Catch-Block fange ich unerwartete Eingaben ab.
 */
	public static void main(String[] args) {
		int zahl1, zahl2, rechenart, ergebnis = 0;
		String wertName = "";
		try {
		zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Bitte gib die erste (ganze) Zahl ein:"));
		zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte gib die zweite (ganze) Zahl ein:"));
		
		rechenart = Integer.parseInt(JOptionPane.showInputDialog("Wähle die Rechenart \n1 für +\n2 für -\n3 für /\n4 für *"));
		
		switch (rechenart) {
		case 1: {
			ergebnis = addition(zahl1, zahl2);
			wertName = "Die Summe";
			break;
		}
		case 2:
			ergebnis = subtraktion(zahl1, zahl2);
			wertName = "Die Differenz";
			break;
		case 3:
			ergebnis = (int)division(zahl1, zahl2);
			wertName = "Der Quotient";
			break;
		case 4: 
			ergebnis = multiplikation(zahl1, zahl2);
			wertName = "Das Produkt";
			break;
		default:
			System.out.println("Diese Variante gibt es leider nicht.");
		}
		System.out.println(wertName + " von " + zahl1 + " und  " + zahl2 + " ist " + ergebnis);
		}
		catch (Exception e) {
			System.out.println("Bitte gib nur ganze Zahlen ein!");
		}
		
		System.exit(0);
		
	}

}
