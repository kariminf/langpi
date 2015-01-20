
package aak.as.preProcess.hungarian;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.hungarianStemmer;

import aak.as.preProcess.lang.Stemmer;

public class HuStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		hungarianStemmer stemmer = new hungarianStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		HuStemmer Stemmer=new HuStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("a");
		tstList.add("római");
		tstList.add("korban");
		tstList.add("sok");
		tstList.add("államelmélet");
		tstList.add("foglalkozott");
		tstList.add("a");
		tstList.add("spártai");
		tstList.add("alkotmánnyal");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





