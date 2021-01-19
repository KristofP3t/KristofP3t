package de.kristofpetersen.javaAnDerILS.modul2;

public class Java02d_05_04 {

	public static void main(String[] args) {
		byte byteZahl1, byteZahl2, byteZahl3;
		byteZahl2 = 10;
		byteZahl3 = 20;
		//Das folgende geht nicht
		//byteZahl1 = byteZahl2 + byteZahl3;
		//und das auch nicht:
		//byteZahl1 = (byte) byteZahl2 + (byte)byteZahl3;
		
		//so ist es möglich
		byteZahl1 = 10 + 20;
		//und auch über ein Casting des gesamten Ausdrucks
		byteZahl1 = (byte)(byteZahl2 + byteZahl3); 
	}

}
