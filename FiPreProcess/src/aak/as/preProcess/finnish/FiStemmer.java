
package aak.as.preProcess.finnish;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.finnishStemmer;

import aak.as.preProcess.lang.Stemmer;

public class FiStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		finnishStemmer stemmer = new finnishStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		FiStemmer Stemmer=new FiStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("tekemään");
		lst.add("saadakseen");
		lst.add("valtioilta");
		lst.add("vaatimansa");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





