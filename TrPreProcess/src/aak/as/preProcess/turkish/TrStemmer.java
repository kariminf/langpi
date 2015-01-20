
package aak.as.preProcess.turkish;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.turkishStemmer;

import aak.as.preProcess.lang.Stemmer;

public class TrStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		turkishStemmer stemmer = new turkishStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new TrStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("kurucu");
		tstList.add("yönetmeni");
		tstList.add("olmasına");
		tstList.add("rağmen");
		tstList.add("tiyatrodaki");
		tstList.add("kariyeri");
		tstList.add("ihanet");
		tstList.add("ve");
		tstList.add("trajediyle");
		tstList.add("sona");
		tstList.add("erdi");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





