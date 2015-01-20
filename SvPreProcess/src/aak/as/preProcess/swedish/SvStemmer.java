
package aak.as.preProcess.swedish;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.swedishStemmer;

import aak.as.preProcess.lang.Stemmer;

public class SvStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		swedishStemmer stemmer = new swedishStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new SvStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("som");
		tstList.add("d");
		tstList.add("holbach");
		tstList.add("kallas");
		tstList.add("de");
		tstList.add("inkompatibilister");
		tstList.add("som");
		tstList.add("accepterar");
		tstList.add("determinism");
		tstList.add("och");
		tstList.add("förkastar");
		tstList.add("fri");
		tstList.add("vilja");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





