package de.kristofpetersen.email;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MiniDBTools {

	public static Connection oeffnenDB(String arg) {
		Connection verbindung = null;
		try {
			verbindung = DriverManager.getConnection(arg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem:\n" + e.toString());
		}
		
		return verbindung;
	}
	
	public static ResultSet liefereErgebnis(Connection verbindung, String sqlAnweisung) {
		ResultSet ergebnismenge = null;
		
		try {
			Statement state = verbindung.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ergebnismenge = state.executeQuery(sqlAnweisung);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem:\n" + e.toString());
		}
		
		return ergebnismenge;		
	}
	
	public static void schliessenDB (String protokoll) {
		boolean erfolg = false;
		try {
			DriverManager.getConnection(protokoll + "; shutdown = true");
		} catch (Exception e) {
			erfolg = true;
		}
		if (!erfolg) {
			JOptionPane.showMessageDialog(null, "Das DBMS konnte nicht heruntergefahren werden.");
		}
	}
}
