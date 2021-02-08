package de.kristofpetersen.roemischeUndArabischeZahlenUmwandeln;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RoemischeZahlenUmrechnen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9218901752635159665L;

	private JTextField roemischTextField, arabischTextField;
	private JButton umrechnenButton, changeButton;
	
	//Konstuktor für die GUI
	public RoemischeZahlenUmrechnen(String titel) {
		super(titel);
		setLayout(new GridLayout(0,2));
		Label roemischLabel = new Label("Römische Zahl: ");
		roemischTextField = new JTextField();
		Label arabischLabel = new Label("Arabische Zahl: ");
		arabischTextField = new JTextField();
		arabischTextField.setEnabled(false);
		
		MeineAktionen listener = new MeineAktionen();
		umrechnenButton = new JButton("Umrechnen");
		umrechnenButton.setActionCommand("umrechnen");
		umrechnenButton.addActionListener(listener);
		changeButton = new JButton("Ändere Umrechnung");
		changeButton.setActionCommand("change");
		changeButton.addActionListener(listener);
		
		
		add(roemischLabel);
		add(roemischTextField);
		add(arabischLabel);
		add(arabischTextField);
		add(umrechnenButton);
		add(changeButton);
		
		setSize(400,100);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class MeineAktionen extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6146986586855030807L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("umrechnen")) {
				umrechnen();
			}
			if (e.getActionCommand().equals("change")) {
				aendereEingabe();
			}
		}
	}
	//Wählt aus, in welcher Richtung übersetzt wird.
	private void aendereEingabe() {
		if(roemischTextField.isEnabled()) {
			roemischTextField.setEnabled(false);
			arabischTextField.setEnabled(true);
			roemischTextField.setText("");
			arabischTextField.setText("");
		} else {
			roemischTextField.setEnabled(true);
			arabischTextField.setEnabled(false);
			roemischTextField.setText("");
			arabischTextField.setText("");
		}
		
	}
	//startet die Umrechnung
	private void umrechnen() {
		if(arabischTextField.isEnabled()) {
			
			roemischTextField.setText(umrechnungArabischZuRoemisch(arabischTextField.getText()));
			
		}else if (roemischTextField.isEnabled()){
			
			arabischTextField.setText(umrechnungRoemischZuArabisch(roemischTextField.getText()));
			
		}
	}
	
	private String umrechnungArabischZuRoemisch(String arabischeZahlString) {
		
		String ergebnis = "";
		
		try {
			int arabischeZahl = Integer.parseInt(arabischeZahlString);
			ergebnis= berechneRoemischeZahl(arabischeZahl);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bitte nur ganze Zahlen zwischen 1 und 10000 eingeben.");
		}
		
		return ergebnis;
	}
	
	/*
	 * Berechnet die römische Zahl anhand der eingegebenen arabischen Zahl.
	 */
	private String berechneRoemischeZahl(int arabischeZahl) {
		StringBuilder roemischeZahl = new StringBuilder();
		int restzahl = arabischeZahl;
		
		
		while (restzahl >= 1000) {
			roemischeZahl.append("M");
			restzahl -= 1000;
		}
		if (restzahl >=900) {
			roemischeZahl.append("CM");
			restzahl -= 900;
		}else if (restzahl >= 500) {
			roemischeZahl.append("D");
			restzahl -= 500;
		}
		if (restzahl >= 400) {
			roemischeZahl.append("CD");
			restzahl -= 400;
		}
		while (restzahl >= 100) {
			roemischeZahl.append("C");
			restzahl -= 100;
		}	
		if (restzahl >=90) {
			roemischeZahl.append("XC");
			restzahl -= 90;
		}else if (restzahl >= 50) {
			roemischeZahl.append("L");
			restzahl -= 50;
		}
		if (restzahl >= 40) {
			roemischeZahl.append("XL");
			restzahl -= 40;
		}
		while (restzahl >= 10) {
			roemischeZahl.append("X");
			restzahl -= 10;
		}	
		if (restzahl >=9) {
			roemischeZahl.append("IX");
			restzahl -= 9;
		}else if (restzahl >= 5) {
			roemischeZahl.append("V");
			restzahl -= 5;
		}
		if (restzahl >= 4) {
			roemischeZahl.append("IV");
			restzahl -= 4;
		}
		while (restzahl >= 1) {
			roemischeZahl.append("I");
			restzahl -= 1;
		}	
		if (restzahl < 0)
			roemischeZahl.append("Bitte geben Sie nur positive Zahlen ein.");
		
		return roemischeZahl.toString();
	}
