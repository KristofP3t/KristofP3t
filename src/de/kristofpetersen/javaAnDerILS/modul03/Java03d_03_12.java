package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * If-Abfrage statt continue
 */
public class Java03d_03_12 {

	public static void main(String[] args) {
		int quadrat, schleifenVariable, eingabe;
		
		quadrat = 0;
		schleifenVariable = 1;
		eingabe = 1;
		
		while (schleifenVariable <= 10) {
			boolean gelungen = false;
			while (gelungen == false) {
				try {
					eingabe = Integer.parseInt(JOptionPane.showInputDialog("Bitte gib eine Zahl ein!"));
					gelungen = true;
				} catch (Exception e) {
					System.out.println("Die Eingabe war nicht erfolgreich. Probiere es erneut:");
				}
			}
			
			
			if(eingabe != 0) {
				quadrat = eingabe * eingabe;
				System.out.println("Das Quadrat der Zahl ist " + quadrat);
			}
			schleifenVariable ++;
			
		}
		System.out.println("Das Einlesen ist beendet.");
		System.exit(0);

	}

}