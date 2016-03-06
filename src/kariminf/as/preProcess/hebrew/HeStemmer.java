
package kariminf.as.preProcess.hebrew;

import java.util.ArrayList;
import java.util.List;

import com.code972.hebmorph.HebrewToken;
import com.code972.hebmorph.Lemmatizer;
import com.code972.hebmorph.hspell.HSpellLoader;


import dz.aak.as.preProcess.lang.Stemmer;


/**
* Stemmer, actualy it is a lemmatizer
*
* The Stemmer class transforms a word into its root form.  The input
* word can be provided a character at time (by calling add()), or at once
* by calling one of the various stem(something) methods.
*/

public class HeStemmer implements Stemmer {

	private Lemmatizer sf = new Lemmatizer(HSpellLoader.getDictHebMorph());
	
	@Override
	public List<String> stemListWords(List<String> listWords) {

		List<String> output = new ArrayList<String>();
		
		for (String word: listWords)
			output.add(stemWord(word));

		return output;
	}
	
	public String stemWord (String word){
		List<HebrewToken> lht = sf.lemmatize(word);
		String newword = word;
		if (lht.size() > 0)
			newword = lht.get(0).getLemma();
		
		return newword;
	}
	
	
	public static void main(String[] args) {
		List<String> tstList = new ArrayList<String>();
		tstList.add("הצונאמי");
		tstList.add("התרומות");
		tstList.add("הוצאותיהם");      
		tstList.add("לקורבנות");
		
		HeStemmer stemmer = new HeStemmer();
		List<String> outList = stemmer.stemListWords(tstList);
		
		System.out.println(tstList.toString());
		System.out.println(outList.toString());

	}
	   
	

}





