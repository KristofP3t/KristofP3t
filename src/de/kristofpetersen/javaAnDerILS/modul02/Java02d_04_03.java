package de.kristofpetersen.javaAnDerILS.modul02;

public class Java02d_04_03 {

	public static void main(String[] args) {
		float floatVariable;
		double doubleVariable;
		
		System.out.println("Ausgabe von Gleitkommazahlen: ");
		System.out.println("Standardeinstellungen: ");
		System.out.println("==========================");
		
		floatVariable = 10000000000f;
		System.out.println("Große Zahlen (10000000000): " + floatVariable);
		System.out.println("Kommazahlen (1.33333333333333333333) ");
		floatVariable = 1.33333333333333333333f;
		
		System.out.println("Als float: " + floatVariable);
		doubleVariable = 1.33333333333333333333;
		
		System.out.println("Als double: " + doubleVariable);
		
		System.out.println("\n\n18 Stellen:");
		System.out.println("==========================");
		
		floatVariable = 10000000000f;
		System.out.printf("Große Zahlen (10000000000 : %.18f\n" , floatVariable);
		
		System.out.println("Kommazahlen (1.33333333333333333333)");
		floatVariable = 1.33333333333333333333f;
		
		System.out.printf("Als float: %.18f\n", floatVariable);
		System.out.printf("Als double: %.18f\n", doubleVariable);
		
		floatVariable = 1001.33333333333333333333f;
		System.out.printf("Als float: %.18f\n", floatVariable);

	}

}
