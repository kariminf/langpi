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

package aak.as.preProcess.chinese;

import java.util.ArrayList;
import java.util.List;


import aak.as.preProcess.lang.Segmenter;

import org.apache.lucene.analysis.cn.smart.hhmm.*;

public class ZhSegmenter implements Segmenter {

	private static String punctuation =",.`-_='|\"(){}[]<>*#&^$@~:;+/\\《》—－，。、：；！·？?!“”）（【】［］●";
	public List<String> splitToSentences(String text) {
		
		List<String> sentences = new ArrayList<String>();
		for(String sentence:  text.split("。|\\.|\\?|\\!|！|？")) 
		      if(sentence.trim().length() > 0) 
		    	  sentences.add(sentence.trim());
		

		return sentences;
	}
	
	public List<String> segmentWords(String text) {
		
		List<String> ret = new ArrayList<String>();
		
		HHMMSegmenter segmenter = new HHMMSegmenter();
		List<SegToken> ctokens = segmenter.process(text);
		
		for(SegToken ctoken: ctokens){
			if (ctoken.startOffset < 0 || ctoken.endOffset > text.length())
				continue;
			String word = text.substring(ctoken.startOffset, ctoken.endOffset);
			
			if (punctuation.contains(word)) continue;
			//System.out.println("<"+ctoken.startOffset+","+ ctoken.endOffset+">");
			ret.add(word);
		}
	    
	    return ret;
	}
	
	public static void main(String[] args) {
		
		ZhSegmenter segmenter = new ZhSegmenter();
		/*String text="";

		text += "今天爬山，明天露营。"; //今天+爬+山+明天+露+营。Today hike up mountains, tomorrow camp outdoors.
		text += "我到公园走走。";//我+到+公+园+走+走。I'm going for a walk in the park.
		text += "我给了她六本书。"; //我+给+了+她+六+本书。I have given her six books.
		text += "你能推荐一个好的电视吗。";// 你+能+推荐+一个+好的+电视+吗。You can recommend a good television ?
		List<String> sent = segmenter.splitToSentences(text);
		
		for (String s: sent){
			System.out.println(s);
			List<String> words = segmenter.segmentWords(s);
			for (String w: words)
				System.out.print(w + "+");
			System.out.println("\n---------");
		}*/
			
		
		List<String> words = segmenter.segmentWords("如果您在新加坡只能前往一间夜间娱乐场所，Zouk必然是您的不二之选。");
		for (String w: words)
			System.out.print(w + "+");
		//System.out.println("如果+您+在+新加坡+只+能+前往+一+间+夜间娱+乐场所，Zouk必然是您的不二之选。");

	}
	
	

}
