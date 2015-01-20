
package aak.as.preProcess.thai;

import java.util.ArrayList;
import java.util.List;

import aak.as.preProcess.lang.Stemmer;

public class ThStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){

		return word;
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new ThStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("แต่ละ");
		tstList.add("เรื่อง");
		tstList.add("เกี่ยวกับ");
		tstList.add("ฮัลโลวีน");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





