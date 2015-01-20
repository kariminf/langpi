
package aak.as.preProcess.basque;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.BasqueStemmer;

import aak.as.preProcess.lang.Stemmer;

public class EuStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		BasqueStemmer stemmer = new BasqueStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new EuStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("bitxia");
		tstList.add("denez");
		tstList.add("left");
		tstList.add("hitzak");
		tstList.add("ere");
		tstList.add("ganorabako");
		tstList.add("edo");
		tstList.add("utzia");
		tstList.add("bezala");
		tstList.add("ere");
		tstList.add("itzuli");
		tstList.add("daiteke");
		tstList.add("right");
		tstList.add("zuzena");
		tstList.add("den");
		tstList.add("bitartean");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





