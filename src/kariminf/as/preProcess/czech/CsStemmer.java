
package kariminf.as.preProcess.czech;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.cz.CzechStemmer;

import dz.aak.as.preProcess.lang.Stemmer;

public class CsStemmer implements Stemmer {
	
	private CzechStemmer stemmer = new CzechStemmer();
	
	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	public String stemWord (String word){
		char[] wordarray = word.toCharArray();
		int newlength = stemmer.stem(wordarray, wordarray.length);
		return new String(wordarray, 0, newlength);
	}
	
	public static void main(String[] args) {
		CsStemmer stemmer=new CsStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("sebevražedný");
		lst.add("londýnského");
		lst.add("právníka");
		lst.add("odposlechy");
	
		lst = stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





