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

package dz.aak.as.preProcess.finnish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dz.aak.as.preProcess.lang.SWEliminator;



public class FiSWEliminator implements SWEliminator {

	private final String  pathToStopWords = "/ressources/stopWords/finnish.stop";
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
	
	private List<String> getStopList(String path) {
		
		List<String> stopwords = new ArrayList<String>();
	    try {

	    	InputStream in = getClass().getResourceAsStream(path);
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
		tstList.add("Yhdysvaltalaisen");
		tstList.add("aikakauslehden");
		tstList.add("Timen");
		tstList.add("toukokuussa");
		tstList.add("1936");
		tstList.add("julkaisemassa");
		tstList.add("artikkelissa");
		tstList.add("Catechism");
		tstList.add("for");
		tstList.add("Hitler");
		tstList.add("pohdittiin");
		
		FiSWEliminator eliminator = new FiSWEliminator();
		eliminator.deleteSW(tstList);
		
		System.out.println(tstList.toString());
		

	}


}
