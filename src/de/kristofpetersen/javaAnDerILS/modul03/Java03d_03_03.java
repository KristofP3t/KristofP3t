package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Die umgebaute Schleife
 */
public class Java03d_03_03 {

	public static void main(String[] args) {
		int schleifenVariable = 0;
		while (schleifenVariable <= 10) {
			schleifenVariable = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie einen Wert größer als 10 ein."));
		}
		System.out.println("Danke!");
		System.exit(0);

	}

}
