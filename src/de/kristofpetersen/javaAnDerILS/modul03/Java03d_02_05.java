package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * switch ... case
 */
public class Java03d_02_05 {

	public static void main(String[] args) {
		String essenEinlesen;
		char essenWahl;
		
		System.out.println("Sie haben folgende Auswahl: \n");
		System.out.println("a Schweineschnitzel mit Nudeln");
		System.out.println("b Wiener Schnitzel mit Pommes");
		System.out.println("c Vegetarische Hackbällchen mit Reis\n");
		
		essenEinlesen = JOptionPane.showInputDialog("Bitte treffen Sie Ihre Wahl: ");
		essenWahl = essenEinlesen.charAt(0);
		
		switch (essenWahl) {
		case 'a': 
			System.out.println("Sie haben Schweineschnitzel mit Nudeln gewählt!");
			System.out.println("Kommt sofort.");
			break;
		case 'b':
			System.out.println("Sie haben Wiener Schnitzel mit Pommes gewählt!");
			System.out.println("Das dauert einen Moment!");
			break;
		case 'c':
			System.out.println("Sie haben Vegetarische Hackbällchen mit Reis gewählt!");
			System.out.println("Einen Augenblick!");
			break;
		default:
			System.out.println("Sie haben keine gültige Auswahl getroffen!");
			System.out.println("Dann gibt es eben nichts!");
		}
		System.exit(0);
	}

}
