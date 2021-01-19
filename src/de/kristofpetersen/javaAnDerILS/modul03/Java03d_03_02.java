package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * do ... while-Schleife
 */
public class Java03d_03_02 {

	public static void main(String[] args) {
		int schleifenVariable = 0;
		
		do {
			schleifenVariable = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie einen Wert größer als 10 ein."));
		} while (schleifenVariable <= 10);
		
		System.out.println("Danke!");
		System.exit(0);

	}

}
