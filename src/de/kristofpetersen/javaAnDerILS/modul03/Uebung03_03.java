package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * break in einer Schleife
 */
public class Uebung03_03 {

	public static void main(String[] args) {
		int i,k;
		i=0;
		k=0;
		
		while (( i <= 5) && (k != 1)) {
			k = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie eine 1 zum Abbruch ein."));
			i++;
		}
		System.out.println("Schleife beendet!");
		System.exit(0);

	}

}
