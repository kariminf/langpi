
package aak.as.preProcess.persian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JHazm.Lemmatizer;

import aak.as.preProcess.lang.Stemmer;

public class FaStemmer implements Stemmer {

	Lemmatizer lematizer;
	
	public FaStemmer(){
		try {
			lematizer = new Lemmatizer();
		} catch (IOException e) {
			System.out.println("no persian lematizer");
			lematizer = null;
		}
	}
	
	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public String stemWord (String word){
		
		if (lematizer != null)
			return lematizer.Lemmatize(word);

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





