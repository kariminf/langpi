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

package kariminf.as.preProcess.swedish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dz.aak.as.preProcess.lang.SWEliminator;



public class SvSWEliminator implements SWEliminator {

	private final String  pathToStopWords = "/ressources/stopWords/swedish.stop";
	private List<String> stopList = 
			getStopList(pathToStopWords);//stopwords.txt

	
	public Boolean isNotStopWord(String word) {
			
		word = word.toLowerCase().trim();
		if (!stopList.contains(word)) //
			return true;

		return false;
	}
	
	private Boolean isStopWord(String word) {
	    word = word.toLowerCase().trim();
	    if (stopList.contains(word)) //a non-word character
			return true;
		
		return false;
	}
	
	public void deleteSW(List<String> wordsList){
		for(int i=wordsList.size()-1; i >=0; i--)
			if (isStopWord(wordsList.get(i)))
				wordsList.remove(i);
	}
	
	private static List<String> getStopList(String path) {
		
		List<String> stopwords = new ArrayList<String>();
	    try {

	    	InputStream in = SvSWEliminator.class.getResourceAsStream(path);
	    	BufferedReader input = new BufferedReader(
		                new InputStreamReader(in));
	      for(String line = input.readLine(); line != null; line = input.readLine()) {
	        stopwords.add(line);
	      }
	      input.close();

	      return stopwords;
	      
	    } catch(IOException e) {
	      e.printStackTrace();
	      System.exit(1);
	      return null;
	    } 
	  }
	
	
	public static void main(String[] args) {
		List<String> tstList = new ArrayList<String>();
		tstList.add("som");
		tstList.add("d");
		tstList.add("holbach");
		tstList.add("kallas");
		tstList.add("de");
		tstList.add("inkompatibilister");
		tstList.add("som");
		tstList.add("accepterar");
		tstList.add("determinism");
		tstList.add("och");
		tstList.add("förkastar");
		tstList.add("fri");
		tstList.add("vilja");
		
		SWEliminator eliminator = new SvSWEliminator();
		eliminator.deleteSW(tstList);
		
		System.out.println(tstList.toString());
		

	}


}
