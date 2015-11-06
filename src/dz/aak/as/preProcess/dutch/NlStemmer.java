
package dz.aak.as.preProcess.dutch;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.DutchStemmer;

import dz.aak.as.preProcess.lang.Stemmer;

public class NlStemmer implements Stemmer {

	private DutchStemmer stemmer = new DutchStemmer();
	
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
		NlStemmer Stemmer=new NlStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("ongediplomeerd");
		lst.add("junghuhns");
		lst.add("mijnarts");
		lst.add("predikant");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





