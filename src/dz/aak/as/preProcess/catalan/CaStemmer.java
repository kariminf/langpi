
package dz.aak.as.preProcess.catalan;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.CatalanStemmer;

import aak.as.preProcess.lang.Stemmer;

public class CaStemmer implements Stemmer {
	
	CatalanStemmer stemmer = new CatalanStemmer();

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
		Stemmer Stemmer=new CaStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("jordan");
		tstList.add("va");
		tstList.add("rebre");
		tstList.add("una");
		tstList.add("beca");
		tstList.add("per");
		tstList.add("jugar");
		tstList.add("a");
		tstList.add("bàsquet");
		tstList.add("a");
		tstList.add("la");
		tstList.add("universitat");
		tstList.add("de");
		tstList.add("carolina");
		tstList.add("del");
		tstList.add("nord");
		tstList.add("on");
		tstList.add("s");
		tstList.add("especialitzà");
		tstList.add("en");
		tstList.add("geografia");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





