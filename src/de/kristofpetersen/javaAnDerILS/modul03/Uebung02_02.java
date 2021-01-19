package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

public class Uebung02_02 {

	public static void main(String[] args) {
		int essenWahl;
		
		System.out.println("Bitte wählen Sie ein Essen aus: \n");
		System.out.println("1 Jägerschnitzel mit Pommes");
		System.out.println("2 Currywurst mit Pommes");
		System.out.println("3 Bratwurst mit Brötchen");
		
		essenWahl = Integer.parseInt(JOptionPane.showInputDialog("Was möchten Sie essen?"));
		
		switch (essenWahl) {
		case 1:
			System.out.println("Sie haben ein Jägerschnitzel mit Pommes gewählt");
			break;
		case 2:
			System.out.println("Sie haben Currywurst mit Pommes gewählt");
			break;
		case 3:
			System.out.println("Sie haben Bratwurst mit Brötchen gewählt");
			break;
		}
		System.exit(0);

	}

}
