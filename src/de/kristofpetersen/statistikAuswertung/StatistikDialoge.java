package de.kristofpetersen.statistikAuswertung;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class StatistikDialoge {
	
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
			return "CSV-Dateien";
		}
	}
	
	public File oeffnenDialogZeigen() {
		JFileChooser oeffnenDialog= new JFileChooser();
		oeffnenDialog.setFileFilter(new MeinFilter());
		oeffnenDialog.setAcceptAllFileFilterUsed(false);
		int status = oeffnenDialog.showOpenDialog(null);
		if(status == JFileChooser.APPROVE_OPTION)
			return(oeffnenDialog.getSelectedFile());
		else
			return null;
	}
	
	public File speichernDialogZeigen() {
		JFileChooser speichernDialog = new JFileChooser();
		speichernDialog.setFileFilter(new MeinFilter());
		speichernDialog.setAcceptAllFileFilterUsed(false);
		int status= speichernDialog.showSaveDialog(null);
		if(status== JFileChooser.APPROVE_OPTION)
			return (speichernDialog.getSelectedFile());
		else 
			return null;
	}
}
