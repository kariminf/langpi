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

package aak.as.preProcess.hebrew;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.code972.hebmorph.Reference;
import com.code972.hebmorph.Tokenizer;
import com.code972.hebmorph.hspell.HSpellLoader;

import aak.as.preProcess.std.LatinSegmenter;



public class HeSegmenter extends LatinSegmenter {
	
	@Override
	public List<String> segmentWords(String text) {
		
	    List<String> ret = new ArrayList<String>();

	    if (text == null || text =="") return ret;
	    
	    Reader r = new StringReader(text);
        final Tokenizer tokenizer = new Tokenizer(r, HSpellLoader.readDefaultPrefixes());
        
        Reference<String> hebref = new Reference<String>("");

        try {
			while (tokenizer.nextToken(hebref) > 0)
				ret.add(hebref.ref);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    return ret;
	}
	
	public static void main(String[] args) {
		
		HeSegmenter segmenter = new HeSegmenter();
		String text="";
		
		text += "המזרנים המיוחדים יהיו זמינים בחנויות לאחר החגים, לניסיון ורכישה.";
		text += " הרשת מאמינה שזה יהיה אחד המוצרים הטובים שלה אי פעם.";
		text += " מרטין בוקסדורף וצוות משרד יחסי הציבור ילוו את ההשקה כולה ויבטיחו את הצלחתה בפועל.";
		List<String> sent = segmenter.splitToSentences(text);
		
		for (String s: sent){
			System.out.println(s);
			System.out.println(segmenter.segmentWords(s));
			System.out.println("------------------");
		}
	}
}
