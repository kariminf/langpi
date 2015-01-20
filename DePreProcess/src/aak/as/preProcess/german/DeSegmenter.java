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

package aak.as.preProcess.german;

import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.Segmenter;


public class DeSegmenter implements Segmenter {

	public List<String> splitToSentences(String text) {
		
		List<String> sentences = new ArrayList<String>();
		for(String sentence:  text.split("\\.|\\?|\\!")) 
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
	
	public static void main(String[] args) {
		
		DeSegmenter segmenter = new DeSegmenter();
		List<String> sent = segmenter.segmentWords("Im Jahr 1908 und somit zum 60-jährigen Bestehen des Friedhofs fiel das Jubiläum in die Zeit des politischen Streits um das Wahlrecht in Deutschland.");
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
