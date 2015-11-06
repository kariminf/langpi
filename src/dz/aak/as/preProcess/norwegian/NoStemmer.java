
package dz.aak.as.preProcess.norwegian;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.no.NorwegianLightStemmer;

import aak.as.preProcess.lang.Stemmer;

public class NoStemmer implements Stemmer {
	
	//remove Bokmål-specific endings = 1
	//remove Nynorsk-specific endings = 2
	private NorwegianLightStemmer stemmer = new NorwegianLightStemmer(3);

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public String stemWord (String word){
		
		
		char[] wordseq = word.toCharArray();
		int newlength = stemmer.stem(wordseq, word.length());
		
		char[] newwordseq = new char[newlength];
		System.arraycopy(wordseq, 0, newwordseq, 0, newlength);

		return new String(newwordseq);
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new NoStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("utallige");
		tstList.add("slike");
		tstList.add("tilfeller");
		tstList.add("nåde");
		tstList.add("nedtegnet");
		tstList.add("ulike");
		tstList.add("asiatiske");
		tstList.add("kongedømmer");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





