package dz.aak.as.preProcess.english;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.PorterStemmer;

import dz.aak.as.preProcess.lang.Stemmer;

public class EnStemmer implements Stemmer {

	private PorterStemmer porter = new PorterStemmer();
	
	@Override
	public List<String> stemListWords(List<String> listWords) {
		List<String> stemmedLW = new ArrayList<String>();
		
		for (String word: listWords)
			stemmedLW.add(stemWord(word));
			
		return stemmedLW;
	}
	
	public String stemWord (String word){
		porter.setCurrent(word);
		porter.stem();
		
		return porter.getCurrent();
	}
	
	public static void main(String[] args)
	{
		
		EnStemmer Stemmer=new EnStemmer();
		List<String> lst = new ArrayList<String>();
		lst.add("informatics");
		lst.add("statistical");
		lst.add("itersection");
		lst.add("learning");
	
		lst = Stemmer.stemListWords(lst);
		System.out.println(lst.toString());	
	   
	}	

}
