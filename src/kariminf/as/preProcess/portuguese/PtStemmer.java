
package kariminf.as.preProcess.portuguese;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.PortugueseStemmer;

import kariminf.as.preProcess.lang.Stemmer;

public class PtStemmer implements Stemmer {
	
	private PortugueseStemmer stemmer = new PortugueseStemmer();

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
		Stemmer Stemmer=new PtStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("o");
		tstList.add("condor");
		tstList.add("da");
		tstList.add("califórnia");
		tstList.add("foi");
		tstList.add("descrito");
		tstList.add("pelo");
		tstList.add("naturalista");
		tstList.add("inglês");
		tstList.add("george");
		tstList.add("shaw");
		tstList.add("em");
		tstList.add("1797");
		tstList.add("como");
		tstList.add("vultur");
		tstList.add("californianus");
	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	
	
}





