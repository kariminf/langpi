
package aak.as.preProcess.french;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.frenchStemmer;

import aak.as.preProcess.lang.Stemmer;

public class FrStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		frenchStemmer stemmer = new frenchStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		FrStemmer Stemmer=new FrStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("reprendrait");
		lst.add("information");
		lst.add("détournement");
		lst.add("applaudissement");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





