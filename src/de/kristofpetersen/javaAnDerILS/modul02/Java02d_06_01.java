package de.kristofpetersen.javaAnDerILS.modul02;
/*
 * Eingabe I
 */
//Import der Swing-Klassen
import javax.swing.*;
public class Java02d_06_01 {

	public static void main(String[] args) {
		String eingabe;
		eingabe = JOptionPane.showInputDialog("Bitte machen Sie Ihre Eingabe.");
		
		System.out.println("Ihre Eingabe war: " + eingabe);
		
		// Das System zur Sicherheit ausdrücklich beenden
		System.exit(0);
	}

}
