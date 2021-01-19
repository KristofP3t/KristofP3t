package de.kristofpetersen.javaAnDerILS.modul03;
import javax.swing.JOptionPane;

/*
 * Kombination von logischen Operatoren mit Vergleichsoperatoren
 */
public class Java03d_01_05 {

	public static void main(String[] args) {
		int wertA, wertB, wertC;
		boolean ergebnis;
		
		wertA = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie den Wert für A ein:"));
		wertB = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie den Wert für B ein:"));
		wertC = Integer.parseInt(JOptionPane.showInputDialog("Geben Sie den Wert für C ein:"));
		
		System.out.printf("A = %d \t B = %d \t C = %d\n", wertA, wertB, wertC);
		ergebnis = (wertA < wertB) && (wertB < wertC);
		System.out.println("A ist kleiner als B und B ist kleiner als C: " + ergebnis);

		ergebnis = (wertA < wertB) || (wertB < wertC);
		System.out.println("A ist kleiner als B oder B ist kleiner als C: " + ergebnis);
		
		ergebnis = (wertA == wertB) || !(wertB < wertC);
		System.out.println("A ist gleich B und B ist nicht kleiner als C: " + ergebnis);
		
		System.exit(0);
	}

}
