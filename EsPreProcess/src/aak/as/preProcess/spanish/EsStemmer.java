
package aak.as.preProcess.spanish;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.spanishStemmer;

import aak.as.preProcess.lang.Stemmer;

public class EsStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		spanishStemmer stemmer = new spanishStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		EsStemmer stemmer=new EsStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("checa"); //chec
		lst.add("tórax"); //torax
		lst.add("chetumaleños");//chetumaleñ
		lst.add("toros"); //tor
	
		lst = stemmer.stemListWords(lst);
		System.out.println(lst.toString());	

	}
	
	
}





