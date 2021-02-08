package de.kristofpetersen.dateiverwaltung;

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
import javax.swing.JTextField;

public class NeuerEintrag extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name, nachname, strasse, plz, ort, telefon;
	private JButton ok, abbrechen;
	
	class NeuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ok")) {
				uebernehmen();
			}
			if (e.getActionCommand().equals("abbrechen")) {
				dispose();
			}	
		}
	}
	
	public NeuerEintrag(JFrame parent, boolean modal) {
		super(parent, modal);
		setTitle("Neuer Eintrag");
		initGUI();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void initGUI() {
		setLayout(new GridLayout(0, 2));
		add(new JLabel("Vorname:"));
		name = new JTextField();
		add(name);
		add(new JLabel("Nachname:"));
		nachname = new JTextField();
		add(nachname);
		add(new JLabel("Straße:"));
		strasse = new JTextField();
		add(strasse);
		add(new JLabel("PLZ:"));
		plz = new JTextField();
		add(plz);
		add(new JLabel("Ort:"));
		ort = new JTextField();
		add(ort);
		add(new JLabel("Telefon:"));
		telefon = new JTextField();
		add(telefon);
		
		ok = new JButton("OK");
		ok.setActionCommand("ok");
		abbrechen = new JButton("Abbrechen");
		abbrechen.setActionCommand("abbrechen");
		NeuListener listener = new NeuListener();
		ok.addActionListener(listener);
		abbrechen.addActionListener(listener);
		add(ok);
		add(abbrechen);
		
		pack();
		setVisible(true);
		
	}

	private void uebernehmen() {
		Connection verbindung;
		ResultSet ergebnisMenge;
		try {
			verbindung = MiniDBTools.oeffnenDB("jdbc:derby:adressenDB");
			ergebnisMenge = MiniDBTools.liefereErgebnis(verbindung, "SELECT * FROM adressen");
			ergebnisMenge.moveToInsertRow();
			ergebnisMenge.updateString(2, name.getText());
			ergebnisMenge.updateString(3, nachname.getText());
			ergebnisMenge.updateString(4, strasse.getText());
			ergebnisMenge.updateString(5, plz.getText());
			ergebnisMenge.updateString(6, ort.getText());
			ergebnisMenge.updateString(7, telefon.getText());
			ergebnisMenge.insertRow();
			ergebnisMenge.close();
			verbindung.close();
			MiniDBTools.schliessenDB("jdbc:derby:adressenDB");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Problem:\n" + e.toString());
		}
		
	}

}
