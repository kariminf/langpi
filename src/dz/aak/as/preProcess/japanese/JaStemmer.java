
/*

Porter stemmer in Java. The original paper is in

    Porter, 1980, An algorithm for suffix stripping, Program, Vol. 14,
    no. 3, pp 130-137,

See also http://www.tartarus.org/~martin/PorterStemmer

History:

Release 1

Bug 1 (reported by Gonzalo Parra 16/10/99) fixed as marked below.
The words 'aed', 'eed', 'oed' leave k at 'a' for step 3, and b[k-1]
is then out outside the bounds of b.

Release 2

Similarly,

Bug 2 (reported by Steve Dyrdahl 22/2/00) fixed as marked below.
'ion' by itself leaves j = -1 in the test for 'ion' in step 5, and
b[j] is then outside the bounds of b.

Release 3

Considerably revised 4/9/00 in the light of many helpful suggestions
from Brian Goetz of Quiotix Corporation (brian@quiotix.com).

Release 4

*/

package dz.aak.as.preProcess.japanese;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.ja.JapaneseBaseFormFilter;
import org.apache.lucene.analysis.ja.JapaneseKatakanaStemFilter;
import org.apache.lucene.analysis.ja.tokenattributes.BaseFormAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


import dz.aak.as.preProcess.lang.Stemmer;


/**
* Stemmer, implementing the Porter Stemming Algorithm
*
* The Stemmer class transforms a word into its root form.  The input
* word can be provided a character at time (by calling add()), or at once
* by calling one of the various stem(something) methods.
*/

public class JaStemmer implements Stemmer {

	protected static HashMap<String,String> lemma = new HashMap<String,String>();
	
	@Override
	public List<String> stemListWords(List<String> listWords) {
		List<String> output = new ArrayList<String>();
		
		for(String word:listWords)
			output.add(stemWord(word));

		return output;
	}
	
	public String stemWord (String word){
		return (lemma.containsKey(word))?lemma.get(word):word;
	}
	
	
	public static void main(String[] args) {
		
		JaStemmer Stemmer=new JaStemmer();
		List<String> tstList = new ArrayList<String>();
		tstList.add("多く");
		tstList.add("学生");
		tstList.add("試験");
		tstList.add("落ち");
		
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	   
	

}





