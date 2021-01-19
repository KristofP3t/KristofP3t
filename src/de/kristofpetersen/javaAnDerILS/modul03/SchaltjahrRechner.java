package de.kristofpetersen.javaAnDerILS.modul03;

import javax.swing.JOptionPane;

/*
 * Einsendeaufgabe 3.1
 */
public class SchaltjahrRechner {
	/*
	 * In dieser Methode wird überprüft, ob die eingegebene Jahreszahl ein Schaltjahr ist. 
	 * Dabei wird eine Jahreszahl(Integer) an die Methode übergeben und ein "true" zurückgeliefert, sofern es sich um ein Schaltjahr handelt.
	 */
	
	static boolean istSchaltjahr(int jahr) {
		boolean schaltjahr = false;
		if ((jahr % 400 == 0)|| (jahr%4== 0 && jahr%100 != 0)){
			schaltjahr = true;
		}
		return schaltjahr;
	}
	/*
	 * Es soll vom Nutzer eine Jahreszahl eingegeben werden. Um Abbrüche zu verhindern soll das Programm nur ausgeführt werden, sofern sich die Eingabe in ein
	 * Integer umwandeln lässt. In der Ausgabe ist zu sehen, ob es sich bei der Eingabe um ein Schaltjahr handelt oder nicht.
	 */
	public static void main(String[] args) {
		boolean eingabeOk = true;
		String eingabe = JOptionPane.showInputDialog("Bitte gib eine Jahreszahl im Format XXXX ein.");
		try {
			Integer.parseInt(eingabe);
		}
		catch (Exception e){
			eingabeOk = false;
		}
		
		if(eingabeOk) {
			System.out.println("Das Jahr " + eingabe + " ist ein Schaltjahr: " + istSchaltjahr(Integer.parseInt(eingabe)));
		}else {
			System.out.println("Bei der eingegebenen Jahreszahl handelt es sich nicht um das korrekte Format.");
		}
		System.exit(0);

	}

}
