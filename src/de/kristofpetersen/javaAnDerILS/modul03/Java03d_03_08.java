package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * break in einer Schleife
 */
public class Java03d_03_08 {

	public static void main(String[] args) {
		int schleifenVariable, eingabe, summe;
		summe = 0;
		schleifenVariable = 1;
		
		while (schleifenVariable <=10) {
			eingabe = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die " + schleifenVariable + ". ganze Zahl ein: "));
			if(eingabe == 0)
				break;
			summe = eingabe + summe;
			schleifenVariable++;
		}
		System.out.println("Das Einlesen ist beendet.");
		System.out.println("Die Summe der Zahlen ist " + summe);
		System.exit(0);

	}

}