/*
 * Berechnet die Arabische Zahl zu einer römischen Zahl,
 * sofern nur korrekte Zeichen in korrekter Reihenfolge und im richtiger Anzahl eingegeben wurden
 */
	private String umrechnungRoemischZuArabisch(String roemischeZahlString) {
		
		String arabischeZahl = "";
		
		if(	roemischeZahlHatKorrekteZeichen(roemischeZahlString) && 
			roemischeZahlHatKorrekteReihenfolge(roemischeZahlString)&&
			hatNichtMehrAlsVierWiederholendeZeichen(roemischeZahlString)) 
		{
			arabischeZahl = arabischeZahlBerechnen(roemischeZahlString);

		}else {
			arabischeZahl = "Bitte prüfen Sie die Eingabe.";
		}
			
		
		return arabischeZahl;
	}


//Test, dass nur gültige Zeichen verwendet wurden
	private boolean roemischeZahlHatKorrekteZeichen(String roemischeZahlString) {
		boolean tempErgebnis = true;
		
		for (int i = 0; i < roemischeZahlString.length(); i++) {
			if (roemischeZahlString.charAt(i) != 'I' &&
				roemischeZahlString.charAt(i) != 'V' &&
				roemischeZahlString.charAt(i) != 'X' &&
				roemischeZahlString.charAt(i) != 'L' &&
				roemischeZahlString.charAt(i) != 'C' &&
				roemischeZahlString.charAt(i) != 'D' &&
				roemischeZahlString.charAt(i) != 'M' )
				{
				tempErgebnis = false;
				}
		}
		if (roemischeZahlString.isEmpty()) {
			tempErgebnis = false;
		}
		
		return tempErgebnis;
	}
	
	//Test, dass keine ungültige Zahl vor einer anderen steht
	private boolean roemischeZahlHatKorrekteReihenfolge(String roemischeZahlString) {
		boolean gueltigeEingabe = true;
		if (roemischeZahlString.length() > 1) {
			for (int i = 1; i < roemischeZahlString.length(); i++) {
				if(	roemischeZahlString.charAt(i) == 'M' ||
					roemischeZahlString.charAt(i) == 'D') {
					
					if (roemischeZahlString.charAt(i-1) != 'M' &&
						roemischeZahlString.charAt(i-1) != 'C') {
						gueltigeEingabe = false;
						}
				}
		
				if(	roemischeZahlString.charAt(i) == 'C'|| 
					roemischeZahlString.charAt(i) == 'L') {
					if (roemischeZahlString.charAt(i-1) != 'M' &&
						roemischeZahlString.charAt(i-1) != 'D' &&
						roemischeZahlString.charAt(i-1) != 'C' &&
						roemischeZahlString.charAt(i-1) != 'X') {
						gueltigeEingabe = false;
						}
				}
				
				if(	roemischeZahlString.charAt(i) == 'X' ||
					roemischeZahlString.charAt(i) == 'V') {
					if (roemischeZahlString.charAt(i-1) == 'V') {
						gueltigeEingabe=false;
					}
					
				}
				
			}
		}

		return gueltigeEingabe;
	}
	//bei 4 gleichen, aufeinanderfolgenden römischen Zahlen, wird die Berechnung nicht durchgeführt
	private boolean hatNichtMehrAlsVierWiederholendeZeichen(String roemischeZahlString) {
		boolean gueltigeEingabe = true;
		if(roemischeZahlString.length() > 3) {
			for (int i = 3; i < roemischeZahlString.length(); i++) {
				if (roemischeZahlString.charAt(i) == roemischeZahlString.charAt(i-1) &&
					roemischeZahlString.charAt(i-1) == roemischeZahlString.charAt(i-2)&&
					roemischeZahlString.charAt(i-2) == roemischeZahlString.charAt(i-3)) {
						gueltigeEingabe = false;
				}
			}
		}
		
		
		return gueltigeEingabe;
	}
	
	private String arabischeZahlBerechnen(String roemischeZahlString) {
		int arabischeZahl = 0;
		for (int i = 0; i < roemischeZahlString.length(); i++) {
			if (roemischeZahlString.charAt(i) == 'M') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'C')
					arabischeZahl += 800;
				else 
					arabischeZahl += 1000;
			}
			if (roemischeZahlString.charAt(i) == 'D') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'C')
					arabischeZahl += 300;
				else 
					arabischeZahl += 500;
			}
			if (roemischeZahlString.charAt(i) == 'C') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'X')
					arabischeZahl += 80;
				else 
					arabischeZahl += 100;
			}
			if (roemischeZahlString.charAt(i) == 'L') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'X')
					arabischeZahl += 30;
				else 
					arabischeZahl += 50;
			}
			if (roemischeZahlString.charAt(i) == 'X') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'I')
					arabischeZahl += 8;
				else 
					arabischeZahl += 10;
			}
			if (roemischeZahlString.charAt(i) == 'V') {
				if(i > 0 && roemischeZahlString.charAt(i-1) == 'I')
					arabischeZahl += 3;
				else 
					arabischeZahl += 5;
			}
			if (roemischeZahlString.charAt(i) == 'I') {
				arabischeZahl += 1;
			}
		}
		
		return arabischeZahl + "" ;
	}
}