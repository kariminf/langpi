
package kariminf.as.preProcess.bulgarian;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.bg.BulgarianStemmer;

import dz.aak.as.preProcess.lang.Stemmer;

public class BgStemmer implements Stemmer {

	private BulgarianStemmer stemmer = new BulgarianStemmer();
	
	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public String stemWord (String word){
		
		
		char[] wordseq = word.toCharArray();
		int newlength = stemmer.stem(wordseq, wordseq.length);
		
		char[] newwordseq = new char[newlength];
		System.arraycopy(wordseq, 0, newwordseq, 0, newlength);

		return new String(newwordseq);
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new BgStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("модулът");
		tstList.add("ще");
		tstList.add("побира");
		tstList.add("до");
		tstList.add("шестима");
		tstList.add("души");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





