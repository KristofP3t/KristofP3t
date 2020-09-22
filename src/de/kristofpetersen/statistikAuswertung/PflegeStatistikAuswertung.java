package de.kristofpetersen.statistikAuswertung;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PflegeStatistikAuswertung extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2144419201198548508L;
	private JComboBox<String> statistikAuswahl;
	private String[] statistik = {"Gutachtenverarbeitung", "Antragsinterview"};
	
	public PflegeStatistikAuswertung(String titel) {
		super(titel);
		setLayout(new BorderLayout());
		erstelleMenueLeiste();
		statistikAuswahl = new JComboBox<String>(statistik);
		setSize(600, 600);
		setLocationRelativeTo(null);
		add(statistikAuswahl, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	class StatistikListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("beenden"))
				System.exit(0);
			if(e.getActionCommand().equals("suchen"))
				dateiLaden();
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
	
	private void dateiLaden() {
		
	}

}
