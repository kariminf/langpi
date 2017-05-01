package kariminf.langpi.eval.ats;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class GramModel {
	
	protected HashMap<String, Integer> tf = 
			new HashMap<String, Integer>();
	
	protected int gramsAmount = 0;
	
	public abstract void addSentence(List<String> sentence);
	
	public Set<String> getTerms(){
		return tf.keySet();
	}
	
	public int getFrequency(String term){
		if (! tf.containsKey(term)) return 0;
		return tf.get(term);
	}
	
	public int getGramsAmount(){
		return gramsAmount;
	}
	

}
