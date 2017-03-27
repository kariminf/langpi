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


package kariminf.langpi.wordnet;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IPointer;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.ISynsetID;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.item.WordID;
import edu.mit.jwi.morph.WordnetStemmer;

public class WordNetTest {

	public static void testWordSearch() throws IOException{

		// construct the URL to the Wordnet dictionary directory
		File file =new File("wordnetDB/dict/");
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(file) ;
		dict.open();
		
		IIndexWord idxWord = dict.getIndexWord ("midnight" , POS.NOUN) ;
		if(idxWord == null) System.out.println("no word\n--------\n");
		
		for (IWordID iword: idxWord.getWordIDs()){
			System.out.println("offset: " + iword.getSynsetID().getOffset());
			IWord word = dict.getWord(iword);
			System.out.println("gloss: " + word.getSynset().getGloss());
		}
		
		//System.out.println("offset: " + idxWord.getWordIDs().get(0).getSynsetID().getOffset());

		dict.close();
	}
	
	public static void  testTopic() throws IOException{
		// construct the URL to the Wordnet dictionary directory
		File file =new File("wordnetDB/dict/");
		// construct the dictionary object and open it
		IDictionary dict = new Dictionary(file) ;
		dict.open();
		// look up first sense of the word " dog "
		WordnetStemmer ws = new WordnetStemmer(dict);
		
		
		//dog: 10114209
		//car: 2958343
		//home: 8559508
		//midnight
		IWordID wordID1 = new WordID(15168185,POS.NOUN,1);
		IWord word1 = dict.getWord(wordID1);
		
		System.out.println("LexFile = " + word1.getSynset().getLexicalFile());
		
		System.out.println("Lemma = " + word1.getLemma());
		
		System.out.println("Gloss = " + word1.getSynset().getGloss()) ;
		
		System.out.print("Synonyms: ");
		for(IWord iword : word1.getSynset().getWords())
			System.out.print(iword.getLemma() + ", ");
		System.out.println("\n-----------------------") ;
		System.out.println("related: ") ;
		
		Map<IPointer, List<ISynsetID>> k = word1.getSynset().getRelatedMap();
		for(IPointer ip : k.keySet()){
			System.out.print(ip.getName() + ": ");
			for (ISynsetID isid : k.get(ip)){
				System.out.print(isid.getOffset() + ".");
				ISynset ss = dict.getSynset(isid);
				System.out.print(ss.getPOS() + "=");
				IWord iw = dict.getWord(new WordID(ss.getOffset(),ss.getPOS(), 1));
				System.out.print(iw.getLemma() + ", ");
			}
				
			System.out.println();
		}
		
		
		/*
		for(IWordID wordID: idxWord.getWordIDs()){
			IWord word = dict.getWord(wordID);
			
			//System.out.println("Sence = " + word.getSenseKey().getLexicalID()) ;
			System.out.println("Lemma = " + word.getLemma()) ;
			System.out.println("Gloss = " + word.getSynset().getGloss()) ;
			
			System.out.println("Synonyms: ");
			
			for(IWord iword : word.getSynset().getWords())
				System.out.print(iword.getLemma() + ", ");
			System.out.println("\n-----------------------");
			
		}
		*/
		
		dict.close();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			testTopic();
			//testWordSearch();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}


}
