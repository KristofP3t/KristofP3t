package de.kristofpetersen.javaAnDerILS.modul2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Eingabe III (über die Klasse Console
 */
public class Java02d_06_04 {

	public static void main(String[] args) throws IOException {
		String eingabe, eingabe1;
		int eingabe2, eingabe3;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		eingabe = reader.readLine();
		eingabe1 = reader.readLine();
		eingabe2 = Integer.parseInt(reader.readLine());
		eingabe3 = Integer.parseInt(reader.readLine());
		System.out.println("Hallo " + eingabe1 + ". Es grüßt dich " + eingabe);
		System.out.println("Die Summe der beiden Zahlen ist " + (eingabe2 + eingabe3));
	}

}
