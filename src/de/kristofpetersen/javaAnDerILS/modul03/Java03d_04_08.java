package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Eine Methode mit mehreren Argumenten
 */
public class Java03d_04_08 {
	static int summe (int x, int y) {
		return (x + y);
	}
	public static void main(String[] args) {
		int einVariable1 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die erste Zahl ein."));
		int einVariable2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie die zweite Zahl ein."));
		System.out.println("Die Summe der beiden Zahlen ist: " + summe(einVariable1, einVariable2));
		
		System.exit(0);

	}

}
