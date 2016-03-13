package kariminf.langpi.eval.ats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



import aak.as.preProcess.def.DefSegmenter;
import aak.as.preProcess.def.DefStemmer;
import aak.as.preProcess.lang.PreProcessInfo;
import aak.as.preProcess.lang.Segmenter;
import aak.as.preProcess.lang.Stemmer;
import aak.as.tools.Calculus;
import aak.as.tools.FileManager;
import aak.as.tools.JarLoader;

public class MssStats {

	private static final String peerFolder = "/home/kariminf/Data/ATS/multilingMss2015Training/training2015/";

	private static final String[] langs = {"ko"};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (String lang : langs) {// langs

			File langFolder = new File(peerFolder + lang + "/");
			if (!langFolder.exists())
				continue;
			if (!langFolder.isDirectory())
				continue;

			File[] filesFolders = langFolder.listFiles();
			
			String csv = "";
			for (File fileFolder : filesFolders) {
				if (!fileFolder.exists())
					continue;
				if (!fileFolder.isDirectory())
					continue;
				File stat = new File(fileFolder.getAbsolutePath() + "/stats.csv");
				if (!stat.exists())
					continue;
				csv += readStats(stat);

			} // files
			
			try {
				FileManager.saveFile(peerFolder + "ROUGE/csv/" + lang + "stat.csv", csv);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}// languages

	}

	private static String readStats(File file){
		try {
			String contents = file.getParent();
			contents = contents.substring(contents.lastIndexOf("/")+1) + "\n";

			BufferedReader input = new BufferedReader(new FileReader(file));
			boolean begin = true;
			for(String line = input.readLine(); line != null; line = input.readLine()) {
				if (begin){
					begin = false;
					continue;
				}
				if (line.startsWith("00") || line.length() < 1)
					break;
				contents += line + "\n";
			}
			input.close();

			return contents + "\n";

		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
			return "";
		} 
	}

}
