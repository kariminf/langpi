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

package kariminf.langpi.basic.thai;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools14.lang.thai.EndOfSentenceScanner;
import opennlp.tools14.lang.thai.SentenceContextGenerator;
import opennlp.tools14.sentdetect.SentenceDetectorME;
import opennlp.tools14.tokenize.TokenizerME;
import kariminf.langpi.basic.Segmenter;
import opennlp.maxent25.GISModel;
import opennlp.maxent25.io.SuffixSensitiveGISModelReader;


public class ThSegmenter implements Segmenter {
	
	//TODO find a way to transform data from version 1.4 to version 1.5

	private final String sentBin = "/ressources/sentenceDetection/thai.sent.bin";
	private final String wordBin = "/ressources/wordTokenization/thai.tok.bin";
	private final String punctuation="\"'()[]{}!:;,?&.";
	
	public List<String> splitToSentences(String text) {
		
		List<String> sentences = new ArrayList<String>();
		//List<String> returnedSentences = new ArrayList<String>();
		
		try {
			InputStream input = ThSegmenter.class.getResourceAsStream(sentBin);
			//File file = new File(ThSegmenter.class.getResource(sentBin).getPath());
			GISModel modelIn = new SuffixSensitiveGISModelReader(input).getModel();
			SentenceDetectorME sentenceDetector = 
					new SentenceDetectorME(modelIn, new SentenceContextGenerator(), new EndOfSentenceScanner());
			String[] initSentences = sentenceDetector.sentDetect(text);
			for(String snt : initSentences){
				sentences.add(snt);
			}
			//modelIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return sentences;
	}
	
	public List<String> segmentWords(String text) {
	    List<String> wordsList = new ArrayList<String>();
	    
	    try {
			InputStream input = ThSegmenter.class.getResourceAsStream(wordBin);
			//File file = new File(ThSegmenter.class.getResource(wordBin).getPath());
			GISModel modelIn = new SuffixSensitiveGISModelReader(input).getModel();
			TokenizerME tokenizer = new TokenizerME(modelIn);
			String[] words = tokenizer.tokenize(text);
			for(String word : words)
				if (!punctuation.contains(word))
					wordsList.add(word);
			
			//modelIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
	    return wordsList;
	}
	
	public static void main(String[] args) {
		
		Segmenter segmenter = new ThSegmenter();
		List<String> sent = segmenter.splitToSentences("ตอนพิเศษฮัลโลวีนถือเป็นประเพณีทุก ๆ ปีของ เดอะซิมป์สันส์ ในปี ค.ศ. 1990 ได้ออกอากาศครั้งแรกกับตอนที่ชื่อว่า \"Treehouse of Horror\" ในฤดูกาลที่ 2 โดยสร้างเป็น 3 ส่วน โดยแต่ละเรื่องจะเกี่ยวกับฮัลโลวีน ซึ่งแต่ละส่วนมักจะเกี่ยวข้องกับครอบครัวเดอะซิมป์สันส์ในเรื่องเขย่าขวัญ นวนิยายวิทยาศาสตร์ และสิ่งเร้นลับ และมักจะล้อเลียนหรือคารวะต่อผลงานชิ้นโด่งดังในอดีต และมักเกิดขึ้นนอกเหนือจากตอนปกติทั่วไป ถึงแม้ว่าซีรีส์ Treehouse จะเห็นได้ในช่วงวันฮัลโลวีน แต่ปีล่าสุดได้ฉายรอบปฐมทัศน์หลังวันฮัลโลวีนเนื่องจากทางฟ็อกซ์ติดสัญญากับเวิร์ลซีรีส์ของเบสบอลเมเจอร์ลีก ");
		
		for (String s: sent)
			System.out.println(s);
		
		System.out.println("------------------");
		
		List<String> words = segmenter.segmentWords("ตอนพิเศษฮัลโลวีนถือเป็นประเพณีทุก ๆ ปีของ เดอะซิมป์สันส์ ในปี ค.ศ. 1990 ได้ออกอากาศครั้งแรกกับตอนที่ชื่อว่า \"Treehouse of Horror\" ในฤดูกาลที่ 2 โดยสร้างเป็น 3 ส่วน โดยแต่ละเรื่องจะเกี่ยวกับฮัลโลวีน");
		for (String s: words)
			System.out.println(s);
	}
	
	

}
