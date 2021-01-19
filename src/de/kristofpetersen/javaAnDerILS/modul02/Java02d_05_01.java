package de.kristofpetersen.javaAnDerILS.modul02;

public class Java02d_05_01 {

	public static void main(String[] args) {
		long longZahl = 1234567891234567L;
		int intZahl = 100;
		double doubleZahl = 1.2;
		byte byteZahl = 10;
		
		//folgende Zuweisungen sind möglich
		longZahl = intZahl;
		doubleZahl = intZahl;
		doubleZahl = longZahl;
		longZahl = byteZahl;
		
		//diese dagegen nicht
		//intZahl = longZahl;
		//intZahl = doubleZahl;
		//byteZahl = intZahl;
		
	}

}
