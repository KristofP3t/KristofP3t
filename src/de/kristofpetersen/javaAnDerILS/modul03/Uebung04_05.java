package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

public class Uebung04_05 {
	static int produkt (int a, int b) {
		return (a*b);
	}
	static int quadrat(int x) {
		return produkt(x, x);
	}
	public static void main(String[] args) {
		int eingabe = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein:"));
		System.out.println("Das Produkt dieser Zahl ist " + quadrat(eingabe));
		System.exit(0);

	}

}
