
package aak.as.preProcess.russian;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.russianStemmer;

import aak.as.preProcess.lang.Stemmer;

public class RuStemmer implements Stemmer {

	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
		
		return stemmedLW;
	}
	
	
	public static String stemWord (String word){
		
		russianStemmer stemmer = new russianStemmer();
		
		stemmer.setCurrent(word);
		stemmer.stem();
		return stemmer.getCurrent();
	}
	
	public static void main(String[] args) {
		Stemmer Stemmer=new RuStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("все");
		tstList.add("эти");
		tstList.add("источники");
		tstList.add("информации");
		tstList.add("дают");
		tstList.add("одинаковую");
		tstList.add("картину");
		tstList.add("с");
		tstList.add("точностью");
		tstList.add("до");
		tstList.add("погрешности");
		tstList.add("используемых");
		tstList.add("методов");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





