package de.kristofpetersen.javaAnDerILS.modul02;
/*
 * Beispiele für weitere Operatoren
 */
public class Java02d_03_05 {

	public static void main(String[] args) {
		
		int zahl = 5;
		int ausgabe;
		
		//Einfaches Inkrement
		System.out.println("zahl hat den Wert " + zahl); //5
		++zahl; 
		System.out.println("zahl hat nach  ++zahl den Wert " + zahl); //6
		System.out.println();
		
		//Inkrement nach der Zuweisung
		ausgabe = zahl++; 
		System.out.println("ausgabe hat den Wert " + ausgabe); //6
		System.out.println("zahl hat jetzt den Wert " + zahl); //7
		System.out.println();

		//Inkrement vor der Zuweisung
		ausgabe = ++zahl;
		System.out.println("ausgabe hat den Wert " + ausgabe); // 8
		System.out.println("zahl hat jetzt den Wert " + zahl); // 8
		System.out.println();
		
		//entspricht zahl = zahl / 7
		zahl /= 7;
		System.out.println("zahl /= 7 liefert den Wert " + zahl); //1
		System.out.println();
		
		//entspricht zahl = zahl + 10
		zahl += 10;
		System.out.println("zahl +=10 liefert den Wert " + zahl); //11
			
	}

}
