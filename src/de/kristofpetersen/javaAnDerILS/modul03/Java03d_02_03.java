package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * If .. else - Verzweigung
 */
public class Java03d_02_03 {

	public static void main(String[] args) {
		int zahl;
		zahl = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie eine Zahl ein: "));
		
		if(zahl > 5)
			System.out.println("Sie haben eine Zahl größer als 5 eingegeben.");
		else 
			System.out.println("Sie haben eine Zahl kleiner als 5 oder 5 eingegeben.");
		System.out.println("Die Zahl war " + zahl);
		System.exit(0);
	}

}
