package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

public class Uebung04_04 {
	static int produkt(int x, int y) {
		return (x*y);
	}
	
	
	public static void main(String[] args) {
		int var1 = Integer.parseInt(JOptionPane.showInputDialog("Wert 1:"));
		int var2 = Integer.parseInt(JOptionPane.showInputDialog("Wert 2:"));
		System.out.println("Das Prudukt der Werte lautet: " + produkt(var1, var2));
		System.exit(0);
	}

}
