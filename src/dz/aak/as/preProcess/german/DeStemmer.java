
package dz.aak.as.preProcess.german;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.GermanStemmer;

import dz.aak.as.preProcess.lang.Stemmer;

public class DeStemmer implements Stemmer {
	
	private GermanStemmer stemmer = new GermanStemmer();

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
		DeStemmer Stemmer=new DeStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("jährigen");
		lst.add("bestehen");
		lst.add("friedhofs");
		lst.add("jubiläum");
		lst.add("bücher");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





