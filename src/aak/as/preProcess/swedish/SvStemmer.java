
package aak.as.preProcess.swedish;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.SwedishStemmer;

import aak.as.preProcess.lang.Stemmer;

public class SvStemmer implements Stemmer {
	
	private SwedishStemmer stemmer = new SwedishStemmer();

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
		Stemmer Stemmer=new SvStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("holbach");
		tstList.add("kallas");
		tstList.add("inkompatibilister");
		tstList.add("accepterar");
		tstList.add("determinism");
		tstList.add("förkastar");
		tstList.add("vilja");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}

