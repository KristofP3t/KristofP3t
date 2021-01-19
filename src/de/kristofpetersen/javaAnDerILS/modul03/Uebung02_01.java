package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

public class Uebung02_01 {

	public static void main(String[] args) {
		int zahl1, zahl2;
		zahl1 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie eine ganze Zahl ein."));
		zahl2 = Integer.parseInt(JOptionPane.showInputDialog("Bitte geben Sie eine weitere ganze Zahl ein."));
		
		if (zahl1 == zahl2) {
			System.out.println(zahl1 + " und " + zahl2 + " sind identisch.");
		}else if (zahl1 > zahl2) {
			System.out.println( zahl1 +" ist größer als " + zahl2);
		}else {
			System.out.println(zahl1 + " ist kleiner als " + zahl2);
		}
		System.exit(0);
	}

}
