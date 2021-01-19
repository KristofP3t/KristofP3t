package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * geschachtelte switch .. case-Konstruktion
 */
public class Java03d_02_06 {

	public static void main(String[] args) {
		String eingabe;
		char essenWahl, beilagenWahl;
		System.out.println("Sie haben folgende Auswahl: \n");
		System.out.println("a Schweineschnitzel");
		System.out.println("b Wiener Schnitzel\n");
		
		eingabe = JOptionPane.showInputDialog("Was möchten Sie essen?");
		essenWahl = eingabe.charAt(0);
		
		System.out.println("Sie können folgende Beilagen wählen: \n");
		System.out.println("c Pommes");
		System.out.println("d Reis\n");
		
		eingabe = JOptionPane.showInputDialog("Welche Beilage möchten Sie?");
		beilagenWahl = eingabe.charAt(0);
		
		switch (essenWahl) {
		case 'a':
			switch (beilagenWahl) {
			case 'c': 
				System.out.println("Sie haben Schweineschnitzel mit Pommes gewählt!");
				break;
			case 'd':
				System.out.println("Sie haben Schweineschnitzel mit Reis gewählt!");
				break;
			default: 
				System.out.println("Diese Beilage gibt es nicht!");
			}
			break;
		case 'b':
			switch (beilagenWahl) {
			case 'c':
				System.out.println("Sie haben Wiener Schnitzel mit Pommes gewählt!");
				break;
			case 'd':
				System.out.println("Sie haben WIener Schnitzel mit Reis gewählt!");
				break;
			default: 
				System.out.println("Diese Beilage gibt es nicht!");
			}
			break;
		default:
			System.out.println("Dieses Gericht steht nicht auf der Karte!");
		}
		System.exit(0);
	}	

}
