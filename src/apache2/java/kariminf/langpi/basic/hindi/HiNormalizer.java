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

package kariminf.langpi.basic.hindi;

import java.util.HashMap;


import kariminf.langpi.basic.Normalizer;

public class HiNormalizer implements Normalizer {

	/*
	 * This function is used to delete new lines
	 */
	public static String deleteNewLine(String text){
		text = text.replaceAll("[\\r\\n]", " ");
		text = text.replaceAll(" +", " ");
		
		return text;
	}
	
	/*
	 * This function is used to lowercase
	 */
	public static String lowerCase(String text){
		//StringReader reader = new StringReader(text);
		//GreekLowerCaseFilter lower = new GreekLowerCaseFilter();
		
		return text;
	}
	
	
	@Override
	public String normalize(String text) {
		// TODO Auto-generated method stub
		return deleteNewLine(text);
	}
	@Override
	public String normalize(String text, String param) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, String> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
