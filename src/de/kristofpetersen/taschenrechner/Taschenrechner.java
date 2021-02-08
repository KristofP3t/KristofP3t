package de.kristofpetersen.taschenrechner;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Taschenrechner extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4007903767096230556L;
	
	private JLabel eingabeLabel;
	private StringBuilder eingabeBuilder;
	private JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
	private JButton loeschenButton, plusButton, minusButton, geteiltButton, malButton, gleichButton, kommaButton;
	private String gemerkteAktion = null, gemerkteZahl = null;
	private boolean weiterrechnen = false, gleichMehrmalsKlicken = false;
	private String zuletztEingegebeneZahl;
	
	public Taschenrechner(String titel) {
		super(titel);
		JPanel panelOben, panelUnten;
		
		
		panelOben = initPanelOben();
		panelUnten = initPanelUnten();
		
		setLayout(new GridLayout(0,1));
		add(panelOben);
		add(panelUnten);
		
		setSize(200,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	


	class MeineAktionen extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3328103536689555248L;

		@Override
		public void actionPerformed(ActionEvent e) {
					
			if(e.getActionCommand().equals("echtNull")){
				zahlKlick("0");
			}
			if(e.getActionCommand().equals("eins")){
				zahlKlick("1");
			}
			if(e.getActionCommand().equals("zwei")){
				zahlKlick("2");
			}
			if(e.getActionCommand().equals("drei")){
				zahlKlick("3");
			}
			if(e.getActionCommand().equals("vier")){
				zahlKlick("4");
			}
			if(e.getActionCommand().equals("fuenf")){
				zahlKlick("5");
			}
			if(e.getActionCommand().equals("sechs")){
				zahlKlick("6");
			}
			if(e.getActionCommand().equals("sieben")){
				zahlKlick("7");
			}
			if(e.getActionCommand().equals("acht")){
				zahlKlick("8");
			}
			if(e.getActionCommand().equals("neun")){
				zahlKlick("9");
			}
			if(e.getActionCommand().equals("komma")){
				kommaklick();
			}
			if(e.getActionCommand().equals("loeschen")){
				loeschenKlick();
			}
			if(e.getActionCommand().equals("geteilt")){
				rechenoperation("geteilt");	
			}
			if(e.getActionCommand().equals("plus")){
				rechenoperation("plus");	
			}
			if(e.getActionCommand().equals("minus")){
				rechenoperation("minus");	
			}	
			if(e.getActionCommand().equals("mal")){
				rechenoperation("mal");	
			}
			if(e.getActionCommand().equals("gleich")){
				gleichKlick();
			}
			aktualisiereEingabe();
		}	
	}
	/*
	 * aktion wird ausgeführt, wenn auf das gleich geklickt wird.
	 * Sofern einfach auf gleich geklickt wird, so wird die Rechenaktion durchgeführt.
	 * Wird mehrfach auf gleich geklickt wird die rechenaktion mit der letzten Eingabe wiederholt 
	 */
	private void gleichKlick() {
		if(gemerkteZahl != null && eingabeBuilder.length() > 0 && gleichMehrmalsKlicken) {
			String neueGemerkteZahl = eingabeBuilder.toString();
			berechnungDurchfuehren(gemerkteAktion, neueGemerkteZahl, zuletztEingegebeneZahl);
			gleichMehrmalsKlicken = true;
			weiterrechnen = true;
		}
		if(gemerkteZahl != null && eingabeBuilder.length() > 0 && !gleichMehrmalsKlicken) {
			zuletztEingegebeneZahl = eingabeBuilder.toString();
			berechnungDurchfuehren(gemerkteAktion, gemerkteZahl, eingabeBuilder.toString());
			gleichMehrmalsKlicken = true;
			weiterrechnen = true;
		}
		
	}

	/*
	 * Die operationen sind zugefasst und werden je nach auslöser ausgeführt.
	 * Wenn zuletzt auf gleich geklickt wird, werden entsprechende Variablen zurückgesetzt
	 * Die Berechnung startet, wenn zuvor eine Zahl gemerkt wurde. Ein Klicken auf gleich ist dann nicht nötig.
	 */
	private void rechenoperation(String operation) {
		if (gleichMehrmalsKlicken) {
			gemerkteAktion = null;
			gleichMehrmalsKlicken =false;
		}
		if (gemerkteAktion == null && eingabeBuilder.length() > 0) {
			gemerkteAktion = operation;
			gemerkteZahl = eingabeLabel.getText();
			eingabeBuilder.delete(0, eingabeBuilder.length());
		}
		else if (gemerkteAktion != null && eingabeBuilder.length() > 0) {
			berechnungDurchfuehren(gemerkteAktion, gemerkteZahl, eingabeBuilder.toString());
			gemerkteAktion = operation;
			gemerkteZahl = eingabeBuilder.toString();
			weiterrechnen = true;
		}
		
	}
	
	/*
	 * löscht alle vorherigen Eingaben und setzt die Anwendung zurück
	 */
	private void loeschenKlick() {
		eingabeBuilder.delete(0, eingabeBuilder.length());
		gemerkteZahl = null;
		gemerkteAktion = null;
		zuletztEingegebeneZahl = null;
		gleichMehrmalsKlicken = false;
		weiterrechnen = false;
	}
	
	/*
	 * löscht die Eingabe, wenn eine neue Rechenoperation gestartet werden sol
	 * Wenn das Komma als erstes eingegeben wird, so erscheint davor eine "0" 
	 */
	private void kommaklick() {
		if (weiterrechnen) {
			eingabeBuilder.delete(0, eingabeBuilder.length());
			weiterrechnen = false;
		}
		if (eingabeBuilder.indexOf(".") == -1) {
			if (eingabeBuilder.length() == 0) {
				eingabeBuilder.append("0");
			}
			eingabeBuilder.append(".");
		}
	}

	private void zahlKlick(String zahl) {
		if (weiterrechnen) {
			eingabeBuilder.delete(0, eingabeBuilder.length());
			weiterrechnen = false;
		}
		eingabeBuilder.append(zahl);
		
	}

	/*
	 * Führt die gemerkte Operation aus
	 */
	private void berechnungDurchfuehren(String gemerkteAktion, String gemerkteZahl, String neueZahl) {
			
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.UNNECESSARY);
			
			float ersteZahl = Float.parseFloat(gemerkteZahl);
			float zweiteZahl = Float.parseFloat(neueZahl);
			eingabeBuilder.delete(0, eingabeBuilder.length());
			float ergebnis = 0;
			
			if (gemerkteAktion.equals("geteilt")) {
				ergebnis = divisionDurchfuehren(ersteZahl, zweiteZahl);
			}
			else if (gemerkteAktion.equals("plus")) {
				ergebnis = additionDurchfuehren(ersteZahl, zweiteZahl);
			}
			else if (gemerkteAktion.equals("minus")) {
				ergebnis = subtraktionDurchfuehren(ersteZahl, zweiteZahl);
			}
			else if (gemerkteAktion.equals("mal")) {
				ergebnis = multiplikationDurchfuehren(ersteZahl, zweiteZahl);
			}
			
			try {
				eingabeBuilder.append(df.format(ergebnis));
			} catch (ArithmeticException e) {
				eingabeBuilder.append(ergebnis);
			}
	}
		
	private float multiplikationDurchfuehren(float ersteZahl, float zweiteZahl) {
		return (ersteZahl * zweiteZahl);	
		}
	
	private float subtraktionDurchfuehren(float ersteZahl, float zweiteZahl) {
		return (ersteZahl - zweiteZahl);
		}
	
	private float additionDurchfuehren(float ersteZahl, float zweiteZahl) {
		return (ersteZahl + zweiteZahl);
		}
	
	private float divisionDurchfuehren(float ersteZahl, float zweiteZahl) {
		if(zweiteZahl == 0)
			return 1;
		else 
			return (ersteZahl / zweiteZahl);
		}

	private JPanel initPanelOben() {
		JPanel tempPanel = new JPanel();
		eingabeBuilder = new StringBuilder();
		eingabeLabel = new JLabel();
		tempPanel.add(eingabeLabel);
		
		return tempPanel;
	}
	
	private JPanel initPanelUnten() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(0,4));
		MeineAktionen listener = new MeineAktionen();
		
		loeschenButton = new JButton("C");
		loeschenButton.setActionCommand("loeschen");
		loeschenButton.addActionListener(listener);
		button7 = new JButton("7");
		button7.setActionCommand("sieben");
		button7.addActionListener(listener);
		button8 = new JButton("8");
		button8.setActionCommand("acht");
		button8.addActionListener(listener);
		button9 = new JButton("9");
		button9.setActionCommand("neun");
		button9.addActionListener(listener);
		malButton = new JButton("*");
		malButton.setActionCommand("mal");
		malButton.addActionListener(listener);
		button4 = new JButton("4");
		button4.setActionCommand("vier");
		button4.addActionListener(listener);
		button5 = new JButton("5");
		button5.setActionCommand("fuenf");
		button5.addActionListener(listener);
		button6 = new JButton("6");
		button6.setActionCommand("sechs");
		button6.addActionListener(listener);
		geteiltButton = new JButton("/");
		geteiltButton.setActionCommand("geteilt");
		geteiltButton.addActionListener(listener);
		button1 = new JButton("1");
		button1.setActionCommand("eins");
		button1.addActionListener(listener);
		button2 = new JButton("2");
		button2.setActionCommand("zwei");
		button2.addActionListener(listener);
		button3 = new JButton("3");
		button3.setActionCommand("drei");
		button3.addActionListener(listener);
		plusButton = new JButton("+");
		plusButton.setActionCommand("plus");
		plusButton.addActionListener(listener);
		button0 = new JButton("0");
		button0.setActionCommand("echtNull");
		button0.addActionListener(listener);
		minusButton = new JButton("-");
		minusButton.setActionCommand("minus");
		minusButton.addActionListener(listener);
		gleichButton = new JButton("=");
		gleichButton.setActionCommand("gleich");
		gleichButton.addActionListener(listener);
		kommaButton = new JButton(",");
		kommaButton.setActionCommand("komma");
		kommaButton.addActionListener(listener);
		
		tempPanel.add(new JLabel());
		tempPanel.add(new JLabel());
		tempPanel.add(new JLabel());
		tempPanel.add(loeschenButton);
		tempPanel.add(button7);
		tempPanel.add(button8);
		tempPanel.add(button9);
		tempPanel.add(malButton);
		tempPanel.add(button4);
		tempPanel.add(button5);
		tempPanel.add(button6);
		tempPanel.add(geteiltButton);
		tempPanel.add(button1);
		tempPanel.add(button2);
		tempPanel.add(button3);
		tempPanel.add(plusButton);
		tempPanel.add(minusButton);
		tempPanel.add(button0);
		tempPanel.add(kommaButton);
		tempPanel.add(gleichButton);

		return tempPanel;
	}

	private void aktualisiereEingabe() {
		eingabeLabel.setText(eingabeBuilder.toString());	
	}

}