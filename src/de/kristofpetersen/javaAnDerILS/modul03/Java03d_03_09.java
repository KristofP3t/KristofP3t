package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Schleifenabbruch über eine Flag-Variable
 */
public class Java03d_03_09 {

	public static void main(String[] args) {
		int summe, schleifenVariable, eingabe;
		boolean weiter = true;
		summe = 0;
		schleifenVariable = 1;
		eingabe = 1;
		
		while ((schleifenVariable <= 10) && (weiter == true)) {
			eingabe = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die " + schleifenVariable + ". Zahl ein."));
			if (eingabe == 0)
				weiter = false;
			else {
				summe = summe + eingabe;
				schleifenVariable ++;
			}
			
		}
		System.out.println("Die Eingabe ist beendet.\nDie Summe der Zahlen ist: " + summe);
		System.exit(0);

	}

}
