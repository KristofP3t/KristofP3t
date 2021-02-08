package de.kristofpetersen.email;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class Senden extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3901301687579892812L;
	//Einsendeaufgabe 2
	//weiterleitenAct und beantwortenAct ergänzt
	private MeineAktionen sendenAct, weiterleitenAct, beantwortenAct;
	
	private JTable tabelle;
	private DefaultTableModel modell;
	
	class MeineAktionen extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4058488065512172490L;

		public MeineAktionen(String text, ImageIcon icon, String beschreibung, KeyStroke shortcut, String actionText) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, beschreibung);
			putValue(ACCELERATOR_KEY, shortcut);
			putValue(ACTION_COMMAND_KEY, actionText);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("senden"))
				senden();
			//Die Methoden antworten() und weiterleiten() werden beim klicken von "Antworten" oder "Weiterleiten" ausgeführt
			if(e.getActionCommand().equals("antworten"))
				antworten();
			if(e.getActionCommand().equals("weiterleiten"))
				weiterleiten();
		}	
	}
	
	Senden() {
		super();
		
		setTitle("E-Mail senden");
		setLayout(new BorderLayout());
		
		sendenAct = new MeineAktionen("Neue E-Mail", new ImageIcon("icons/mail-generic.gif"), "Erstellt eine neue E-Mail", null, "senden");
		//Einsendeaufgabe 2
		beantwortenAct = new MeineAktionen("Antworten", new ImageIcon("icons/mail-reply.gif"), "Erstellt eine Antwort an Absender", null, "antworten");
		weiterleitenAct = new MeineAktionen("Weiterleiten", new ImageIcon("icons/mail-forward.gif"), "Leitet die E-Mail weiter", null, "weiterleiten");
		
		add(symbolleiste(), BorderLayout.NORTH);
		setVisible(true);
		setSize(700,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tabelleErstellen();
		tabelleAktualisieren();
	}
	
	private void tabelleAktualisieren() {
		Connection verbindung;
		ResultSet ergebnisMenge;
		
		String empfaenger, betreff, inhalt, ID;
		modell.setRowCount(0);
		
		try {
			verbindung = MiniDBTools.oeffnenDB("jdbc:derby:gesendetDB");
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, "SELECT * FROM gesendet");
			while (ergebnisMenge.next()) {
				ID = ergebnisMenge.getString("iNummer");
				empfaenger = ergebnisMenge.getString("empfaenger");
				betreff = ergebnisMenge.getString("betreff");
				Clob clob;
				clob = ergebnisMenge.getClob("inhalt");
				inhalt = clob.getSubString(1,  (int) clob.length());
				
				modell.addRow(new Object[] {ID, empfaenger, betreff, inhalt});
				
			}
			ergebnisMenge.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:gesendetDB");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}

	private void tabelleErstellen() {
		String[] spaltenNamen = {"ID", "Empfänger", "Betreff", "Text"};
		
		modell = new DefaultTableModel();
		modell.setColumnIdentifiers(spaltenNamen);
		
		tabelle = new JTable();
		tabelle.setModel(modell);
		tabelle.setDefaultEditor(Object.class, null);
		tabelle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabelle.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(tabelle);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		tabelle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int zeile = tabelle.getSelectedRow();
					String empfaenger, betreff, inhalt, ID;
					ID = tabelle.getModel().getValueAt(zeile,  0).toString();
					empfaenger = tabelle.getModel().getValueAt(zeile, 1).toString();
					betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
					inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
					
					new Anzeige(Senden.this, true, ID, empfaenger, betreff, inhalt);
				}
			}
		});
		
	}

	private JToolBar symbolleiste() {
		JToolBar leisteBar = new JToolBar();
		leisteBar.add(sendenAct);
		leisteBar.add(beantwortenAct);
		leisteBar.add(weiterleitenAct);
		return leisteBar;
	}

	private void senden(){
		new NeueNachricht(this, true);
		tabelleAktualisieren();
	}
	//Einsendeaufgabe 2
	//Wenn eine Zeile selektiert ist, werden empfänger, betreff und inhalt gespeichert. Es wurd eine Instanz von NeueNachricht mit den 
	//parametern erstellt. Anschließend wird die tabelle der gesendeten Nachrichten aktualisiert.
	//Wenn keine Zeile selektiert ist, erscheint eine Hinweismeldung.
	private void antworten() {
		int zeile = tabelle.getSelectedRow();
		if(zeile >= 0) {
			String empfaenger, betreff, inhalt;
			empfaenger = tabelle.getModel().getValueAt(zeile, 1).toString();
			betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
			inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
			new NeueNachricht(this, true, empfaenger, betreff, inhalt, "antworten");
			tabelleAktualisieren();
		}
		else {
			JOptionPane.showMessageDialog(this, "Es wurde keine Zeile markiert. Bitte erneut versuchen");
		}
	}
	//Einsendeaufgabe 2
	//Wenn eine Zeile selektiert ist, werden empfänger, betreff und inhalt gespeichert. Es wurd eine Instanz von NeueNachricht mit den 
	//parametern erstellt. Anschließend wird die tabelle der gesendeten Nachrichten aktualisiert.
	//Wenn keine Zeile selektiert ist, erscheint eine Hinweismeldung.
	private void weiterleiten() {
		int zeile = tabelle.getSelectedRow();
		if (zeile >= 0) {
			String empfaenger, betreff, inhalt;
			empfaenger = tabelle.getModel().getValueAt(zeile, 1).toString();
			betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
			inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
			new NeueNachricht(this, true, empfaenger, betreff, inhalt, "weiterleiten" );
			tabelleAktualisieren();
		}
		else {
			JOptionPane.showMessageDialog(this, "Es wurde keine Zeile markiert. Bitte erneut versuchen");
		}
	}
}
