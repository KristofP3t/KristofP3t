package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Zwei Methoden mit Rückgabe
 */
public class Java03d_04_03 {

	static int rueckgabe100() {
		return 100;
	}
	static int rueckgabe10() {
		return 10;
	}
	public static void main(String[] args) {
		int ergebnis;
		System.out.println("Die Methode Rückgabe100 liefert den Wert: " + rueckgabe100());
		System.out.println("Die Methode Rückgabe10 liefert den Wert: " + rueckgabe10());
		
		ergebnis = rueckgabe10() + rueckgabe100();
		System.out.println("Das Ergebnis ist: " + ergebnis);
		
		ergebnis = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie eine Zahl ein:"));
		if (ergebnis < rueckgabe100())
			System.out.println("Ihre Eingabe war kleiner als 100");
		else 
			System.out.println("Ihre Eingabe war größer oder gleich 100.");
		System.exit(0);

	}

}
