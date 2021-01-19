package de.kristofpetersen.javaAnDerILS.modul2;
/*
 * Eingabe II
 */
import javax.swing.*;

public class Java02d_06_02 {

	public static void main(String[] args) {
		String eingabe, eingabe1, eingabe2, eingabe3;
		eingabe = JOptionPane.showInputDialog("Wie heißen Sie?");
		eingabe1 = JOptionPane.showInputDialog("Wen wollen Sie grüßen?");
		System.out.println("Hallo " + eingabe1 + ". Es grüßt Dich " + eingabe);

		eingabe2 = JOptionPane.showInputDialog("Bitte geben Sie die erste Zahl ein:");
		eingabe3 = JOptionPane.showInputDialog("Bitte geben Sie die zweite Zahl ein:");
		
		//addieren --> funktioniert falsch, da beides Strings
		System.out.println("Das Ergebnis der Addition lautet: " + (eingabe2 + eingabe3));
		
		System.exit(0);
	}

}
