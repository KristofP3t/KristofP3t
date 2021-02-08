package de.kristofpetersen.email;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MiniMailStart extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1465509452781251681L;
	
	class MeinListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("senden"))
				senden();
			if (e.getActionCommand().equals("empfangen"))
				empfangen();
			if (e.getActionCommand().equals("ende"))
				beenden();
			
		}
	}
	
	public MiniMailStart(String titel) {
		super(titel);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton liste = new JButton("Senden");
		liste.setActionCommand("senden");
		JButton einzel = new JButton("Empfangen");
		einzel.setActionCommand("empfangen");
		JButton beenden = new JButton("Beenden");
		beenden.setActionCommand("ende");
		
		MeinListener listener = new MeinListener();
		liste.addActionListener(listener);
		einzel.addActionListener(listener);
		beenden.addActionListener(listener);
		
		add(einzel);
		add(liste);
		add(beenden);
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		}
	
	private void beenden() {
		System.exit(0);		
	}

	private void empfangen() {
		new Empfangen();
		
	}

	private void senden() {
		new Senden();
		
	}

}
