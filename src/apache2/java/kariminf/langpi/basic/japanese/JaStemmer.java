
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

package kariminf.langpi.basic.japanese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import kariminf.langpi.basic.Stemmer;


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
		
		JaSegmenter segmenter = new JaSegmenter();
		JaStemmer Stemmer=new JaStemmer();
		List<String> tstList = segmenter.segmentWords("先生は学生に早く行きます");
		
		System.out.println(tstList.toString());	
		tstList = Stemmer.stemListWords(tstList);
		System.out.println(tstList.toString());	

	}
	   
	

}





