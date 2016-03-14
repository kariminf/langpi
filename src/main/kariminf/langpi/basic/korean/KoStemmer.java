package kariminf.langpi.basic.korean;

import java.util.ArrayList;
import java.util.List;


import kariminf.langpi.basic.Stemmer;

public class KoStemmer implements Stemmer {

	//TODO find a stemmer
	
	
	@Override
	public List<String> stemListWords(List<String> listWords) {
		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
			
		return stemmedLW;
	}
	
	public String stemWord (String word){
		
		return word;
	}
	
	public static void main(String[] args)
	{
		
		KoStemmer Stemmer=new KoStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("대학");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	
	   
	}	

}
