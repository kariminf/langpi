
package aak.as.preProcess.italian;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.italianStemmer;

import aak.as.preProcess.lang.Stemmer;

public class ItStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		italianStemmer stemmer = new italianStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		ItStemmer Stemmer=new ItStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("il");
		tstList.add("risultato");
		tstList.add("della");
		tstList.add("prima");
		tstList.add("campagna");
		tstList.add("fu");
		tstList.add("l");
		tstList.add("assedio");
		tstList.add("della");
		tstList.add("capitale");
		tstList.add("dacica");
		tstList.add("sarmizegetusa");
		tstList.add("regia");
		tstList.add("e");
		tstList.add("l");
		tstList.add("occupazione");
		tstList.add("di");
		tstList.add("parte");
		tstList.add("del");
		tstList.add("suo");
		tstList.add("territorio");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





