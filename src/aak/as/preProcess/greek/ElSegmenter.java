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

package aak.as.preProcess.greek;

import java.util.List;

import aak.as.preProcess.std.LatinSegmenter;



public class ElSegmenter extends LatinSegmenter {
	
	public static void main(String[] args) {
		
		ElSegmenter segmenter = new ElSegmenter();
		String text="";
		
		text += "εἰ δὲ καὶ τῷ ἡγεμόνι 60.4 πιστεύσομεν ὃν ἂν Κῦρος διδῷ, τί κωλύει καὶ τὰ ἄκρα ἡμῖν κελεύειν Κῦρον προκαταλαβεῖν;";
		text += " ἐγὼ γὰρ ὀκνοίην μὲν ἂν εἰς τὰ πλοῖα ἐμβαίνειν ἃ ἡμῖν δοίη, μὴ ἡμᾶς ταῖς τριήρεσι καταδύσῃ, φοβοίμην δ᾽ ἂν τῷ ἡγεμόνι ὃν δοίη ἕπεσθαι, μὴ ἡμᾶς ἀγάγῃ ὅθεν οὐκ ἔσται ἐξελθεῖν· βουλοίμην δ᾽ ἂν ἄκοντος ἀπιὼν Κύρου λαθεῖν αὐτὸν ἀπελθών· ὃ οὐ δυνατόν ἐστιν.";
		text += " ἀλλ᾽ ἐγώ φημι ταῦτα μὲν φλυαρίας εἶναι· δοκεῖ δέ μοι ἄνδρας ἐλθόντας πρὸς Κῦρον οἵτινες ἐπιτήδειοι σὺν Κλεάρχῳ ἐρωτᾶν ἐκεῖνον τί βούλεται ἡμῖν χρῆσθαι· καὶ ἐὰν μὲν ἡ πρᾶξις ᾖ παραπλησία οἵᾳπερ καὶ πρόσθεν ἐχρῆτο τοῖς ξένοις, ἕπεσθαι καὶ ἡμᾶς καὶ μὴ κακίους εἶναι τῶν πρόσθεν τούτῳ συναναβάντων· ἐὰν δὲ μείζων ἡ πρᾶξις τῆς πρόσθεν φαίνηται καὶ ἐπιπονωτέρα καὶ ἐπικινδυνοτέρα, ἀξιοῦν ἢ πείσαντα ἡμᾶς ἄγειν ἢ πεισθέντα πρὸς φιλίαν ἀφιέναι· οὕτω γὰρ καὶ ἑπόμενοι ἂν φίλοι αὐτῷ καὶ πρόθυμοι ἑποίμεθα καὶ ἀπιόντες ἀσφαλῶς ἂν ἀπίοιμεν· ὅ τι δ᾽ ἂν πρὸς ταῦτα λέγῃ ἀπαγγεῖλαι δεῦρο· ἡμᾶς δ᾽ ἀκούσαντας πρὸς ταῦτα βουλεύεσθαι.";
		List<String> sent = segmenter.splitToSentences(text);
		
		for (String s: sent)
			System.out.println(s);

	}
	
	

}
