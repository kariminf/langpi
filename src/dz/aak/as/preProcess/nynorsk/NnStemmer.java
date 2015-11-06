
package dz.aak.as.preProcess.nynorsk;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.no.NorwegianLightStemmer;

import aak.as.preProcess.lang.Stemmer;

public class NnStemmer implements Stemmer {
	
	//remove Bokmål-specific endings = 1
	//remove Nynorsk-specific endings = 2
	private NorwegianLightStemmer stemmer = new NorwegianLightStemmer(2);

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
		Stemmer Stemmer=new NnStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("fitjar");
		tstList.add("kommune");
		tstList.add("består");
		tstList.add("den");
		tstList.add("nordlege");
		tstList.add("halvdelen");
		tstList.add("øya");
		tstList.add("stord");
		tstList.add("fitjarøyane");
		tstList.add("mellom");
		tstList.add("stord");
		tstList.add("bømlo");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





