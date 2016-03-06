package kariminf.as.preProcess.arabic;

import java.util.ArrayList;
import java.util.List;

import dz.aak.as.preProcess.lang.Segmenter;

public class ArSegmenter implements Segmenter{

	@Override
	public List<String> splitToSentences(String text) {
		List<String> ret = new ArrayList<String>();
		text = text.replaceAll("([!\\.\\?؟]+)[\\s$]", "$1#&#");
	    for(String sentence:  text.split("#&#")) 
	      if(sentence.trim().length() > 0) 
	        ret.add(sentence.trim());
	 
	    return ret;
	}

	@Override
	public List<String> segmentWords(String text) {
		List<String> ret = new ArrayList<String>();
	    for(String word:  text.split("[\\.،؛:\"\'؟\\!]?\\s+|\\.$")){
	    	//word = word.replace(" ", "");
	    	if(word.length() > 0)
	    		ret.add(word.trim());//.toLowerCase().trim()
	    	
	    }
	      
	 
	    return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String in = "أنا ذاهب إلى السوق. هل تريد أن أحضر لك شيء ما؟ هكذا إذن! نلتقي بعد أن أعود.";
		ArSegmenter seg = new ArSegmenter();
		//System.out.println(seg.segmentWords(in).toString());
		System.out.println(seg.splitToSentences(in).toString());
	}

}
