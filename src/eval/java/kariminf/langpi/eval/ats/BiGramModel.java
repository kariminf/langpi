package kariminf.langpi.eval.ats;

import java.util.List;

public class BiGramModel extends GramModel {
	
	private String past = "";
	private int start = 1;
	private int number = 1;

	@Override
	public void addSentence(List<String> sentence) {
		int grams = 0;
		
		if (start == 1)
			past = sentence.get(0);
		for (int i = start; i< sentence.size(); i++){
			grams++;
			String bigram = past + " " + sentence.get(i);
			past = sentence.get(i);
			int freq = tf.containsKey(bigram)?
					tf.get(bigram) + 1:1;

			tf.put(bigram, freq);
		}
		
		start = 0;

		gramsAmount += grams;
		
	}

}
