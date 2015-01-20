
package aak.as.preProcess.bulgarian;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.bg.BulgarianStemmer;

import aak.as.preProcess.lang.Stemmer;

public class BgStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		
		char[] wordseq = word.toCharArray();
		int newlength = word.length();
		{
			BulgarianStemmer stemmer = new BulgarianStemmer();
			newlength = stemmer.stem(wordseq, newlength);
		}
		
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





