package de.kristofpetersen.javaAnDerILS.modul02;
/*
 * Ein provozierter Überlauf
 */
public class Java02d_04_02 {

	public static void main(String[] args) {
		//Vereinbarungen mit den Variablen
		byte byteVariable = 127;
		System.out.println("Wert: " + byteVariable);
		byteVariable ++ ;
		System.out.println("Wert ist jetzt: " + byteVariable);

	}

}
