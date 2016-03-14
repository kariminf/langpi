/* NaLanGen: Natural Language Generation tool:
 * It contains tools to generate texts in many languages
 * --------------------------------------------------------------------
 * Copyright (C) 2015 Abdelkrime Aries (kariminfo0@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package kariminf.nalangen.wordnet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISenseKey;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.WordID;

public class JWIRequestor implements WNRequestor {

	private IDictionary dict;
	
	private JWIRequestor(IDictionary dict) {
		this.dict = dict;
	}
	
	public static JWIRequestor create(String wordnetPath){
		File file =new File(wordnetPath);
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(file) ;
		return new JWIRequestor (dict);
	}

	@Override
	public String getWord(int synset, String pos) {
		try {
			dict.open();
		} catch (IOException e) {
			return "";
		}
		IWordID wordID1 = new WordID(synset,POS.valueOf(pos),1);
		IWord word1 = dict.getWord(wordID1);
		
		String lemma = word1.getLemma();
		
		dict.close();
		
		return lemma;
	}

	@Override
	public List<String> getWords(int synset, String pos) {
		ArrayList<String> words = new ArrayList<String>();
		
		try {
			dict.open();
		} catch (IOException e) {
			return words;
		}
		
		IWordID wordID1 = new WordID(synset,POS.valueOf(pos),1);
		IWord word1 = dict.getWord(wordID1);
		ISynset isynset = word1.getSynset() ;
		
		for( IWord word : isynset.getWords () )
			words.add(word.getLemma());
			
		return words;
	}

	@Override
	public int getSynset(String word, String pos) {
		try {
			dict.open();
		} catch (IOException e) {
			return -1;
		}
		
		IIndexWord idxWord = dict . getIndexWord (word, POS.valueOf(pos)) ;
		IWordID wordID = idxWord.getWordIDs().get(0) ;
		
		return wordID.getSynsetID().getOffset();
	}

}
