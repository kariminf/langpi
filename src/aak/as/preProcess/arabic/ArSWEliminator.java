package aak.as.preProcess.arabic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.SWEliminator;

public class ArSWEliminator implements SWEliminator {

	private final String  pathToStopWords = "/ressources/stopWords/arabic.stop";
	private List<String> stopList = 
			getStopList(pathToStopWords);//stopwords.txt

	private List<String> getStopList(String path) {

		List<String> stopwords = new ArrayList<String>();
		try {

			InputStream in = getClass().getResourceAsStream(path);
			InputStreamReader ir = new InputStreamReader(in, "UTF-8");
			BufferedReader input = new BufferedReader(ir);
			for(String line = input.readLine(); line != null; line = input.readLine())
				stopwords.add(line);
			input.close();
			
			return stopwords;

		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		} 
	}

	private Boolean isStopWord(String word) {
	    word = word.toLowerCase().trim();
	    if (stopList.contains(word))
			return true;
		
		return false;
	}
	
	public void deleteSW(List<String> wordsList){
		for(int i=wordsList.size()-1; i >=0; i--)
			if (isStopWord(wordsList.get(i)))
				wordsList.remove(i);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> tstList = new ArrayList<String>();
		tstList.add("أنا");
		tstList.add("سأذهب");
		tstList.add("إلى");
		tstList.add("المحل");
		tstList.add("المجاور");
		tstList.add("ثم");
		tstList.add("أعود");
		tstList.add("بعد");
		tstList.add("ذلك");
		tstList.add("للعمل");
		tstList.add("من");
		tstList.add("جديد");
		
		ArSWEliminator eliminator = new ArSWEliminator();
		eliminator.deleteSW(tstList);
		
		System.out.println(tstList.toString());

	}

}
