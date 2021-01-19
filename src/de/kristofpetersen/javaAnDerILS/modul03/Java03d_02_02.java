package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * If-Verzweigung mit Anweisungsblock
 */
public class Java03d_02_02 {

	public static void main(String[] args) {
		int zahl;
		
		zahl = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein: "));
		
		if (zahl > 5) {
			System.out.println("Sie haben eine Zahl größer als 5 eingegeben");
		System.out.println("Die Different zwischen " + zahl + " und 5 ist " + (zahl - 5));
		}
		System.out.println("Die zahl war " + zahl);
		System.exit(0);
	}

}
