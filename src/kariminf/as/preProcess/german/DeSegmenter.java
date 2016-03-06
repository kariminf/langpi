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

package kariminf.as.preProcess.german;

import java.util.List;

import kariminf.as.preProcess.std.OpennlpSegmenter;




public class DeSegmenter extends OpennlpSegmenter {
	
	public DeSegmenter() {
		super("de-sent.bin", "de-token.bin");
	}
	
	public static void main(String[] args) {
		
		DeSegmenter segmenter = new DeSegmenter();
		List<String> sent = segmenter.splitToSentences("Im Jahr 1908 und somit zum 60-jährigen Bestehen des Friedhofs fiel das Jubiläum in die Zeit des politischen Streits um das Wahlrecht in Deutschland.");
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
