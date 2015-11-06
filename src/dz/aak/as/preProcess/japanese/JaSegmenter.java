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

package dz.aak.as.preProcess.japanese;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.Segmenter;

import org.apache.lucene.analysis.ja.JapaneseTokenizer;
import org.apache.lucene.analysis.ja.tokenattributes.BaseFormAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import dz.aak.as.preProcess.std.CJSegmenter;

public class JaSegmenter extends CJSegmenter {
	
	@Override
	public List<String> segmentWords(String text) {
		
		List<String> ret = new ArrayList<String>();
		
		StringReader textreader = new StringReader(text);
		JapaneseTokenizer segmenter = 
				new JapaneseTokenizer(textreader, null, true, JapaneseTokenizer.Mode.SEARCH);
		
		JaStemmer.lemma.clear();
		CharTermAttribute termAtt = segmenter.getAttribute(CharTermAttribute.class);
		BaseFormAttribute baseAtt = segmenter.getAttribute(BaseFormAttribute.class);
		try {
			segmenter.reset();
			while (segmenter.incrementToken()){
				//segmenter.clearAttributes();
				ret.add(termAtt.toString());
				if(baseAtt.getBaseForm()!=null)
					JaStemmer.lemma.put(termAtt.toString(), baseAtt.getBaseForm());
			}
				
			segmenter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block.
			e.printStackTrace();
		}

	    return ret;
	}
	
	public static void main(String[] args) {
		
		JaSegmenter segmenter = new JaSegmenter();
		
		List<String> words = 
				segmenter.splitToSentences("多くの学生が試験に落ちた。多くの学生が試験に落ちた。");
		/*for (String w: words)
			System.out.print(w + "+");*/
		System.out.println(words);

	}
	
	

}
