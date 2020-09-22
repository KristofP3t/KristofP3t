package de.kristofpetersen.statistikAuswertung;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PflegeStatistikAuswertung extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2144419201198548508L;
	private JComboBox<String> statistikAuswahl;
	private String[] statistik = {"Gutachtenverarbeitung", "Antragsinterview"};
	private JLabel einstufungsGutachten, aussortiert, vollUndTeilautomatisiert, vollautomatisiert, teilautomatisiert, unplausiblePunktwerte,
		erledigungsart, keineZugelasseneKombi, befristet, unterbrechungVorhanden, eilantragVorhanden, vorkasseVorhanden, keinGueltigesVvh,
		sonstige, gesamtquote, quoteVollautomatisiert, quoteTeilautomatisiert;
	
	public PflegeStatistikAuswertung(String titel) {
		super(titel);
		setLayout(new BorderLayout());
		erstelleMenueLeiste();
		statistikAuswahl = new JComboBox<String>(statistik);
		setSize(600, 600);
		setLocationRelativeTo(null);
		add(statistikAuswahl, BorderLayout.NORTH);
		add(statistikDatstellungErstellen(), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	class StatistikListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("beenden"))
				System.exit(0);
			if(e.getActionCommand().equals("suchen")) {
				if (JOptionPane.showConfirmDialog(null, "Soll eine Statistik für \"" + statistikAuswahl.getSelectedItem().toString() + "\" erstellt werden?", 
						statistikAuswahl.getSelectedItem().toString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					erstelleStatistik();
			}
		}
		
	}
	
	private void erstelleMenueLeiste() {
		JMenuBar menue = new JMenuBar();
		JMenu datei = new JMenu("Datei");
		JMenuItem suchen = new JMenuItem("Suchen...");
		JMenuItem beenden = new JMenuItem("Beenden");
		StatistikListener listener = new StatistikListener();
		suchen.addActionListener(listener);
		beenden.addActionListener(listener);
		suchen.setActionCommand("suchen");
		beenden.setActionCommand("beenden");
		datei.add(suchen);
		datei.add(beenden);
		menue.add(datei);
		this.setJMenuBar(menue);
	}
	
	private JPanel statistikDatstellungErstellen() {
		JPanel tempPanel = new JPanel();
		tempPanel.setBorder(new TitledBorder("Statistik"));
		tempPanel.setLayout(new GridLayout(0,1));
		einstufungsGutachten = new JLabel("Einstufungsgutachten: ");
		aussortiert = new JLabel("Nicht maschinell Verarbeitbar: ");
		vollUndTeilautomatisiert = new JLabel("Voll- und Teilautomatisiert: ");
		vollautomatisiert = new JLabel("Vollautomatisiert: ");
		teilautomatisiert = new JLabel("Teilautomatisiert: ");
		unplausiblePunktwerte = new JLabel("Unplausible Punktwerte: ");
		erledigungsart = new JLabel("Erledigungsart: ");
		keineZugelasseneKombi = new JLabel("Keine zugelassene Kombi: ");
		befristet = new JLabel("Befristung: ");
		unterbrechungVorhanden = new JLabel("Unterbrechung vorhanden: ");
		eilantragVorhanden = new JLabel("Eilantrag vorhanden: ");
		vorkasseVorhanden = new JLabel("Vorkasse vorhanden: ");
		keinGueltigesVvh = new JLabel("Kein gültiges Versicherungsverhältnis: ");
		sonstige = new JLabel("Sonstige: ");
		gesamtquote = new JLabel("Verarbeitungsquote gesamt: ");
		quoteVollautomatisiert = new JLabel("Verarbeitungsquote Vollautomatisiert: ");
		quoteTeilautomatisiert = new JLabel("Verarbeitungsquote Teilautomatisiert: ");
		
		tempPanel.add(einstufungsGutachten);
		tempPanel.add(aussortiert);
		tempPanel.add(vollUndTeilautomatisiert);
		tempPanel.add(vollautomatisiert);
		tempPanel.add(teilautomatisiert);
		tempPanel.add(unplausiblePunktwerte);
		tempPanel.add(erledigungsart);
		tempPanel.add(keineZugelasseneKombi);
		tempPanel.add(befristet);
		tempPanel.add(unterbrechungVorhanden);
		tempPanel.add(eilantragVorhanden);
		tempPanel.add(vorkasseVorhanden);
		tempPanel.add(keinGueltigesVvh);
		tempPanel.add(sonstige);
		tempPanel.add(gesamtquote);
		tempPanel.add(quoteVollautomatisiert);
		tempPanel.add(quoteTeilautomatisiert);
		
		return tempPanel;
	}
	
	private void erstelleStatistik() {
		StatistikDialoge dialoge = new StatistikDialoge();
		File datei = dialoge.oeffnenDialogZeigen();
		String headLine = "";
		String lineString = "";
		int gesamtGutachten = 0;
		int anzahlAussortiert = 0;
		int anzahlVollUndTeilAutomatisiert = 0;
		int anzahlVollautomatisiert = 0;
		int anzahlAblehnungen = 0;
		int anzahlBewilligungen = 0;
		int anzahlTeilautomatisiert = 0;
		int anzahlUnplausiblePunktwerte = 0;
		int anzahlErledigungsart = 0;
		int anzahlKeineZugelasseneKombi = 0;
		int anzahlBefristet = 0;
		int anzahlUnterbrechungVorhanden = 0;
		int anzahlEilantragVorhanden = 0;
		int anzahlVorkasseVorhanden = 0;
		int anzahlKeinGueltigesVvh = 0;
		int anzahlSonstige = 0;
		double wertGesamtquote = 0;
		double wertVollquote = 0;
		double wertTeilquote = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(datei));
			headLine = reader.readLine();
			lineString = reader.readLine();
			while (lineString != null) {
				gesamtGutachten++;
				String[] aktuelleLine = lineString.split(";");
				if(!aktuelleLine[8].isEmpty()) {
					if (aktuelleLine[8].equals("UNPLAUSIBLE_PUNKTWERTE")) {
						anzahlUnplausiblePunktwerte++;
					}
					if (aktuelleLine[8].equals("ERLEDIGUNGSART")) {
						anzahlErledigungsart++;
					}
					if (aktuelleLine[8].equals("KEINE_ZUGELASSENEN_KOMBI_GUTACHTENART_PFLEGEEINSTUFUNG_PFLEGEGRAD")) {
						anzahlKeineZugelasseneKombi++;
					}
					if (aktuelleLine[8].equals("BEFRISTET")) {
						anzahlBefristet++;
					}
					if (aktuelleLine[8].equals("UNTERBRECHUNG_VORHANDEN")) {
						anzahlUnterbrechungVorhanden++;
					}
					if (aktuelleLine[8].equals("EILANTRAG_VORHANDEN")) {
						anzahlEilantragVorhanden++;
					}
					if (aktuelleLine[8].equals("VORKASSE_VORHANDEN")) {
						anzahlVorkasseVorhanden++;
					}
					if (aktuelleLine[8].equals("")) {
						anzahlKeinGueltigesVvh++;
					}
				}
				if(!aktuelleLine[40].isEmpty()) {
					//System.out.println(aktuelleLine[40]);
					anzahlBewilligungen++;
					anzahlVollautomatisiert++;
					}
				if(aktuelleLine[43].equals("9")) {
					anzahlTeilautomatisiert++;
				}
				if(!aktuelleLine[45].isEmpty()) {
					//System.out.println(aktuelleLine[45]);
					anzahlAblehnungen++;
					anzahlVollautomatisiert++;
				}
					
				
				lineString = reader.readLine();
			} 
		}
		catch (IOException e) {
			e.printStackTrace();
			}
		einstufungsGutachten.setText(einstufungsGutachten.getText() + gesamtGutachten);
		anzahlAussortiert = gesamtGutachten - anzahlVollautomatisiert - anzahlTeilautomatisiert;
		aussortiert.setText(aussortiert.getText() + anzahlAussortiert);
		anzahlVollUndTeilAutomatisiert = anzahlVollautomatisiert + anzahlTeilautomatisiert;
		vollUndTeilautomatisiert.setText(vollUndTeilautomatisiert.getText() + anzahlVollUndTeilAutomatisiert);
		vollautomatisiert.setText(vollautomatisiert.getText() + anzahlVollautomatisiert);
		teilautomatisiert.setText(teilautomatisiert.getText() + anzahlTeilautomatisiert);		
		
		
		unplausiblePunktwerte.setText(unplausiblePunktwerte.getText() + anzahlUnplausiblePunktwerte);
		erledigungsart.setText(erledigungsart.getText() + anzahlErledigungsart);
		keineZugelasseneKombi.setText(keineZugelasseneKombi.getText() + anzahlKeineZugelasseneKombi);
		befristet.setText(befristet.getText() + anzahlBefristet);
		unterbrechungVorhanden.setText(unterbrechungVorhanden.getText() + anzahlUnterbrechungVorhanden);
		eilantragVorhanden.setText(eilantragVorhanden.getText() + anzahlEilantragVorhanden);
		vorkasseVorhanden.setText(vorkasseVorhanden.getText() + anzahlVorkasseVorhanden);
		keinGueltigesVvh.setText(keinGueltigesVvh.getText() + anzahlKeinGueltigesVvh);
		anzahlSonstige = anzahlAussortiert
				-anzahlUnplausiblePunktwerte
				-anzahlErledigungsart
				-anzahlKeineZugelasseneKombi
				-anzahlBefristet
				-anzahlUnterbrechungVorhanden
				-anzahlEilantragVorhanden
				-anzahlVorkasseVorhanden
				-anzahlKeinGueltigesVvh;
		sonstige.setText(sonstige.getText() + anzahlSonstige);
		wertGesamtquote = anzahlVollUndTeilAutomatisiert * 1.0 / gesamtGutachten;
		gesamtquote.setText(gesamtquote.getText() + wertGesamtquote);
		wertVollquote =  anzahlVollautomatisiert * 1.0 / anzahlVollUndTeilAutomatisiert;
		quoteVollautomatisiert.setText(quoteVollautomatisiert.getText() + wertVollquote);
		wertTeilquote = anzahlTeilautomatisiert * 1.0 / anzahlVollUndTeilAutomatisiert;
		quoteTeilautomatisiert.setText(quoteTeilautomatisiert.getText() + wertTeilquote);
		
		
		}

	}

