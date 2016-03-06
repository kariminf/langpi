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

package kariminf.as.preProcess.std;

import java.util.ArrayList;
import java.util.List;


import kariminf.as.preProcess.lang.Segmenter;


public abstract class LatinSegmenter implements Segmenter {

	protected static final String sentPattern = "([!\\.\\?]+)[\\s$]";
	
	//protected static final RegexPattern wordPatern =
			//new RegexPattern("([!\\.\\?]+)[\\s$]", "$1#&#");
	
	public List<String> splitToSentences(String text) {
		
		List<String> sentences = new ArrayList<String>();
		text = text.replaceAll(sentPattern, "$1#&#");

		for(String sentence:  text.split("#&#")) 
		      if(sentence.trim().length() > 0) 
		    	  sentences.add(sentence.trim());
		

		return sentences;
	}
	
	public List<String> segmentWords(String text) {
	    List<String> ret = new ArrayList<String>();
	    for(String word:  text.split("[\\.,;:\"\\?\\!]?\\s+|[\\.\\?\\!]$|-|\'")) {
	      if(word.length() > 0) {
	        ret.add(word.toLowerCase().trim());
	      }
	    }
	    return ret;
	}
	
	

}
