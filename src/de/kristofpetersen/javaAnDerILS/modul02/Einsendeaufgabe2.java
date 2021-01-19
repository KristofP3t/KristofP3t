package de.kristofpetersen.javaAnDerILS.modul02;
import javax.swing.JOptionPane;

public class Einsendeaufgabe2 {

	public static void main(String[] args) {
		int wert1, wert2;
		double ausgabe;
		
		wert1 = Integer.parseInt(JOptionPane.showInputDialog("Bitte Wert 1 eingeben"));
		wert2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte Wert 2 eingeben"));
		ausgabe = (double) wert1 / wert2;
		System.out.println(wert1 + " / " + wert2 + " = " + ausgabe);
		System.exit(0);
	}

}
