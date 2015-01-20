
package aak.as.preProcess.persian;

import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.Stemmer;

public class FaStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		//TODO find a stemmer if there is one

		return word;
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new FaStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("قسمتی");
		tstList.add("آبشار");
		tstList.add("نیاگارا");
		tstList.add("بین");
		tstList.add("آمریکا");
		tstList.add("کانادا");
		tstList.add("قرار");
		tstList.add("دارد");
		tstList.add("ازجاذبه‌های");
		tstList.add("طبیعی");
		tstList.add("توریستی");
		tstList.add("آمریکا");
		tstList.add("شمار");
		tstList.add("می‌رود");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





