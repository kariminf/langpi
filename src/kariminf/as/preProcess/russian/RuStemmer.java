
package kariminf.as.preProcess.russian;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.RussianStemmer;

import kariminf.as.preProcess.lang.Stemmer;

public class RuStemmer implements Stemmer {
	
	private RussianStemmer stemmer = new RussianStemmer();

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
		Stemmer Stemmer=new RuStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("источники");
		tstList.add("информации");
		tstList.add("одинаковую");
		tstList.add("картину");
		tstList.add("точностью");
		tstList.add("погрешности");
		tstList.add("используемых");
		tstList.add("методов");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





