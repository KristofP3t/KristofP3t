package de.kristofpetersen.email;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Anzeige extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7573414237517903081L;
	private JTextField empfaengerFeld, betreffFeld;
	private JTextArea inhaltFeld;
	private JButton ok;
	
	class NeuListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("ok"))
				dispose();
			
		}
		
	}
	
	public Anzeige (JFrame parent, boolean modal, String ID, String empfaenger, String betreff, String inhalt) {
		super(parent, modal);
		setTitle("Anzeige");
		initGUI(ID, empfaenger, betreff, inhalt);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void initGUI(String iD, String empfaenger, String betreff, String inhalt) {
		setLayout(new BorderLayout());
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(0,2));
		
		oben.add(new JLabel("Empfänger:"));
		empfaengerFeld = new JTextField(empfaenger);
		oben.add(empfaengerFeld);
		oben.add(new JLabel("Betreff:"));
		betreffFeld = new JTextField(betreff);
		oben.add(betreffFeld);
		add(oben, BorderLayout.NORTH);
		inhaltFeld = new JTextArea(inhalt);
		inhaltFeld.setLineWrap(true);
		inhaltFeld.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(inhaltFeld);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		
		empfaengerFeld.setEditable(false);
		betreffFeld.setEditable(false);
		inhaltFeld.setEditable(false);
		
		JPanel unten = new JPanel();
		ok = new JButton("OK");
		ok.setActionCommand("ok");
		
		NeuListener listener = new NeuListener();
		ok.addActionListener(listener);
		
		unten.add(ok);
		add(unten, BorderLayout.SOUTH);
		 
		setSize(600,300);
		setVisible(true);
	}
}
