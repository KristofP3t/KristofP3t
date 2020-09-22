package de.kristofpetersen.statistikAuswertung;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class StatistikDialoge {
	//eine Innere Klasse für den Filter
	class MeinFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			//für den kompakteren Zugriff
			String name = f.getName().toLowerCase();
			if(f.isDirectory())
				return true;
			if (name.endsWith(".csv"))
				return true;
			return false;
		}

		@Override
		public String getDescription() {
			return "Textdateien";
		}
	}
	
	//die Methode zeigt den Öffnendialog für eine Datei
	public File oeffnenDialogZeigen() {
		//einen neuen Dialog erzeugen
		JFileChooser oeffnenDialog= new JFileChooser();
		oeffnenDialog.setFileFilter(new MeinFilter());
		oeffnenDialog.setAcceptAllFileFilterUsed(false);
		//den DIalog anzeugen und den Status holen
		int status = oeffnenDialog.showOpenDialog(null);
		//wurde auf Öffnen geklickt, dann die ausgewählte Datei als Zeichenkette ausgeben
		if(status == JFileChooser.APPROVE_OPTION)
			return(oeffnenDialog.getSelectedFile());
		else
			return null;
	}
	public File speichernDialogZeigen() {
		JFileChooser speichernDialog = new JFileChooser();
		speichernDialog.setFileFilter(new MeinFilter());
		speichernDialog.setAcceptAllFileFilterUsed(false);
		//den Dialog anzeigen und den Status holen
		//hier wird die Methode showSaveDialog() aufgerufen
		int status= speichernDialog.showSaveDialog(null);
		//wurde auf Save geklickt, dann die ausgewählte Datei als Typ File zurückliefern
		if(status== JFileChooser.APPROVE_OPTION)
			return (speichernDialog.getSelectedFile());
		else 
			return null;
	}
}
