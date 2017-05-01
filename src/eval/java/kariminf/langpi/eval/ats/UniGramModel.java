package kariminf.langpi.eval.ats;

import java.util.List;

public class UniGramModel extends GramModel {

	@Override
	public void addSentence(List<String> sentence) {
		int grams = 0;

		for (int i=0; i < sentence.size(); i++){
			grams++;
			String unigram = sentence.get(i);
			int freq = tf.containsKey(unigram)?
					tf.get(unigram) + 1:1;

			tf.put(unigram, freq);
		}

		gramsAmount += grams;
		
	}

}
