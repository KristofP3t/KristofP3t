package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Eine Methode mit Argument
 */
public class Java03d_04_07 {
	static int quadrat(int zahl) {
		int ergebnis;
		ergebnis = zahl * zahl;
		return ergebnis;
	}

	public static void main(String[] args) {
		int einVariable = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein."));
		System.out.println("Das Quadrat der Zahl " + einVariable + " ist: " + quadrat(einVariable));
		System.exit(0);

	}

}
