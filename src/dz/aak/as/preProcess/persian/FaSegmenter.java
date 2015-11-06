/*
 * AllSumarizer v2
 * This file is part of AllSummarizer project; an implementation of the method
 * described in this paper:
 * http://dx.doi.org/10.1117/12.2004001
 * 
 * Copyright (C) 2013  Abdelkrime Aries <kariminfo0@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dz.aak.as.preProcess.persian;

import hazm.jhazm.PersianSentTokenizer;
import hazm.jhazm.PersianWordTokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.Segmenter;


public class FaSegmenter implements Segmenter {

	private final String punctuation = "\\.،؛:\"\'؟([?]+|:»)}\"«{";
	PersianSentTokenizer sentSegmenter =  new PersianSentTokenizer();
	PersianWordTokenizer wordTokenizer;
	
	public FaSegmenter(){
		 try {
			wordTokenizer = new PersianWordTokenizer();
		} catch (IOException e) {
			wordTokenizer = null;
		}
	}
	
	@Override
	public List<String> splitToSentences(String text) {
	    return sentSegmenter.Tokenize(text);
	}

	
	@Override
	public List<String> segmentWords(String text) {
		if (wordTokenizer != null){
			List<String> words = wordTokenizer.Tokenize(text);
			deletePunctuation(words);
			return (words);
		}
		System.out.println("no persian tokenizer");
		
		return segmentWordsDef(text);
	}
	
	
	private List<String> segmentWordsDef(String text){
		List<String> ret = new ArrayList<String>();
	    for(String word:  text.split("[\\.،؛:\"\'؟\\!]?\\s+|\\.$")){
	    	//word = word.replace(" ", "");
	    	if(word.length() > 0)
	        ret.add(word.trim());//.toLowerCase().trim()
	    	
	    }
	      
	 
	    return ret;
	}
	
	private void deletePunctuation (List<String> words){
		for(int i=words.size()-1; i>=0; i--){
			if(words.get(i).length() < 1 
					|| punctuation.contains(words.get(i).substring(0, 1)))
				words.remove(i);
		}
			
	}
	
	public static void main(String[] args) {
		
		Segmenter segmenter = new FaSegmenter();
		List<String> sent = segmenter.segmentWords("قسمتی از آبشار نیاگارا در مرز بین آمریکا و کانادا قرار دارد و ازجاذبه‌های طبیعی توریستی در آمریکا به شمار می‌رود.");
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
