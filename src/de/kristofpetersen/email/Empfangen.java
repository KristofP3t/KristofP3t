package de.kristofpetersen.email;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class Empfangen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4098647284053489458L;
	
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
			if(e.getActionCommand().equals("antworten"))
				antworten();
			if(e.getActionCommand().equals("weiterleiten"))
				weiterleiten();
		}	
	}
	
	private ZugangsdatenErmittler nutzer;
	
	class MeinWindowAdapter extends WindowAdapter{

		@Override
		public void windowOpened(WindowEvent e) {
			nachrichtenEmpfangen();
		}
		
	}
	
	Empfangen() {
		super();
		setTitle("E-Mail empfangen");
		setLayout(new BorderLayout());

		sendenAct = new MeineAktionen("Neue E-Mail", new ImageIcon("icons/mail-generic.gif"), "Erstellt eine neue E-Mail", null, "senden");
		beantwortenAct = new MeineAktionen("Antworten", new ImageIcon("icons/mail-reply.gif"), "Erstellt eine Antwort an Absender", null, "antworten");
		weiterleitenAct = new MeineAktionen("Weiterleiten", new ImageIcon("icons/mail-forward.gif"), "Leitet die E-Mail weiter", null, "weiterleiten");
		add(symbolleiste(), BorderLayout.NORTH);
			
		setVisible(true);
		setSize(700,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWindowListener(new MeinWindowAdapter());
		tabelleErstellen();
		tabelleAktualisieren();
		}
	private void tabelleErstellen() {
		String[] spaltenNamen = {"ID", "Sender", "Betreff", "Text"};
		
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
					String sender, betreff, inhalt, ID;
					ID = tabelle.getModel().getValueAt(zeile,  0).toString();
					sender = tabelle.getModel().getValueAt(zeile, 1).toString();
					betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
					inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
						
				new Anzeige(Empfangen.this, true, ID, sender, betreff, inhalt);
				}
			}
				});
				
			}
	private void tabelleAktualisieren() {
		Connection verbindung;
		ResultSet ergebnisMenge;
				
		String sender, betreff, inhalt, ID;
		modell.setRowCount(0);
				
		try {
			verbindung = MiniDBTools.oeffnenDB("jdbc:derby:empfangenDB");
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, "SELECT * FROM empfangen");
			while (ergebnisMenge.next()) {
				ID = ergebnisMenge.getString("iNummer");
				sender = ergebnisMenge.getString("sender");
				betreff = ergebnisMenge.getString("betreff");
				Clob clob;
				clob = ergebnisMenge.getClob("inhalt");
				inhalt = clob.getSubString(1,  (int) clob.length());
						
				modell.addRow(new Object[] {ID, sender, betreff, inhalt});
						
			}
			ergebnisMenge.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:empfangenDB");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}
	private void nachrichtenEmpfangen() {
		nachrichtenAbholen();
		tabelleAktualisieren();
		
	}
	private void nachrichtenAbholen() {

		nutzer = new ZugangsdatenErmittler();
		String benutzername = nutzer.getBenutzername();
		String kennwort = nutzer.getKennwort();
		
		String server = "pop.gmail.com";
		
		Properties eigenschaften = new Properties();
		eigenschaften.put("mail.store.protocol", "pop3");
		eigenschaften.put("mail.pop3.host", server);
		eigenschaften.put("mail.pop3.port", "995");
		eigenschaften.put("mail.pop3.starttls.enable", "true");
		Session sitzung = Session.getDefaultInstance(eigenschaften);
		
		try (Store store = sitzung.getStore("pop3s")){
			store.connect(server, benutzername, kennwort);
			Folder postEingang = store.getFolder("INBOX");
			postEingang.open(Folder.READ_WRITE);
			
			Message nachrichten[] = postEingang.getMessages();
			if (nachrichten.length != 0) {
				JOptionPane.showMessageDialog(this, "Es gibt " + postEingang.getUnreadMessageCount() + " neue Nachrichten.");
				for (Message nachricht : nachrichten) {
					nachrichtVerarbeiten(nachricht);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Es gibt keine neuen Nachrichten");
			}
			postEingang.close(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	
	}
	private void nachrichtVerarbeiten(Message nachricht) {
		try {
			if (nachricht.isMimeType("text/plain")) {
				String sender = nachricht.getFrom()[0].toString();
				String betreff = nachricht.getSubject();
				String inhalt = nachricht.getContent().toString();
				nachrichtSpeichern(sender, betreff, inhalt);
				nachricht.setFlag(javax.mail.Flags.Flag.DELETED, true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Der Typ der Nachricht " + nachricht.getContentType() + "kann nicht verarbeitet werden");
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}
	
	private void nachrichtSpeichern(String sender, String betreff, String inhalt) {
		Connection verbindung;
		
		verbindung = MiniDBTools.oeffnenDB("jdbc:derby:empfangenDB");
		try {
			PreparedStatement prepState;
			prepState = verbindung.prepareStatement("insert into empfangen (sender, betreff, inhalt) values (?,?,?)");
			prepState.setString(1, sender);
			prepState.setString(2, betreff);
			prepState.setString(3, inhalt);
			prepState.executeUpdate();
			verbindung.commit();
			
			prepState.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:empfangenDB");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
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
	}

	private void antworten() {
		int zeile = tabelle.getSelectedRow();
		if(zeile >=0) {
			String empfaenger, betreff, inhalt;
			empfaenger = tabelle.getModel().getValueAt(zeile, 1).toString();
			betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
			inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
			new NeueNachricht(this, true, empfaenger, betreff, inhalt, "antworten");
		}
		else {
			JOptionPane.showMessageDialog(this, "Es wurde keine Zeile markiert. Bitte erneut versuchen");
	}
		
	}

	private void weiterleiten() {
		int zeile = tabelle.getSelectedRow();
		if(zeile >=0) {
			String empfaenger, betreff, inhalt;
			empfaenger = tabelle.getModel().getValueAt(zeile, 1).toString();
			betreff = tabelle.getModel().getValueAt(zeile, 2).toString();
			inhalt = tabelle.getModel().getValueAt(zeile, 3).toString();
			new NeueNachricht(this, true, empfaenger, betreff, inhalt, "weiterleiten" );
		}
		else {
			JOptionPane.showMessageDialog(this, "Es wurde keine Zeile markiert. Bitte erneut versuchen");
		}
	}
}
