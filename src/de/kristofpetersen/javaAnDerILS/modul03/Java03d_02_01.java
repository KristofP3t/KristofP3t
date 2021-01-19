package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Einfache If-Verzweigung
 */
public class Java03d_02_01 {

	public static void main(String[] args) {
		int zahl;
		
		zahl = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie bitte eine Zahl ein: "));
		if (zahl > 5)
			System.out.println("Sie haben eine Zahl größer als 5 eingegeben");
		
		System.out.println("Die Zahl war " + zahl);
		
		System.exit(0);
	}

}
