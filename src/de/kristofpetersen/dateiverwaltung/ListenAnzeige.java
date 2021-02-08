package de.kristofpetersen.dateiverwaltung;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListenAnzeige extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4339702643025290872L;
	private JButton ok;
	
	class ListenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ok")) 
				dispose();
		}
	}
	public ListenAnzeige(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Listenanzeige");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		lesen();
		ok = new JButton("OK");
		ok.setActionCommand("ok");
		ok.addActionListener(new ListenListener());
		add(ok);
		
		setSize(220,300);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	private void lesen() {
		Connection verbindung;
		ResultSet ergebnisMenge;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));
		JScrollPane pane = new JScrollPane(panel);
		pane.setPreferredSize(new Dimension(200, 220));
		
		try {
			verbindung = MiniDBTools.oeffnenDB("jdbc:derby:adressenDB");
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, "SELECT * FROM adressen");
			while (ergebnisMenge.next()) {
				panel.add(new JLabel("ID-Nummer: "));
				panel.add(new JLabel(Integer.toString(ergebnisMenge.getInt(1))));
				panel.add(new JLabel("Vorname: "));
				panel.add(new JLabel(ergebnisMenge.getString(2)));
				panel.add(new JLabel("Nachname: "));
				panel.add(new JLabel(ergebnisMenge.getString(3)));
				panel.add(new JLabel("Straße: "));
				panel.add(new JLabel(ergebnisMenge.getString(4)));
				panel.add(new JLabel("PLZ: "));
				panel.add(new JLabel(ergebnisMenge.getString(5)));
				panel.add(new JLabel("Ort: "));
				panel.add(new JLabel(ergebnisMenge.getString(6)));
				panel.add(new JLabel("Telefon: "));
				panel.add(new JLabel(ergebnisMenge.getString(7)));
				panel.add(new JLabel("------------------"));
				panel.add(new JLabel("------------------"));
			}
			add(pane);
			
			ergebnisMenge.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:adressenDB");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
	}
	
	
	
	
}
