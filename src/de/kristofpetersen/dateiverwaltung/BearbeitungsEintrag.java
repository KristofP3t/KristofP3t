package de.kristofpetersen.dateiverwaltung;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class BearbeitungsEintrag extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8594718396583659645L;
	private JTextField name, nachname, strasse, plz, ort, telefon;
	//Aufgabe 1:
	//Neues JLabel hinzugefügt zur Anzeige des gewünschten Textes
	private JLabel nummer, datensatzNummer;
	private MeineAktionen loeschenAct, vorAct, zurueckAct, startAct, endeAct, aktualisierenAct;
	private Connection verbindung;
	private ResultSet ergebnisMenge;
	private String sqlAbfrage;
	
	class MeineAktionen extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1094617125746498709L;

		public MeineAktionen(String text, ImageIcon icon, String beschreibung, KeyStroke shortcut, String actionText) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, beschreibung);
			putValue(ACCELERATOR_KEY, shortcut);
			putValue(ACTION_COMMAND_KEY, actionText);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("vor"))
				ganzVor();
			if (e.getActionCommand().equals("zurueck"))
				ganzZurueck();
			if (e.getActionCommand().equals("einenvor"))
				einenVor();
			if (e.getActionCommand().equals("einenzurueck"))
				einenZurueck();
			if (e.getActionCommand().equals("loeschen"))
				loeschen();
			if (e.getActionCommand().equals("aktualisieren"))
				aktualisieren();	
		}
	}
	class FensterListener extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			try {
				BearbeitungsEintrag.this.ergebnisMenge.close();
				BearbeitungsEintrag.this.verbindung.close();
				MiniDBTools.schliessenDB("jdbc:derby:adressenDB");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Problem:\n" + e2.toString());
			}
		}
		
	}
	
	public BearbeitungsEintrag(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Einträge bearbeiten");
		setLayout(new BorderLayout());
		addWindowListener(new FensterListener());
		loeschenAct = new MeineAktionen("Datensatz löschen", new ImageIcon("icons/Delete24.gif"), 
				"Löscht den aktuellen Datensatz", null, "loeschen");
		vorAct = new MeineAktionen("Einen Datensatz weiter", new ImageIcon("icons/Forward24.gif"), 
				"Blättert einen Datensatz weiter", null, "einenvor");
		zurueckAct = new MeineAktionen("Einen Datensatz zurück", new ImageIcon("icons/Back24.gif"), 
				"Blättert einen Datensatz zurück", null, "einenzurueck");
		startAct = new MeineAktionen("Zum ersten Datensatz", new ImageIcon("icons/Front24.gif"),
				"Geht zum ersten Datensatz", null, "vor");
		endeAct = new MeineAktionen("Zum letzten Datensatz", new ImageIcon("icons/End24.gif"), 
				"Geht zum letzten Datensatz", null, "zurueck");
		aktualisierenAct = new MeineAktionen("Änderungen speichern", new ImageIcon("icons/Save24.gif"), 
				"Speichert Änderungen am aktuellen Datensatz", null, "aktualisieren");
		add(symbolleiste(), BorderLayout.NORTH);
		add(initGui(), BorderLayout.CENTER);
		
		sqlAbfrage = "SELECT * FROM adressen";
		
		initDB();
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private JPanel initGui() {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new GridLayout(0, 2));
		tempPanel.add(new JLabel("ID-Nummer:"));
		nummer = new JLabel();
		tempPanel.add(nummer);
		tempPanel.add(new JLabel("Vorname:"));
		name = new JTextField();
		tempPanel.add(name);
		tempPanel.add(new JLabel("Nachname:"));
		nachname = new JTextField();
		tempPanel.add(nachname);
		tempPanel.add(new JLabel("Straße:"));
		strasse = new JTextField();
		tempPanel.add(strasse);
		tempPanel.add(new JLabel("PLZ:"));
		plz = new JTextField();
		tempPanel.add(plz);
		tempPanel.add(new JLabel("Ort:"));
		ort = new JTextField();
		tempPanel.add(ort);
		tempPanel.add(new JLabel("Telefon:"));
		telefon = new JTextField();
		tempPanel.add(telefon);
		//Bei der Initialisierung des JPanel wurd nun ein Label initialisiert. 
		//Das Label ist gefettet, und wird immer aktualisiert, sobald die Daten gelesen werden.
		datensatzNummer = new JLabel("");
		datensatzNummer.setFont(datensatzNummer.getFont().deriveFont(Font.BOLD));
		tempPanel.add(datensatzNummer);
		
		return tempPanel;
	}

	private JToolBar symbolleiste() {
		JToolBar leiste = new JToolBar();
		leiste.add(loeschenAct);
		leiste.add(aktualisierenAct);
		leiste.addSeparator();
		leiste.add(startAct);
		leiste.add(zurueckAct);
		leiste.add(vorAct);
		leiste.add(endeAct);
		
		return leiste;
	}
	
	private void initDB() {
		try {
			verbindung = MiniDBTools.oeffnenDB("jdbc:derby:adressenDB");
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, sqlAbfrage);
			if (ergebnisMenge.next()) {
				datenLesen();
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	
	private void datenLesen() {
		try {
			nummer.setText(Integer.toString(ergebnisMenge.getInt(1)));
			name.setText(ergebnisMenge.getString(2));
			nachname.setText(ergebnisMenge.getString(3));
			strasse.setText(ergebnisMenge.getString(4));
			plz.setText(ergebnisMenge.getString(5));
			ort.setText(ergebnisMenge.getString(6));
			telefon.setText(ergebnisMenge.getString(7));
			//Die Methode aktualisiereDatensatzNummer() setzt den Text des JLabel "datensatzNummer" neu. 
			aktualisiereDatensatzNummer();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}
	
	//Der Text des Labels wird neu gesetzt. Dabei speichere ich zunächst die aktuelleReihe, springe dann zur letzten Reihe
	//und speichere dann die anzahlReihen. Diese kann sich ändern und muss daher jedes Mal neu ermittelt werden.
	//Zum schluss springe ich zur aktuellen Reihe zurück und ändere den Text der Anzeige.
	private void aktualisiereDatensatzNummer() {
		String tempString = "Datensatz ";
		int aktuelleReihe, anzahlReihen;
		try {
			aktuelleReihe = ergebnisMenge.getRow();
			ergebnisMenge.last();
			anzahlReihen = ergebnisMenge.getRow();
			
			ergebnisMenge.absolute(aktuelleReihe);
			tempString += aktuelleReihe;
			tempString += " von ";
			tempString += anzahlReihen;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		datensatzNummer.setText(tempString);
	}

	private void ganzVor(){
		try {
			ergebnisMenge.first();
			datenLesen();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	private void ganzZurueck(){
		try {
			ergebnisMenge.last();
			datenLesen();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	//Aufgabe 2:
	//Es wird nur der nächste Datensatz geprüft und gelesen, solange wir uns noch nicht am letzten Element
	//des Datensatzes befinden.
	//Die Prüfung ergebnisMenge.next() wird nur durchgeführt, wenn die erste Bedingung true (!false) liefert.
	private void einenVor(){
		try {
			if (!ergebnisMenge.isLast() && ergebnisMenge.next()) {
				datenLesen();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	//Aufgabe 2:
	//Es wird nur der vorherige Datensatz geprüft und gelesen, solange wir uns nicht am ersten Element
	//des Datensatzes befinden.
	//Die Prüfung ergebnisMenge.previous() wird nur durchgeführt, wenn die erste Bedingung true (!false) liefert.
	private void einenZurueck(){
		try {
			if (!ergebnisMenge.isFirst() && ergebnisMenge.previous())
				datenLesen();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	private void loeschen(){
		try {
			int position;
			position = ergebnisMenge.getRow();
			ergebnisMenge.deleteRow();
			ergebnisMenge.close();
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, sqlAbfrage);
			ergebnisMenge.absolute(position);
			if(ergebnisMenge.isAfterLast())
				ergebnisMenge.last();
			datenLesen();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	private void aktualisieren(){
		try {
			int position;
			position = ergebnisMenge.getRow();
			ergebnisMenge.updateString(2, name.getText());
			ergebnisMenge.updateString(3, nachname.getText());
			ergebnisMenge.updateString(4, strasse.getText());
			ergebnisMenge.updateString(5, plz.getText());
			ergebnisMenge.updateString(6, ort.getText());
			ergebnisMenge.updateString(7, telefon.getText());
			ergebnisMenge.updateRow();
			ergebnisMenge.close();
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, sqlAbfrage);
			ergebnisMenge.absolute(position);
			datenLesen();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}

}
