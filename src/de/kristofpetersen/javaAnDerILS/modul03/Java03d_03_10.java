package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * continue in einer Schleife
 */
public class Java03d_03_10 {

	public static void main(String[] args) {
		int quadrat, schleifenVariable, eingabe;
		quadrat = 0;
		schleifenVariable = 1;
		eingabe = 1;
		
		while (schleifenVariable <= 10) {
			eingabe = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie eine Zahl ein: "));
			schleifenVariable++;
			if (eingabe == 0)
				continue;
			quadrat = eingabe * eingabe;
			System.out.println("Das Quadrat der Zahl ist: " + quadrat);
			
		}
		System.out.println("Die Eingabe ist beendet.");
		System.exit(0);

	}

}
