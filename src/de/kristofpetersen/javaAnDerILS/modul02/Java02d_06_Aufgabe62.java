package de.kristofpetersen.javaAnDerILS.modul02;
import javax.swing.JOptionPane;

public class Java02d_06_Aufgabe62 {

	public static void main(String[] args) {
		int eingabe;
		double eingabe1, eingabe2;
		
		eingabe = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie eine erste Zahl ein."));
		eingabe1 = Double.parseDouble(JOptionPane.showInputDialog("Geben Sie bitte eine zweite Zahl ein."));
		eingabe2 = Double.parseDouble(JOptionPane.showInputDialog("Geben Sie bitte eine dritte Zahl ein."));
		
		System.out.println("Ergebnis: " + (eingabe + eingabe1 + eingabe2));
		
		System.exit(0);

	}

}
