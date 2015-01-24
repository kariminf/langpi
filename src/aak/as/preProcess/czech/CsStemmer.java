
package aak.as.preProcess.czech;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.czechStemmer;

import aak.as.preProcess.lang.Stemmer;

public class CsStemmer implements Stemmer {
	
	private czechStemmer stemmer = new czechStemmer();
	
	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public String stemWord (String word){

		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
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





