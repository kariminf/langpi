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

package aak.as.preProcess.hindi;

import java.util.List;

import aak.as.preProcess.std.LatinSegmenter;



public class HiSegmenter extends LatinSegmenter {

	public static void main(String[] args) {
		
		HiSegmenter segmenter = new HiSegmenter();
		String text="";
		
		text += "अमेरिका निवासी जो सुनामी पीडितो के लिए 2005 में दान कर चुके हैं उनको 2004 की आय मुक्ति मिल रही हैं क्यूंकि एक बिल अमेरिकी प्रतिनिधि सभा और अमेरिकी सीनेट में पास हुई हैं गेओर्गे बुश के आमोद से.";
		text += " नए कानून के बिना, योगदानकर्ताओं को 2006 तक इंतजार करना होगा अपने 2005 कर रिटर्न के लिए ताकि उनको आय मुक्ति मिले. ये विधि सुनामी राहत कार्यों की ओर दान को बढ़ावा देने के इरादे से बनायीं गयी हैं.";
		text += " सीबीएस समाचार के अनुसार  इंडियाना विश्वविद्यालय के परोपकार केंद्र ने 322 मिलियन अमरीकी डॉलर के दान और 350 मिलियन अमरीकी डॉलर के दान (अमरीकी सरकार से) की सूचना की हैं.";
		List<String> sent = segmenter.splitToSentences(text);
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
