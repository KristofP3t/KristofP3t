package de.kristofpetersen.javaAnDerILS.modul02;
/*
 * Die Datentypen String und char
 */
public class Java02d_04_04 {

	public static void main(String[] args) {
		char charVariable;
		String stringVariableString;
		
		charVariable = 'A';
		//Das folgende geht nicht:
		// charVariable = "A";
		
		stringVariableString = "Ich bin eine Zeichenkette";
		
		System.out.println("Das Zeichen: " + charVariable);
		System.out.println("Die Zeichenkette: " + stringVariableString);
		
		charVariable = 64;
		stringVariableString = "Auch Zeichenketten lassen sich ändern!";
		
		System.out.println("Das Zeichen: " + charVariable);
		System.out.println("Die Zeichenkette: " + stringVariableString);
		
		stringVariableString = "Auch das " + "verketten funktioniert!";
		
		System.out.println("Die Zeichenkette: " + stringVariableString);
	}

}
