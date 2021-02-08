package de.kristofpetersen.email;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NeueNachricht extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4616222192806348949L;
	
	private JTextField empfaenger, betreff;
	private JTextArea inhalt;

	private ZugangsdatenErmittler nutzer;
	
	private JButton ok, abbrechen;
	
	class NeuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("senden"))
				senden();
			if(e.getActionCommand().equals("abbrechen"))
				dispose();
		}
	}
	
	public NeueNachricht(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Neue Nachricht");
		initGUI();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public NeueNachricht(JFrame parent, boolean modal, String empfaengerFeld, String betreffFeld,
			String inhaltFeld, String artDerNachricht) {
		super(parent, modal);
		String inhalt = "\n----- Text der Ursrünglichen Nachricht-----\n\n" + inhaltFeld;
		if(artDerNachricht.equals("antworten")) {
			setTitle("Antwort versenden");
			String empfaenger = empfaengerFeld;
			String betreff = "AW: " + betreffFeld;
			initGUI(empfaenger, betreff, inhalt);
		}
		else if (artDerNachricht.equals("weiterleiten")) {
			setTitle("E-Mail weiterleiten");
			String betreff = "WG: " + betreffFeld;
			initGUI("", betreff, inhalt);
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

		private void initGUI() {
		setLayout(new BorderLayout());
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(0,2));
		oben.add(new JLabel("Empfänger:"));
		empfaenger = new JTextField();
		oben.add(empfaenger);
		oben.add(new JLabel("Betreff:"));
		betreff = new JTextField();
		oben.add(betreff);
		add(oben, BorderLayout.NORTH);
		inhalt = new JTextArea();
		inhalt.setLineWrap(true);
		inhalt.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(inhalt);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		JPanel unten = new JPanel();
		ok = new JButton("Senden");
		ok.setActionCommand("senden");
		abbrechen = new JButton("Abbrechen");
		abbrechen.setActionCommand("abbrechen");
		
		NeuListener listener = new NeuListener();
		ok.addActionListener(listener);
		abbrechen.addActionListener(listener);
		
		unten.add(ok);
		unten.add(abbrechen);
		add(unten, BorderLayout.SOUTH);
		
		setSize(600, 300);
		setVisible(true);
	}
	
	private void initGUI(String empfaengerAlt, String betreffAlt, String inhaltAlt) {
		setLayout(new BorderLayout());
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(0,2));
		oben.add(new JLabel("Empfänger:"));
		empfaenger = new JTextField();
		empfaenger.setText(empfaengerAlt);
		oben.add(empfaenger);
		oben.add(new JLabel("Betreff:"));
		betreff = new JTextField();
		betreff.setText(betreffAlt);
		oben.add(betreff);
		add(oben, BorderLayout.NORTH);
		inhalt = new JTextArea();
		inhalt.setText(inhaltAlt);
		inhalt.setLineWrap(true);
		inhalt.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(inhalt);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		JPanel unten = new JPanel();
		ok = new JButton("Senden");
		ok.setActionCommand("senden");
		abbrechen = new JButton("Abbrechen");
		abbrechen.setActionCommand("abbrechen");
		
		NeuListener listener = new NeuListener();
		ok.addActionListener(listener);
		abbrechen.addActionListener(listener);
		
		unten.add(ok);
		unten.add(abbrechen);
		add(unten, BorderLayout.SOUTH);
		
		setSize(600, 300);
		setVisible(true);
	}

	private void senden() {
		Session sitzung;
		
		sitzung = verbindungHerstellen();
		nachrichtVerschicken(sitzung);
		nachrichtSpeichern();
		
	}

	private Session verbindungHerstellen() {

				nutzer = new ZugangsdatenErmittler();
				String benutzername = nutzer.getBenutzername();
				String kennwort = nutzer.getKennwort();
				
				String server = "smtp.gmail.com";
				
				Properties eigenschaften = new Properties();
				eigenschaften.put("mail.smtp.auth", "true");
				eigenschaften.put("mail.smtp.starttls.enable", "true");
				eigenschaften.put("mail.smtp.host", server);
				eigenschaften.put("mail.smtp.port", "587");
				
				Session session = Session.getInstance(eigenschaften, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(benutzername, kennwort);
					}
				});
				
				return session;
	}

	private void nachrichtVerschicken(Session sitzung) {

				nutzer = new ZugangsdatenErmittler();
				String absender = nutzer.getBenutzername();
				
				try {
					MimeMessage nachricht = new MimeMessage(sitzung);
					nachricht.setFrom(new InternetAddress(absender));
					nachricht.setRecipients(Message.RecipientType.TO, InternetAddress.parse(empfaenger.getText()));
					nachricht.setSubject(betreff.getText());
					nachricht.setText(inhalt.getText());
					Transport.send(nachricht);
					
					JOptionPane.showMessageDialog(this, "Die Nachricht wurde verschickt.");
					
					dispose();
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
				}
		}

	private void nachrichtSpeichern() {
		Connection verbindung;
		
		verbindung = MiniDBTools.oeffnenDB("jdbc:derby:gesendetDB");
		
		try {
			PreparedStatement prepState;
			prepState = verbindung.prepareStatement("insert into gesendet (empfaenger, betreff, inhalt) values (?,?,?)");
			prepState.setString(1, empfaenger.getText());
			prepState.setString(2, betreff.getText());
			prepState.setString(3, inhalt.getText());
			prepState.executeUpdate();
			verbindung.commit();
			
			prepState.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:gesendetDB");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}
}
