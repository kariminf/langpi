
package aak.as.preProcess.romanian;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.RomanianStemmer;

import aak.as.preProcess.lang.Stemmer;

public class RoStemmer implements Stemmer {
	
	RomanianStemmer stemmer = new RomanianStemmer();

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
		RoStemmer stemmer=new RoStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("Ctitorită"); 
		lst.add("documentară"); 
		lst.add("Mănăstirea");
		lst.add("Naţionale");
	
		lst = stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





