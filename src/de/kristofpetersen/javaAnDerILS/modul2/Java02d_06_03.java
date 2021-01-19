package de.kristofpetersen.javaAnDerILS.modul2;
/*
 * Eingabe II (mit Konvertierung)
 */
import javax.swing.*;

public class Java02d_06_03 {
	public static void main(String[] args) {
		String eingabe, eingabe1;
		int eingabe2, eingabe3;
		
		eingabe = JOptionPane.showInputDialog("Wie heißen Sie?");
		eingabe1 = JOptionPane.showInputDialog("Wen wollen Sie grüßen?");
		
		System.out.println("Hallo " + eingabe1 + ". Es grüßt dich " + eingabe);
		
		eingabe2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die erste Zahl ein"));
		eingabe3 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die zweite Zahl ein"));
		
		System.out.println("Die Summe der beiden Zahlen ist " + (eingabe2 + eingabe3));
		
		System.exit(0);
	}

}
