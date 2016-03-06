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

package kariminf.as.preProcess.catalan;

import java.util.List;

import kariminf.as.preProcess.std.LatinSegmenter;



import dz.aak.as.preProcess.lang.Segmenter;


public class CaSegmenter extends LatinSegmenter {
	
	public static void main(String[] args) {
		
		Segmenter segmenter = new CaSegmenter();
		List<String> sent = segmenter.segmentWords("Jordan va rebre una beca per jugar a bàsquet a la Universitat de Carolina del Nord, on s'especialitzà en geografia.");
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
