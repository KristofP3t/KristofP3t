package de.kristofpetersen.javaAnDerILS.modul03;

import javax.swing.JOptionPane;

public class TaschenrechnerErgaenzt {
	static int addition(int x, int y) {
		return x+y;
	}
	static int subtraktion(int x, int y) {
		return x-y;
	}
	static int multiplikation ( int x, int y) {
		return x*y;
	}
	static double division (int x, int y) {
		return 1.0*x/y;
	}
	
	/*
	 * Es werden fürs potenzieren zwei int-Werte als Parameter übergeben. Es wird die Potenz als int-Wert von der Methode zurückgegeben.
	 * In der for-Schleife wird die Potenz berechnet. Sofern der Exponent "Null" ist, so wird als Ergebnis der Potenz 1 zurückgegeben.
	 */
	static int potenz ( int x, int y) {
		int diesePotenz = x;
		if (y == 0) {
			diesePotenz = 1; 
		} else {
			for (int i = 1; i < y; i++) {
				diesePotenz = diesePotenz*x;
			}
		}
		
		
		return diesePotenz;
	}
/*
 * Fürs potenzieren habe ich Switch-Case "5" hinzugefügt und die Beschreibung der Anweisung ergänzt.
 */
	public static void main(String[] args) {
		int zahl1, zahl2, rechenart;
		try {
		zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Bitte gib die erste (ganze) Zahl ein:"));
		zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte gib die zweite (ganze) Zahl ein:"));
		
		rechenart = Integer.parseInt(JOptionPane.showInputDialog("Wähle die Rechenart \n1 für +\n2 für -\n3 für /\n4 für *\n5 fürs potenzieren"));
		
		switch (rechenart) {
		case 1: {
			System.out.println("Die Summe von " + zahl1 + " und " + zahl2 + " ist " + addition(zahl1, zahl2));
			break;
		}
		case 2:
			System.out.println("Die Differenz von " + zahl1 + " und " + zahl2 + " ist " + subtraktion(zahl1, zahl2));
			break;
		case 3:
			System.out.println("Der Quotient von " + zahl1 + " und " + zahl2 + " ist " + division(zahl1, zahl2));
			break;
		case 4: 
			System.out.println("Das Produkt von " + zahl1 + " und " + zahl2 + " ist " + multiplikation(zahl1, zahl2));
			break;
		case 5: 
			System.out.println("Die Potenz der Basis " + zahl1 + " und dem Exponenten " + zahl2 + " lautet " + potenz(zahl1, zahl2));
			break;
		default:
			System.out.println("Diese Variante gibt es leider nicht.");
		}
		}
		catch (Exception e) {
			System.out.println("Bitte gib nur ganze Zahlen ein!");
		}
		System.exit(0);
		
	}

}
