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

package kariminf.langpi.basic.dutch;

import java.util.List;

import kariminf.langpi.basic.std.OpennlpSegmenter;




public class NlSegmenter extends OpennlpSegmenter {

	public NlSegmenter() {
		super("nl-sent.bin", "nl-token.bin");
	}

	public static void main(String[] args) {

		NlSegmenter segmenter = new NlSegmenter();
		List<String> sent = segmenter.segmentWords("Junghuhns vader, een ongediplomeerd mijnarts, wilde dat zijn zoon arts werd en stelde een predikant aan als privéleraar. De jonge Junghuhn had zo'n hekel aan zijn leraar dat hij zijn hele leven een afkeer van de clerus zou blijven houden. Hij begaf zich al in 1825 naar de universiteit van Halle om medicijnen te gaan studeren. Omdat hij nog veel te jong gevonden werd, lukte het pas in 1827 zich in te laten schrijven. Onder invloed van zijn vriend Hermann Burmeister wijdde hij zijn tijd echter aan de bestudering van de natuur. In 1830 wist hij een studie van lokale soorten paddenstoelen gepubliceerd te krijgen in de Linnaea. Dit zorgde voor een breuk met zijn vader, die zijn financiële steun staakte. Door zijn persoonlijke bezittingen te verkopen en allerlei kleine baantjes probeerde Junghuhn het hoofd boven water te houden. Pas na een zelfmoordpoging in de lente van 1830 verzoende zijn vader zich weer met hem.");

		for (String s: sent)
			System.out.println(s);
	}



}
